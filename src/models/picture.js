import bookshelf from '../database_config';

const TABLE_NAME = 'm_pictures';

/**
 * Picture model.
 */
/**
 * @swagger
 * definitions:
 *   Picture:
 *     properties:
 *       id:
 *         type: integer
 *       product_id:
 *         type: integer
 *       link:
 *         type: string
 *       created_at:
 *         type: string
 *       updated_at:
 *         type: string
 */
class Picture extends bookshelf.Model {

  get tableName() {
    return TABLE_NAME;
  }

  get hasTimestamps() {
    return false;
  }

}

export default Picture;
