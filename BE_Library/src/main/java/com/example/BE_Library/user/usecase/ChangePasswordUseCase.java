package com.example.BE_Library.user.usecase;

import com.example.BE_Library.common.dto.response.NoDataResponse;
import com.example.BE_Library.common.usecase.BaseUseCase;
import com.example.BE_Library.common.util.ModelMapperUtils;
import com.example.BE_Library.user.dto.request.user.ChangePasswordRequest;
import com.example.BE_Library.user.entity.User;
import com.example.BE_Library.user.exception.UserExistedException;
import com.example.BE_Library.user.exception.UserNotFoundException;
import com.example.BE_Library.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ChangePasswordUseCase extends BaseUseCase<ChangePasswordRequest, NoDataResponse> {
    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public NoDataResponse execute(ChangePasswordRequest requestData) {
        String userId = requestData.getUserId();
        User user = userService.findById(userId);

        if (user == null) {
            throw UserNotFoundException.getInstance();
        }
        ModelMapperUtils.map(requestData, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.updateUser(user);
        return NoDataResponse.getInstance();
    }
}
