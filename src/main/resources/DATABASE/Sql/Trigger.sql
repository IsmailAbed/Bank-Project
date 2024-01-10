use abedbank;
DELIMITER //

CREATE TRIGGER after_client_update
AFTER UPDATE ON Clients
FOR EACH ROW
BEGIN
    IF NEW.status = 'accepted' AND OLD.status != 'accepted' THEN
        -- Decrease id by 1
        UPDATE Clients
        SET id= id- 1
        WHERE id = NEW.id;

        
        IF (SELECT id FROM Clients WHERE id = NEW.id) = 0 THEN
            UPDATE Clients SET status = 'closed' WHERE id = NEW.id;
        END IF;
    END IF;
END //

DELIMITER ;