import { Router } from 'express';
import HttpStatus from 'http-status-codes';
import * as orderItemService from '../services/orderItemService';

const router = Router();


/**
 * POST /api/orderitem
 */
/**
 * @swagger
 * /api/orderitem:
 *   post:
 *     tags:
 *       - OrderItems
 *     description: Add new orderitem
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: orderitem
 *         description: OrderItem object
 *         in: body
 *         required: true
 *         schema:
 *           $ref: '#/definitions/OrderItem'
 *     responses:
 *       200:
 *         description: Add success
 *         schema:
 *           $ref: '#/definitions/OrderItem'
 */
router.post('/', (req, res, next) => {
    orderItemService.createOrderItem(req.body)
        .then(data => res.json({ data }))
        .catch(err => next(err));
});

/**
 * PUT /api/orderitem/:id
 */
/**
 * @swagger
 * /api/orderitem/{id}:
 *   put:
 *     tags:
 *       - OrderItems
 *     description: Update orderitem
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         description: OrderItem id
 *         in: path
 *         required: true
 *         type: integer
 *       - name: orderitem
 *         description: OrderItem object
 *         in: body
 *         required: true
 *         schema:
 *           $ref: '#/definitions/OrderItem'
 *     responses:
 *       200:
 *         description: Update success
 *         schema:
 *           $ref: '#/definitions/OrderItem'
 */
router.put('/:id', (req, res, next) => {
    orderItemService.updateOrderItem(req.params.id, req.body)
        .then(data => res.json({ data }))
        .catch(err => next(err));
});

/**
 * DELETE /api/orderitem/:id
 */
/**
 * @swagger
 * /api/orderitem/{id}:
 *   delete:
 *     tags:
 *       - OrderItems
 *     description: Delete orderitem
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         description: Orderitem id
 *         in: path
 *         required: true
 *         type: integer
 *     responses:
 *       200:
 *         description: Delete success
 */
router.delete('/:id', (req, res, next) => {
    orderItemService.deleteOrderItem(req.params.id)
        .then(data => res.json({ data }))
        .catch(err => next(err));
});

export default router;
