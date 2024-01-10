USE abedbank;

CREATE USER 'client'@'localhost' IDENTIFIED BY 'root';
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'mysql804218$89742';

GRANT INSERT ON abedbank TO 'admin'@'localhost';
GRANT UPDATE ON abedbank TO 'admin'@'localhost';
GRANT DELETE ON abedbank TO 'admin'@'localhost';
GRANT SELECT ON abedbank TO 'admin'@'localhost';

GRANT SELECT ON abedbank.apply TO 'admin'@'localhost';
GRANT DELETE ON abedbank.apply TO 'admin'@'localhost';
GRANT INSERT ON abedbank.apply TO 'admin'@'localhost';
GRANT UPDATE ON abedbank.apply TO 'admin'@'localhost';


GRANT SELECT ON abedbank.job_seeker TO 'admin'@'localhost';

GRANT SELECT ON abedbank.recruiter TO 'admin'@'localhost';
GRANT UPDATE ON abedbank.recruiter TO 'admin'@'localhost';
GRANT EXECUTE ON PROCEDURE abedbank.ChangePassword_Sp TO 'admin'@'localhost';




GRANT SELECT ON abedbank.client TO 'client'@'localhost';

GRANT SELECT ON abedbank.client TO 'client'@'localhost';
GRANT UPDATE ON abedbank.client TO 'client'@'localhost';

GRANT SELECT ON abedbank.apply TO 'client'@'localhost';
GRANT INSERT ON abedbank.apply TO 'client'@'localhost';
GRANT UPDATE ON abedbank.apply TO 'client'@'localhost';
GRANT DELETE ON abedbank.apply TO 'client'@'localhost';

GRANT EXECUTE ON FUNCTION abedbank.CheckUsernameExists TO 'client'@'localhost';
GRANT EXECUTE ON PROCEDURE abedbank.ChangePassword_Sp TO 'client'@'localhost';




##REVOKE SELECT ON job_hub_db.job FROM 'job-sekeer'@'localhost';