const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,
  devServer:{
    proxy: {
      '/base': {
        target: 'http://localhost:8081',
        changeOrigin: false,
        ws: false //是否支持websocket
      },
      '/menus': {
        target: 'http://localhost:8082',
        changeOrigin: true,
        ws: false //是否支持websocket
      },
      '/visitors': {
        target: 'http://localhost:8083',
        changeOrigin: true,
        ws: false //是否支持websocket
      }
    }
  }
})
