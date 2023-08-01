package hiFes.hiFes.user.util;

import java.util.Random;

public class PasswordUtil {

    public static String generateRandomPassword() {
        int index = 0;
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };

        StringBuffer password = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < 8 ; i++) {
            double rd = random.nextDouble();
            index = (int) (charSet.length * rd);

            password.append(charSet[index]);
        }
        System.out.println(password);
        return password.toString();
        //StringBuffer를 String으로 변환해서 return 하려면 toString()을 사용하면 된다는 말 이해 필요...
        // 소셜 로그인 유저에게도 랜덤 비밀번호 부여하기 위함임. 그래야 통과가 될 테니까.
    }
}