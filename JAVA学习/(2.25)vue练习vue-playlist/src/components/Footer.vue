<template>
  <footer calss="app-footer">
    <p>{{footer}}</p>
  </footer>
</template>
<script>
  export default {
    name: "app-footer",
    data(){
      return {
        footer:"welcom! "
      }
    }
  }
</script>
<style scoped>
  footer{
    background-color: lightblue;
    padding:14px;
  }
  p{
    color: grey;
    test-align: center;
  }
</style>
