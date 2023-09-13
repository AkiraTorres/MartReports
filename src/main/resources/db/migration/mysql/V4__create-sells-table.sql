CREATE TABLE sells (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sell_date VARCHAR(255) NOT NULL,
    total DECIMAL(15, 3) NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(id)
) ENGINE = innodb;