import { Router } from 'express';
import HttpStatus from 'http-status-codes';
import * as orderService from '../services/orderService';

const router = Router();



/**
 * GET /api/order/:id
 */
/**
 * @swagger
 * /api/order/{id}:
 *   get:
 *     tags:
 *       - Order
 *     description: Returns order by id
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         description: Order id
 *         in: path
 *         required: true
 *         type: integer
 *     responses:
 *       200:
 *         description: A single order
 *         schema:
 *           $ref: '#/definitions/Order'
 */
router.get('/:id', (req, res, next) => {
    orderService.getOrderById(req.params.id)
      .then(data => res.json({ data }))
      .catch(err => next(err)); 
  });


 /**
 * GET /api/order/user/:userId
 */
/**
 * @swagger
 * /api/products/user/{userId}:
 *   get:
 *     tags:
 *       - Orders
 *     description: Returns orders by user id
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: userId
 *         description: User id
 *         in: path
 *         required: true
 *         type: integer
 *     responses:
 *       200:
 *         description: List orders
 *         schema:
 *           $ref: '#/definitions/Orders'
 */
router.get('/user/:userId', (req, res, next) => {
    orderService.getOrdersByUserId(req.params.userId)
      .then(data => res.json({ data }))
      .catch(err => next(err)); 
  });
  


/**
 * POST /api/order
 */
/**
 * @swagger
 * /api/order:
 *   post:
 *     tags:
 *       - Orders
 *     description: Create order
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: order
 *         description: order object
 *         in: body
 *         required: true
 *         schema:
 *           $ref: '#/definitions/order'
 *     responses:
 *       200:
 *         description: Create success
 *         schema:
 *           $ref: '#/definitions/Order'
 */
router.post('/', (req, res, next) => {
    orderService.createOrder(req.body)
      .then(data => res.status(HttpStatus.CREATED).json({ data }))
      .catch(err => next(err));
  });
  
/**
 * PUT /api/order/:id
 */
/**
 * @swagger
 * /api/order/:id:
 *   put:
 *     tags:
 *       - Orders
 *     description: Update order
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: order
 *         description: Order object
 *         in: body
 *         required: true
 *         schema:
 *           $ref: '#/definitions/Order'
 *     responses:
 *       200:
 *         description: Update success
 *         schema:
 *           $ref: '#/definitions/Order'
 */
router.put('/:id',(req, res, next) => {
  orderService.updateOrder(req.params.id, req.body)
    .then(data => res.json({ data }))
    .catch(err => next(err));
});
  
/**
 * DELETE /api/order/:id
 */
/**
 * @swagger
 * /api/order/:id:
 *   delete:
 *     tags:
 *       - Orders
 *     description: Delete order
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         description: order id
 *         in: path
 *         required: true
 *         type: integer
 *     responses:
 *       200:
 *         description: Delete success
 */
router.delete('/:id', (req, res, next) => {
  orderService.deleteOrder(req.params.id)
    .then(data => res.status(HttpStatus.NO_CONTENT).json({ data }))
    .catch(err => next(err));
});
  

export default router; 