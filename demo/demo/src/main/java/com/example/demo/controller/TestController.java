package com.example.demo.controller;

import com.example.demo.common.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Test", tags = {"Test"})
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/")
    @ApiOperation(value = "출력", notes = "test 출력")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버오류")
    })
    public ResponseEntity<? extends BaseResponse> printTest() {
        return ResponseEntity.status(200).body(BaseResponse.of(200,"success"));
    }

    @GetMapping("/user")
    @ApiOperation(value = "user", notes = "user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버오류")
    })
    public ResponseEntity<? extends BaseResponse> user() {
        return ResponseEntity.status(200).body(BaseResponse.of(200,"success"));
    }

    @GetMapping("/login1")
    @ApiOperation(value = "login1", notes = "login1")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버오류")
    })
    public ResponseEntity<? extends BaseResponse> login1() {
        return ResponseEntity.status(200).body(BaseResponse.of(200,"success"));
    }

    @GetMapping("/admin")
    @ApiOperation(value = "admin", notes = "admin")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버오류")
    })
    public ResponseEntity<? extends BaseResponse> admin() {
        return ResponseEntity.status(200).body(BaseResponse.of(200,"success"));
    }

    @GetMapping("/manager")
    @ApiOperation(value = "manager", notes = "manager")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버오류")
    })
    public ResponseEntity<? extends BaseResponse> manager() {
        return ResponseEntity.status(200).body(BaseResponse.of(200,"success"));
    }

}
