import Vue from 'vue'
import Vuex from 'vuex'
import router from '../router'
import axios from "axios"

Vue.use(Vuex)
export default new Vuex.Store({
  // 상태 저장소 data() 영역과 비슷함
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    ErrorInfo: null,

    modalOpen: false,
    profileModalOpen: false,
    replyModal: false,
    replyIdx: '',
    modalSeq: null,
    modalBoardSeq: '',
    followCheck: '',
    myBoardList: '',
    allBoardList: [],
    followList: [],
    followerList: [],
  },
  // state의 상태를 변화시키는 부분 actions에서 실행 시켜서 commit으로 적용 시킴
  mutations: {
    loginSuccess(state) {
      state.isLogin = true
      state.isLoginError = false
      // state.userInfo = payload
      state.ErrorInfo = null
    },
    loginError(state, payload) {
      state.isLogin = false
      state.isLoginError = true
      state.ErrorInfo = payload
    },
    isLogout(state) {
      state.isLogin = false
      state.isLoginError = false
      state.userInfo = null
    },
    isModal(state, payload) {
      state.modalOpen = payload.isModal
      state.modalSeq = payload.modalSeq
      state.followCheck = payload.followCheck
      state.modalBoardSeq = payload.boardSeq
      state.replyModal = payload.replyModal
      state.replyIdx = payload.idx
    },
    profileModla(state, payload) {
      state.profileModalOpen = payload.isModal
    },
    isMyBoardList(state, payload) {
      state.myBoardList = payload
    },
    isAllBoardList(state, payload) {
      state.allBoardList = payload
    },
    isFollowList(state, payload) {
      state.followList = payload
    },
    isFollowerList(state, payload) {
      state.followerList = payload
    },
    addFollow(state, payload) {
      state.followList.push(payload)
    },
    isDisFollow(state, payload) {
      state.followList.splice(payload, 1)
    },
    disFollower(state, payload) {
      state.followerList.splice(payload, 1)
    },
    disReply(state, payload) {
      state.allBoardList[payload.boardIdx].replyList.splice(payload.replyIdx, 1)
    },
    isAddReply(state, payload) {
      state.allBoardList[payload.idx].replyList.push(payload.reply)
    }
  },
  actions: {
    async login({commit, state}, loginObj) {
      if (loginObj.userId === null || loginObj.userPw === null || loginObj.userId === "" || loginObj.userPw === "") {
        state.ErrorInfo = "사용자 정보를 입력하세요"
      }
      else {
      await axios.post('http://localhost:9000/user/login', loginObj 
      )
      .then(function(res) {
          // commit("loginSuccess")
          localStorage.setItem("access-token", res.headers.authorization)
          if (res.headers.authorization != null) {
            // dispatch("getUserInfo")
            commit("loginSuccess")
            // if (localStorage.authorization != null) {
            //   router.push({path:'/main/'})
            // }
            router.go()
          }
          else {
            console.log(res.data)
            commit("loginError", res.data)
          }
      })
      .catch(function(err) {
          console.log('로그인 실패')
          console.log(err)
          // commit("loginError")
      })
    }
    },
    async getUserInfo({commit, state}) {
      let token = localStorage.getItem("access-token")
      let config = {
        headers: {
          Authorization : token
        }
      }
      if(token != null)
      await axios.post("http://localhost:9000/user/getUserInfo", {}, config)
        .then(function (response) {
          console.log("유저 정보 요청 성공")
          let userInfo = {
            userSeq: response.data.userSeq,
            userId: response.data.userId,
            userNick: response.data.userNick,
            roles: response.data.roles,
            profileImgSeq: response.data.imgSeq,
            profileImg: "http://localhost:9000/"+response.data.storedImgPath,
            writeCnt: response.data.writeCnt
          }
          commit("loginSuccess")
          state.userInfo = userInfo
          if (response.data.storedImgPath == null) {
            state.userInfo.profileImg = "http://localhost:9000/images/default_img.jpeg"
          }
        })
        .catch(function (error) {
          console.log(error)
          // commit("loginError")
          localStorage.removeItem("access-token")
          router.push("/")
        })
    },
    logout({commit}) {
      commit("isLogout")
      localStorage.removeItem("access-token")
      console.log('로그아웃')
      router.push("/")
    },
    getMyBoardList({commit, state}) {
        axios.get("http://localhost:9000/board/getBoardList", {
          params: {
            userSeq: state.userInfo.userSeq,
          }
        })
        .then(function (res) {
          console.log("통신 성공")
          commit("isMyBoardList", res.data)
        })
        .catch(function (err) {
          console.log(err)
          console.log("통신 실패")
        })
    },
    getAllBoardList({commit, state}) {
      axios.post("http://localhost:9000/board/getAllBoardList", {}, {
        params: {
            userSeq: state.userInfo.userSeq
        }
    })
    .then(function (res) {
        console.log('통신 성공')
        commit("isAllBoardList", res.data)
    })
    .catch(function (err) {
        console.log(err)
    })
    },
    getFollowList({commit, state}) {
      axios.get("http://localhost:9000/user/getFollowList", {
            params: {
                userSeq: state.userInfo.userSeq
            }
        })
        .then(function (res) {
            commit("isFollowList", res.data)
            console.log(res.data)
            console.log('팔로우 목록 요청 성공')
        })
        .catch(function (err) {
            console.log(err)
            console.log('팔로우 목록 요청 실패')
        })
    },
    getFollowerList({state}) {
      axios.get("http://localhost:9000/user/getFollowerList", {
            params: {
                userSeq: state.userInfo.userSeq
            }
        })
        .then(function (res) {
            // commit("isFollowerList", res.data)
            state.followerList = res.data
            console.log('리스폰스 리스트 : '+res.data)
            console.log('팔로워 목록 요청 성공')
        })
        .catch(function (err) {
            console.log(err)
            console.log('팔로워 목록 요청 실패')
        })
    },
    addFollow({getters, state, commit}, modalSeq) {
      if (getters.follow == 0) {
          axios.post("http://localhost:9000/user/addFollow", {}, {
              params: {
                  fromUserSeq: state.userInfo.userSeq,
                  toUserSeq: modalSeq.seq,
              }
          })
          .then(function(res) {
              console.log('팔로우 요청 성공')
              commit('addFollow', res.data)
              if (modalSeq.idx != -1) {
                commit('disFollower', modalSeq.idx)
              }
          })
          .catch(function (err) {
              console.log(err)
              console.log('팔로우 요청 실패')
          })
      }
    },
    deleteReply({state, commit},modalBoardSeq) {
      axios.post("http://localhost:9000/board/deleteReply", {}, {
        params: {
          replySeq: modalBoardSeq
        }
      })
      .then(function () {
        console.log('댓글삭제 요청 성공')
        // let replydSeq = getters.replyBoardSeq;
        let boardIdx = -1
        let replyIdx = -1
        for (let i = 0; i < state.allBoardList.length; i ++) {
          for (let j = 0; j < state.allBoardList[i].replyList.length; j++) {
            if (state.allBoardList[i].replyList[j].replySeq === modalBoardSeq) {
              // console.log(state.allBoardList[i].replyList[j].replySeq)
              boardIdx = state.allBoardList.findIndex(board => board.replyList[j].replySeq === modalBoardSeq)
              replyIdx = state.allBoardList[i].replyList.findIndex(board => board.replySeq === modalBoardSeq)
            }
            
          }
        }
        if (boardIdx != -1 && replyIdx != -1) {
          commit('disReply', {boardIdx, replyIdx})
        }
      })
      .catch(function (err) {
        console.log(err)
        console.log('댓글삭제 요청 실패')
      })
    },
    clickModal({commit}, payload) {
      commit("isModal", payload)
    },
    clickProfileModal({commit}, payload) {
      commit("profileModla", payload)
    },
  },
  getters: {
    myUserInfo(state) {
      return state.userInfo
    },
    modal({state}) {
      return state.modalOpen
    },
    modalSeq(state) {
      return state.modalSeq
    },
    getterMyBoardList({state}) {
      return state.myBardList
    },
    getBoardSeq(state) {
      return state.modalBoardSeq
    },
    follow(state) {
      let cnt = 0
      state.followList.forEach(function(follow) {
          if (follow.userSeq == state.modalSeq) {
              cnt++
          }
      })
      return cnt;
    },
    replyBoardSeq(state) {
      let boardSeq = 0;
      for (let i = 0; i < state.allBoardList.length; i++) {
        for (let j = 0; j < state.allBoardList[i].replyList.length; j++) {
          if (state.allBoardList[i].replyList[j].replySeq == state.modalBoardSeq) {
            boardSeq = [i]
          }
        }
      }
      return boardSeq;
    },
  }
})
