import { Router } from 'express';
import HttpStatus from 'http-status-codes';
import * as cartItemService from '../services/cartItemService';

const router = Router();

/**
 * GET /api/cart/:id
 * 
 */
/**
 * @swagger
 * /api/cart/{id}:
 *   get:
 *     tags:
 *       - Cart Items
 *     description: Returns cart by user id
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         description: User id
 *         in: path
 *         required: true
 *         type: integer
 *     responses:
 *       200:
 *         description: List cart items
 *         schema:
 *           $ref: '#/definitions/CartItem'
 */
router.get('/:id', (req, res, next) => {
    cartItemService.getCartItemByUserId(req.params.id)
        .then(data => res.json({ data }))
        .catch(err => next(err));
});

/**
 * POST /api/cart
 */
/**
 * @swagger
 * /api/cart:
 *   post:
 *     tags:
 *       - Cart Items
 *     description: Add new item to cart
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: item
 *         description: Cart item object
 *         in: body
 *         required: true
 *         schema:
 *           $ref: '#/definitions/CartItem'
 *     responses:
 *       200:
 *         description: Add success
 *         schema:
 *           $ref: '#/definitions/CartItem'
 */
router.post('/', (req, res, next) => {
    cartItemService.createCartItem(req.body)
        .then(data => res.json({ data }))
        .catch(err => next(err));
});

/**
 * PUT /api/cart/
 */
/**
 * @swagger
 * /api/cart:
 *   put:
 *     tags:
 *       - Cart Items
 *     description: Update item in cart
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: item
 *         description: Cart item object
 *         in: body
 *         required: true
 *         schema:
 *           $ref: '#/definitions/CartItem'
 *     responses:
 *       200:
 *         description: Update success
 *         schema:
 *           $ref: '#/definitions/CartItem'
 */
router.put('/', (req, res, next) => {
    cartItemService.updateCartItem(req.body)
        .then(data => res.json({ data }))
        .catch(err => next(err));
});

/**
 * DELETE /api/cart/:userid/:productid
 */
/**
 * @swagger
 * /api/cart/{userid}/{productid}:
 *   delete:
 *     tags:
 *       - Cart Items
 *     description: Delete item from cart
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: userid
 *         description: User id
 *         in: path
 *         required: true
 *         type: integer
 *       - name: productid
 *         description: Product id
 *         in: path
 *         required: true
 *         type: integer
 *     responses:
 *       200:
 *         description: Delete success
 */
router.delete('/:userid/:productid', (req, res, next) => {
    cartItemService.deleteCartItem(req.params.userid, req.params.productid)
        .then(data => res.json({ data }))
        .catch(err => next(err));
});

export default router;
