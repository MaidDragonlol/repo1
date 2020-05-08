<template>
  <header class="app-header">
    <h1>{{title}}</h1>
  </header>
</template>
<script>
  export default {
    name: "app-header",
    data(){
      return{
        title:"日志处理"
      }}}
</script>
<style scoped>
header{
background-color: lightgreen;
padding:14px;
}
h1{
color: #222222;
test-align: center;
}
</style>
