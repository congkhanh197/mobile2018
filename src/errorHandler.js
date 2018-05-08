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
  res.status(err.status).json({
    error: {
      code: err.status,
      message: err.message || HttpStatus.getStatusText(err.status)
    }
  });
}