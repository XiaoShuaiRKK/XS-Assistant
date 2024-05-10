package com.xs.DAO.option;

@FunctionalInterface
public interface Operation {
    Long apply(long... number);
}
