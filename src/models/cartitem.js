import bookshelf from '../database_config';
import Product from './product';

const TABLE_NAME = 't_cartitems';

/**
 * Cartitem model.
 */
/**
 * @swagger
 * definitions:
 *   CartItem:
 *     properties:
 *       user_id:
 *         type: integer
 *       product_id:
 *         type: integer
 *       quantity:
 *         type: integer       
 *       created_at:
 *         type: string
 *       updated_at:
 *         type: string
 */
class CartItem extends bookshelf.Model {

  get tableName() {
    return TABLE_NAME;
  }

  get hasTimestamps() {
    return true;
  }

  product() {
    return this.belongsTo(Product, "product_id");
  }
}

export default CartItem;
