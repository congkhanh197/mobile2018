import Product from '../models/product';
import FuseJs from 'fuse.js';

const options = {
    shouldSort: true,
    threshold: 0.6,
    location: 0,
    distance: 100,
    maxPatternLength: 32,
    minMatchCharLength: 1,
    keys: [
      "id",
      "name"
    ]
};
/**
 * Search product by  name
 */
export function searchProductName(name) {
    return new Product().fetchAll()
        .then(product => {
            const fuse = new FuseJs(product.toJSON(), options);
            const result = fuse.search(name);
            return result;
        });
        
}
  
