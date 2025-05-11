-- Insertar Roles
INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'SUPERVISOR');
INSERT INTO role (id, name) VALUES (3, 'LOGISTICS_STAFF');
INSERT INTO role (id, name) VALUES (4, 'WAREHOUSE_STAFF');
INSERT INTO role (id, name) VALUES (5, 'CUSTOMER');

-- Insertar Alert Types
INSERT INTO alert_type (id, name) VALUES (1, 'LOST');
INSERT INTO alert_type (id, name) VALUES (2, 'DAMAGED');
INSERT INTO alert_type (id, name) VALUES (3, 'DELAYED');
INSERT INTO alert_type (id, name) VALUES (4, 'OTHER');

-- Insertar Package Status
INSERT INTO package_status (id, name) VALUES (1, 'PENDING');
INSERT INTO package_status (id, name) VALUES (2, 'IN_TRANSIT');
INSERT INTO package_status (id, name) VALUES (3, 'DELIVERED');
INSERT INTO package_status (id, name) VALUES (4, 'CANCELLED');
INSERT INTO package_status (id, name) VALUES (5, 'RETURNED');

-- Insertar Usuarios
INSERT INTO systemuser (name, email, created_at, role_id) VALUES
                                                        ('Santiago', 'santiago@example.com', NOW(), 1),
                                                        ('Mateo', 'mateo@example.com', NOW(), 2),
                                                        ('Charlie', 'charlie@example.com', NOW(), 3),
                                                        ('Alice', 'alice@example.com', NOW(), 4),
                                                        ('Bob', 'bob@example.com', NOW(), 5);
