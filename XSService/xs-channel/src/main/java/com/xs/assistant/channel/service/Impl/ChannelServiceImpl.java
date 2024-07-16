package com.xs.assistant.channel.service.Impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xs.DAO.DO.channel.ChannelDO;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.factory.ChannelFactory;
import com.xs.DAO.factory.ResponseResultFactory;
import com.xs.assistant.channel.DAO.ChannelMapper;
import com.xs.assistant.channel.service.ChannelService;
import com.xs.assistant.redis.listener.MessageChannelUtil;
import com.xs.assistant.util.Impl.UIDCodeUtil;
import com.xs.assistant.util.uid.Impl.SnowflakeDistributeId;
import org.springframework.stereotype.Service;

@Service
public class ChannelServiceImpl implements ChannelService {

    private static final Integer CHANNEL_ID_COUNT = 64;

    final SnowflakeDistributeId snowflakeDistributeId;
    final UIDCodeUtil codeUtil;
    final ChannelMapper channelMapper;
    final MessageChannelUtil messageChannelUtil;

    public ChannelServiceImpl(UIDCodeUtil codeUtil, ChannelMapper channelMapper, MessageChannelUtil messageChannelUtil) {
        this.snowflakeDistributeId = new SnowflakeDistributeId(0,0);
        this.codeUtil = codeUtil;
        this.channelMapper = channelMapper;
        this.messageChannelUtil = messageChannelUtil;
    }

    @Override
    public ResponseResult<Boolean> addChannel(String idNumber, String channelName) {
        String channelId = codeUtil.createCode(UIDCodeUtil.CreateCodeType.CHANNEL,
                snowflakeDistributeId.nextId(),CHANNEL_ID_COUNT);
        ChannelDO channel = ChannelDO.builder()
                .creatorId(idNumber)
                .channelName(channelName)
                .channelId(channelId)
                .build();
        int rs = channelMapper.insert(channel);
        if(rs <= 0)
            return ResponseResult.error(false,"创建新的channel失败");
        return ResponseResultFactory.decideResultByUpdateOrInsert(messageChannelUtil.addChannel(channelName),
                true,false,"创建新的channel成功","创建新的channel失败");
    }

    @Override
    public ResponseResult<Boolean> editChannelWithId(String channelId, String newChannelName) {
        ChannelDO channelOld = channelMapper.selectByChannelId(channelId);
        channelOld.setChannelName(newChannelName);
        UpdateWrapper<ChannelDO> channelDOUpdateWrapper = new UpdateWrapper<>();
        channelDOUpdateWrapper.eq(ChannelFactory.ChannelMysqlEnum.CHANNEL_ID_COLUMN.getColumnName(),channelId);
        return ResponseResultFactory.decideResultByUpdateOrInsert(channelMapper.update(channelOld,channelDOUpdateWrapper),
                true,false,"修改channel成功","修改channel失败");
    }

    @Override
    public ResponseResult<Boolean> editChannelWithName(String channelName, String newChannelName) {
        ChannelDO channelOld = channelMapper.selectByChannelName(channelName);
        channelOld.setChannelName(newChannelName);
        UpdateWrapper<ChannelDO> channelDOUpdateWrapper = new UpdateWrapper<>();
        channelDOUpdateWrapper.eq(ChannelFactory.ChannelMysqlEnum.CHANNEL_NAME_COLUMN.getColumnName(),channelName);
        return ResponseResultFactory.decideResultByUpdateOrInsert(channelMapper.update(channelOld,channelDOUpdateWrapper),
                true,false,"修改channel成功","修改channel失败");
    }
}
