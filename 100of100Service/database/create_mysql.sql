DROP DATABASE IF EXISTS organization_management;

CREATE DATABASE organization_management;
USE organization_management;

DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;
DROP TABLE IF EXISTS organizations;

CREATE TABLE organizations (
    organization_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    details JSON
);

CREATE TABLE departments (
    department_id INT PRIMARY KEY AUTO_INCREMENT,
    organization_id INT,
    name VARCHAR(255) NOT NULL,
    head_employee_id INT,
    FOREIGN KEY (organization_id) REFERENCES organizations(organization_id)
);

CREATE TABLE employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    organization_id INT,
    department_id INT,
    name VARCHAR(255) NOT NULL,
    position VARCHAR(100),
    hire_date DATE,
    salary DECIMAL(10, 2),
    contact_info JSON,
    FOREIGN KEY (organization_id) REFERENCES organizations(organization_id),
    FOREIGN KEY (department_id) REFERENCES departments(department_id)
);

-- Add foreign key constraint for department head after employees table is created
ALTER TABLE departments
ADD CONSTRAINT fk_department_head
FOREIGN KEY (head_employee_id) REFERENCES employees(employee_id);

INSERT INTO organizations (name, details) VALUES
('Acme Corp', '{"founded": "1990-01-01", "industry": "Technology"}');

INSERT INTO departments (organization_id, name) VALUES
(1, 'Engineering'),
(1, 'Marketing');

INSERT INTO employees (organization_id, department_id, name, position, hire_date, salary, contact_info) VALUES
(1, 1, 'John Doe', 'Software Engineer', '2020-01-15', 75000.00, '{"email": "john.doe@acme.com", "phone": "123-456-7890"}'),
(1, 2, 'Jane Smith', 'Marketing Manager', '2019-05-01', 80000.00, '{"email": "jane.smith@acme.com", "phone": "098-765-4321"}');


UPDATE departments SET head_employee_id = 1 WHERE department_id = 1;
UPDATE departments SET head_employee_id = 2 WHERE department_id = 2;