import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,

  // async beforeCreate() {
  //   await this.$store.dispatch("getUserInfo")
  //   this.$mount('#app')
  //   if(localStorage.getItem("access-token") === null) {
  //     this.$router.push("/").catch(()=>{})
  //   }
  //   // console.log("beforeCreate 실행 : ");
  // },
  render: h => h(App)
}).$mount('#app')
