package com.WTT.ExpenseTrackingAppBE.exceptions;

public class InvalidRoleException  extends RuntimeException{
    public InvalidRoleException(String message) {
        super(message);
    }
}