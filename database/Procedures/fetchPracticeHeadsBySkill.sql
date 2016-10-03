DROP PROCEDURE IF EXISTS `fetchPracticeHeadsBySkill`;
DELIMITER $$

CREATE PROCEDURE `fetchPracticeHeadsBySkill`(IN skillId Varchar(10))
BEGIN
########Handlers###########################################
DECLARE CODE INT DEFAULT 400;
DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN 
         CALL saveLog(CODE, 'Exception in fetchPracticeHeadsBySkill');  
    END;
########Handlers###########################################
		select 
			u.*
		from
			employee e
				inner join
			user u ON e.employeeId = u.userId
		where
			u.groupId in (select Distinct
					groupId
				from
					group_rights g
				where
					e.skillId = skillId
						AND g.operationId in ('SRCHALL' , 'SRCHREL'));
	END $$
DELIMITER ;