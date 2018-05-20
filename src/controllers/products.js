import { Router } from 'express';
import HttpStatus from 'http-status-codes';
import * as productService from '../services/productService';

const router = Router();

/**
 * GET /api/products/all
 */
/**
 * @swagger
 * /api/products/all:
 *   get:
 *     tags:
 *       - Products
 *     description: Returns all product
 *     produces:
 *       - application/json
 *     responses:
 *       200:
 *         description: List of products
 *         schema:
 *           $ref: '#/definitions/Product'
 */
router.get('/all', (req, res, next) => {
    productService.getAllProducts()
        .then(data => res.json({ data }))
        .catch(err => next(err));
});

/**
 * GET /api/products/:id
 */
/**
 * @swagger
 * /api/products/{id}:
 *   get:
 *     tags:
 *       - Products
 *     description: Returns product by id
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         description: Product id
 *         in: path
 *         required: true
 *         type: integer
 *     responses:
 *       200:
 *         description: A single product
 *         schema:
 *           $ref: '#/definitions/Product'
 */
router.get('/:id', (req, res, next) => {
    productService.getProductById(req.params.id)
      .then(data => res.json({ data }))
      .catch(err => next(err)); 
  });


 /**
 * GET /api/products/category/:categoryId
 */
/**
 * @swagger
 * /api/products/category/{categoryId}:
 *   get:
 *     tags:
 *       - Products
 *     description: Returns products by category id
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: categoryId
 *         description: Category id
 *         in: path
 *         required: true
 *         type: integer
 *     responses:
 *       200:
 *         description: List products
 *         schema:
 *           $ref: '#/definitions/Products'
 */
router.get('/category/:categoryId', (req, res, next) => {
    productService.getProductByCategoryId(req.params.categoryId)
      .then(data => res.json({ data }))
      .catch(err => next(err)); 
  });
  

/**
 * GET /api/products/genre/:genreId
 */
/**
 * @swagger
 * /api/products/genre/{genreId}:
 *   get:
 *     tags:
 *       - Products
 *     description: Returns products by genre id
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: genreId
 *         description: Genre id
 *         in: path
 *         required: true
 *         type: integer
 *     responses:
 *       200:
 *         description: List products
 *         schema:
 *           $ref: '#/definitions/Products'
 */
router.get('/genre/:genreId', (req, res, next) => {
    productService.getProductByGenreId(req.params.genreId)
      .then(data => res.json({ data }))
      .catch(err => next(err)); 
  });


/**
 * POST /api/products
 */
/**
 * @swagger
 * /api/products:
 *   post:
 *     tags:
 *       - Products
 *     description: Create product
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: product
 *         description: Product object
 *         in: body
 *         required: true
 *         schema:
 *           $ref: '#/definitions/Product'
 *     responses:
 *       200:
 *         description: Create success
 *         schema:
 *           $ref: '#/definitions/Product'
 */
router.post('/', (req, res, next) => {
    productService.createProduct(req.body)
      .then(data => res.status(HttpStatus.CREATED).json({ data }))
      .catch(err => next(err));
  });
  
/**
 * PUT /api/products/:id
 */
/**
 * @swagger
 * /api/products/:id:
 *   put:
 *     tags:
 *       - Products
 *     description: Update product
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: product
 *         description: Product object
 *         in: body
 *         required: true
 *         schema:
 *           $ref: '#/definitions/Product'
 *     responses:
 *       200:
 *         description: Update success
 *         schema:
 *           $ref: '#/definitions/Product'
 */
router.put('/:id',(req, res, next) => {
  productService.updateProduct(req.params.id, req.body)
    .then(data => res.json({ data }))
    .catch(err => next(err));
});
  
/**
 * DELETE /api/products/:id
 */
/**
 * @swagger
 * /api/products/:id:
 *   delete:
 *     tags:
 *       - Products
 *     description: Delete product
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         description: Product id
 *         in: path
 *         required: true
 *         type: integer
 *     responses:
 *       200:
 *         description: Delete success
 */
router.delete('/:id', (req, res, next) => {
  productService.deleteProduct(req.params.id)
    .then(data => res.status(HttpStatus.NO_CONTENT).json({ data }))
    .catch(err => next(err));
});
  

export default router; 