package ru.greatbit.utils.exceptions;

/**
 * Created by azee on 5/15/14.
 */
public class MathException extends Exception {
    public MathException() {
        super();
    }

    public MathException(String message) {
        super(message);
    }


    public MathException(String message, Throwable cause) {
        super(message, cause);
    }


    public MathException(Throwable cause) {
        super(cause);
    }

}
