-- Предзаполнение ролей
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN') ON CONFLICT DO NOTHING;
INSERT INTO roles (id, name) VALUES (2, 'ROLE_TEACHER') ON CONFLICT DO NOTHING;
INSERT INTO roles (id, name) VALUES (3, 'ROLE_STUDENT') ON CONFLICT DO NOTHING;

-- Предзаполнение пользователей
INSERT INTO users (id, email, password, full_name) VALUES (1, 'teacher@lms.com', 'pass123', 'Иванов Иван (Преподаватель)') ON CONFLICT DO NOTHING;
INSERT INTO users (id, email, password, full_name) VALUES (2, 'student@lms.com', 'pass123', 'Петров Петр (Студент)') ON CONFLICT DO NOTHING;

-- Предзаполнение категорий
INSERT INTO categories (id, name, description) VALUES (1, 'Программирование', 'Курсы по разработке ПО') ON CONFLICT DO NOTHING;
INSERT INTO categories (id, name, description) VALUES (2, 'Дизайн', 'Курсы по веб и графическому дизайну') ON CONFLICT DO NOTHING;

-- Предзаполнение курса
INSERT INTO courses (id, title, description, teacher_id, category_id) VALUES (1, 'Java Spring Boot с нуля', 'Полный курс по Spring Boot 3 и PostgreSQL', 1, 1) ON CONFLICT DO NOTHING;

-- Предзаполнение модуля и урока
INSERT INTO modules (id, title, course_id) VALUES (1, 'Модуль 1. Основы Spring', 1) ON CONFLICT DO NOTHING;
INSERT INTO lessons (id, title, content, module_id) VALUES (1, 'Урок 1. Введение в Dependency Injection', 'Содержимое урока по DI и IoC Контейнеру', 1) ON CONFLICT DO NOTHING;