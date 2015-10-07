package com.acompany.fmc.framework.exception;

public class FMCFrameworkException extends RuntimeException
{
    static final long serialVersionUID = 1L;

    private String type = this.getClass().getSimpleName();

    private String message;

    public FMCFrameworkException(Throwable cause) {
        super(cause);
    }

    public FMCFrameworkException(String message, Throwable cause) {  
        super(cause);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
