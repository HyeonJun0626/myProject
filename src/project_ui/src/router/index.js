import Vue from 'vue'
import VueRouter from 'vue-router'
// import Sign from '../views/Sign.vue'
// import Signup from '../views/Signup.vue'
import Login from '../components/Login'
import Main from '../components/Main'
import Insert from '../components/Insert'
import Sign from '../views/Sign'
import Signup from '../views/Signup'
import store from "../store"
Vue.use(VueRouter)

const rejectAuthUser = (to, from, next) => {
  if (store.state.isLogin === true) {
    alert('이미 로그인했습니다.')
    next("/Main")
  }
  else {
    next()
  }
}
const onlyAuthUser = (to, from, next) => {
  if (store.state.isLogin === false) {
    alert('로그인이 필요합니다.')
    next("/")
  }
  else {
    next()
  }
}
const routes = [
  
  {
    path: '/',
    name: 'Login',
    beforeEnter: rejectAuthUser,
    component : Login,
    children: [
      {
        path: '/',
        name: 'Sign',
        component: Sign
      },
      {
        path: '/signup',
        name: 'Signup',
        component: Signup
      }
    ]
  },
  {
    path: '/main',
    name: 'Main',
    beforeEnter: onlyAuthUser,
    component: Main,
    // meta: {
    //   requireAuth: true // requireAuth: true 인증이 필요하다고 표시함
    // }
  },
  {
    path: '/insert',
    name: 'Insert',
    component: Insert,
    // meta: {
    //   requireAuth: true
    // },
    // // 그렇지 않으면 / 경로로 리다이렉트
  },
  // { path: '*', redirect: '/' }
]

const router = new VueRouter({
  mode: 'history',
  routes
});

// Vue router에서 제공하는 네비게이션 가드
// 경로 접근을 취소하거나 리디렉션 시킬수 있다.
// $router.matched: 라우트와 일치하는 모든 라우트 레코드는 mathed 배열로 노출된다
// router.beforeEach((to, from, next) => {
//   if (to.matched.some(record => record.meta.requiresAuth)) {
//       if (!this.store.getters.isLoggedIn) {
//       next({
//           name: 'Main'
//       })
//       } else {
//       next();
//       }
//   } else {
//       next();
//   }
// });

export default router
