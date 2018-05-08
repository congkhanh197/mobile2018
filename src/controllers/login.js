import { Router } from 'express';
import HttpStatus from 'http-status-codes';
import * as loginService from '../services/loginService';

const router = Router();

/**
 * POST /api/login/signup
 */
/**
 * @swagger
 * /api/login/signup:
 *   post:
 *     tags:
 *       - Login
 *     description: Sign up new account
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: user
 *         description: User object
 *         in: body
 *         required: true
 *         schema:
 *           $ref: '#/definitions/User'
 *     responses:
 *       200:
 *         description: Successfullt created new account
 *         schema:
 *           $ref: '#/definitions/User'
 */
router.post('/signup', (req, res, next) => {
    loginService.signup(req.body)
      .then(data => res.json({ data }))
      .catch(err => next(err));
  });

/**
 * POST /api/login/signup
 */
/**
 * @swagger
 * /api/login:
 *   post:
 *     tags:
 *       - Login
 *     description: Login
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: user
 *         description: User object
 *         in: body
 *         required: true
 *         properties:
 *            username:
 *                type: string
 *            password:
 *                type: string
 *     responses:
 *       200:
 *         description: Login successfully
 *         schema:
 *           $ref: '#/definitions/User'
 */
router.post('/', (req, res, next) => {
    loginService.login(req.body)
      .then(data => res.json({ data }))
      .catch(err => next(err));
  });

  export default router;