import bookshelf from '../database_config';

const TABLE_NAME = 'm_users';

/**
 * User model.
 */
class User extends bookshelf.Model {

  get tableName() {
    return TABLE_NAME;
  }

  get hasTimestamps() {
    return true;
  }

}

export default User;
