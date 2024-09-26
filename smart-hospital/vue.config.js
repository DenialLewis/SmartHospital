const { defineConfig } = require('@vue/cli-service')
  module.exports = {
    // Options that Vue CLI expects
    publicPath: '/',
    devServer: {
      proxy: 'http://localhost:8080',
    },
    lintOnSave: false, // Example of turning off linting on save
  };
