drop database if exists furniture3;
create database if not exists furniture3;
use furniture3;

create table if not exists suppliers
(
    id            int auto_increment primary key      not null,
    name          varchar(255)                        not null,
    contact_email varchar(255)                        null,
    contact_phone varchar(20)                         null,
    address       text                                null,
    country       varchar(100)                        null,
    website       varchar(255)                        null,
    is_active     tinyint   default 1                 null,
    created_at    timestamp default CURRENT_TIMESTAMP null,
    updated_at    timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    deleted_at    timestamp                           null
    );

INSERT INTO `suppliers`
VALUES (1, 'IKEA', 'info@ikea.com', '+46 771 123 456', 'Älmhult, Sweden', 'Thụy Điển', 'https://www.ikea.com', 1,
        '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (2, 'Wayfair', 'service@wayfair.com', '+1 877 929 3247', '4 Copley Place, Boston, MA, USA', 'Hoa Kỳ',
        'https://www.wayfair.com', 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (3, 'Ashley Furniture', 'support@ashleyfurniture.com', '+1 800 477 2222', 'One Ashley Way, Arcadia, WI, USA',
        'Hoa Kỳ', 'https://www.ashleyfurniture.com', 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (4, 'Herman Miller', 'info@hermanmiller.com', '+1 888 443 4357', 'Zeeland, Michigan, USA', 'Hoa Kỳ',
        'https://www.hermanmiller.com', 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (5, 'Steelcase', 'customercare@steelcase.com', '+1 800 333 9939', 'Grand Rapids, Michigan, USA', 'Hoa Kỳ',
        'https://www.steelcase.com', 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (6, 'Muji', 'info@muji.com', '+81 3 3989 4531', 'Tokyo, Japan', 'Japan', 'https://www.muji.com', 1,
        '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (7, 'Saint Louis', 'contact@saint-louis.com', '+33 3 87 06 40 50',
        'Rue Coëtlosquet, Saint-Louis-lès-Bitche, France', 'Pháp', 'https://www.saint-louis.com', 1,
        '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (8, 'Cire Trudon', 'info@ciretrudon.com', '+33 1 42 77 00 34', '78 Rue de Seine, Paris, France', 'Pháp',
        'https://www.ciretrudon.com', 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (9, 'Arcahorn', 'info@arcahorn.com', '+39 0733 616030', 'Via Velluti, 41, Recanati, Italy', 'Ý',
        'https://www.arcahorn.com', 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (10, 'Fürstenberg', 'info@fuerstenberg-porzellan.com', '+49 5271 401 0',
        'Meinbrexener Str. 2, Fürstenberg, Germany', 'Đức', 'https://www.fuerstenberg-porzellan.com', 1,
        '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (11, 'Riviere', 'info@rivieredecor.it', '+39 0322 938174', 'Via Sesiana 9, Gozzano, Italy', 'Ý',
        'https://www.rivieredecor.it', 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (12, 'Ralph Lauren', 'customer.service@ralphlauren.com', '+1 888 475 7674', '650 Madison Avenue, New York, USA',
        'Hoa Kỳ', 'https://www.ralphlauren.com', 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (13, 'Décor Walther', 'info@decor-walther.com', '+49 69 971 4700', 'Liebfrauenberg 37, Frankfurt, Germany',
        'Đức', 'https://www.decor-walther.com', 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL);

create table if not exists categories
(
    id         int auto_increment primary key,
    name       varchar(100)                        not null,
    is_active  tinyint   default 1                 null,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    updated_at timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    deleted_at timestamp                           null
    );
INSERT INTO `categories`
VALUES (1, 'Ghế', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (2, 'Bàn', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (3, 'Tủ', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (4, 'Chén', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (5, 'Dĩa', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (6, 'Giường', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (7, 'Kệ', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (8, 'Đèn', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (9, 'Sofa', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (10, 'Thảm', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (11, 'Gương', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (12, 'Phụ kiện trang trí', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL);


create table rooms
(
    id         int auto_increment
        primary key,
    name       varchar(100) charset utf8mb3         not null,
    is_active  tinyint(1) default 1                 null,
    created_at timestamp  default CURRENT_TIMESTAMP not null,
    updated_at timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    deleted_at timestamp                            null
);
INSERT INTO `rooms`
VALUES (1, 'Phòng ngủ', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (2, 'Phòng khách', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (3, 'Phòng bếp', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (4, 'Phòng tắm', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (5, 'Phòng làm việc', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (6, 'Phòng ăn', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (7, 'Phòng sách', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL);

create table roles
(
    id          int auto_increment
        primary key,
    name        varchar(50)                         not null,
    description varchar(255) charset utf8mb3        null,
    created_at  timestamp default CURRENT_TIMESTAMP null,
    updated_at  timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    deleted_at  timestamp                           null
);
INSERT INTO `roles`
VALUES (1, 'ADMIN', 'Quản trị viên hệ thống, có toàn quyền quyết định trong hệ thống', '2024-09-28 08:44:00',
        '2024-09-28 08:44:00', NULL),
       (2, 'MOD', 'Người điều hành nội dung', '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (3, 'USER', 'Người dùng thông thường', '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL);

CREATE TABLE `users`
(
    `id`             BIGINT  NOT NULL,
    `username`        varchar(255) NOT NULL,
    `email`           varchar(255) NOT NULL,
    `password`        varchar(255)                                                  DEFAULT NULL,
    `phone`           varchar(20)                                                   DEFAULT NULL,
    `full_name`       varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
    `oauth2_id`       varchar(255)                                                  DEFAULT NULL,
    `oauth2_provider` varchar(50)                                                   DEFAULT NULL,
    `otp`             varchar(6)                                                    DEFAULT NULL,
    `otp_expired`      timestamp    NULL                                             DEFAULT NULL,
    `role_id`         int          NOT NULL,
    `is_active`       tinyint(1)                                                    DEFAULT '0',
    `created_at`      timestamp    NULL                                             DEFAULT CURRENT_TIMESTAMP,
    `updated_at`      timestamp    NULL                                             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at`      timestamp    NULL                                             DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`),
    UNIQUE KEY `email` (`email`),
    UNIQUE KEY `oauth2_id` (`oauth2_id`),
    KEY `role_id` (`role_id`),
    CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
);

CREATE TABLE `products`
(
    `id`          int                                                           NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `description` text,
    `price`       decimal(18, 0)                                                     DEFAULT NULL,
    `stock`       int                                                                DEFAULT '0',
    `image`       varchar(255)                                                       DEFAULT NULL,
    `category_id` int                                                           NOT NULL,
    `supplier_id` int                                                           NOT NULL,
    `is_active`   tinyint(1)                                                         DEFAULT '1',
    `created_at`  timestamp                                                     NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`  timestamp                                                     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at`  timestamp                                                     NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_category` (`category_id`),
    KEY `supplier_id` (`supplier_id`),
    KEY `idx_name_price` (`name`, `price`),
    CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE,
    CONSTRAINT `products_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`)
);
INSERT INTO `products`
VALUES (1, 'Đèn Spotlight', NULL, 25500000, 12,
        'https://www.decor-walther.com/img/products/0219400/Studio-S_Perspektive-3_chrom.png', 6, 13, 1,
        '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (2, 'Hộp khăn giấy', NULL, 10000000, 5,
        'https://www.decor-walther.com/img/products/0830400/0830400_KB%2082_chrom-web.png', 10, 13, 1,
        '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (3, 'Đèn cầm tay mini vàng', NULL, 26118408, 5,
        'https://www.saint-louis.com/media/catalog/product/…ble_lamp_saint-louis_crystal_lighting_gold_or.jpg', 6, 7, 1,
        '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (4, 'Đĩa sứ họa tiết cánh hoa', NULL, 5058723, 8,
        '	https://www.fuerstenberg-porzellan.com/thumbnail/d…0919800/img1_fl2011325900_platzteller_600x600.png', 5,
        10, 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (5, 'Đĩa ăn Pasta đáy lõm', NULL, 1457132, 5,
        'https://www.fuerstenberg-porzellan.com/thumbnail/8a/a1/ca/1710921104/img1_tf_68328weiss_gourmetteller_tief_600x600.png',
        5, 10, 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (6, 'Gương trang điểm', NULL, 34472846, 2, 'https://www.decor-walther.com/img/products/0110900/0110900.png', 11,
        13, 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (7, 'Đèn treo tường Flannel xám', NULL, 33266604, 1,
        'https://www.saint-louis.com/media/catalog/product/cache/e1648193c4bd079fe5e6c9e530413384/1/0/10170716-1.jpg',
        6, 7, 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL);

CREATE TABLE `carts`
(
    `id`         varchar(12) NOT NULL,
    `user_id`    BIGINT NOT NULL,
    `quantity`   int              DEFAULT '0',
    `created_at` timestamp   NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp   NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp   NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`),
    CONSTRAINT `carts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `cart_items`
(
    `id`         char(36)    NOT NULL,
    `product_id` int         NOT NULL,
    `cart_id`    varchar(12) NOT NULL,
    `quantity`   int              DEFAULT '1',
    `created_at` timestamp   NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp   NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp   NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `product_id` (`product_id`),
    KEY `cart_id` (`cart_id`),
    CONSTRAINT `cart_items_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
    CONSTRAINT `cart_items_ibfk_2` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`)
);

CREATE TABLE `address` (
                           `id` INT AUTO_INCREMENT PRIMARY KEY,
                           `address_line` VARCHAR(255),
                           `ward` VARCHAR(100),
                           `district` VARCHAR(100),
                           `province` VARCHAR(100),
                           `country` VARCHAR(100),
                           `is_default` BOOLEAN,
                           `user_id` BIGINT NOT NULL,
                           CONSTRAINT `fk_user_address` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `orders`
(
    `id`           bigint                                                                  NOT NULL AUTO_INCREMENT,
    `user_id`      BIGINT                                                            NOT NULL,
    `total_amount` double                                                                  NOT NULL,
    `status`       enum ('Chờ xác nhận','Đã duyệt','Đang giao hàng','Hoàn thành','Đã hủy') NOT NULL DEFAULT 'Chờ xác nhận',
    `payment`      varchar(100)                                                            NOT NULL,
    `address_id`      int          NOT NULL,
    `created_at`   timestamp                                                               NULL     DEFAULT CURRENT_TIMESTAMP,
    `updated_at`   timestamp                                                               NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at`   timestamp                                                               NULL     DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
);

CREATE TABLE `order_items`
(
    `id`         bigint    NOT NULL AUTO_INCREMENT,
    `order_id`   bigint    NOT NULL,
    `product_id` int       NOT NULL,
    `quantity`   int       NOT NULL,
    `price`      double    NOT NULL,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_order_item_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE,
    CONSTRAINT `FK_order_item_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE
);



CREATE TABLE `promotions`
(
    `id`                  int         NOT NULL AUTO_INCREMENT,
    `code`                varchar(50) NOT NULL,
    `description`         text,
    `discount_percent`    double           DEFAULT '0',
    `max_discount_amount` double           DEFAULT '0',
    `start_date`          timestamp   NOT NULL,
    `end_date`            timestamp   NOT NULL,
    `usage_limit`         int              DEFAULT NULL,
    `is_active`           tinyint(1)       DEFAULT '1',
    `created_at`          timestamp   NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`          timestamp   NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at`          timestamp   NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `code` (`code`)
);

CREATE TABLE `promotion_orders`
(
    `id`           int    NOT NULL AUTO_INCREMENT,
    `promotion_id` int    NOT NULL,
    `order_id`     bigint NOT NULL,
    PRIMARY KEY (`id`),
    KEY `promotion_id` (`promotion_id`),
    KEY `order_id` (`order_id`),
    CONSTRAINT `promotion_orders_ibfk_1` FOREIGN KEY (`promotion_id`) REFERENCES `promotions` (`id`),
    CONSTRAINT `promotion_orders_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
);

CREATE TABLE `reviews`
(
    `id`         int         NOT NULL AUTO_INCREMENT,
    `product_id` int         NOT NULL,
    `user_id`    BIGINT NOT NULL,
    `rating`     tinyint     NOT NULL,
    `comment`    text,
    `created_at` timestamp   NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp   NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp   NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `product_id` (`product_id`),
    KEY `user_id` (`user_id`),
    CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
    CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `room_product`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `room_id`    int NOT NULL,
    `product_id` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_room` (`room_id`),
    KEY `fk_product` (`product_id`),
    CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_room` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`) ON DELETE CASCADE
);
INSERT INTO `room_product`
VALUES (1, 1, 1),
       (2, 2, 1),
       (3, 3, 1),
       (4, 4, 1),
       (5, 5, 1),
       (6, 6, 1),
       (7, 7, 1),
       (8, 1, 2),
       (9, 2, 2),
       (10, 3, 2),
       (11, 5, 2),
       (12, 6, 2),
       (13, 7, 2),
       (14, 1, 3),
       (15, 6, 4),
       (16, 3, 4),
       (17, 6, 5),
       (18, 3, 5),
       (19, 1, 6),
       (20, 1, 7),
       (21, 2, 7),
       (22, 3, 7),
       (23, 4, 7),
       (24, 5, 7),
       (25, 6, 7),
       (26, 7, 7);


CREATE TABLE `user_logs`
(
    `id`         int       NOT NULL AUTO_INCREMENT,
    `user_id`    BIGINT    DEFAULT NULL,
    `action`     varchar(255)   DEFAULT NULL,
    `message`    text,
    `log_level`  varchar(50)    DEFAULT NULL,
    `ip_address` varchar(50)    DEFAULT NULL,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`),
    CONSTRAINT `user_logs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `wishlists`
(
    `id`         int         NOT NULL AUTO_INCREMENT,
    `user_id`    BIGINT NOT NULL,
    `product_id` int         NOT NULL,
    `created_at` timestamp   NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp   NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp   NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_wishlist_user` (`user_id`),
    KEY `FK_wishlist_product` (`product_id`),
    CONSTRAINT `FK_wishlist_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE,
    CONSTRAINT `FK_wishlist_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
);

DELIMITER //

CREATE TRIGGER after_user_insert
    AFTER INSERT ON users
    FOR EACH ROW
BEGIN
    DECLARE new_cart_id VARCHAR(12);

    SELECT IFNULL(CONCAT('CART', LPAD(SUBSTRING(MAX(id), 5) + 1, 7, '0')), 'CART0000001')
    INTO new_cart_id
    FROM carts;
    INSERT INTO carts (id, user_id, created_at, updated_at)
    VALUES (new_cart_id, NEW.id, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
END; //

DELIMITER ;
