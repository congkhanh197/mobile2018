import bookshelf from '../database_config';

const TABLE_NAME = 'm_genres';

/**
 * Genre model.
 */
/**
 * @swagger
 * definitions:
 *   Genre:
 *     properties:
 *       id:
 *         type: integer
 *       name:
 *         type: string
 *       cate_id:
 *         type: integer
 *       created_at:
 *         type: string
 *       updated_at:
 *         type: string
 */
class Genre extends bookshelf.Model {

  get tableName() {
    return TABLE_NAME;
  }

  get hasTimestamps() {
    return false;
  }

}

export default Genre;
