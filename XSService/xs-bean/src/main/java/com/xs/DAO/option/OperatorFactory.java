package com.xs.DAO.option;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class OperatorFactory {
    private OperatorFactory(){}
    static Map<OperatorEnum,Operation> operationMap = new EnumMap<>(OperatorEnum.class);
    static {
        operationMap.put(OperatorEnum.ADD,number -> number[0] + number[1]);
        operationMap.put(OperatorEnum.SUBTRACT,number -> number[0] - number[1]);
        operationMap.put(OperatorEnum.MULTIPLY,number -> number[0] * number[1]);
        operationMap.put(OperatorEnum.DIVIDE,number -> number[0] / number[1]);
        operationMap.put(OperatorEnum.INCREMENT, number -> number[0] + 1);
        operationMap.put(OperatorEnum.DECREMENT,number -> number[0] - 1);
    }
    public static Optional<Operation> getOperation(OperatorEnum operatorEnum){
        return Optional.ofNullable(operationMap.get(operatorEnum));
    }
}
