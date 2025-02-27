package com.WTT.ExpenseTrackingAppBE.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostNewUser(
        //annotate it with @JsonProperty to help Jackson map JSON fields correctly
        @JsonProperty("userName") String userName,
        @JsonProperty("password") String password,
        @JsonProperty("email") String email
) {

}