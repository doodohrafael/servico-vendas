package br.com.rafael.projeto.ecommerce.vendas.domain.shared;

import java.math.BigDecimal;

public class AssertionConcern {

    public static void assertNotNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertNotEmpty(String stringValue, String message) {
        if (stringValue == null || stringValue.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertPositive(int value, String message) {
        if (value <= 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertPositive(BigDecimal value, String message) {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(message);
        }
    }
}