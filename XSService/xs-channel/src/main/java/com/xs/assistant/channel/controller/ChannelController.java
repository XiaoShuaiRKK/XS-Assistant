package com.xs.assistant.channel.controller;

import com.xs.DAO.ResponseResult;
import com.xs.assistant.channel.service.ChannelService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/channel")
@Validated
public class ChannelController {
    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @PostMapping("/add")
    public ResponseResult<Boolean> addChannel(@RequestParam("accountId") String idNumber,
                                              @RequestParam("channelName") String channelName) {
        return channelService.addChannel(idNumber, channelName);
    }
}
