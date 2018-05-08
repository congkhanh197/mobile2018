import { Router } from 'express';
import HttpStatus from 'http-status-codes';
import * as loginService from '../services/loginService';

const router = Router();

/**
 * POST /api/signup
 */
router.post('/signup', (req, res, next) => {
    loginService.signup(req.body)
      .then(data => res.json({ data }))
      .catch(err => next(err));
  });

router.post('/', (req, res, next) => {
    loginService.login(req.body)
      .then(data => res.json({ data }))
      .catch(err => next(err));
  });

  export default router;