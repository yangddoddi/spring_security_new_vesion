package io.study.security_basic.auth;

import io.study.security_basic.Entity.User;
import io.study.security_basic.repository.UserRepository;
import io.study.security_basic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
*
* loginProcessingUrl
* -> UserDetailService
* -> loadUserByUsername
*
* form 방식으로 로그인할 경우 action을 반드시 username으로 보내야 매칭 가능
* (혹은 parameter로 변경)
*
* */


@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    /*
    *
    * 해당 함수 종료 시 AuthenticationPrincipal 생성
    *
    * */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser = Optional
                .ofNullable(userRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 계정입니다"));
        return new PrincipalDetails(findUser);
    }
}
