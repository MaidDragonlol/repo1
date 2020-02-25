<template>
  <div id="app">
  <app-header></app-header>
    <users></users>
    <app-footer></app-footer>
<users></users>
  </div>
</template>

<script>
import Users from './components/Users'
import Footer from "./components/Footer";
import Header from "./components/Header";


export default {
  name: 'app',

  components: {
  "users":Users,
  "app-header":Header,
  "app-footer":Footer}
}
</script>

<style scoped>
h1{
  color: red;
}
</style>
