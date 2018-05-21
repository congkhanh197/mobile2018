import { Router } from 'express';
import HttpStatus from 'http-status-codes';
import * as pictureService from '../services/pictureService';

const router = Router();

 /**
 * GET /api/picture/:productId
 */
/**
 * @swagger
 * /api/picture/{productId}:
 *   get:
 *     tags:
 *       - Pictures
 *     description: Returns pictures by product id
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: productId
 *         description: Product id
 *         in: path
 *         required: true
 *         type: integer
 *     responses:
 *       200:
 *         description: List pictures
 *         schema:
 *           $ref: '#/definitions/Pictures'
 */
router.get('/:productId', (req, res, next) => {
    pictureService.getPictureByProductId(req.params.productId)
      .then(data => res.json({ data }))
      .catch(err => next(err)); 
  });
  



/**
 * POST /api/picture
 */
/**
 * @swagger
 * /api/picture:
 *   post:
 *     tags:
 *       - Picture
 *     description: Create Picture
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: picture
 *         description: Picture object
 *         in: body
 *         required: true
 *         schema:
 *           $ref: '#/definitions/Picture'
 *     responses:
 *       200:
 *         description: Create success
 *         schema:
 *           $ref: '#/definitions/Picture'
 */
router.post('/', (req, res, next) => {
    pictureService.createPicture(req.body)
      .then(data => res.status(HttpStatus.CREATED).json({ data }))
      .catch(err => next(err));
  });
  
  
/**
 * DELETE /api/picture/:id
 */
/**
 * @swagger
 * /api/picture/:id:
 *   delete:
 *     tags:
 *       - Pictures
 *     description: Delete picture
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         description: Picture id
 *         in: path
 *         required: true
 *         type: integer
 *     responses:
 *       200:
 *         description: Delete success
 */
router.delete('/:id', (req, res, next) => {
  pictureService.deletePicture(req.params.id)
    .then(data => res.status(HttpStatus.NO_CONTENT).json({ data }))
    .catch(err => next(err));
});
  

export default router; 