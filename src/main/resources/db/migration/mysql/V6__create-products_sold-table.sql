CREATE TABLE products_sold (
    sale_id INT NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY (sale_id, product_id),
    FOREIGN KEY(sale_id) REFERENCES sales(id),
    FOREIGN KEY(product_id) REFERENCES products(id)
) ENGINE = innodb;