import Order from '../models/order';
import * as cartItemService from './cartItemService';
import * as orderItemService from './orderItemService';

/**
 * Get orders by user id
 *
 */
export function getOrdersByUserId(userId) {
    return new Order().where({
      user_id : userId
    })
    .fetchAll({ withRelated : ["items"] })
    .then(orders => {
      if (!orders) {
        throw new Object({status:404, message:"Order not found"});
      }

      return orders;
    }); 
}

/**
 * Get a order by orderId.
 *
 * @param  {Number|String}  id
 * @return {Promise}
 */
export function getOrderById(id) {
    return new Order({ id }).fetch({withRelated:["items"]})
      .then(order => {
        if (!order) {
          throw new Object({status:404, message:"Order not found"});
        }
  
        return order;
      }); 
  }


/**
 * Create new order
 *
 */
export function createOrder(order) {
    return new Order({ 
        user_id : order.userId,
        total: order.total,
        status: order.status
      }).save(null, { method: 'insert' })
        .then(data => {
          cartItemService.getCartItemByUserId(order.userId)
            .then(cartItems => {
              cartItems = cartItems.toJSON();
              cartItems.forEach(item => {
                item.orderId = data.id;
                orderItemService.createOrderItem(item);
                cartItemService.deleteCartItem(item.userId, item.productId);
              });
            });
          return data;
        });
}
  
/**
 * Update a order
 *
 */
export function updateOrder(id, order) {  
    return new Order({ id })
        .save(order, { method: 'update' })
        .then(data => data.refresh());    
}
  
/**
 * Delete a order.
 *
 */
export function deleteOrder(id) {
    return new Order({ id }).fetch()    
        .then(data => data.destroy());    
}
  
  