import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login'
import Main from '../components/Main'
import Insert from '../components/Insert'
import Sign from '../views/Sign'
import Signup from '../views/Signup'
import Mypage from '../components/MyPage'
import SideBar from '../views/SideBar'
import Follow from '../views/Follow'
import Follower from '../views/Follower'

// import store from "../store"
Vue.use(VueRouter)

const rejectAuthUser = (to, from, next) => {
  if (localStorage.getItem("access-token") != null) {
    next("/main/")
  }
  else {
    next()
  }
}
// const onlyAuthUser = (to, from, next) => {
//   if (store.state.isLogin === false) {
//     next("/")
//   }
//   else {
//     next()
//   }
// }
const routes = [
  
  {
    path: '/',
    name: 'Login',
    beforeEnter: rejectAuthUser,
    component : Login,
    redirect: {
      name: 'Sign'
    },
    children: [
      {
        path: '',
        name: 'Sign',
        // beforeEnter: rejectAuthUser,
        component: Sign
      },
      {
        path: '/signup',
        name: 'Signup',
        // beforeEnter: rejectAuthUser,
        component: Signup
      }
    ]
  },
  {
    path: '/main',
    name: 'Main',
    // beforeEnter: onlyAuthUser,
    component: Main,
    children: [
      {
        path: '',
        component: SideBar,
        children: [
          {
            path: '/',
            name: 'Follow',
            component: Follow
          },
          {
            path: '/follower',
            name: 'Follower',
            component: Follower
          }
        ]
      }
    ],
  },
  {
    path: '/mypage',
    name: 'MyPage',
    component: Mypage,
    // beforeEnter: onlyAuthUser,
  },
  {
    path: '/insert',
    name: 'Insert',
    component: Insert,
    // beforeEnter: onlyAuthUser,
    // meta: {
    //   requireAuth: true
    // },
    // // 그렇지 않으면 / 경로로 리다이렉트
  },


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
