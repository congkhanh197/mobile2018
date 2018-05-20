import Genre from '../models/genre';

/**
 * Get all Genre.
 *
 */
export function getAllGenre() {
    return new Genre().fetchAll()
        .then(data => data); 
}

/**
 * Get genre by categoryid.
 *
 * @param  {Number|String}  categoryId
 * @return {Promise}
 */
export function getGenresByCategoryId(categoryId) {
    return new Genre().where({cate_id:categoryId}).fetchAll()
      .then(genres => {
        if (!genres) {
          throw new Object({status:404, message:"Genres not found"});
        }
  
        return genres;
      }); 
  }


/**
 * Create new genre
 *
 */
export function createGenre(genre) {
    return new Genre({ 
        name : genre.name,
        cate_id:genre.cateId
    }).save(null, { method: 'insert' })
        .then(data => data);
}
  
  
/**
 * Delete a genre item.
 *
 */
export function deleteGenre(id) {
    return new Genre({ id }).fetch()    
        .then(data => data.destroy());    
}
  
  