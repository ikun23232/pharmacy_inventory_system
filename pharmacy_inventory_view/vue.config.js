const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,
  devServer:{
    webSocketServer:false,
    proxy: {
      '/user': {
        target: 'http://localhost:8088',
        changeOrigin: true,
        ws: false //是否支持websocket
      },
      '/base': {
        target: 'http://localhost:8081',
        changeOrigin: true,
        ws: false //是否支持websocket
      },
      '/warehouse': {
        target: 'http://localhost:8085',
        changeOrigin: true,
        ws: false //是否支持websocket
      }
    }
  }
})
