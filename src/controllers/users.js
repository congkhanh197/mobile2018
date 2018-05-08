import { Router } from 'express';
import HttpStatus from 'http-status-codes';
import * as userService from '../services/userService';

const router = Router();

/**
 * GET /api/users/:id
 */
router.get('/:id', (req, res, next) => {
    userService.getUserById(req.params.id)
        .then(data => res.json({ data }))
        .catch(err => next(err));
});

export default router;