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
  },
  // state의 상태를 변화시키는 부분 actions에서 실행 시켜서 commit으로 적용 시킴
  mutations: {
    loginSuccess(state, payload) {
      state.isLogin = true
      state.isLoginError = false
      state.userInfo = payload
    },
    loginError(state) {
      state.isLogin = false
      state.isLoginError = true
    },
    isLogout(state) {
      state.isLogin = false
      state.isLoginError = false
      state.userInfo = null
    }
  },

  actions: {
    login({dispatch, commit}, loginObj) {
      axios.post('http://localhost:9000/user/login', loginObj 
      )
      .then(function(res) {
          commit("loginSuccess")
          console.log(res)
          
          localStorage.setItem("access-token", res.headers.authorization)
          dispatch("getUserInfo")
      })
      .catch(function() {
          console.log('로그인 실패')
          commit("loginError")
      })
    },
    getUserInfo({commit}) {
      let token = localStorage.getItem("access-token")
      let config = {
        headers: {
          Authorization : token
        }
      }
      if(token != null)
      axios.post("http://localhost:9000/user/getUserInfo", {}, config)
        .then(function (response) {
          console.log("유저 정보 요청 성공")
          let userInfo = {
            userSeq: response.data.userSeq,
            userId: response.data.userId,
            userNick: response.data.userNick,
            roles: response.data.roles
          }
          commit("loginSuccess", userInfo)
          router.push({name:'Main'}).catch(()=>{})
        })
        .catch(function (error) {
          console.log(error)
          router.push("/").catch(()=>{})
        })
    },
    logout({commit}) {
      commit("isLogout")
      localStorage.removeItem("access-token")
      console.log('로그아웃')
      router.push("/")
    }
  },
  modules: {
    
  }
})
