use abedbank;

ALTER TABLE `Transactions` ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY(`sender`) REFERENCES `Clienrs`(`id`);
ALTER TABLE `Transactions` ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY(`receiver`) REFERENCES `Clienrs`(`id`);




