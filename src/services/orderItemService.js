import OrderItem from '../models/orderitem';

/**
 * Create new order item.
 *
 */
export function createOrderItem(oderItem) {
    return new OrderItem({ 
        orderId : oderItem.orderId,
        quantity : oderItem.quantity,
        productId : oderItem.productId
    }).save(null, { method: 'insert' }).then(oderItem => oderItem);
}
  
/**
 * Update a CartItem.
 *
 */
export function updateOrderItem(id, item) {  
    return new OrderItem({ id }).fetch()
        .save(item, { method : 'update' })
        .then(item => item);    
}
  
/**
 * Delete a order item.
 *
 */
export function deleteOrderItem(id) {
    return new OrderItem({ id }).fetch()
        .then(orderItem => orderItem.destroy());    
}
  
  