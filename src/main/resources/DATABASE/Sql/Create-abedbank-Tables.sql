use abedbank;


-- Create Admins Table
CREATE TABLE Admins (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) ,
    password VARCHAR(255) 
);

-- Create Checking Account Table
CREATE TABLE CheckingAccount (
    id INT PRIMARY KEY AUTO_INCREMENT,
    owner VARCHAR(255) ,
    accountNumber VARCHAR(20) ,
    transactionLimit DECIMAL(10, 2),
    balance DECIMAL(10, 2)
);

-- Create Clients Table
CREATE TABLE Clients (
    id INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    payeeAddress VARCHAR(255) ,
    password VARCHAR(255) NOT NULL,
    dateOfBirth DATE 
);

-- Create Saving Accounts Table
CREATE TABLE SavingAccounts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    owner VARCHAR(255) NOT NULL,
    accountNumber VARCHAR(20) ,
    withdrawLimit DECIMAL(10, 2),
    balance DECIMAL(10, 2)
);

-- Create Transactions Table
CREATE TABLE Transactions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sender INT,
    receiver INT,
    amount DECIMAL(10, 2),
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    message TEXT,
    FOREIGN KEY (sender) REFERENCES Clients(id),
    FOREIGN KEY (receiver) REFERENCES Clients(id)
);