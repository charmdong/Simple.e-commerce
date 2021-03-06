package com.commerce.api;

import com.commerce.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public ResponseEntity<Map<String, Object>> checkId (@PathVariable("memberId") String id) {

        Map<String, Object> result = new HashMap<>();

        Boolean isValid = memberService.isValidId(id);
        result.put("isValid", isValid);
        result.put("message", isValid ? "사용 가능한 아이디입니다." : "사용할 수 없는 아이디입니다.");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
