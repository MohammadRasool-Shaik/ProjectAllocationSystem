DROP PROCEDURE IF EXISTS `fetchAllPracticeHeads`;
DELIMITER $$

CREATE PROCEDURE `fetchAllPracticeHeads`(IN employeeId varchar(10),IN employeeName varchar(10))
BEGIN
########Handlers###########################################
DECLARE CODE INT DEFAULT 400;
DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN 
         CALL saveLog(CODE, 'Exception in fetchAllPracticeHeads');  
    END;
########Handlers###########################################
		select 
			e.*
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
					g.operationId in ('SRCHALL' , 'SRCHREL')) and (e.employeeId like employeeId
						or e.ename like employeeName)	
		order by e.ename asc
		limit 10;
	END $$
DELIMITER ;