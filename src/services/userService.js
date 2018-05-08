import Boom from 'boom';
import User from '../models/user';

/**
 * Get user by id
 */
export function getUserById(id) {
    return new User({ id }).fetch()
        .then(user => {
            if (!user) 
                throw new Object({status: 404, message: 'User not found'});
            return user;
        });
}


/**
 * Get a user by username.
 *
 * @param  {Number|String}  username
 * @return {Promise}
 */
export function getUserByUsername(username_) {
    return new User({'username':username_ }).fetch()
      .then(user => {
        if (!user) {
        //   throw Boom.notFound("User not found");
          throw JSON.parse('{ "statusCode": 404, "error": "Not Found", "message": "User not found" }')
        }
  
        return user;
      });
  }
  
  /**
   * Create new user.
   *
   * @param  {Object}  user
   * @return {Promise}
   */
  export function createUser(user) {
    return new User({ name: user.name }).save().then(user => user.refresh());
  }
  