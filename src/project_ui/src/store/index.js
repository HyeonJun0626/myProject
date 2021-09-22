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
    modalSeq: '',
    modalBoardSeq: '',
    followCheck: '',
    myBoardList: ''
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
    },
    profileModla(state, payload) {
      state.profileModalOpen = payload.isModal
    },
    isMyBoardList(state, payload) {
      state.myBoardList = payload
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
            if (state.isLogin === true) {
              router.push({path:'/main/'})
            }
          }
          else {
            console.log(res.data)
            commit("loginError", res.data)
          }
      })
      .catch(function() {
          console.log('로그인 실패')
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
    }
  }
})
