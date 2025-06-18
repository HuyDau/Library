package com.example.BE_Library.user.usecase;

import com.example.BE_Library.common.usecase.BaseUseCase;
import com.example.BE_Library.common.util.ModelMapperUtils;
import com.example.BE_Library.common.util.RequestContext;
import com.example.BE_Library.user.dto.request.user.ListUserRequest;
import com.example.BE_Library.user.dto.response.user.ListUserResponse;
import com.example.BE_Library.user.dto.response.user.UserResponse;
import com.example.BE_Library.user.entity.User;
import com.example.BE_Library.user.repository.query.SearchUserQuery;
import com.example.BE_Library.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
@AllArgsConstructor
public class ListUserUseCase extends BaseUseCase<ListUserRequest, ListUserResponse> {

    private final UserService userService;

    @Override
    public ListUserResponse execute(ListUserRequest requestData) {
        boolean isIncludeTotal = requestData.isIncludeTotal();
        var query = ModelMapperUtils.toObject(requestData, SearchUserQuery.class);

        List<User> users = userService.findUsers(query);

        List<UserResponse> userResponses = users.stream()
                .map(user -> {
                    var userResponse = ModelMapperUtils.toObject(user, UserResponse.class);
                    return userResponse;
                })
                .toList();

        ListUserResponse response = new ListUserResponse(userResponses);
        if (isIncludeTotal) {
            long total = userService.countUser(query);
            response.setTotal(total);
        }

        return response;
    }
}
