CREATE TABLE Amigo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(255)
);

CREATE TABLE Ferramenta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    marca VARCHAR(255),
    custo_de_aquisicao DECIMAL(10,2)
);

CREATE TABLE Administrador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE EmprestimosAtivos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_do_emprestimo DATE NOT NULL,
    data_da_devolucao DATE,
    ferramenta_id INT NOT NULL,
    amigo_id INT NOT NULL,
    FOREIGN KEY (ferramenta_id) REFERENCES Ferramenta(id),
    FOREIGN KEY (amigo_id) REFERENCES Amigo(id)
);