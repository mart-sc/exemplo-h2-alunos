CREATE TABLE aluno (
	id LONG AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	salario NUMERIC(10, 2) NOT NULL,
	nascimento DATE NOT NULL
);