INSERT INTO role(id, name) VALUES(1, 'ROLE_SCRUM_MASTER');
INSERT INTO role(id, name) VALUES(2, 'ROLE_PRODUCT_OWNER');
INSERT INTO role(id, name) VALUES(3, 'ROLE_TEAM');

INSERT INTO user(id, username, password, enabled, role_id) VALUES(4, 'scrum.master', '$2a$10$KPEUbHsXovC2.fKsA0D1zuXpOiSvtuH4MSkA7cwUt0ql1eMWB2zCC', 1, 1);
INSERT INTO user(id, username, password, enabled, role_id) VALUES(5, 'product.owner', '$2a$10$DwKKKf2D1I4PX022b8tkyuaLKqoyxGCgouZAJNSj.JzXAzefnC.Ru', 1, 2);
INSERT INTO user(id, username, password, enabled, role_id) VALUES(6, 'team', '$2a$10$s94IKcZEVzTIabwJSo0gmulDs0YKBNzrCTqYkaEH2AmhPQhvP2GNm', 1, 3);

INSERT INTO status(id, name) VALUES(7, "To do");
INSERT INTO status(id, name) VALUES(8, "In Progress");
INSERT INTO status(id, name) VALUES(9, "Done");

INSERT INTO priority(id, name) VALUES(10, "Low");
INSERT INTO priority(id, name) VALUES(11, "Normal");
INSERT INTO priority(id, name) VALUES(12, "High");

INSERT INTO backlog(id) VALUES(13);

INSERT INTO project(id, name, backlog_id) VALUES(14, "Project 1", 13);

INSERT INTO project_statuses(project_id, statuses_id) VALUES(14, 7);
INSERT INTO project_statuses(project_id, statuses_id) VALUES(14, 8);
INSERT INTO project_statuses(project_id, statuses_id) VALUES(14, 9);

INSERT INTO project_priorities(project_id, priorities_id) VALUES(14, 10);
INSERT INTO project_priorities(project_id, priorities_id) VALUES(14, 11);
INSERT INTO project_priorities(project_id, priorities_id) VALUES(14, 12);

INSERT INTO project_users(project_id, users_id) VALUES(14, 4);
INSERT INTO project_users(project_id, users_id) VALUES(14, 5);
INSERT INTO project_users(project_id, users_id) VALUES(14, 6);