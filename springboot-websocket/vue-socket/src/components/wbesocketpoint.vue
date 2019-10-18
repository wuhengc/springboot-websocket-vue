<template>
    <div>
        <div>{{msg}}</div>
        <p><router-link to="/ws">自动发消息，收消息(广播)</router-link></p>
	    <p><router-link to="/">请求接口收消息</router-link></p>
    </div>
</template>

<script>
import SockJS from  'sockjs-client';
import  Stomp from 'stompjs';
  export default {
    name: 'HelloWorld',
    data() {
      return {
        msg: "你好",  
        stompClient:'',
        timer:''
      }
    },
    methods: {
        initWebSocket() {
            this.connection()
            let that= this
            // 断开重连机制,尝试发送消息,捕获异常发生时重连
            this.timer = setInterval(() => {
                try {
                    that.stompClient.send("/app/points",
                    {},JSON.stringify({name: 'admin'}),)   //用户加入接口 
                } catch (err) {
                    console.log("断线了: " + err)
                    that.connection();
                }
            }, 5000);
        },
        // 连接服务器
        connection() {
            // 建立连接对象
            let socket = new SockJS('http://127.0.0.1:8080/endpointSang')
            // 获取STOMP子协议的客户端对象
            this.stompClient = Stomp.over(socket);
            // 定义客户端的认证信息,按需求配置
            let headers = {
                name:'admin'
            }
            let that = this
            // 向服务器发起websocket连接
            this.stompClient.connect(headers,() => {
                // 这里的路径需要和后台 发送人保持一致
                this.stompClient.subscribe('/user/admin/queue/points',  function(data) { //订阅消息
                    console.log(data, "******收到消息了********")
                    that.msg += data.body
                },headers);
            }, (err) => {
                // 连接发生错误时的处理函数
                console.log('失败')
                console.log(err)
            });
        },
        // 断开连接
        disconnect() {
            if (this.stompClient) {
                this.stompClient.disconnect();
            }
        }
    },
    // 初始化连接
    mounted(){
        this.initWebSocket()
    },
    beforeDestroy: function () {
        // 页面离开时断开连接,清除定时器
        this.disconnect()
        clearInterval(this.timer)
    }
  }

</script>

<style scoped>
  
</style>
