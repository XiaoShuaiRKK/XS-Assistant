package com.xs.DAO.factory;

import lombok.Getter;

public class ChannelFactory {
    @Getter
    public enum ChannelMysqlEnum{
        ID_COLUMN("id"),
        CHANNEL_ID_COLUMN("channel_id"),
        CHANNEL_NAME_COLUMN("channel_name"),
        CREATOR_ID_COLUMN("creator_name"),
        CREATE_TIME_COLUMN("create_time");
        private final String columnName;
        ChannelMysqlEnum(String columnName) {
            this.columnName = columnName;
        }
    }
}
