package hiFes.hiFes.service.user;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.dto.user.NormalUserSignUpDto;
import hiFes.hiFes.repository.user.NormalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class NormalUserService {
    private final NormalUserRepository normalUserRepository;
    private final JwtService jwtService;


    public void signUp(NormalUserSignUpDto normalUserSignUpDto, Map<String, Object> context) throws Exception {

        // 데이터 베이스에 없는 이메일이라면
        // 닉네임 빼고 전부 가져와서 넣어야 함
        NormalUser normalUser = NormalUser.builder()
                .email((String) context.get("email"))
                .name((String) context.get("name"))
                .profilePic(normalUserSignUpDto.getProfilePic())
                .phoneNo(normalUserSignUpDto.getPhoneNo()) // 필요한 정보인지 고민 좀
                .nickname(normalUserSignUpDto.getNickname())
                .build();

        normalUserRepository.save(normalUser);
    }

    public NormalUser getByEmail(String email) {
        return normalUserRepository.findByEmail(email).orElse(null);
    }


    public JsonObject login(String email) throws UsernameNotFoundException {

        String accessToken = jwtService.createAccessToken(email); // JwtService의 createAccessToken을 사용하여 AccessToken 발급
        String refreshToken = jwtService.createRefreshToken();

        normalUserRepository.findByEmail(email)
                .ifPresent(user -> {
                    user.updateRefreshToken(refreshToken);
                    normalUserRepository.saveAndFlush(user);});

        JsonObject tokens =new JsonObject();


        tokens.addProperty("accessToken", accessToken);
        tokens.addProperty("refreshToken", refreshToken);

        return tokens;
    }
    public Map<String, Object> searchKakaoUser(String token) {

        String reqURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();


            // access_token 전송
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token);

            //결과 코드가 200이면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //응답 메세지 읽기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            int id = element.getAsJsonObject().get("id").getAsInt();
            String email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            String name = element.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString();

            System.out.println("id : " + id);
            System.out.println("email : " + email);
            System.out.println("name : " + name);

            // 이거 성공하면 카카오 적용...

            br.close();

            Map<String, Object> context = new HashMap<>();
            context.put("id", id);
            context.put("email", email);
            context.put("name", name);

            return context;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
