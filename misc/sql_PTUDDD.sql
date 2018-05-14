create database if not exists mobileapp;
use mobileapp;    

CREATE TABLE IF NOT EXISTS m_users
	(
		id int(11) unsigned NOT NULL AUTO_INCREMENT,
		username character varying(50),
        password character varying(50),
		ava text,
        fullname character varying(100),
        dob timestamp not null default now(),
        email character varying(255),
        phone character varying(20),
		sex tinyint(1),
		first_login tinyint(1),
		type character varying(50),
        third_party_id character varying(100),
		created_at timestamp not null default now(),
    	updated_at timestamp not null default now(),
		constraint user_pkey primary key (id),
		constraint user_email_unique unique (email),
        constraint user_username_unique unique (username)


	);


CREATE TABLE IF NOT EXISTS m_products
	(
		id int(11) unsigned NOT NULL AUTO_INCREMENT,
		name character varying(100) NOT NULL,
        info text,
        price int(11) unsigned NOT NULL,
        creator_id int(11) unsigned NOT NULL,
		created_at timestamp not null default now(),
        updated_at timestamp not null default now(),
        type character varying(50),
		constraint product_pkey primary key (id),
        constraint product_creatorid_foreign  foreign key (creator_id)
					REFERENCES m_users (id) MATCH SIMPLE
					ON UPDATE NO ACTION ON DELETE NO ACTION
        
	);
    

CREATE TABLE IF NOT EXISTS m_pictures
	(
		id int(11) unsigned NOT NULL AUTO_INCREMENT,
		product_id int(11) unsigned NOT NULL,
        link text,
		created_at timestamp not null default now(),
        updated_at timestamp not null default now(),
		constraint picture_pkey primary key (id)        
	);    

CREATE TABLE IF NOT EXISTS m_categories
	(
		id int(11) unsigned NOT NULL AUTO_INCREMENT,
        picture text,
		name character varying(100) NOT NULL,
		created_at timestamp not null default now(),
        updated_at timestamp not null default now(),
		constraint category_pkey primary key (id)        
	);    

CREATE TABLE IF NOT EXISTS t_cartitems
	(
		user_id int(11) unsigned NOT NULL ,
		quantity int(11) unsigned DEFAULT 1,
		product_id int(11) unsigned NOT NULL,
		created_at timestamp not null default now(),
        updated_at timestamp not null default now(),
		constraint cart_pkey primary key (user_id,product_id)        
	);    

CREATE TABLE IF NOT EXISTS m_orders
	(
		id int(11) unsigned NOT NULL AUTO_INCREMENT,
		user_id int(11) unsigned NOT NULL ,
		total int(11) unsigned DEFAULT 1,
		status character varying(100) NOT NULL,
		created_at timestamp not null default now(),
        updated_at timestamp not null default now(),
		constraint order_pkey primary key (id)        
	);    

CREATE TABLE IF NOT EXISTS m_orderitems
	(
		id int(11) unsigned NOT NULL AUTO_INCREMENT,
		order_id int(11) unsigned NOT NULL ,
		quantity int(11) unsigned DEFAULT 1,
		product_id int(11) unsigned NOT NULL,
		created_at timestamp not null default now(),
        updated_at timestamp not null default now(),
		constraint orderitem_pkey primary key (id)        
	);    

CREATE TABLE IF NOT EXISTS m_genres
	(
		id int(11) unsigned NOT NULL AUTO_INCREMENT,
		cate_id int(11) unsigned NOT NULL ,
		name character varying(100) NOT NULL,
		created_at timestamp not null default now(),
        updated_at timestamp not null default now(),
		constraint orderitem_pkey primary key (id)        
	);    


