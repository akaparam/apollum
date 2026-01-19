package com.example.apollum.models.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.util.HashMap;
import java.util.Map;

@Getter
public class AppException extends RuntimeException {
    private final String title;
    private final String message;
    private final Map<String, Object> properties = new HashMap<>();

    public AppException(String title, String message) {
        super(message);
        this.title = title;
        this.message = message;
    }

    public ProblemDetail toProblemDetail() {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle(title);
        pd.setDetail(message);
        properties.forEach(pd::setProperty);
        return pd;
    }
}
