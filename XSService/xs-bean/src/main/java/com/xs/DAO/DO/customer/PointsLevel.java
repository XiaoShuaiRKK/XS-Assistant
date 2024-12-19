package com.xs.DAO.DO.customer;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("customer_points_level")
public class PointsLevel {
    @TableField("points_id")
    private String pointsId;
    @TableField("points_level")
    private Integer pointsLevel;
    private String pointsLevelName;
    private Integer minPoints;
    private Integer nextPoints;
    @TableField("points")
    private Integer points;
}
