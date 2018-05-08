import { Router } from 'express';

import userController from './controllers/users';
import loginController from './controllers/login';

/**
 * Contains all API routes for the application.
 */
let router = Router();

router.get('/', (req, res) => {
  res.json({
    app: req.app.locals.title,
    apiVersion: req.app.locals.version
  });
});

router.use('/users', userController);
router.use('/login', loginController);

export default router;
