package com.xs.DAO.DO.channel;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChannelDO {
    @TableId
    Long id;
    String channelId;
    String channelName;
    String creatorId;
    Date createTime;
}
