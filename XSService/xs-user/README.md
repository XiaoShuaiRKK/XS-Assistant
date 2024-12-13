# XS-User

## Points-Level

### 表
customer_points_level 表
```sql
CREATE TABLE `customer_points_level` (
  `points_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `points_level` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  PRIMARY KEY (`points_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin
```

points_level 表
```sql
CREATE TABLE `points_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `min_points` int(11) NOT NULL,
  `max_points` int(11) NOT NULL,
  `privileges` text COLLATE utf8_bin COMMENT '等级权限描述',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin
```

### 触发器

tri_customer_points_level_update

```sql
create trigger tri_customer_points_level_update
AFTER update on customer_points_level
for each row
begin
	declare new_level_id int;
	select id into new_level_id
	from points_level
	where new.points between min_points and max_points
	LIMIT 1;
	if (new.points_level != new_level_id)
	then
		update customer_points_level
		set points_level = new_level_id
		WHERE points_id = new.points_id;
	end if;
end;

```