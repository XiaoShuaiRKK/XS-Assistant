package com.xs.assistant.channel.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xs.DAO.DO.channel.ChannelDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChannelMapper extends BaseMapper<ChannelDO>{
    @Select("SELECT * FROM channel WHERE channel_id = #{channelId}")
    ChannelDO selectByChannelId(String channelId);
    @Select("SELECT * FROM channel WHERE channel_name = #{channelName}")
    ChannelDO selectByChannelName(String channelName);
}
