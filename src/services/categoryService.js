import Category from '../models/category';

/**
 * Get all categories.
 *
 */
export function getAllCategories() {
    return new Category().fetchAll()
        .then(data => data); 
}

/**
 * Create new category
 *
 */
export function createCategory(category) {
    return new Category({ 
        name : category.name,
        picture:category.picture
    }).save(null, { method: 'insert' })
        .then(data => data);
}
  
/**
 * Update a category
 *
 */
export function updateCategory(id, category) {  
    return new Category({ id })
        .save(category, { method: 'update' })
        .then(data => data.refresh());    
}
  
/**
 * Delete a category item.
 *
 */
export function deleteCategory(id) {
    return new Category({ id }).fetch()    
        .then(data => data.destroy());    
}
  
  