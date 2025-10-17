-- Create database if it doesn't exist and use it
CREATE DATABASE IF NOT EXISTS fileshare;
USE fileshare;

-- Create Users table
CREATE TABLE Users (
	user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(36) UNIQUE NOT NULL,
    email_address VARCHAR(256) UNIQUE NOT NULL,
    password_hash VARCHAR(256) NOT NULL
);

-- Create Collections table
CREATE TABLE Collections (
	collection_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    visibility BOOL NOT NULL,
    created_at DATE NOT NULL,
    CONSTRAINT fk_user_id FOREIGN KEY(user_id)
    REFERENCES Users(user_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create Files table
CREATE TABLE Files (
	file_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    collection_id BIGINT NOT NULL,
    file_name VARCHAR(256) NOT NULL,
    file_type VARCHAR(64) NOT NULL,
    file_size_kb INT NOT NULL,
    hashed_file_name VARCHAR(256) UNIQUE NOT NULL,
    CONSTRAINT fk_collection_id FOREIGN KEY(collection_id)
    REFERENCES Collections(collection_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create SharedCollections table
CREATE TABLE SharedCollections (
	collection_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_shared_collection_id FOREIGN KEY(collection_id)
    REFERENCES Collections(collection_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_shared_with_user_id FOREIGN KEY(user_id)
    REFERENCES Users(user_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);