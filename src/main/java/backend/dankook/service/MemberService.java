package backend.dankook.service;

import backend.dankook.domain.Member;
import backend.dankook.enums.GenderEnum;
import backend.dankook.enums.MemberTypeEnum;
import backend.dankook.exception.DankookException;
import backend.dankook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public void join(String name, String email, String password, MemberTypeEnum memberType, GenderEnum gender) {

        if(memberRepository.findByEmail(email).isPresent()){
            throw new DankookException(HttpStatus.CONFLICT, "This email is already exists.");
        }

        Member member = createMemberAfterPasswordEncoding(name, email, password, memberType, gender);

        memberRepository.save(member);
    }

    private Member createMemberAfterPasswordEncoding(String name, String email, String password, MemberTypeEnum memberType, GenderEnum gender) {
        return Member.createMember(
                name,
                email,
                passwordEncoder.encode(password),
                memberType,
                gender
        );
    }

}
