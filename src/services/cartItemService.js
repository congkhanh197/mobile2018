import CartItem from '../models/cartitem';

/**
 * Get cart by userId.
 *
 */
export function getCartItemByUserId(userId) {
    return new CartItem().where({ user_id:userId })
        .fetchAll({
            withRelated: ['product']
        })
        .then(cartitems => cartitems); 
}
  
/**
 * Create new cart item.
 *
 */
export function createCartItem(cartItem) {
    return new CartItem({ 
      userId:cartItem.userId,
      quantity:cartItem.quantity,
      productId:cartItem.productId
    }).save(null, { method: 'insert' }).then(cartItem => cartItem);
}
  
/**
 * Update a CartItem.
 *
 */
export function updateCartItem(newCartItem) {  
    return new CartItem().where({
        user_id:newCartItem.userId,
        product_id:newCartItem.productId
    }).save(newCartItem, { method: 'update' })
        .then(newCartItem => newCartItem);    
}
  
/**
 * Delete a cart item.
 *
 */
export function deleteCartItem(userId, productId) {
    return new CartItem().where({
        user_id : userId,
        product_id : productId
    }).destroy()
    .then(cartItem => cartItem);    
}
  
  