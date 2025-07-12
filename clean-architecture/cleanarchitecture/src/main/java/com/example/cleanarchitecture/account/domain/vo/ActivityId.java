package com.example.cleanarchitecture.account.domain.vo;

public record ActivityId(Long value) {

    public static ActivityId empty() {
        return new ActivityId(null);
    }

    public boolean isEmpty() {
        return this.value == null;
    }
}
