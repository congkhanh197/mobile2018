import Product from '../models/product';

/**
 * Get all Products.
 *
 * @return {Promise}
 */
export function getAllProducts() {
    console.log(req.app.get('port'));
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
 * Get product by category.
 *
 * @param  {Number|String}  categoryId
 * @return {Promise}
 */
export function getProductByCategoryId(categoryId) {
    return new Product().where({category_id:categoryId}).fetch()
      .then(products => {
        if (!products) {
          throw new Object({status:404, message:"Product not found"});
        }
  
        return products;
      }); 
  }

/**
 * Get product by genre.
 *
 * @param  {Number|String}  genreId
 * @return {Promise}
 */
export function getProductByGenreId(genreId) {
    return new Product().where({genre_id:genreId}).fetch()
      .then(products => {
        if (!products) {
          throw new Object({status:404, message:"Product not found"});
        }
  
        return products;
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
  
  