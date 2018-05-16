import Product from '../models/product';

/**
 * Get all Products.
 *
 * @return {Promise}
 */
export function getAllProducts() {
    return Product.fetchAll(); 
  }
  
/**
 * Get a product.
 *
 * @param  {Number|String}  id
 * @return {Promise}
 */
export function getProductById(id) {
    return new Product({ id }).fetch()
      .then(product => {
        if (!product) {
          throw new Object({status:404, message:"Product not found"});
        }
  
        return product;
      }); 
  }
  

/**
 * Create new product.
 *
 * @param  {Object}  product
 * @return {Promise}
 */
export function createProduct(product) {
    return new Product({ 
      name: product.name,
      info: product.info,
      price: product.price,
      creatorId:product.creatorId,
      type:product.type
    }).save(null, { method: 'insert' }).then(product => product);
  }
  
  /**
   * Update a product.
   *
   * @param  {Number|String}  id
   * @param  {Object}         product
   * @return {Promise}
   */
  export function updateProduct(id, newProduct) {  
      return new Product({id}).fetch()
        .then(product=> {
            if (!product) {
                throw new Object({status:404,message:"Product not found"});
            } else {
                return new Product({ id }).save(newProduct, { method: 'update' })
                    .then(updatedProduct => updatedProduct);
            }
        })  
    
  }
  
  /**
   * Delete a product.
   *
   * @param  {Number|String}  id
   * @return {Promise}
   */
  export function deleteProduct(id) {
    return new Product({id}).fetch()
    .then(product=> {
        if (!product) {
            throw new Object({status:404,message:"Product not found"});
        } else {
            return new Product({ id }).fetch().then(product => product.destroy());
        }
    })  
  }
  
  