import { Router } from 'express';

import userController from './controllers/users';
import loginController from './controllers/login';
import productController from './controllers/products';

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
router.use('/products', productController);

export default router;
