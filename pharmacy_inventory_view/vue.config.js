const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,
  devServer: {
    client: {
      overlay: false,
        },
    port: 8080,
    proxy: {
      '/base': {
        target: 'http://localhost:8081',
        changeOrigin: true,
        ws: false //是否支持websocket
      },
      '/warehouse': {
        target: 'http://localhost:8085',
        changeOrigin: true,
        ws: false //是否支持websocket
      },
      '/finance': {
        target: 'http://localhost:8089',
        changeOrigin: true,
        ws: false //是否支持websocket
      },
      '/procurement': {
        target: 'http://localhost:8084',
        changeOrigin: true,
        ws: false //是否支持websocket
      },
      '/sale': {
        target: 'http://localhost:8086',
        changeOrigin: true,
        ws: false //是否支持websocket
      },
      '/user': {
        target: 'http://localhost:8088',
        changeOrigin: true,
        ws: false //是否支持websocket
      }
    }
  }
})
