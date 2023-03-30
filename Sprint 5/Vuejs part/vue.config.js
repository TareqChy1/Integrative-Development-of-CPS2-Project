module.exports = {
    devServer: {
      proxy: {
        "^/data": {
          target: "http://192.168.67.225",
          changeOrigin: true,
        },
      },
    },
  };