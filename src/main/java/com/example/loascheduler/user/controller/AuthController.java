package com.example.loascheduler.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

//    private final AuthService authService;
//
//    @PostMapping("/auth/signup")
//    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest, BindingResult result) {
//
//        if (result.hasErrors()) {
//            // 검증 실패 시 첫 번째 오류 메시지 반환
//            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
//        }
//        String bearerToken = authService.signup(signupRequest);
//        return ResponseEntity
//                .ok()
//                .body(bearerToken);
//    }
//
//    @PostMapping("/auth/signin")
//    public ResponseEntity<Void> signin(@RequestBody SigninRequest signinRequest) {
//        String bearerToken = authService.signin(signinRequest);
//        return ResponseEntity.ok().header("Authorization", bearerToken).build();
//    }

//    @DeleteMapping("/auth/signout")
//    public ResponseEntity<String> signout(@AuthenticationPrincipal AuthUser authUser, @RequestBody SignoutRequest signoutRequest) {
//        return ResponseEntity.ok(authService.signout(authUser.getUserId(), signoutRequest));
//    }
}
