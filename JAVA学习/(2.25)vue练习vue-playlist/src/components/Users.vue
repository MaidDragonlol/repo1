<template>
  <div class="users">
     <h1>hello！</h1>
     <ul>
       <li v-for="(user,index) in users" @click="user.show = !user.show">
         <h2>{{user.name}}</h2>
         <h3 v-show="user.show">{{user.position}}</h3>
       </li>
     </ul>

  </div>
</template>
<script>
  export default {
    name: "Users",
    data(){
      return {
        users: [
          {name:"日志统计",position:"list1",show:false},
          {name:"日志2",position:"list2",show:false},
          {name:"日志3",position:"list3",show:false},
          {name:"日志4",position:"list4",show:false},
          {name:"日志5",position:"list5",show:false},
          {name:"日志6",position:"list6",show:false},]
      }
    }


  }
</script>
<style scoped>
  h1{
    color: orange;
  }
  .users{
    width: 100%;
    max-width: 1200px;
    margin: 20px auto;
    padding: 0 20px;
    box-sizing: border-box;
  }
  .users ul{
    display: flex;
    flex-wrap: wrap;
    list-style-type: none;
    padding: 0px;
  }
  .user ul li{
    flex-grow: 1;
    flex-basis: 200px;
    text-align: center;
    padding: 30px;
    border: 1px solid grey;
    margin: 10px;
  }
</style>
