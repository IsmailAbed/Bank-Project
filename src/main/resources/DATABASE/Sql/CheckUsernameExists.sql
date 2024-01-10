use abedbank;

DELIMITER //
CREATE FUNCTION CheckUsernameExists(
    p_username VARCHAR(10),
    p_client VARCHAR(20)
)
RETURNS INT
DETERMINISTIC
READS SQL DATA
BEGIN
    DECLARE user_exists INT;

    IF p_role = 'client' THEN
        SELECT COUNT(*) INTO user_exists FROM abedbank.client WHERE Username = p_username;
    ELSEIF p_role = 'admin' THEN
        SELECT COUNT(*) INTO user_exists FROM abedbank.admin WHERE Username = p_username;
    END IF;

    RETURN user_exists;
END //

DELIMITER ;