package com.xs.assistant.service.user.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xs.DAO.DO.customer.PointsLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

@Mapper
public interface PointsLevelMapper extends BaseMapper<PointsLevel> {
    @Select("""
        SELECT
            cpl.points_id,cpl.points_level,cpl.points,pl.level_name AS pointsLevelName,pl.max_points AS nextPoints,pl.min_points AS minPoints
        FROM customer_points_level cpl
        LEFT JOIN points_level pl ON cpl.points_level = pl.id
        WHERE cpl.points_id = #{pointsLevelId}
    """)
    PointsLevel selectPointsLevelById(@Param("pointsLevelId") String pointsLevelId);

    @Update({
            """
        <script>
        UPDATE customer_points_level
        SET points = CASE points_id
            <foreach collection="pointsMap.entrySet()" item="entry" index="key" open="" separator=" " close="">
                WHEN #{key} THEN points + #{entry}
            </foreach>
        END
        WHERE points_id IN
            <foreach collection="pointsMap.keySet()" item="key" index="index" open="(" separator="," close=")">
                #{key}
            </foreach>
        </script>
        """
    })
    Boolean batchUpdatePoints(@Param("pointsMap")Map<String,Integer> pointsMap);

}
