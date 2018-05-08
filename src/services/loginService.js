import User from '../models/user';
import * as userService from '../services/userService';

/**
 * Signup user
 */
export function signup(user) {
    
    return new User({username:user.username }).fetch()
        .then(userByUsername => { 
            if (!userByUsername) {
                return new User({
                    username: user.username,
                    password: user.password,
                    fullname: user.fullname,
                    dob: user.dob,    
                    email: user.email,
                    phone: user.phone,
                    sex: user.sex
                    }).save(null, { method: 'insert' }).then(user => user);

            } else {
                throw new Object({status: 400, message: 'Username already exist'});
            }
            
        })  
}

export function login(user) {
    return new User({username:user.username}).fetch()
        .then(userByUsername => {
            if (!userByUsername) {
                throw new Object({status: 400, message: 'User not exist'});
            } else {

                console.log(userByUsername);
                if (user.password.valueOf()!=userByUsername.attributes.password.valueOf()) {
                    throw new Object({status:400, message:'Password not correct'});
                } else {
                    return userByUsername;
                }
            }
        })
}


