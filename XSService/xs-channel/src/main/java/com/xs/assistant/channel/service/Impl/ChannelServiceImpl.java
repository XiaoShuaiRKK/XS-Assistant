package com.xs.assistant.channel.service.Impl;

import com.xs.DAO.DO.channel.ChannelDO;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.channel.DAO.ChannelMapper;
import com.xs.assistant.channel.service.ChannelService;
import com.xs.assistant.util.Impl.UIDCodeUtil;
import com.xs.assistant.util.uid.Impl.SnowflakeDistributeId;
import org.springframework.stereotype.Service;

@Service
public class ChannelServiceImpl implements ChannelService {

    private static final Integer CHANNEL_ID_COUNT = 64;

    final SnowflakeDistributeId snowflakeDistributeId;
    final UIDCodeUtil codeUtil;
    final ChannelMapper channelMapper;

    public ChannelServiceImpl(SnowflakeDistributeId snowflakeDistributeId, UIDCodeUtil codeUtil, ChannelMapper channelMapper) {
        this.snowflakeDistributeId = snowflakeDistributeId;
        this.codeUtil = codeUtil;
        this.channelMapper = channelMapper;
    }

    @Override
    public ResponseResult<Boolean> addChannel(String idNumber, String channelName) {
        String channelId = codeUtil.createCodeWithArticle(snowflakeDistributeId.nextId(),CHANNEL_ID_COUNT);
    }
}
