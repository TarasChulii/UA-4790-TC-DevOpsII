const path = require('path');
// Prior to use need to install 
// npm install --save-dev html-webpack-plugin
const HtmlWebpackPlugin = require("html-webpack-plugin");
// Prior to use need to install
// npm install copy-webpack-plugin --save-dev
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
