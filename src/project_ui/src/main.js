import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'

Vue.config.productionTip = false

new Vue({
  router,
  store,
  beforeCreate() {
    this.$store.dispatch("getUserInfo")
    if(localStorage.getItem("access-token") === null) {
      this.$router.push("/").catch(()=>{})
    }
  },
  render: h => h(App)
}).$mount('#app')
