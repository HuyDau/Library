package com.example.BE_Library.common.usecase;

import com.example.BE_Library.common.dto.request.NoDataRequest;
import com.example.BE_Library.common.util.ModelMapperUtils;
import com.example.BE_Library.common.util.RequestContext;
import com.example.BE_Library.user.dto.response.user.GetUserInfoResponse;
import com.example.BE_Library.user.entity.User;
import com.example.BE_Library.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetUserInfoUseCase extends BaseUseCase<NoDataRequest, GetUserInfoResponse>{

    private final UserService userService;

    public GetUserInfoResponse execute(NoDataRequest requestData) {
        String userId = RequestContext.getUserId();
        User user = userService.findById(userId);
        GetUserInfoResponse response = ModelMapperUtils.toObject(user, GetUserInfoResponse.class);
        return response;
    }
}
