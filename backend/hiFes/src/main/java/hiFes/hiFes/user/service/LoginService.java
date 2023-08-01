package hiFes.hiFes.user.service;

import hiFes.hiFes.user.HostUser;
import hiFes.hiFes.user.repository.HostUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final HostUserRepository hostUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        HostUser hostuser = hostUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일이 존재하지 않습니다."));

        return org.springframework.security.core.userdetails.User.builder()
                .username(hostuser.getEmail())
                .password(hostuser.getPassword())
                .roles(hostuser.getRole().name())
                .build();
    }
}
