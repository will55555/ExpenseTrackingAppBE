package com.WTT.ExpenseTrackingAppBE.exceptions;

public class UsernameTakenException  extends RuntimeException{
    public UsernameTakenException(String message) {
        super(message);
    }
}