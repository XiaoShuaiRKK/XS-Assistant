package com.xs.DAO.DO.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@TableName("system_info")
public class SystemInfo {
    @TableId("customer_id")
    private String customerId;
    @TableField("system")
    private String system;
    @TableField("computer_name")
    private String computerName;
    @TableField("sys_id")
    private String sysId;
    @TableField("last_time")
    private Date lastTime;
    @TableField("version")
    private Integer version;
}
