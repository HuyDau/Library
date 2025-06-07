package com.example.BE_Library.user.controller;

import com.example.BE_Library.user.controller.swagger.CreateUserResponseSchema;
import com.example.BE_Library.user.dto.request.CreateUserRequest;
import com.example.BE_Library.user.dto.response.CreateUserResponse;
import com.example.BE_Library.user.usecase.CreateUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "User")
@RequestMapping("/v1/users")
public class UserController {

    private CreateUserUseCase createUserUseCase;

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
}
