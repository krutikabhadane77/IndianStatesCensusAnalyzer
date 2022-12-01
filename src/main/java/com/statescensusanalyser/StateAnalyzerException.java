package com.statescensusanalyser;

public class StateAnalyzerException extends Exception {

    public enum ExceptionType{
        INVALID_FILE_PATH, INVALID_CLASS_TYPE
    }

    public ExceptionType type;

    public StateAnalyzerException(String message,ExceptionType type) {
        super(message);
        this.type = type;
    }
}