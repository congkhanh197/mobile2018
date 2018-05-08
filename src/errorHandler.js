import HttpStatus from 'http-status-codes';

export function notFoundApi(req, res) {
  res.status(HttpStatus.NOT_FOUND).json({
    error: {
      code: HttpStatus.NOT_FOUND,
      message: 'Api not found'
    }
  });
}

export function bodyParser(err, req, res, next) {
  console.log(err);
  const status = err.status || 500
  res.status(status).json({
    error: {
      code: status,
      message: err.message || HttpStatus.getStatusText(status)
    }
  });
}