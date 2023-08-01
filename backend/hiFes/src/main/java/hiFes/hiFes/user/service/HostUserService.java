package hiFes.hiFes.user.service;

import hiFes.hiFes.user.HostUser;
import hiFes.hiFes.user.Role;
import hiFes.hiFes.user.dto.UserSignUpDto;
import hiFes.hiFes.user.repository.HostUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HostUserService {
    private final HostUserRepository hostUserRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(UserSignUpDto userSignUpDto) throws Exception{
        if(hostUserRepository.findByEmail(userSignUpDto.getEmail()).isPresent()){
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        HostUser hostUser = HostUser.builder()
                .email(userSignUpDto.getEmail())
                .password(userSignUpDto.getPassword())
                .name(userSignUpDto.getName())
                .role(Role.USER)
                .build();

        hostUser.passwordEncode(passwordEncoder);
        hostUserRepository.save(hostUser);
    }
}
