const path = require('path');
const HtmlWebpackPlugin = require("html-webpack-plugin");
const CopyWebpackPlugin = require("copy-webpack-plugin");

module.exports = {
  mode : 'development',
  entry : './src/index.js',
  output : {
    filename : 'app.js',
    path : path.resolve(__dirname, 'dist'),
  },
  plugins : [
    new HtmlWebpackPlugin({
      template : './src/index.html',
    }),
    new CopyWebpackPlugin({
      patterns: [
        { from: './src/style.css', to: 'css' },
      ],
    }),
  ],
};
