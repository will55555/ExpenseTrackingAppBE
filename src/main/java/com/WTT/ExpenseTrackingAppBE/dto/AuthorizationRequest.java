package com.WTT.ExpenseTrackingAppBE.dto;

public record AuthorizationRequest(
        String userName, String password
) {
}