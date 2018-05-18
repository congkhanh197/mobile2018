import { Router } from 'express';
import HttpStatus from 'http-status-codes';
import * as genreService from '../services/genreService';

const router = Router();

/**
 * GET /api/genre/all
 */
/**
 * @swagger
 * /api/genre/all:
 *   get:
 *     tags:
 *       - Genres
 *     description: Returns all genres
 *     produces:
 *       - application/json
 *     responses:
 *       200:
 *         description: List of Genres
 *         schema:
 *           $ref: '#/definitions/Genres'
 */
router.get('/all', (req, res, next) => {
    genreService.getAllGenre()
        .then(data => res.json({ data }))
        .catch(err => next(err));
});



 /**
 * GET /api/genre/:categoryId
 */
/**
 * @swagger
 * /api/genre/{categoryId}:
 *   get:
 *     tags:
 *       - Genres
 *     description: Returns genres by category id
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
 *         description: List genres
 *         schema:
 *           $ref: '#/definitions/Genres'
 */
router.get('/:categoryId', (req, res, next) => {
    genreService.getGenresByCategoryId(req.params.categoryId)
      .then(data => res.json({ data }))
      .catch(err => next(err)); 
  });
  



/**
 * POST /api/genre
 */
/**
 * @swagger
 * /api/genre:
 *   post:
 *     tags:
 *       - Genre
 *     description: Create genre
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: genre
 *         description: Genre object
 *         in: body
 *         required: true
 *         schema:
 *           $ref: '#/definitions/Genre'
 *     responses:
 *       200:
 *         description: Create success
 *         schema:
 *           $ref: '#/definitions/Genre'
 */
router.post('/', (req, res, next) => {
    genreService.createGenre(req.body)
      .then(data => res.status(HttpStatus.CREATED).json({ data }))
      .catch(err => next(err));
  });
  
  
/**
 * DELETE /api/genre/:id
 */
/**
 * @swagger
 * /api/genre/:id:
 *   delete:
 *     tags:
 *       - Genres
 *     description: Delete genre
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         description: Genre id
 *         in: path
 *         required: true
 *         type: integer
 *     responses:
 *       200:
 *         description: Delete success
 */
router.delete('/:id', (req, res, next) => {
  genreService.deleteGenre(req.params.id)
    .then(data => res.status(HttpStatus.NO_CONTENT).json({ data }))
    .catch(err => next(err));
});
  

export default router; 