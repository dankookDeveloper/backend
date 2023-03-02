package backend.dankook.domain;

import backend.dankook.enums.GenderEnum;
import backend.dankook.enums.MemberTypeEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberTypeEnum memberType;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public static Member createMember(String name, String email, String password, MemberTypeEnum memberType, GenderEnum gender){
        Member member = new Member();
        member.name = name;
        member.email = email;
        member.password = password;
        member.memberType = memberType;
        member.gender = gender;
        return member;
    }

    public void updateProfile(Profile profile){
        this.profile = profile;
    }

}
