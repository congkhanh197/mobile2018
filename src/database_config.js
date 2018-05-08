import knexJS from 'knex';
import bookshelfJS from 'bookshelf';

const dbConfig = {
    client: 'mysql',
    connection: {
        port: '3306',
        host: 'localhost',
        user: 'root',
        password: '1234',
        database: 'mobileapp',
        charset: 'utf8',
        timezone: 'UTC'
    }
};
const knex = knexJS(dbConfig);
const bookshelf = bookshelfJS(knex);

bookshelf.plugin(['virtuals', 'pagination', 'visibility', 'bookshelf-camelcase']);
export default bookshelf;
