基于springboot、websocket vue 前后端分离进行的广播订阅、以及点对点订阅的demo.

## 运行环境：
1. NodeJs   下载地址：[https://code.visualstudio.com](https://code.visualstudio.com)
2. IDEA     下载地址：[https://www.jetbrains.com/idea/download/#section=windows](https://www.jetbrains.com/idea/download/#section=windows)
3. VSCODE   下载地址：[http://nodejs.cn/download](http://nodejs.cn/download)

>注：先运行后台springboot后台，避免端口冲突（前端后根据已启动的端口会新选择一个端口，后台是8080，前端选择的是8081）


## 运行方式：
1. 使用命令`git clone https://github.com/wuhengc/springboot-websocket-vue.git`将项目克隆到本地。
2. 将项目导入IDEA。在IDEA里，File->open...，然后选择项目文件夹（springboot-websocket）。
  如果是初次使用spring boot，这个过程可能会有点久，需要下载许多依赖的jar包。然后启动项目，确保项目没有报错。
3. 将前端项目导入VSCODE。在VSCODE里，选择文件打开文件夹，选择文件夹（vue-socket）。然后新建终端，在下面的cmd窗口下载项目需要的依赖，npm install，等待所有依赖下载完成，
  如果下载失败，可以尝试cnpm install(国内源下载方式，也需要安装依赖，参照：[http://npm.taobao.org](http://npm.taobao.org/))。待下载完毕后启动前端项目，
  npm run dev 即可。
4.  
