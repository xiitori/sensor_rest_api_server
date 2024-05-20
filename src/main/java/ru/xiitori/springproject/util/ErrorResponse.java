package ru.xiitori.springproject.util;

import java.util.List;

public class ErrorResponse {
    private final List<String> errors;

    private final long timestamp;

    public ErrorResponse(List<String> errors) {
        this.errors = errors;
        timestamp = System.currentTimeMillis();
    }

    public List<String> getErrors() {
        return errors;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
