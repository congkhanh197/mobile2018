import bookshelf from '../database_config';
import Product from './product';


const TABLE_NAME = 'm_orderitems';

/**
 * Orderitem model.
 */
/**
 * @swagger
 * definitions:
 *   OrderItem:
 *     properties:
 *       id:
 *         type: integer
 *       orderId:
 *         type: integer
 *       quantity:
 *         type: integer
 *       productId:
 *         type: integer
 *       createdAt:
 *         type: string
 *       updatedAt:
 *         type: string
 */
class OrderItem extends bookshelf.Model {

  get tableName() {
    return TABLE_NAME;
  }

  get hasTimestamps() {
    return false;
  }

  product() {
    return this.belongsTo(Product, "product_id");
  }

}

export default OrderItem;
