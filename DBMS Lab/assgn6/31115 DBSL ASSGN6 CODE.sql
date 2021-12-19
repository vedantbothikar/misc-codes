delimiter //
CREATE PROCEDURE merge_old_into_new()
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE cid int;
  DECLARE cname varchar(200);
  DECLARE cur1 CURSOR FOR SELECT * FROM old_students;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  OPEN cur1;

  read_loop: LOOP
    FETCH cur1 INTO cid, cname;
    IF done THEN
      LEAVE read_loop;
    END IF;
    insert ignore into new_students values(cid,cname);
  END LOOP;

  CLOSE cur1;
END//

delimiter ;