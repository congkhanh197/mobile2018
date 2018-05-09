import { Router } from 'express';
import HttpStatus from 'http-status-codes';
import * as productService from '../services/productService';

const router = Router();

/**
 * GET /api/products/all
 */
router.get('/all', (req, res, next) => {
    productService.getAllProducts()
        .then(data => res.json({ data }))
        .catch(err => next(err));
});

/**
 * GET /api/products/:id
 */
router.get('/:id', (req, res, next) => {
    productService.getProductById(req.params.id)
      .then(data => res.json({ data }))
      .catch(err => next(err)); 
  });
  
/**
 * POST /api/products
 */
router.post('/', (req, res, next) => {
    productService.createProduct(req.body)
      .then(data => res.status(HttpStatus.CREATED).json({ data }))
      .catch(err => next(err));
  });
  
  /**
   * PUT /api/products/:id
   */
  router.put('/:id',(req, res, next) => {
    productService.updateProduct(req.params.id, req.body)
      .then(data => res.json({ data }))
      .catch(err => next(err));
  });
  
  /**
   * DELETE /api/products/:id
   */
  router.delete('/:id', (req, res, next) => {
    productService.deleteProduct(req.params.id)
      .then(data => res.status(HttpStatus.NO_CONTENT).json({ data }))
      .catch(err => next(err));
  });
  

export default router; 