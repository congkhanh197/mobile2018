import { Router } from 'express';
import swaggerJSDoc from 'swagger-jsdoc';
import path from 'path';
import userController from './controllers/users';
import loginController from './controllers/login';
import productController from './controllers/products';
import cartController from './controllers/cart_items';
import categoryController from './controllers/categories';

/**
 * Contains all API routes for the application.
 */
let router = Router();

const swaggerDefinition = {
  info: {
    title: 'Mobile Spring API',
    version: '1.0.0',
    description: 'RESTful API Description',
  },
  host: '127.0.0.1:8080',
  basePath: '/api',
};

// options for the swagger docs
const options = {
  // import swaggerDefinitions
  swaggerDefinition: swaggerDefinition,
  // path to the API docs
  apis: [path.join(__dirname, '/routes.js'), path.join(__dirname, '/models/*.js'), path.join(__dirname, '/controllers/*.js')],
};


// initialize swagger-jsdoc
const swaggerSpec = swaggerJSDoc(options);

router.get('/swagger.json', function(req, res) {
  res.setHeader('Content-Type', 'application/json');
  res.send(swaggerSpec);
});

router.get('/', (req, res) => {
  res.json({
    app: req.app.locals.title,
    apiVersion: req.app.locals.version
  });
});

router.use('/users', userController);
router.use('/login', loginController);
router.use('/products', productController);
router.use('/cart', cartController);
router.use('/category', categoryController);

export default router;
