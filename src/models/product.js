import bookshelf from '../database_config';

const TABLE_NAME = 'm_products';

/**
 * User model.
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
