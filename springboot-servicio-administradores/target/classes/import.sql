insert into users(username, password, enabled)values('admin', '$2a$10$QBe7qO3H/a4P1OosngVkw.gxDw33LZbVUgySFievD478ri8DVoiNG', true)
insert into authorities(authority, enabled, user_id)values('ROLE_OWNER', true, 1);