INSERT INTO users (email, password_hash, role)
VALUES (
        'admin@botica.com',
        '${ADMIN_PASSWORD_HASH}',
        'ADMIN'
       );