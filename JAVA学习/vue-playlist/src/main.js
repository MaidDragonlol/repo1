// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import Footer from './components/Footer'
import Header from './components/Header'
import Users from './components/Users'
Vue.config.productionTip = false
Vue.component('app-footer',Footer)
Vue.component('app-header',Header)
Vue.component('users',Users)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  components: { App },
  template: '<App/>'
})
