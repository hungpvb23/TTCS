CREATE DATABASE topzone_store;

CREATE TABLE Users (
                       user_id SERIAL PRIMARY KEY,
                       fullname VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password_hash TEXT NOT NULL,
                       phone VARCHAR(20),
                       address TEXT,
                       role VARCHAR(20) CHECK (role IN ('customer', 'admin')) DEFAULT 'customer',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO Users (fullname, email, password_hash, phone, address, role) VALUES
                                                                             ('Nguyễn Văn A', 'a@gmail.com', 'hashed_password_123', '0987654321', 'Hà Nội', 'customer'),
                                                                             ('Trần Thị B', 'b@gmail.com', 'hashed_password_456', '0978654321', 'Hồ Chí Minh', 'customer'),
                                                                             ('Admin', 'admin@topzone.com', 'admin_hashed_password', '0909999999', 'Admin Office', 'admin');

CREATE TABLE Categories (
                            category_id SERIAL PRIMARY KEY,
                            name VARCHAR(255) UNIQUE NOT NULL,
                            parent_id INT NULL,
                            FOREIGN KEY (parent_id) REFERENCES Categories(category_id) ON DELETE CASCADE
);

-- Thêm danh mục chính
INSERT INTO Categories (name, parent_id) VALUES
                                             ('iPhone', NULL),
                                             ('MacBook', NULL),
                                             ('iPad', NULL),
                                             ('Apple Watch', NULL);

-- Thêm các đời máy (sub-category)
INSERT INTO Categories (name, parent_id) VALUES
                                             ('iPhone 15', 1),
                                             ('iPhone 14', 1),
                                             ('MacBook Air M2', 2),
                                             ('MacBook Pro M3', 2),
                                             ('iPad Pro M2', 3),
                                             ('Apple Watch Series 9', 4);

CREATE TABLE Products (
                          product_id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          price NUMERIC(10,2) CHECK (price >= 0),
                          stock INT DEFAULT 0 CHECK (stock >= 0),
                          category_id INT,
                          image_url TEXT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (category_id) REFERENCES Categories(category_id) ON DELETE CASCADE
);

INSERT INTO Products (name, description, price, stock, category_id, image_url) VALUES
                                                                                   ('iPhone 15 Pro Max', 'iPhone 15 Pro Max với chip A17 Pro, camera 48MP...', 32990000, 50, 5, 'iphone15promax.jpg'),
                                                                                   ('iPhone 14 Pro', 'iPhone 14 Pro với Dynamic Island, chip A16 Bionic...', 27990000, 30, 6, 'iphone14pro.jpg'),
                                                                                   ('MacBook Air M2 13"', 'MacBook Air M2 với thiết kế siêu mỏng, chip M2...', 25990000, 20, 7, 'macbook_air_m2.jpg'),
                                                                                   ('MacBook Pro M3 16"', 'MacBook Pro M3 hiệu năng mạnh mẽ, màn hình Retina...', 49990000, 15, 8, 'macbookpro_m3.jpg'),
                                                                                   ('iPad Pro M2 12.9"', 'iPad Pro M2 với màn hình Liquid Retina XDR, bút Apple Pencil 2...', 28990000, 25, 9, 'ipadpro_m2.jpg'),
                                                                                   ('Apple Watch Series 9', 'Apple Watch Series 9 với cảm biến nhiệt độ, chip S9...', 11990000, 40, 10, 'applewatch_series9.jpg');

CREATE TABLE Orders (
                        order_id SERIAL PRIMARY KEY,
                        user_id INT,
                        total_price NUMERIC(10,2) CHECK (total_price >= 0),
                        status VARCHAR(20) CHECK (status IN ('pending', 'processing', 'shipped', 'delivered', 'cancelled')) DEFAULT 'pending',
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

INSERT INTO Orders (user_id, total_price, status) VALUES
                                                      (1, 32990000, 'pending'),
                                                      (2, 25990000, 'processing');


CREATE TABLE Order_Items (
                             order_item_id SERIAL PRIMARY KEY,
                             order_id INT,
                             product_id INT,
                             quantity INT CHECK (quantity > 0),
                             price NUMERIC(10,2) CHECK (price >= 0),
                             FOREIGN KEY (order_id) REFERENCES Orders(order_id) ON DELETE CASCADE,
                             FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE CASCADE
);

INSERT INTO Order_Items (order_id, product_id, quantity, price) VALUES
                                                                    (1, 1, 1, 32990000),
                                                                    (2, 3, 1, 25990000);


CREATE TABLE Reviews (
                         review_id SERIAL PRIMARY KEY,
                         user_id INT,
                         product_id INT,
                         rating INT CHECK (rating BETWEEN 1 AND 5),
                         comment TEXT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
                         FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE CASCADE
);

INSERT INTO Reviews (user_id, product_id, rating, comment) VALUES
                                                               (1, 1, 5, 'Sản phẩm rất tốt, pin trâu!'),
                                                               (2, 3, 4, 'MacBook Air M2 mỏng nhẹ, nhưng hơi nóng khi chạy nhiều ứng dụng.');


SELECT * FROM Users;
SELECT * FROM Categories;
SELECT * FROM Products;
SELECT * FROM Orders;
SELECT * FROM Order_Items;
SELECT * FROM Reviews;


SELECT
    p.name AS product_name,
    p.description,
    p.price,
    p.stock,
    p.image_url,
    c.name AS category_name,
    c.parent_id
FROM
    Products p
        JOIN
    Categories c ON p.category_id = c.category_id
;
