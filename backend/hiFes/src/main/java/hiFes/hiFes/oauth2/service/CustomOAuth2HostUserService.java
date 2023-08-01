package hiFes.hiFes.oauth2.service;

import hiFes.hiFes.oauth2.CustomOAuth2HostUser;
import hiFes.hiFes.oauth2.OAuthAttributes;
import hiFes.hiFes.user.HostUser;
import hiFes.hiFes.user.SocialType;
import hiFes.hiFes.user.repository.HostUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2HostUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
    private final HostUserRepository hostUserRepository;

    private static final String NAVER = "naver";

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("로그인 요청");

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        SocialType socialType = getSocialType(registrationId);
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        OAuthAttributes extractAttributes = OAuthAttributes.of(socialType, userNameAttributeName, attributes);

        HostUser createdUser = getUser(extractAttributes, socialType);

        return new CustomOAuth2HostUser(
                Collections.singleton(new SimpleGrantedAuthority(createdUser.getRole().getKey())),
                attributes,
                extractAttributes.getNameAttributeKey(),
                createdUser.getEmail(),
                createdUser.getRole()
        );
    }

    private SocialType getSocialType(String registrationId) {
        if(NAVER.equals(registrationId)) {
            return SocialType.NAVER;
        }
        return SocialType.KAKAO;
    }

    private HostUser getUser(OAuthAttributes attributes, SocialType socialType) {
        HostUser findUser = hostUserRepository.findBySocialTypeAndSocialId(socialType,
                attributes.getOauth2UserInfo().getId()).orElse(null);

        if(findUser == null) {
            return saveUser(attributes, socialType);
        }
        return findUser;
    }

    private HostUser saveUser(OAuthAttributes attributes, SocialType socialType) {
        HostUser createdUser = attributes.toEntity(socialType, attributes.getOauth2UserInfo());
        return hostUserRepository.save(createdUser);
    }



}
