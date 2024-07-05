package com.xs.assistant.channel.service;

import com.xs.DAO.ResponseResult;

public interface ChannelService {
    ResponseResult<Boolean> addChannel(String idNumber,String channelName);
}
