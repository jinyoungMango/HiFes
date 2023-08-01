package hiFes.hiFes.oauth2;

import hiFes.hiFes.oauth2.userinfo.KakaoOAuth2UserInfo;
import hiFes.hiFes.oauth2.userinfo.NaverOAuth2UserInfo;
import hiFes.hiFes.oauth2.userinfo.OAuth2UserInfo;
import hiFes.hiFes.user.HostUser;
import hiFes.hiFes.user.Role;
import hiFes.hiFes.user.SocialType;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class OAuthAttributes {
    private String nameAttributeKey;
    private OAuth2UserInfo oauth2UserInfo;

    @Builder
    public OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oauth2UserInfo){
        this.nameAttributeKey = nameAttributeKey;
        this.oauth2UserInfo = oauth2UserInfo;
    }

    public static OAuthAttributes of(SocialType socialType,
                                     String userNameAttributeName, Map<String, Object> attributes) {

        if (socialType == SocialType.NAVER) {
            return ofNaver(userNameAttributeName, attributes);
        }

        return ofKakao(userNameAttributeName, attributes);


    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new KakaoOAuth2UserInfo(attributes))
                .build();
    }

    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new NaverOAuth2UserInfo(attributes))
                .build();
    }

    public HostUser toEntity(SocialType socialType, OAuth2UserInfo oauth2UserInfo) {
        return HostUser.builder()
                .socialType(socialType)
                .socialId(oauth2UserInfo.getId())
                .email(UUID.randomUUID() + "@socialUser.com")
                .name(oauth2UserInfo.getName())
                .profilePic(oauth2UserInfo.getImageUrl())
                .role(Role.GUEST)
                .build();
    }
}
