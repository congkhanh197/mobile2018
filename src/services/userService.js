import User from '../models/user';

/**
 * Get user by id
 */
export function getUserById(id) {
    return new User({ id }).fetch()
        .then(user => {
            if (!user) 
                throw JSON.parse('{ "statusCode": 404, "error": "Not Found", "message": "User not found" }');

            return user;
        });
}