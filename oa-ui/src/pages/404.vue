<template>
  <div>
    <InputNumber @on-blur="co"></InputNumber>
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
      }
    },
    stompClient:{
      monitorIntervalTime: 100,
      stompReconnect: true,
      timeout(orgCmd) {
      }
    },
    methods: {
      co(){
        console.log("Asdadad")
      },
      sendMsg() {
        let destination = '/welcome'
        let body=JSON.stringify({'name': this.data})
        let invokeId="send"
        this.sendWM(destination, body, invokeId, this.responseCallback, 3000);
      },
      connectSrv(){
//        var headers = {
//          "login": 'guest',
//          "passcode": 'guest',
//        };
        this.connetWM("/car/car/endpoint",{}, this.onConnected,this.onFailed);
      },
      onConnected(frame) {
        console.log('Connected: ' + frame);
        this.$stompClient.subscribe('/user/1/getResponse', function (val) {
          console.log("订阅" + val);
//          console.log(JSON.parse(val.body));
//          vm.list1 = JSON.parse(val.body);
        });
      },
      responseCallback(frame){
        console.log("responseCallback msg=>" + frame.body);
        let invokeId = frame.body.substr(invokeIdIndex, 4);
        this.removeStompMonitor(invokeId);
      },
      onFailed(frame){
        console.log('Failed: ' + frame);
      },
    },
    mounted() {
      this.connectSrv()
//      let socket = new SockJS('/car/car/endpoint');
//      this.stompClient = Stomp.over(socket);
//      this.stompClient.connect({}, frame => {
//        console.log('Connected: ' + frame);
//        this.stompClient.subscribe('/user/1/getResponse', function (val) {
//          console.log("订阅" + val);
////          console.log(JSON.parse(val.body));
////          vm.list1 = JSON.parse(val.body);
//        });
//      });
    }
  }
</script>
