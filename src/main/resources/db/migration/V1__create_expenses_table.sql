CREATE TABLE expenses (
                          id SERIAL PRIMARY KEY,
                          category VARCHAR(100) NOT NULL,
                          amount NUMERIC(12, 2) NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);