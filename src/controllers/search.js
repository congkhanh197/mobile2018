import { Router } from 'express';
import HttpStatus from 'http-status-codes';
import * as searchService from '../services/searchService';

const router = Router();

/**
 * GET /api/search/:name
 */
/**
 * @swagger
 * /api/search/{name}:
 *   get:
 *     tags:
 *       - Search
 *     description: Returns search product
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: name
 *         description: Product name
 *         in: path
 *         required: true
 *         type: string
 *     responses:
 *       200:
 *         description: List of products
 *         schema:
 *           $ref: '#/definitions/Product'
 */
router.get('/:name', (req, res, next) => {
    searchService.searchProductName(req.params.name)
        .then(data => res.json({ data }))
        .catch(err => next(err));
});

export default router; 