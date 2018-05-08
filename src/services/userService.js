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
    return new User({username:username_ }).fetch()
      .then(user => { 
        return user;
      });
  }
  
