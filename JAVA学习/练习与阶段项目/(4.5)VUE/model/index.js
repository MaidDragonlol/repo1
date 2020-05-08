var mongoose = require('mongoose')
mongoose.connect('mongodb://localhost:27017/blog')
var db = mongoose.connection
// 监听数据库连接
db.on('open', (err) => {
  if (err) {
    console.log('数据库连接失败')
  }
  console.log('数据库连接成功')
})

// 定义数据结构
var newSchema = new mongoose.Schema({
  title: String,
  id: String,
  content: String
}, {
  versionKey: false
})

var userSchema = new mongoose.Schema({
  name: String,
  age: Number,
  password: String
}, {
  versionKey: false
})

// 定义数据模型
var newsList = mongoose.model('news', newSchema, 'newsList')
var userList = mongoose.model('user', userSchema, 'userList')

module.exports = {
  newsList: newsList,
  userList: userList
}
