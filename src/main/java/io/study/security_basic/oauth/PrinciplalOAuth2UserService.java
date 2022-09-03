package io.study.security_basic.oauth;

import io.study.security_basic.Entity.User;
import io.study.security_basic.auth.PrincipalDetails;
import io.study.security_basic.auth.provider.FacebookUserInfo;
import io.study.security_basic.auth.provider.GoogleUserInfo;
import io.study.security_basic.auth.provider.OAuth2UserInfo;
import io.study.security_basic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/*
*
* Spring Security
* SecurityContextHolder(Authentication(UserDetails or OAuth2User))
*
* */


@Service
@Slf4j
@RequiredArgsConstructor
public class PrinciplalOAuth2UserService extends DefaultOAuth2UserService {
    private final PasswordEncoder pwdEncoder;
    private final UserRepository userRepository;

    private OAuth2UserInfo oAuth2UserInfo;

    /*
    *
    * 구글로부터 받은 userRequest 데이터 후처리 함수
    * 함수 종료시 @AuthenticationPrincipal 만들어짐    *
    * */

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        } else {
            throw new OAuth2AuthenticationException("잘못된 요청입니다.");
        }

        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider+"_"+providerId;
        String email = oAuth2UserInfo.getEmail();
        String password = pwdEncoder.encode("패스워드");
        String role = "ROLE_USER";

        User user = userRepository.findByUsername(username);

        if (user==null) {
            user = createUser(provider, providerId, username, email, password, role);
            userRepository.save(user);
        } else {
            throw new OAuth2AuthenticationException("이미 존재하는 회원입니다.");
        }

        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }

    private User createUser(String provider, String providerId, String username, String email, String password, String role) {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .providerId(providerId)
                .provider(provider)
                .role(role)
                .build();
    }


    /*
    * 구글 로그인
    * -> 코드 리턴(Oauth-client)
    * -> AccessToken 요청
    * -> userRequest 정보
    * -> loadUser로 회원 정보 정보 받음
    * -> 회원 프로필 생성
    * */


    /*
    * super.loadUser(userRequest).getAttribute()
    * sub = 구글 회원 번호
    * regestrationId = 회사명
    * name = 풀네임
    * given_name = 이름
    * family_name = 성
    * picture = 프로필 사진 경로
    * email = 구글계정(구글일 경우)
    * email_verfied = 이메일 확인 여부(?)
    * locale = 국적
    * */

    /*
    * username -> googlr_sub
    * password ->
    * email -> email
    * role -> ROLE_USER
    * provider -> google
    * providerId -> sub
    * */


    /*
    * facebook
    * no sub => username
    *
    * */
}
