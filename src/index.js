
// var fs = require('fs');
import routes from './routes';

import bodyParser from 'body-parser';

import http from 'http';

var express = require('express');
var app = express();

const APP_PORT = '8080';
const APP_HOST = '149.28.26.145';

app.set('port', APP_PORT);
app.set('host', APP_HOST);

app.locals.title = 'mobile-spring-api';
app.locals.version = '1.0.0';

app.use('/api', routes);

app.listen(app.get('port'), () => {
  console.log('info', `Server started at http://${app.get('host')}:${app.get('port')}`);
});

export default app;

