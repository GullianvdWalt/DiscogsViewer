// vue.config.js
module.exports = {
    // https://cli.vuejs.org/config/#devserver-proxy
    devServer: {
        port: 3000, // Prevents clashing with Spring boot server
        proxy: {
            // Set proxy, any request starts with /api and forward to target
            // No need to setup environmental variables for production or development
            '/api': {
                target: 'http://localhost:8080',
                ws: true,
                changeOrigin: true
            }
        }
    }
}