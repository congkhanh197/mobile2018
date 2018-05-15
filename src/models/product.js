import bookshelf from '../database_config';

const TABLE_NAME = 'm_products';

/**
 * Product model.
 */
/**
 * @swagger
 * definitions:
 *   Product:
 *     properties:
 *       id:
 *         type: integer
 *       name:
 *         type: string
 *       info:
 *         type: string
 *       price:
 *         type: integer
 *       type:
 *         type: integer
 *       creator_id:
 *         type: integer
 *       category_id:
 *         type: integer
 *       genre_id:
 *         type: integer
 *       sale:
 *         type: integer
 *       created_at:
 *         type: string
 *       updated_at:
 *         type: string
 */
class Product extends bookshelf.Model {

  get tableName() {
    return TABLE_NAME;
  }

  get hasTimestamps() {
    return true;
  }

}

export default Product;
