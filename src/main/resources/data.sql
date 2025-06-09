-- Insertar Roles
INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'SUPERVISOR');
INSERT INTO role (id, name) VALUES (3, 'LOGISTICS_STAFF');
INSERT INTO role (id, name) VALUES (4, 'WAREHOUSE_STAFF');
INSERT INTO role (id, name) VALUES (5, 'CUSTOMER');

-- Insertar AlertEntity Types
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
INSERT INTO users (name, email, created_at, role_id) VALUES
                                                        ('Santiago', 'santiago@example.com', NOW(), 1),
                                                        ('Mateo', 'mateo@example.com', NOW(), 2);

DELETE FROM role WHERE name IS NULL;

SELECT pg_get_serial_sequence('package_status', 'id');
ALTER SEQUENCE public.alert_id_seq RESTART WITH 1;
ALTER SEQUENCE public.alert_type_id_seq RESTART WITH 1;
ALTER SEQUENCE public.location_id_seq RESTART WITH 1;
ALTER SEQUENCE public.package_id_seq RESTART WITH 1;
ALTER SEQUENCE public.package_status_id_seq RESTART WITH 1;
ALTER SEQUENCE public.role_id_seq RESTART WITH 1;
ALTER SEQUENCE public.users_id_seq RESTART WITH 1;

SELECT * FROM role
RIGHT JOIN users ON role.id = users.role_id;

-- UUID Generator
SELECT gen_random_uuid();

SELECT * FROM package WHERE tracking_code = '3121bbc6-5f8b-42cc-9e87-3ed0c19c770f';

SHOW max_connections;



