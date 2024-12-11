# xs-system-management
## mysql 触发器
### 表
system_info
```sql
CREATE TABLE `system_info` (
    `customer_id` int(11) NOT NULL,
    `system` varchar(255) COLLATE utf8_bin NOT NULL,
    `computer_name` varchar(255) COLLATE utf8_bin NOT NULL,
    `sys_id` varchar(255) COLLATE utf8_bin NOT NULL,
    `last_time` datetime NOT NULL,
    `version` int(11) NOT NULL,
    PRIMARY KEY (`sys_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin
```
system_info_history
```sql
CREATE TABLE `system_info_history` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `system` varchar(255) COLLATE utf8_bin NOT NULL,
  `computer_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin
```
system_info_log
```sql
CREATE TABLE `system_info_log` (
  `system_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `operate_type` int(11) NOT NULL,
  `old_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `new_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`system_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin
```
## 触发器
insert trigger
```sql
CREATE TRIGGER tri_insert_system_info 
after insert on system_info for each row
begin
	insert into system_info_log (system_id, create_time, update_time,operate_type,old_id,new_id) 
	VALUES(new.sys_id, now(), now(),1,null,null);
end;
```
update trigger
```sql
CREATE trigger tri_update_system_info
after update on system_info 
for each row
begin
	declare history_id varchar(20) DEFAULT '';
	declare update_count int default 0;
	
	select count(*) 
	into update_count 
	from system_info_log 
	where system_id = old.sys_id
		and update_time >= CURDATE() 
		and update_time < CURDATE() + INTERVAL 1 DAY;
	 
	set history_id = CONCAT('XSSH',DATE_FORMAT(CURDATE(),'%Y%m%d'),LPAD(update_count + 1,4,'0'));
	insert into system_info_history (id,`system`,computer_name,version)
	VALUES(history_id,old.system,old.computer_name,old.version);
	
	insert into system_info_log (system_id, create_time, update_time,operate_type,old_id,new_id) 
	VALUES(new.sys_id, now(), now(),2,history_id,new.sys_id);
end;
```
delete trigger

```sql
CREATE trigger tri_delete_system_info
after delete on system_info
for each row
begin
	declare history_id varchar(20) DEFAULT '';
	declare update_count int default 0;
	
	select count(*) 
	into update_count 
	from system_info_log 
	where system_id = old.sys_id
		and update_time >= CURDATE() 
		and update_time < CURDATE() + INTERVAL 1 DAY;
	 
	set history_id = CONCAT('XSSH',DATE_FORMAT(CURDATE(),'%Y%m%d'),LPAD(update_count + 1,4,'0'));

	insert into system_info_history (id,`system`,computer_name,version)
	VALUES(history_id,old.system,old.computer_name,old.version);
	
	insert into system_info_log (system_id, create_time, update_time,operate_type,old_id,new_id) 
	VALUES(old.sys_id, now(), now(),3,history_id,null);
end;

```