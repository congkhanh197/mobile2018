import { Router } from 'express';
import HttpStatus from 'http-status-codes';
import * as userService from '../services/userService';

const router = Router();

/**
 * GET /api/users/:id
 */
router.get('/:id', (req, res, next) => {
    console.log("vao getUserbyId");
    userService.getUserById(req.params.id)
        .then(data => res.json({ data }))
        .catch(err => next(err));
});

/**
 * GET /api/users/:username
 */
router.get('/username/:username', (req, res, next) => {
    console.log("co vao day 1111");
    userService.getUserByUsername(req.params.username)
        .then(data => res.status(HttpStatus.OK).json({ data }))
        .catch(err => next(err));
});



export default router;