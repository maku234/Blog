DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;
CREATE TABLE users (user_id int(11) NOT NULL AUTO_INCREMENT, username varchar(45) NOT NULL, password varchar(45) NOT NULL, enabled tinyint(1) NOT NULL, PRIMARY KEY (user_id));
CREATE TABLE user_roles (user_role_id int(11) NOT NULL AUTO_INCREMENT, user_id int(11) NOT NULL, authority varchar(45) NOT NULL, PRIMARY KEY (user_role_id), KEY FK_user_roles (user_id), CONSTRAINT FK_user_roles FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION);
INSERT INTO users (username, password, enabled) VALUES ('admin', '098f6bcd4621d373cade4e832627b4f6', 1);
INSERT INTO user_roles (user_id, authority ) VALUES (1, 'ROLE_ADMIN');