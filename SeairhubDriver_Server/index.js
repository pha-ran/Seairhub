const https = require('https');
const express = require('express');
const path = require('path');
const fs = require('fs');
const res = require('express/lib/response');
const req = require('express/lib/request');
const bodyParser = require('body-parser');
const admin = require("firebase-admin");
const serviceAccount = require("./seairhubdriver-firebase-adminsdk.json");
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});
const db = admin.firestore();

const app = express();
const port = 443; // 사용할 포트 번호

app.set('view engine', 'ejs');
app.engine('html', require('ejs').renderFile);

app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

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
  res.render('index_t', {lat_s : 0,  lon_s : 0});
});

app.post('/', (req, res, next) => {
  console.log(req.body);
  lat = req.body.lat
  lon = req.body.lon
  res.render('index_t', {lat : lat, lon : lon});
});

app.post('/send-location', (req, res, next) => {

  db.collection('lists').add({
    'lat' : req.body.lat,
    'lon' : req.body.lon
  });

  console.log("DB set : " + req.body);

  return res.status(200).json({
    isSuccess : true,
    code : 200,
    message : "Successfully sent location"
  })
})

app.post('/send-message', (req, res, next) => {
  admin.messaging().send(req.body)
  .then((response) => {
    console.log('Successfully sent message : ', response)
    return res.status(200).json({
      isSuccess : true,
      code : 200,
      message : "Successfully sent message"
    })
  })
  .catch((err) => {
    console.log('Error Sending message : ', err)
    return res.status(400).json({
      isSuccess : false,
      code : 400,
      message : "Error Sending message"
    })
  });
})

const options = {
  cert: fs.readFileSync(__dirname + '/certificate.crt'),
  ca: fs.readFileSync(__dirname + '/ca_bundle.crt'),
  key: fs.readFileSync(__dirname + '/private.key')
 };

const sslServer = https.createServer(options, app);

sslServer.listen(port, () => console.log('server on'));