package com.example.BE_Library.user.usecase;

import com.example.BE_Library.common.usecase.BaseUseCase;
import com.example.BE_Library.common.util.ModelMapperUtils;
import com.example.BE_Library.user.dto.request.CreateUserRequest;
import com.example.BE_Library.user.dto.response.CreateUserResponse;
import com.example.BE_Library.user.entity.User;
import com.example.BE_Library.user.exception.UserEmailExistedException;
import com.example.BE_Library.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class CreateUserUseCase extends BaseUseCase<CreateUserRequest, CreateUserResponse> {

    private  final UserService userService;


    @Override
    public CreateUserResponse execute(CreateUserRequest requestData) {
        String email = requestData.getEmail();

        if(userService.isExistedEmail(requestData.getFullName(), email)){
            throw UserEmailExistedException.getInstance();
        }

        User user = ModelMapperUtils.toObject(requestData, User.class);

        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("USER");
        }

        user.setId(UUID.randomUUID().toString());
        user.setPassword(user.getPassword());

        user = userService.createUser(user);

        return new CreateUserResponse(user.getId());
    }
}
