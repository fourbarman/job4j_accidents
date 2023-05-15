INSERT INTO users(username, password, enabled, authority_id)
VALUES ('user', '$2a$10$Gfm777abOkm1iO5YrCPJAuo3sDvntwcLm6/CprL1g49erTSRUKnsW', true, (SELECT id FROM authorities WHERE authority = 'ROLE_USER')),
       ('admin', '$2a$10$Gfm777abOkm1iO5YrCPJAuo3sDvntwcLm6/CprL1g49erTSRUKnsW', true, (SELECT id FROM authorities WHERE authority = 'ROLE_ADMIN'));