package com.xs.DAO.DO.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "设备信息")
@TableName("system_info")
public class SystemInfo implements Serializable {
    @Schema(description = "用户id",example = "XS202409180005")
    @NotNull(message = "customer id number cannot be null")
    @TableId("customer_id")
    private String customerId;

    @Schema(description = "操作系统",example = "Windows 10")
    @TableField("`system`")
    @NotNull(message = "system cannot be null")
    private String system;

    @Schema(description = "设备名称",example = "Computer-1")
    @TableField("computer_name")
    @NotNull(message = "computer name cannot be null")
    private String computerName;

    @Schema(description = "生成的用户唯一设备id",example = "XS202409180005S0000")
    @TableField("sys_id")
    private String sysId;

    @Schema(description = "设备最后登录时间")
    @TableField("last_time")
    @NotNull(message = "last login time cannot be null")
    private Timestamp lastTime;

    @Schema(description = "设备迭代更新版本号")
    @TableField("version")
    private Integer version;
}
