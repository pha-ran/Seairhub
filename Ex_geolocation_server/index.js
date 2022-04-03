const https = require('https');
const express = require('express');
const path = require('path');
const fs = require('fs');
const res = require('express/lib/response');
const req = require('express/lib/request');

const app = express();
const port = 443; // 사용할 포트 번호

app.set('view engine', 'ejs');
app.engine('html', require('ejs').renderFile);

app.use("*", (req, res, next) => {
  console.log("req.secure : " + req.secure);

  if(req.secure) {
    // https
    next();
  }else {
    // http
    let to = "https://" + req.header.host + req.url;
    console.log("to ==> " + to);
    return res.redirect("https://" + req.header.host + req.url);
  }
});

app.get('/', (req,res,next)=>{
  res.render('index', {
    javascriptkey:'4d36adb8c66f49ef2c999d273ccb2238'
  });
});

const options = {
  cert: fs.readFileSync(__dirname + '/certificate.crt'),
  ca: fs.readFileSync(__dirname + '/ca_bundle.crt'),
  key: fs.readFileSync(__dirname + '/private.key')
 };

const sslServer = https.createServer(options, app);

sslServer.listen(port, () => console.log('server on'));