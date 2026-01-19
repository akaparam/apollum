package com.example.apollum.models.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class SysException extends RuntimeException {
    private final String title;
    private final String message;

    public SysException(String title, String message) {
        super(message);
        this.title = title;
        this.message = message;
    }

    public ProblemDetail toProblemDetail() {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle(title);
        pd.setDetail(message);
        return pd;
    }
}
