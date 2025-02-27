package com.WTT.ExpenseTrackingAppBE.services.user;

import com.WTT.ExpenseTrackingAppBE.dto.AuthorizationRequest;
import com.WTT.ExpenseTrackingAppBE.dto.PostNewUser;
import com.WTT.ExpenseTrackingAppBE.dto.UserDto;

public interface UserService {
    UserDto registerUser(PostNewUser postNewUser);
    String login(AuthorizationRequest request);

}