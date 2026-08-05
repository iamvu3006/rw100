-- 1. ACCOUNTS (Password: 123456)
INSERT INTO account (username, password, full_name, email, role, created_date)
SELECT 'admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Admin Hệ Thống', 'admin@restaurant.com', 'ADMIN', NOW()
WHERE NOT EXISTS (SELECT 1 FROM account WHERE username = 'admin');

INSERT INTO account (username, password, full_name, email, role, created_date)
SELECT 'staff01', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Nguyễn Văn Phục Vụ', 'staff01@restaurant.com', 'STAFF', NOW()
WHERE NOT EXISTS (SELECT 1 FROM account WHERE username = 'staff01');

-- 2. CATEGORIES
INSERT INTO category (name, description)
SELECT 'Món chính', 'Các món ăn chính: cơm, phở, bún, mì,...'
WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Món chính');

INSERT INTO category (name, description)
SELECT 'Khai vị', 'Các món khai vị, ăn nhẹ trước bữa chính'
WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Khai vị');

INSERT INTO category (name, description)
SELECT 'Đồ uống', 'Nước ngọt, nước ép, trà, cà phê,...'
WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Đồ uống');

INSERT INTO category (name, description)
SELECT 'Tráng miệng', 'Kem, bánh, chè, trái cây,...'
WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Tráng miệng');

-- 3. DISHES
INSERT INTO dish (name, price, description, status, category_id)
SELECT 'Cơm Gà Hải Nam', 89000, 'Cơm dẻo gà luộc mềm', 'AVAILABLE', (SELECT category_id FROM category WHERE name = 'Món chính')
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE name = 'Cơm Gà Hải Nam');

INSERT INTO dish (name, price, description, status, category_id)
SELECT 'Phở Bò Tái Chín', 75000, 'Phở bò truyền thống', 'AVAILABLE', (SELECT category_id FROM category WHERE name = 'Món chính')
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE name = 'Phở Bò Tái Chín');

INSERT INTO dish (name, price, description, status, category_id)
SELECT 'Trà Đào Cam Sả', 35000, 'Trà đào mát lạnh', 'AVAILABLE', (SELECT category_id FROM category WHERE name = 'Đồ uống')
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE name = 'Trà Đào Cam Sả');

-- 4. DINING TABLES
INSERT INTO dining_table (table_number, capacity, status)
SELECT 'B01', 4, 'EMPTY'
WHERE NOT EXISTS (SELECT 1 FROM dining_table WHERE table_number = 'B01');

INSERT INTO dining_table (table_number, capacity, status)
SELECT 'B02', 4, 'EMPTY'
WHERE NOT EXISTS (SELECT 1 FROM dining_table WHERE table_number = 'B02');