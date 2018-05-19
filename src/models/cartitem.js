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
 *       userId:
 *         type: integer
 *       productId:
 *         type: integer
 *       quantity:
 *         type: integer       
 *       createdAt:
 *         type: string
 *       updatedAt:
 *         type: string
 */
class CartItem extends bookshelf.Model {

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

export default CartItem;
