const app = express()
//引入中间件
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
//使用中间件
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());

app.get('/newsList', function (req, res) {
  var currentPage = req.query.currentPage
  var limitNum = 4
  var skipNum = (currentPage - 1) * limitNum
  newsList.count(function (err, num) {
    var totalPage = Math.ceil(num / limitNum)
    newsList.find({}).limit(limitNum).skip(skipNum).exec(function (err, docs) {
      res.status(200).json({
        data: docs,
        totalPage: totalPage
      })
    })
  })
})
