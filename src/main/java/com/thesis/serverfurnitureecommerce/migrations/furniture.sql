CREATE TABLE IF NOT EXISTS suppliers (
                                         id SERIAL PRIMARY KEY,
                                         name VARCHAR(255) NOT NULL,
    contact_email VARCHAR(255),
    contact_phone VARCHAR(20),
    address TEXT,
    country VARCHAR(100),
    website VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
    );

INSERT INTO suppliers (
    id, name, contact_email, contact_phone, address, country, website, is_active,
    created_at, updated_at, deleted_at
)
VALUES
    (1, 'IKEA', 'info@ikea.com', '+46 771 123 456', 'Älmhult, Sweden', 'Thụy Điển', 'https://www.ikea.com', TRUE,
     '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
    (2, 'Wayfair', 'service@wayfair.com', '+1 877 929 3247', '4 Copley Place, Boston, MA, USA', 'Hoa Kỳ',
     'https://www.wayfair.com', TRUE, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
    (3, 'Ashley Furniture', 'support@ashleyfurniture.com', '+1 800 477 2222', 'One Ashley Way, Arcadia, WI, USA',
     'Hoa Kỳ', 'https://www.ashleyfurniture.com', TRUE, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
    (4, 'Herman Miller', 'info@hermanmiller.com', '+1 888 443 4357', 'Zeeland, Michigan, USA', 'Hoa Kỳ',
     'https://www.hermanmiller.com', TRUE, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
    (5, 'Steelcase', 'customercare@steelcase.com', '+1 800 333 9939', 'Grand Rapids, Michigan, USA', 'Hoa Kỳ',
     'https://www.steelcase.com', TRUE, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
    (6, 'Muji', 'info@muji.com', '+81 3 3989 4531', 'Tokyo, Japan', 'Japan', 'https://www.muji.com', TRUE,
     '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
    (7, 'Saint Louis', 'contact@saint-louis.com', '+33 3 87 06 40 50',
     'Rue Coëtlosquet, Saint-Louis-lès-Bitche, France', 'Pháp', 'https://www.saint-louis.com', TRUE,
     '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
    (8, 'Cire Trudon', 'info@ciretrudon.com', '+33 1 42 77 00 34', '78 Rue de Seine, Paris, France', 'Pháp',
     'https://www.ciretrudon.com', TRUE, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
    (9, 'Arcahorn', 'info@arcahorn.com', '+39 0733 616030', 'Via Velluti, 41, Recanati, Italy', 'Ý',
     'https://www.arcahorn.com', TRUE, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
    (10, 'Fürstenberg', 'info@fuerstenberg-porzellan.com', '+49 5271 401 0',
     'Meinbrexener Str. 2, Fürstenberg, Germany', 'Đức', 'https://www.fuerstenberg-porzellan.com', TRUE,
     '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
    (11, 'Riviere', 'info@rivieredecor.it', '+39 0322 938174', 'Via Sesiana 9, Gozzano, Italy', 'Ý',
     'https://www.rivieredecor.it', TRUE, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
    (12, 'Ralph Lauren', 'customer.service@ralphlauren.com', '+1 888 475 7674', '650 Madison Avenue, New York, USA',
     'Hoa Kỳ', 'https://www.ralphlauren.com', TRUE, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
    (13, 'Décor Walther', 'info@decor-walther.com', '+49 69 971 4700', 'Liebfrauenberg 37, Frankfurt, Germany',
     'Đức', 'https://www.decor-walther.com', TRUE, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL);


CREATE TABLE IF NOT EXISTS categories (
                                          id SERIAL PRIMARY KEY,
                                          name VARCHAR(100) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP
    );

INSERT INTO categories (
    id, name, is_active, created_at, updated_at, deleted_at
)
VALUES
    (1, 'Ghế', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
    (2, 'Bàn', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
    (3, 'Tủ', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
    (4, 'Chén', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
    (5, 'Dĩa', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
    (6, 'Giường', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
    (7, 'Kệ', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
    (8, 'Đèn', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
    (9, 'Sofa', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
    (10, 'Thảm', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
    (11, 'Gương', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
    (12, 'Phụ kiện trang trí', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL);

CREATE OR REPLACE FUNCTION update_updated_at_column()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_updated_at
    BEFORE UPDATE ON categories
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TABLE IF NOT EXISTS rooms (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP
    );
INSERT INTO rooms (
    id, name, is_active, created_at, updated_at, deleted_at
)
VALUES
    (1, 'Phòng ngủ', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
    (2, 'Phòng khách', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
    (3, 'Phòng bếp', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
    (4, 'Phòng tắm', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
    (5, 'Phòng làm việc', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
    (6, 'Phòng ăn', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
    (7, 'Phòng sách', TRUE, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL);

CREATE TABLE IF NOT EXISTS roles (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
    );
INSERT INTO roles (name, description)
VALUES
    ('ADMIN', 'Quản trị viên hệ thống, có toàn quyền quyết định trong hệ thống'),
    ('MOD', 'Người điều hành nội dung'),
    ('USER', 'Người dùng thông thường');


CREATE TABLE IF NOT EXISTS users (
                                     id BIGSERIAL PRIMARY KEY,
                                     username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255),
    phone VARCHAR(20),
    full_name VARCHAR(255),
    oauth2_id VARCHAR(255) UNIQUE,
    oauth2_provider VARCHAR(50),
    otp VARCHAR(6),
    otp_expired TIMESTAMP,
    role_id INTEGER NOT NULL,
    is_active BOOLEAN DEFAULT FALSE,
    is_locked BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,

    CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES roles(id)
    );


CREATE TABLE IF NOT EXISTS products (
                                        id SERIAL PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
    description TEXT,
    price NUMERIC(18, 0),
    stock INTEGER DEFAULT 0,
    category_id INTEGER NOT NULL,
    supplier_id INTEGER NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,

    CONSTRAINT fk_products_category FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE,
    CONSTRAINT fk_products_supplier FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
    );
CREATE INDEX idx_products_category_id ON products(category_id);
CREATE INDEX idx_products_supplier_id ON products(supplier_id);
CREATE INDEX idx_products_name_price ON products(name, price);

INSERT INTO products (name, description, price, stock, category_id, supplier_id)
VALUES
    ('Đèn Spotlight', 'Đèn chiếu sáng với thiết kế hiện đại.', 25500000, 12, 8, 1),
    ('Hộp khăn giấy', 'Hộp đựng khăn giấy sang trọng.', 10000000, 5, 12, 6),
    ('Đèn cầm tay mini vàng', 'Đèn cầm tay nhỏ gọn, dễ dàng mang theo.', 26118408, 5, 8, 4),
    ('Đĩa sứ họa tiết cánh hoa', 'Đĩa sứ tinh tế với họa tiết đẹp mắt.', 5058723, 8, 5, 3),
    ('Đĩa ăn Pasta đáy lõm', 'Đĩa ăn cho món pasta với thiết kế đáy lõm.', 1457132, 5, 5, 5),
    ('Gương trang điểm', 'Gương trang điểm với thiết kế sang trọng.', 34472846, 2, 11, 11),
    ('Đèn treo tường Flannel xám', 'Đèn treo tường với phong cách Flannel.', 33266604, 1, 8, 12),
    ('Đèn trần - Ánh sáng trắng hiện đại', 'Đèn trần cung cấp ánh sáng trắng rực rỡ, phù hợp cho phòng khách và văn phòng.', 72836284, 23, 8, 13),
    ('Đèn spotlight - Chiếu sáng điểm nhấn', 'Đèn spotlight mạnh mẽ, lý tưởng để chiếu sáng các khu vực cụ thể hoặc làm nổi bật các chi tiết nội thất.', 35246273, 10, 8, 13),
    ('Đèn trần - Hiện đại', 'Đèn trần hiện đại sử dụng công nghệ LED tiết kiệm năng lượng.', 253647254, 12, 8, 13),
    ('Đèn trần và đèn tường', 'Đèn có thể lắp trên trần và tường, thích hợp cho không gian linh hoạt.', 153738463, 13, 8, 13),
    ('Đèn trần - Cổ điển', 'Đèn trần với thiết kế cổ điển, tạo ánh sáng ấm áp.', 47283647, 16, 8, 13),
    ('Đèn trần - Tối giản', 'Đèn trần với thiết kế tối giản, phù hợp cho nội thất hiện đại.', 15263745, 5, 8, 13),
    ('Đèn trần - Sang trọng', 'Thiết kế sang trọng, hoàn hảo cho không gian cao cấp.', 15264728, 7, 8, 13),
    ('Đèn trần - Phong cách công nghiệp', 'Đèn trần phong cách công nghiệp, mạnh mẽ và độc đáo.', 15362746, 8, 8, 13),
    ('Đèn trần - Tiết kiệm năng lượng', 'Sử dụng công nghệ tiết kiệm năng lượng, thích hợp cho phòng khách và phòng ngủ.', 25342765, 13, 8, 13),
    ('Đèn trần - Ánh sáng vàng ấm', 'Đèn trần cung cấp ánh sáng vàng ấm, phù hợp cho không gian thư giãn.', 13443245, 6, 8, 13),
    ('Clip-on light for mirror', NULL, 23456534, 4, 8, 13),
    ('Clip-on light for mirror', NULL, 23456534, 4, 8, 13),
    ('Clip-on light for mirror', NULL, 23456534, 4, 8, 13),
    ('Clip-on light for mirror', NULL, 23456534, 4, 8, 13),
    ('Clip-on light for mirror', NULL, 23456534, 4, 8, 13),
    ('Clip-on light for mirror', NULL, 23456534, 4, 8, 13),
    ('Clip-on light for mirror', NULL, 23456534, 4, 8, 13),
    ('Clip-on light for mirror', NULL, 23456534, 4, 8, 13),
    ('Clip-on light for mirror', NULL, 23456534, 4, 8, 13),
    ('Clip-on light for mirror', NULL, 23456534, 4, 8, 13);


CREATE TABLE IF NOT EXISTS images (
                                      id SERIAL PRIMARY KEY,
                                      product_id INTEGER NOT NULL,
                                      image_url VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,

    CONSTRAINT fk_product FOREIGN KEY (product_id)
    REFERENCES products(id)
    ON DELETE CASCADE
    );
INSERT INTO images (product_id, image_url)
VALUES
    (1, 'https://www.decor-walther.com/img/products/0219400/Studio-S_Perspektive-3_chrom.png'),
    (2, 'https://www.decor-walther.com/img/products/0830400/0830400_KB%2082_chrom-web.png'),
    (3, 'https://www.saint-louis.com/media/catalog/product/…ble_lamp_saint-louis_crystal_lighting_gold_or.jpg'),
    (4, 'https://www.fuerstenberg-porzellan.com/thumbnail/d…0919800/img1_fl2011325900_platzteller_600x600.png'),
    (5, 'https://www.fuerstenberg-porzellan.com/thumbnail/8a/a1/ca/1710921104/img1_tf_68328weiss_gourmetteller_tief_600x600.png'),
    (6, 'https://www.decor-walther.com/img/products/0110900/0110900.png'),
    (7, 'https://www.saint-louis.com/media/catalog/product/cache/e1648193c4bd079fe5e6c9e530413384/1/0/10170716-1.jpg'),
    (8, 'https://www.decor-walther.com/img/products/0213100/0213100_GLOBE%2020_Chrom_web.png'),
    (9, 'https://www.decor-walther.com/img/products/0219500/Studio-L_Perspektive-1_chrom.png'),
    (10, 'https://www.decor-walther.com/img/products/0218800/0218800_CUT%2030%20N%20LED_Chrom.png'),
    (11, 'https://www.decor-walther.com/img/products/0219300/0219300_BAUHAUS%203%20N%20LED_Chrom.png'),
    (12, 'https://www.decor-walther.com/img/products/0219100/0219100_CONECT%2026%20N%20LED_Chrom.png'),
    (13, 'https://www.decor-walther.com/img/products/0219200/0219200.png'),
    (14, 'https://www.decor-walther.com/img/products/0218700/0218700_CUT%2018%20N%20LED_Chrom.png'),
    (15, 'https://www.decor-walther.com/img/products/0219000/0216000%20GLOW%2028%20LED_1.png'),
    (16, 'https://www.decor-walther.com/img/products/0218900/0218900_CUT%2040%20N%20LED_Chrom.png'),
    (17, 'https://www.decor-walther.com/img/products/0333900/0333900_BAUHAUS%201%20N%20LED_Chrom.png'),
    (18, 'https://www.decor-walther.com/img/products/0411000/0411000_BOX%201-15_Chrom.png'),
    (19, 'https://www.decor-walther.com/img/products/0411200/0411200_BOX%201-60_Chrom.png'),
    (20, 'https://www.decor-walther.com/img/products/0413700/0413700_BOX%201-25_Chrom.png'),
    (21, 'https://www.decor-walther.com/img/products/0418700/0418700_FLAT%201%20LED_Chrom.png'),
    (22, 'https://www.decor-walther.com/img/products/0411100/0411100_BOX%201-40_Chrom.png'),
    (23, 'https://www.decor-walther.com/img/products/0409300/0409300_BOX%201-10_Chrom.png'),
    (24, 'https://www.decor-walther.com/img/products/0402200/0402200_OMEGA%201_Chrom.png'),
    (25, 'https://www.decor-walther.com/img/products/0420300/0420300_BOX%201-40%20N%20LED_Chrom.png'),
    (26, 'https://www.decor-walther.com/img/products/0420200/0420200_BOX%201-25%20N%20LED_chrome.png'),
    (27, 'https://www.decor-walther.com/img/products/0420100/0420100_BOX%201-15%20N%20LED_Chrom.png');

CREATE TABLE IF NOT EXISTS carts (
                                     id VARCHAR(12) PRIMARY KEY,
    user_id BIGINT NOT NULL,
    quantity INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,

    CONSTRAINT fk_carts_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    );


CREATE TABLE IF NOT EXISTS cart_items (
                                          id UUID PRIMARY KEY,
                                          product_id INTEGER NOT NULL,
                                          cart_id VARCHAR(12) NOT NULL,
    quantity INTEGER DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,

    CONSTRAINT fk_cart_items_product FOREIGN KEY (product_id) REFERENCES products(id),
    CONSTRAINT fk_cart_items_cart FOREIGN KEY (cart_id) REFERENCES carts(id)
    );

CREATE TABLE IF NOT EXISTS address (
                                       id SERIAL PRIMARY KEY,
                                       address_line VARCHAR(255),
    ward VARCHAR(100),
    district VARCHAR(100),
    province VARCHAR(100),
    country VARCHAR(100),
    is_default BOOLEAN,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,

    CONSTRAINT fk_user_address FOREIGN KEY (user_id)
    REFERENCES users(id) ON DELETE CASCADE
    );
DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'order_status') THEN
CREATE TYPE order_status AS ENUM ('Chờ xác nhận', 'Đã duyệt', 'Đang giao hàng', 'Hoàn thành', 'Đã hủy');
END IF;
END$$;


CREATE TABLE IF NOT EXISTS orders (
                                      id BIGSERIAL PRIMARY KEY,
                                      user_id BIGINT NOT NULL,
                                      total_amount DOUBLE PRECISION NOT NULL,
                                      status order_status NOT NULL DEFAULT 'Chờ xác nhận',
                                      payment VARCHAR(100) NOT NULL,
    address_id INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,

    CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_orders_address FOREIGN KEY (address_id) REFERENCES address(id)
    );


CREATE TABLE IF NOT EXISTS order_items (
                                           id BIGSERIAL PRIMARY KEY,
                                           order_id BIGINT NOT NULL,
                                           product_id INTEGER NOT NULL,
                                           quantity INTEGER NOT NULL,
                                           price DOUBLE PRECISION NOT NULL,
                                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                           deleted_at TIMESTAMP,

                                           CONSTRAINT fk_order_items_order FOREIGN KEY (order_id)
    REFERENCES orders(id) ON DELETE CASCADE,
    CONSTRAINT fk_order_items_product FOREIGN KEY (product_id)
    REFERENCES products(id) ON DELETE CASCADE
    );



CREATE TABLE IF NOT EXISTS promotions (
                                          id SERIAL PRIMARY KEY,
                                          code VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    discount_percent DOUBLE PRECISION DEFAULT 0,
    max_discount_amount DOUBLE PRECISION DEFAULT 0,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    usage_limit INTEGER DEFAULT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS promotion_orders (
                                                id SERIAL PRIMARY KEY,
                                                promotion_id INTEGER NOT NULL,
                                                order_id BIGINT NOT NULL,

                                                CONSTRAINT fk_promotion_orders_promotion
                                                FOREIGN KEY (promotion_id) REFERENCES promotions(id),
    CONSTRAINT fk_promotion_orders_order
    FOREIGN KEY (order_id) REFERENCES orders(id)
    );

CREATE TABLE IF NOT EXISTS promotion_order_items (
                                                     id SERIAL PRIMARY KEY,
                                                     promotion_id INTEGER NOT NULL,
                                                     order_item_id BIGINT NOT NULL,

                                                     CONSTRAINT fk_promotion_order_items_promotion
                                                     FOREIGN KEY (promotion_id) REFERENCES promotions(id),
    CONSTRAINT fk_promotion_order_items_order_item
    FOREIGN KEY (order_item_id) REFERENCES order_items(id)
    );



CREATE TABLE IF NOT EXISTS reviews (
                                       id SERIAL PRIMARY KEY,
                                       product_id INTEGER NOT NULL,
                                       user_id BIGINT NOT NULL,
                                       rating SMALLINT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    likes INTEGER NOT NULL DEFAULT 0,
    comment TEXT,
    reviews_parent_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,

    CONSTRAINT fk_reviews_product FOREIGN KEY (product_id) REFERENCES products(id),
    CONSTRAINT fk_reviews_user FOREIGN KEY (user_id) REFERENCES users(id)
    );
CREATE TABLE IF NOT EXISTS room_products (
                                             id SERIAL PRIMARY KEY,
                                             room_id INTEGER NOT NULL,
                                             product_id INTEGER NOT NULL,

                                             CONSTRAINT fk_room_product_room
                                             FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE,
    CONSTRAINT fk_room_product_product
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
    );
CREATE INDEX idx_room_products_room_id ON room_products(room_id);
CREATE INDEX idx_room_products_product_id ON room_products(product_id);
INSERT INTO room_products (id, room_id, product_id) VALUES
                                                        (1, 1, 1), (2, 2, 1), (3, 3, 1), (4, 4, 1), (5, 5, 1), (6, 6, 1), (7, 7, 1),
                                                        (8, 1, 2), (9, 2, 2), (10, 3, 2), (11, 5, 2), (12, 6, 2), (13, 7, 2),
                                                        (14, 1, 3), (15, 6, 4), (16, 3, 4), (17, 6, 5), (18, 3, 5),
                                                        (19, 1, 6), (20, 1, 7), (21, 2, 7), (22, 3, 7), (23, 4, 7),
                                                        (24, 5, 7), (25, 6, 7), (26, 7, 7);


CREATE TABLE IF NOT EXISTS user_logs (
                                         id SERIAL PRIMARY KEY,
                                         user_id BIGINT REFERENCES users(id),
    action VARCHAR(255),
    message TEXT,
    log_level VARCHAR(50),
    ip_address VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
    );
CREATE INDEX idx_user_logs_user_id ON user_logs(user_id);

CREATE TABLE IF NOT EXISTS wishlists (
                                         id SERIAL PRIMARY KEY,
                                         user_id BIGINT NOT NULL,
                                         product_id INT NOT NULL,
                                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                         deleted_at TIMESTAMP,

                                         CONSTRAINT fk_wishlist_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_wishlist_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
    );
CREATE INDEX idx_wishlists_user_id ON wishlists(user_id);
CREATE INDEX idx_wishlists_product_id ON wishlists(product_id);

CREATE TABLE IF NOT EXISTS support_customers (
                                                 id SERIAL PRIMARY KEY,
                                                 email VARCHAR(100) NOT NULL,
    title TEXT NOT NULL,
    message TEXT,
    feedback TEXT,
    is_solve BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
    );
CREATE TABLE IF NOT EXISTS policies (
                                        id SERIAL PRIMARY KEY,
                                        title VARCHAR(255) NOT NULL,
    content JSON,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
    );
INSERT INTO policies (title, content) VALUES
                                          (
                                              'Chính sách bảo hành',
                                              '{
                                                "warranty_policy": {
                                                  "warranty_period": "12 tháng",
                                                  "conditions": {
                                                    "eligible": [
                                                      "Sản phẩm bị lỗi kỹ thuật do nhà sản xuất",
                                                      "Sản phẩm không có dấu hiệu sửa chữa hoặc can thiệp từ bên thứ ba",
                                                      "Cung cấp phiếu bảo hành và hóa đơn mua hàng"
                                                    ],
                                                    "ineligible": [
                                                      "Hư hỏng do sử dụng sai hướng dẫn",
                                                      "Hư hỏng do tác động môi trường bên ngoài (nước, nhiệt độ cao)",
                                                      "Thiên tai, hỏa hoạn, lũ lụt"
                                                    ]
                                                  },
                                                  "process": [
                                                    "Liên hệ bộ phận chăm sóc khách hàng qua hotline hoặc email",
                                                    "Hướng dẫn gửi sản phẩm hoặc kiểm tra tận nơi",
                                                    "Thời gian xử lý bảo hành trong vòng 7-15 ngày làm việc",
                                                    "Trả sản phẩm và cung cấp thông tin sau bảo hành"
                                                  ]
                                                }
                                              }'
                                          ),
                                          (
                                              'Chính sách đổi trả sản phẩm',
                                              '{
                                                "return_policy": {
                                                  "return_period": "15 ngày",
                                                  "conditions": {
                                                    "eligible": [
                                                      "Sản phẩm còn nguyên tem, bao bì và không có dấu hiệu sử dụng",
                                                      "Có hóa đơn mua hàng và phiếu bảo hành đi kèm",
                                                      "Lỗi kỹ thuật do nhà sản xuất hoặc hư hỏng trong quá trình vận chuyển"
                                                    ],
                                                    "ineligible": [
                                                      "Sản phẩm đã qua sử dụng hoặc không còn nguyên trạng",
                                                      "Không có hóa đơn mua hàng hoặc phiếu bảo hành",
                                                      "Sản phẩm giảm giá hoặc khuyến mãi không áp dụng đổi trả"
                                                    ]
                                                  },
                                                  "process": [
                                                    "Liên hệ bộ phận chăm sóc khách hàng để yêu cầu đổi trả",
                                                    "Xác nhận điều kiện sản phẩm và chuẩn bị hồ sơ cần thiết",
                                                    "Gửi sản phẩm về trung tâm đổi trả hoặc cửa hàng gần nhất",
                                                    "Xử lý và hoàn tất đổi trả trong 7 ngày làm việc"
                                                  ]
                                                }
                                              }'
                                          ),
                                          (
                                              'Chính sách vận chuyển và giao hàng',
                                              '{
                                                "shipping_policy": {
                                                  "shipping_time": {
                                                    "standard": "3-5 ngày làm việc",
                                                    "express": "1-2 ngày làm việc"
                                                  },
                                                  "costs": {
                                                    "standard": "Miễn phí với đơn hàng trên 1 triệu VNĐ",
                                                    "express": "Phí 50.000 VNĐ"
                                                  },
                                                  "process": [
                                                    "Xác nhận đơn hàng và chuẩn bị sản phẩm trong vòng 24 giờ",
                                                    "Thông báo thời gian giao hàng và mã vận đơn qua email",
                                                    "Theo dõi trạng thái đơn hàng qua hệ thống hoặc trang web đối tác vận chuyển",
                                                    "Nhận hàng và kiểm tra tình trạng sản phẩm khi giao"
                                                  ],
                                                  "notes": [
                                                    "Khách hàng kiểm tra kỹ sản phẩm khi nhận hàng để tránh các trường hợp hư hỏng do vận chuyển",
                                                    "Nếu sản phẩm có dấu hiệu hư hỏng, vui lòng từ chối nhận hàng và báo lại ngay cho chúng tôi"
                                                  ]
                                                }
                                              }'
                                          ),
                                          (
                                              'Chính sách thanh toán',
                                              '{
                                                "payment_policy": {
                                                  "methods": [
                                                    "Thanh toán qua thẻ tín dụng/thẻ ghi nợ",
                                                    "Chuyển khoản ngân hàng",
                                                    "Thanh toán khi nhận hàng (COD)"
                                                  ],
                                                  "guidelines": {
                                                    "secure_payment": "Cam kết bảo mật thông tin thanh toán của khách hàng.",
                                                    "payment_confirmation": "Xác nhận thanh toán sẽ được gửi qua email hoặc SMS."
                                                  },
                                                  "refund_process": [
                                                    "Nếu khách hàng hủy đơn hàng trước khi giao, tiền sẽ được hoàn lại trong vòng 5-7 ngày làm việc.",
                                                    "Đối với các đơn hàng đã nhận, hoàn tiền sẽ tuân theo chính sách đổi trả sản phẩm."
                                                  ]
                                                }
                                              }'
                                          );

CREATE TABLE IF NOT EXISTS faqs (
                                    id SERIAL PRIMARY KEY,
                                    question TEXT NOT NULL,
                                    answer TEXT NOT NULL,
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    deleted_at TIMESTAMP
);


INSERT INTO faqs (question, answer) VALUES
                                        ('Thời gian giao hàng là bao lâu?',
                                         'Thời gian giao hàng từ 3 đến 7 ngày làm việc, tùy thuộc vào địa điểm của bạn.'),
                                        ('Tôi có thể thay đổi địa chỉ giao hàng không?',
                                         'Có, bạn có thể thay đổi địa chỉ giao hàng trước khi đơn hàng được xử lý. Hãy liên hệ với chúng tôi để được hỗ trợ.'),
                                        ('Sản phẩm có được bảo hành không?',
                                         'Có, tất cả các sản phẩm đều được bảo hành theo chính sách bảo hành của chúng tôi.'),
                                        ('Tôi có thể trả lại sản phẩm nếu không hài lòng?',
                                         'Có, bạn có thể trả lại sản phẩm trong vòng 30 ngày kể từ ngày nhận hàng nếu sản phẩm còn mới và chưa sử dụng.'),
                                        ('Có chương trình khuyến mãi nào không?',
                                         'Chúng tôi thường xuyên có các chương trình khuyến mãi. Bạn hãy theo dõi trang web hoặc đăng ký nhận bản tin để nhận thông tin mới nhất.'),
                                        ('Có thể thanh toán bằng hình thức nào?',
                                         'Chúng tôi chấp nhận nhiều hình thức thanh toán, bao gồm thẻ tín dụng, thẻ ghi nợ, và chuyển khoản ngân hàng.'),
                                        ('Sản phẩm có thể tùy chỉnh không?',
                                         'Có, một số sản phẩm của chúng tôi có thể được tùy chỉnh theo yêu cầu của khách hàng. Vui lòng liên hệ với chúng tôi để biết thêm chi tiết.'),
                                        ('Tôi có thể nhận được hỗ trợ lắp đặt không?',
                                         'Chúng tôi cung cấp dịch vụ hỗ trợ lắp đặt cho một số sản phẩm. Bạn có thể chọn dịch vụ này khi đặt hàng.'),
                                        ('Chính sách bảo mật thông tin cá nhân của bạn như thế nào?',
                                         'Chúng tôi cam kết bảo mật thông tin cá nhân của khách hàng và chỉ sử dụng thông tin này cho mục đích xử lý đơn hàng và cung cấp dịch vụ.'),
                                        ('Tôi có thể liên hệ với bộ phận hỗ trợ khách hàng bằng cách nào?',
                                         'Bạn có thể liên hệ với bộ phận hỗ trợ khách hàng qua số điện thoại, email, hoặc chat trực tiếp trên website. Chúng tôi luôn sẵn sàng hỗ trợ bạn.');



CREATE TABLE IF NOT EXISTS invalidated_tokens (
                                                  token_id VARCHAR(255) PRIMARY KEY,
    expired TIMESTAMP
    );
CREATE TABLE IF NOT EXISTS refresh_tokens (
                                              token_id VARCHAR(50) PRIMARY KEY,
    expired TIMESTAMP,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_refresh_tokens_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    );


CREATE OR REPLACE FUNCTION create_cart_after_user_insert()
    RETURNS TRIGGER AS $$
DECLARE
new_cart_id VARCHAR(12);
    max_num     INT;
BEGIN
SELECT COALESCE(MAX(CAST(SUBSTRING(id FROM 5) AS INTEGER)), 0)
INTO max_num
FROM carts
WHERE id LIKE 'CART%';

new_cart_id := CONCAT('CART', LPAD((max_num + 1)::text, 7, '0'));

INSERT INTO carts (id, user_id, created_at, updated_at)
VALUES (new_cart_id, NEW.id, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_user_insert
    AFTER INSERT ON users
    FOR EACH ROW
    EXECUTE FUNCTION create_cart_after_user_insert();
