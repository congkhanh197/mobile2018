import CartItem from '../models/cartitem';

/**
 * Get cart by userId.
 *
 * @return {Promise}
 */
export function getCartItemByUserId(userId) {
    return new CartItem({"userId":userId}).fetch()
                .then(cartitems=>cartitems); 
  }
  
  

/**
 * Create new cart item.
 *
 * @param  {Object}  cartItem
 * @return {Promise}
 */
export function createCartItem(cartItem) {
    return new CartItem({ 
      userId:cartItem.userId,
      quantity:cartItem.userId,
      productId:cartItem.productId
    }).save(null, { method: 'insert' }).then(cartItem => cartItem);
  }
  
  /**
   * Update a CartItem.
   *
   * @param  {Number|String}  id
   * @param  {Object}         cartItem
   * @return {Promise}
   */
  export function updateCartItem(id, newCartItem) {  
      return new CartItem({id}).fetch()
        .then(cartItem=> {
            if (!cartItem) {
                throw new Object({status:404,message:"Cart item not found"});
            } else {
                return new CartItem({ id }).save(newCartItem, { method: 'update' })
                    .then(updatedCartItem => updatedCartItem);
            }
        })  
    
  }
  
  /**
   * Delete a cart item.
   *
   * @param  {Number|String}  id
   * @return {Promise}
   */
  export function deleteCartItem(id) {
    return new CartItem({id}).fetch()
    .then(cartItem=> {
        if (!cartItem) {
            throw new Object({status:404,message:"Cart item not found"});
        } else {
            return new CartItem({ id }).fetch().then(cartItem => cartItem.destroy());
        }
    })  
  }
  
  