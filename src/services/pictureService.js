import Picture from '../models/picture';

/**
 * Get picture by product id.
 *
 * @param  {Number|String}  productId
 * @return {Promise}
 */
export function getPictureByProductId(productId) {
    return new Picture().where({product_id:productId}).fetchAll()
      .then(pictures => {
        if (!pictures) {
          throw new Object({status:404, message:"Picture not found"});
        }
  
        return pictures;
      }); 
  }


/**
 * Create new picture
 *
 */
export function createPicture(picture) {
    return new Picture({ 
        product_id : picture.productId,
        link: picture.link
    }).save(null, { method: 'insert' })
        .then(data => data);
}
  
  
/**
 * Delete a picture item.
 *
 */
export function deletePicture(id) {
    return new Picture({ id }).fetch()    
        .then(data => data.destroy());    
}
  
  