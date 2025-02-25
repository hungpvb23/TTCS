-- Tạo bảng Categories (Loại sản phẩm, hỗ trợ quan hệ cha - con)
CREATE TABLE Categories (
                            category_id SERIAL PRIMARY KEY,
                            name VARCHAR(255) UNIQUE NOT NULL,
                            parent_id INT NULL,
                            FOREIGN KEY (parent_id) REFERENCES Categories(category_id) ON DELETE SET NULL
);

-- Tạo bảng Products (Sản phẩm chính, không lưu giá và kho hàng)
CREATE TABLE Products (
                          product_id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          image_url VARCHAR(255),
                          created_at TIMESTAMP DEFAULT NOW(),
                          category_id INT NOT NULL,
                          FOREIGN KEY (category_id) REFERENCES Categories(category_id) ON DELETE CASCADE
);

-- Tạo bảng Product_Variants (Biến thể sản phẩm theo màu sắc, giá, kho hàng)
CREATE TABLE Product_Variants (
                                 variant_id SERIAL PRIMARY KEY,
                                 product_id INT NOT NULL,
                                 color VARCHAR(100) NOT NULL,
                                 price DECIMAL(12,2) NOT NULL,
                                 stock INT NOT NULL,
                                 FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE CASCADE
);

-- Tạo bảng Orders (Đơn hàng)
CREATE TABLE orders (
                               order_id serial4 NOT NULL,
                               user_id int4 NOT NULL,  -- Tham chiếu đến bảng users
                               total_price numeric(12, 2) NOT NULL,
                               created_at timestamp DEFAULT now() NULL,
                               CONSTRAINT orders_pkey PRIMARY KEY (order_id),
                               CONSTRAINT orders_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON DELETE CASCADE
);

-- Tạo bảng OrderItems (Chi tiết đơn hàng, liên kết với biến thể sản phẩm)
CREATE TABLE OrderItems (
                            order_item_id SERIAL PRIMARY KEY,
                            order_id INT NOT NULL,
                            variant_id INT NOT NULL,
                            quantity INT NOT NULL CHECK (quantity > 0),
                            price DECIMAL(12,2) NOT NULL,
                            FOREIGN KEY (order_id) REFERENCES Orders(order_id) ON DELETE CASCADE,
                            FOREIGN KEY (variant_id) REFERENCES Product_Variants(variant_id) ON DELETE CASCADE
);

CREATE TABLE cart_items (
                            id SERIAL PRIMARY KEY,
                            user_id INT REFERENCES users(user_id) ON DELETE CASCADE,
                            variant_id INT REFERENCES product_variants(variant_id) ON DELETE CASCADE,
                            quantity INT NOT NULL CHECK (quantity > 0)
);

INSERT INTO Categories (name, parent_id) VALUES
                                             ('iPhone', NULL),
                                             ('MacBook', NULL),
                                             ('iPad', NULL),
                                             ('Apple Watch', NULL),
                                             ('iPhone 16', (SELECT category_id FROM Categories WHERE name = 'iPhone'));

INSERT INTO Products (name, description, image_url, category_id) VALUES
    ('iPhone 16 Pro Max', 'Mẫu iPhone mới nhất với nhiều nâng cấp', 'iphone16promax.jpg',
     (SELECT category_id FROM Categories WHERE name = 'iPhone 16'));

INSERT INTO Product_Variants (product_id, color, price, stock) VALUES
                                                                  ((SELECT product_id FROM Products WHERE name = 'iPhone 16 Pro Max'), 'Titan Đen', 35990000, 50),
                                                                  ((SELECT product_id FROM Products WHERE name = 'iPhone 16 Pro Max'), 'Titan Xanh', 36490000, 30),
                                                                  ((SELECT product_id FROM Products WHERE name = 'iPhone 16 Pro Max'), 'Titan Trắng', 35990000, 20);

INSERT INTO OrderItems (order_id, variant_id, quantity, price) VALUES
                                                                   ((SELECT order_id FROM Orders ORDER BY order_id DESC LIMIT 1),
                                                                   (SELECT variant_id FROM Product_Variants WHERE color = 'Titan Đen' LIMIT 1),
    2, 35990000);


SELECT p.name, pv.color, pv.price, pv.stock
FROM Products p
         JOIN Product_Variants pv ON p.product_id = pv.product_id
WHERE p.name = 'iPhone 16 Pro Max';