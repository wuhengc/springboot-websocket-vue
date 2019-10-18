<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
	请求参数：<input type="text" v-model="params"/>
	<button @click="axiosGet">axios请求</button>
	<ul>
		<li v-for="(item,i) in listData">{{item}}</li>
	</ul>
	<p><router-link to="/ws">自动发消息，收消息（广播）</router-link></p>
	<p><router-link to="/wsp">点对点，收消息发消息</router-link></p>
  </div>
</template>

<script>
  import SockJS from 'sockjs-client'
  import Stomp from 'stompjs'
  import axios from 'axios'
  export default {
    name: 'HelloWorld',
    data() {
      return {
		msg: '广播',
		params: '你好',
		listData: ['whdoftinc'],
        stompClient: '',
		timer: ''
      }
    },
    methods: {
		init() {
			// 建立连接对象
			let socket = new SockJS('http://127.0.0.1:8080/endpointSang')
			// 获取STOMP子协议的客户端对象
			this.stompClient = Stomp.over(socket)
			// 向服务器发起websocket连接
			this.stompClient.connect({}, () => {
				this.stompClient.subscribe('/queue/msg', (msg) => { // 订阅服务端提供的某个queue
				console.log('******** 成功收到消息 *****', msg)
				console.log(msg.body) // msg.body存放的是服务端发送给我们的信息
				this.listData.push(msg.body)
				})
			}, (err) => {
				// 连接发生错误时的处理函数
				console.log('失败')
				console.log(err);
			})
		},
		axiosGet() {
			axios.get('http://localhost:8080/sentMes?message='+this.params)
			.then(function (response) {
				console.log(response);
			})
			.catch(function (error) {
				console.log(error);
			})
		}
    },
    // 初始化连接
    mounted() {
      this.init();
    },
  }

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1,
  h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }

</style>
