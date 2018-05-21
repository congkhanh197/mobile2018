import bookshelf from '../database_config';
import Genre from './genre';

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
 *       createdAt:
 *         type: string
 *       updatedAt:
 *         type: string
 */
class Category extends bookshelf.Model {

  get tableName() {
    return TABLE_NAME;
  }

  get hasTimestamps() {
    return false;
  }

  genres() {
    return this.hasMany(Genre, "cate_id");
  }

}

export default Category;
