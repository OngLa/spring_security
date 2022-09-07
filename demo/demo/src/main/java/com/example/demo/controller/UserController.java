package com.example.demo.controller;

import com.example.demo.common.BaseResponse;
import com.example.demo.domain.User;
import com.example.demo.request.JoinRequest;
import com.example.demo.request.LoginRequest;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(value = "User API", tags = {"User"})
public class UserController {

    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "로그인")
    @ApiResponses({
            @ApiResponse(code=200, message = "성공"),
            @ApiResponse(code=403, message = "비밀번호가 일치하지 않습니다"),
            @ApiResponse(code=404, message = "유저가 존재하지 않습니다"),
            @ApiResponse(code=500, message = "토큰 넣는 도중 오류 발생"),
    })
    public ResponseEntity<? extends BaseResponse> login(@RequestBody LoginRequest request) {
        String id = request.getId();
        String pw = request.getPassword();

        User user = userService.findById(id);

        if(user == null){
            return ResponseEntity.status(404).body(BaseResponse.of(404, "유저가 존재하지 않습니다"));
        }
        if(!bCryptPasswordEncoder.matches(pw, user.getPw())) {
            return ResponseEntity.status(403).body(BaseResponse.of(403, "비밀번호가 일치하지 않습니다"));
        }

        return ResponseEntity.status(200).body(BaseResponse.of(200, "성공"));
    }

    @PostMapping("/join")
    @ApiOperation(value = "회원가입", notes = "회원가입")
    @ApiResponses({
            @ApiResponse(code=200, message = "성공"),
            @ApiResponse(code=401, message = "인증번호 불일치"),
            @ApiResponse(code=403, message = "인증번호 만료"),
            @ApiResponse(code=409, message = "이미 존재하는 이메일"),
    })
    public ResponseEntity<? extends BaseResponse> join(@RequestBody JoinRequest request) {
        User user = userService.findById(request.getId());

        if(user != null){
            return ResponseEntity.status(404).body(BaseResponse.of(409, "이미 존재하는 이메일"));
        }

        request.setPw(bCryptPasswordEncoder.encode(request.getPw()));
        userService.addUser(request);

        return ResponseEntity.status(200).body(BaseResponse.of(200, "회원가입 성공"));
    }
}
