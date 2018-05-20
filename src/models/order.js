import bookshelf from '../database_config';
import OrderItem from './orderitem';


const TABLE_NAME = 'm_orders';

/**
 * Order model.
 */
/**
 * @swagger
 * definitions:
 *   Order:
 *     properties:
 *       id:
 *         type: integer
 *       userId:
 *         type: integer
 *       status:
 *         type: string
 *       createdAt:
 *         type: string
 *       updatedAt:
 *         type: string
 */
class Order extends bookshelf.Model {

  get tableName() {
    return TABLE_NAME;
  }

  get hasTimestamps() {
    return false;
  }
  items() {
      return this.hasMany(OrderItem,"order_id")
  }
  

}

export default Order;
