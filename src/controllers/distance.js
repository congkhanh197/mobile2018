import Router from 'express';
import * as DistanceService from '../services/distanceService';
import HttpStatus from 'http-status-codes';
const router = Router();

/**
 * GET /api/distance/:latitude1/:longitude1/:latitude2/:longitude2
 */
router.get('/:latitude1/:longitude1/:latitude2/:longitude2', (req, res, next) => {
  // var request = Url.parse(req.url,true).query;
  var request = req.params;
  res.json(DistanceService.getDistance(request.latitude1,request.longitude1,request.latitude2,request.longitude2))    
});

export default router;
