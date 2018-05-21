import { Router } from 'express';
import HttpStatus from 'http-status-codes';
import * as categoryService from '../services/categoryService';

const router = Router();

/**
 * GET /api/category
 * 
 */
/**
 * @swagger
 * /api/category:
 *   get:
 *     tags:
 *       - Categories
 *     description: Returns all categories
 *     produces:
 *       - application/json
 *     responses:
 *       200:
 *         description: List categories
 *         schema:
 *           $ref: '#/definitions/Category'
 */
router.get('/', (req, res, next) => {
    categoryService.getAllCategories()
        .then(data => res.json({ data }))
        .catch(err => next(err));
});

/**
 * POST /api/category
 */
/**
 * @swagger
 * /api/category:
 *   post:
 *     tags:
 *       - Categories
 *     description: Add new category
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: category
 *         description: Category object
 *         in: body
 *         required: true
 *         schema:
 *           $ref: '#/definitions/Category'
 *     responses:
 *       200:
 *         description: Add success
 *         schema:
 *           $ref: '#/definitions/Category'
 */
router.post('/', (req, res, next) => {
    categoryService.createCategory(req.body)
        .then(data => res.json({ data }))
        .catch(err => next(err));
});

/**
 * PUT /api/category/:id
 */
/**
 * @swagger
 * /api/category/{id}:
 *   put:
 *     tags:
 *       - Categories
 *     description: Update category
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: category
 *         description: Category object
 *         in: body
 *         required: true
 *         schema:
 *           $ref: '#/definitions/Category'
 *     responses:
 *       200:
 *         description: Update success
 *         schema:
 *           $ref: '#/definitions/Category'
 */
router.put('/:id', (req, res, next) => {
    categoryService.updateCategory(req.params.id, req.body)
        .then(data => res.json({ data }))
        .catch(err => next(err));
});

/**
 * DELETE /api/category/:id
 */
/**
 * @swagger
 * /api/category/{id}:
 *   delete:
 *     tags:
 *       - Categories
 *     description: Delete category
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         description: Category id
 *         in: path
 *         required: true
 *         type: integer
 *     responses:
 *       200:
 *         description: Delete success
 */
router.delete('/:id', (req, res, next) => {
    categoryService.deleteCategory(req.params.id)
        .then(data => res.json({ data }))
        .catch(err => next(err));
});

export default router;
