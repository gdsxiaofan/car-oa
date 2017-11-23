<template>
  <div>
    <p style="font-size:20px;text-align: center;color:rgb(192, 204, 218);">404</p>
    <input v-model="data">
    <Button type="primary" shape="circle" icon="ios-search" @click="sendMsg" long>查询</Button>
  </div>
</template>
<script>
  import SockJS from "sockjs-client";
  import Stomp from "webstomp-client";

  export default {
    data() {
      return {
        data: "",
        stompClient: null
      }
    },
    methods: {
      sendMsg() {
        this.stompClient.send("/welcome", JSON.stringify({'name': this.data}), {});
      }
    },
    mounted() {
      let socket = new SockJS('/car/car/endpoint');
      this.stompClient = Stomp.over(socket);
      this.stompClient.connect({}, frame => {
        console.log('Connected: ' + frame);
        this.stompClient.subscribe('/user/1/getResponse', function (val) {
          console.log("订阅" + val);
//          console.log(JSON.parse(val.body));
//          vm.list1 = JSON.parse(val.body);
        });
      });


    }
  }
</script>
