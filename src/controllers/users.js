import { Router } from 'express';
import HttpStatus from 'http-status-codes';
import * as userService from '../services/userService';

const router = Router();

/**
 * GET /api/users/:id
 */
/**
 * @swagger
 * /api/users/{id}:
 *   get:
 *     tags:
 *       - Users
 *     description: Returns user by id
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
 *         description: A single user
 *         schema:
 *           $ref: '#/definitions/User'
 */
router.get('/:id', (req, res, next) => {
    userService.getUserById(req.params.id)
        .then(data => res.json({ data }))
        .catch(err => next(err));
});

/**
 * GET /api/users/username/:username
 */
/**
 * @swagger
 * /api/users/username/{username}:
 *   get:
 *     tags:
 *       - Users
 *     description: Returns user by name
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: username
 *         description: Username
 *         in: path
 *         required: true
 *         type: string
 *     responses:
 *       200:
 *         description: A single user
 *         schema:
 *           $ref: '#/definitions/User'
 */
router.get('/username/:username', (req, res, next) => {
    userService.getUserByUsername(req.params.username)
        .then(data => res.status(HttpStatus.OK).json({ data }))
        .catch(err => next(err));
});


export default router;