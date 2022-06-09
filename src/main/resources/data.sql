INSERT INTO role(id, name) VALUES(1, 'ROLE_SCRUM_MASTER');
INSERT INTO role(id, name) VALUES(2, 'ROLE_PRODUCT_OWNER');
INSERT INTO role(id, name) VALUES(3, 'ROLE_TEAM');

INSERT INTO user(id, username, password, enabled, role_id) VALUES(4, 'scrum.master', '$2a$10$KPEUbHsXovC2.fKsA0D1zuXpOiSvtuH4MSkA7cwUt0ql1eMWB2zCC', 1, 1);
INSERT INTO user(id, username, password, enabled, role_id) VALUES(5, 'product.owner', '$2a$10$DwKKKf2D1I4PX022b8tkyuaLKqoyxGCgouZAJNSj.JzXAzefnC.Ru', 1, 2);
INSERT INTO user(id, username, password, enabled, role_id) VALUES(6, 'team', '$2a$10$s94IKcZEVzTIabwJSo0gmulDs0YKBNzrCTqYkaEH2AmhPQhvP2GNm', 1, 3);