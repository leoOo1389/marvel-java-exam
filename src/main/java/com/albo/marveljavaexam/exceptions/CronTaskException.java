package com.albo.marveljavaexam.exceptions;

public class CronTaskException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CronTaskException(String message) {
        super("Error executing cron:" + message);
    }
}
