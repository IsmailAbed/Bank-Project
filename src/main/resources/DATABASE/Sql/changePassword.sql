CREATE DEFINER=`root`@`localhost` PROCEDURE `ChangePassword_Sp`(
    IN p_username VARCHAR(10),
    IN P_password VARCHAR(255),
    IN p_role VARCHAR(20)
)
BEGIN
	 CASE
        WHEN p_role ='client' THEN
             UPDATE abedbank.client SET password = P_password WHERE Username = p_username ;
        WHEN p_role = 'admin' THEN
             UPDATE abedbank.admin SET password = P_password WHERE Username = p_username ;
    END CASE;

END