package com.woowacourse.calculator.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 클래스 이름 : ExpressionFactory.java
 *
 * @author S.W.PARK
 * @author K.S.KIM
 * @version 1.0
 * <p>
 * 날짜 : 2020.02.06 목요일
 */

public class ExpressionFactory {
    private static final String DELIMITER = " ";
    private static final String DIVIDE_BY_ZERO_STRING = "/ 0";
    private static final String EXPRESSION_VALIDATION_PATTERN = "^-?\\d*(\\s[-+*/]\\s-?\\d*)*\\b";
    private static final int NUMBER_START_INDEX = 0;
    private static final int OPERATION_START_INDEX = 1;
    private static final int LOOP_JUMP_COUNT = 2;

    private ExpressionFactory() {}

    public static Expression create(final String input) {
        validateTokens(input);
        validateDivideByZero(input);
        List<String> tokens = Arrays.asList(input.split(DELIMITER));
        List<Double> numbers = makeNumbers(tokens);
        List<OperatorType> operators = makeOperators(tokens);
        return new Expression(numbers, operators);
    }

    private static void validateTokens(final String input) {
        if (!input.matches(EXPRESSION_VALIDATION_PATTERN)) {
            throw new IllegalArgumentException("잘못된 형식으로 입력하셨습니다.");
        }
    }

    private static void validateDivideByZero(final String input) {
        if (input.contains(DIVIDE_BY_ZERO_STRING)) {
            throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
        }
    }

    private static List<Double> makeNumbers(final List<String> tokens) {
        List<Double> numbers = new ArrayList<>();
        for (int index = NUMBER_START_INDEX, end = tokens.size(); index < end; index += LOOP_JUMP_COUNT) {
            numbers.add(Double.valueOf(tokens.get(index)));
        }
        return numbers;
    }

    private static List<OperatorType> makeOperators(final List<String> tokens) {
        List<OperatorType> operators = new ArrayList<>();
        for (int index = OPERATION_START_INDEX, end = tokens.size(); index < end; index += LOOP_JUMP_COUNT) {
            operators.add(OperatorType.of(tokens.get(index)));
        }
        return operators;
    }
}
