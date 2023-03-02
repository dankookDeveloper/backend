package backend.dankook.controller;

import backend.dankook.dtos.MemberJoinDto;
import backend.dankook.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public void join(@RequestBody MemberJoinDto memberJoinDto){
        memberService.join(
                memberJoinDto.getName(),
                memberJoinDto.getEmail(),
                memberJoinDto.getPassword(),
                memberJoinDto.getMemberType(),
                memberJoinDto.getGender()
        );

    }

}
