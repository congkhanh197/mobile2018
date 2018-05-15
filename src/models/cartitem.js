import bookshelf from '../database_config';

const TABLE_NAME = 't_cartitems';

/**
 * Cartitem model.
 */
class CartItem extends bookshelf.Model {

  get tableName() {
    return TABLE_NAME;
  }

  get hasTimestamps() {
    return true;
  }

}

export default CartItem;
