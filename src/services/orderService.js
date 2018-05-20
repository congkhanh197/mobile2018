import Order from '../models/order';

/**
 * Get orders by user id
 *
 */
export function getOrdersByUserId(userId) {
    return new Order().where({user_id:userId}).fetchAll()
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
        .then(data => data);
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
  
  