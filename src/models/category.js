import bookshelf from '../database_config';

const TABLE_NAME = 'm_categories';

/**
 * Category model.
 */
/**
 * @swagger
 * definitions:
 *   Category:
 *     properties:
 *       id:
 *         type: integer
 *       name:
 *         type: string
 *       picture:
 *         type: string
 *       created_at:
 *         type: string
 *       updated_at:
 *         type: string
 */
class Category extends bookshelf.Model {

  get tableName() {
    return TABLE_NAME;
  }

  get hasTimestamps() {
    return false;
  }

}

export default Category;
