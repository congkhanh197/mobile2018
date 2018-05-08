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