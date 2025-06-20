package com.example.BE_Library.user.controller;

import com.example.BE_Library.common.constant.PagingConstant;
import com.example.BE_Library.common.dto.request.NoDataRequest;
import com.example.BE_Library.common.usecase.GetUserInfoUseCase;
import com.example.BE_Library.user.controller.swagger.CreateUserResponseSchema;
import com.example.BE_Library.user.controller.swagger.GetUserInfoResponseSchema;
import com.example.BE_Library.user.controller.swagger.ListUserResponseSchema;
import com.example.BE_Library.user.dto.request.CreateUserRequest;
import com.example.BE_Library.user.dto.request.user.ChangePasswordRequest;
import com.example.BE_Library.user.dto.request.user.ListUserRequest;
import com.example.BE_Library.user.dto.response.CreateUserResponse;
import com.example.BE_Library.user.dto.response.user.GetUserInfoResponse;
import com.example.BE_Library.user.dto.response.user.ListUserResponse;
import com.example.BE_Library.user.usecase.ChangePasswordUseCase;
import com.example.BE_Library.user.usecase.CreateUserUseCase;
import com.example.BE_Library.user.usecase.ListUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "User")
@RequestMapping("/v1/users")
public class UserController {

    private CreateUserUseCase createUserUseCase;
    private ListUserUseCase listUserUseCase;
    private GetUserInfoUseCase getUserInfoUseCase;
    private ChangePasswordUseCase changePasswordUseCase;

    @PostMapping
    @Operation(summary = "Create User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = CreateUserResponseSchema.class))}),
            @ApiResponse(responseCode = "409", description = """
            Error:
            <br/>
            - 409007000: `USER_EMAIL_EXISTED`
            """,
                    content = {@Content(examples = @ExampleObject(value = """
                {"result": null, "error": {"code": 409007000,"message": "User email existed"}, "success": false}"""))}
            )
    })
    public CreateUserResponse createUser(@Validated @RequestBody CreateUserRequest request){
        return createUserUseCase.execute(request);
    }

    @GetMapping("/me")
    @Operation(summary = "Get User Info")
    @Tags(value = {@Tag(name = "User ")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = GetUserInfoResponseSchema.class))})
    })
    public GetUserInfoResponse getUserInfo() {
        return getUserInfoUseCase.execute(NoDataRequest.getInstance());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "List Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = ListUserResponseSchema.class))})
    })
    public ListUserResponse listUsers(
            @RequestParam(value = "searchTerm", required = false, defaultValue = "") String searchTerm,
            @RequestParam(value = "sort", required = false, defaultValue = "name_english,DESC") String sort,
            @RequestParam(value = "page", required = false, defaultValue = PagingConstant.DEFAULT_PAGE_VALUE) Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = PagingConstant.DEFAULT_PAGE_SIZE_VALUE) Integer pageSize,
            @RequestParam(value = "includeTotal", defaultValue = "false") boolean includeTotal
    ){
        ListUserRequest request = new ListUserRequest(searchTerm, sort, page, pageSize, includeTotal);
        return listUserUseCase.execute(request);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "ChangePassword")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = ListUserResponseSchema.class))})
    })
    public void changePassword(@PathVariable("userId") String userId, @Validated @RequestBody ChangePasswordRequest request){
        request.setUserId(userId);
        changePasswordUseCase.execute(request);
    }
}
