import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:kakao_flutter_sdk/kakao_flutter_sdk.dart';
import 'package:web/constants.dart';
import 'package:http/http.dart' as http;

class LoginPage extends StatelessWidget {
  const LoginPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisSize: MainAxisSize.max,
          children: [
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                LoginTitle(),
                LoginText(),
                LoginButton(),
              ],
            ),
            SizedBox(
              width: 100,
            ),
            Center(
                child: ClipRRect(
                    borderRadius: BorderRadius.circular(200),
                    child: Image.network(
                      "https://i.imgur.com/05SMxGu.jpeg",
                      width: 400,
                      height: 600,
                      fit: BoxFit.cover,
                    )))
          ],
        ),
      ),
    );
  }

  Row LoginButton() {
    return Row(
      children: [
        Column(
          children: [
            InkWell(
              onTap: () async {
                await KakaoLogin();
              },
              child: Container(
                child: Padding(
                  padding: const EdgeInsets.all(4.0),
                  child: SvgPicture.asset(
                    '/kakao.svg',
                    width: 200,
                    height: 200,
                  ),
                ),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(4), // 테두리를 둥글게 만드는 반지름 값
                  border: Border.all(
                    color: Colors.amber, // 테두리 색깔
                    width: 2, // 테두리 두께
                  ),
                ),
                width: 400,
                height: 40,
              ),
            ),
            const SizedBox(
              height: 20,
            ),
            InkWell(
              onTap: () => Get.rootDelegate.toNamed(Routes.LOGININFO),
              child: Container(
                child: Padding(
                  padding: const EdgeInsets.all(4.0),
                  child: SvgPicture.asset(
                    '/naver.svg',
                    width: 200,
                    height: 200,
                  ),
                ),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(4), // 테두리를 둥글게 만드는 반지름 값
                  border: Border.all(
                    color: Colors.green, // 테두리 색깔
                    width: 2, // 테두리 두께
                  ),
                ),
                width: 400,
                height: 40,
              ),
            ),
            SizedBox(
              height: 100,
            ),
          ],
        ),
      ],
    );
  }

  Future<void> KakaoLogin() async {
    try {
      OAuthToken token = await UserApi.instance.loginWithKakaoAccount();
      print('카카오계정으로 로그인 성공 ${token.accessToken}');

      // 여기서 토큰을 서버에 넘긴다
      var url = Uri.http('192.168.100.178:8080', 'host/login');
      var response = await http.post(url, body: {'accessToken' : token.accessToken});

      print('Response status: ${response.statusCode}');
      print('Response body: ${response.body}');

      // 계정과 관련된 데이터를 DB에서 가져온다.
      // 추가정보가 입력되어 있으면 메인 화면으로 이동한다.
      // 그렇지 않으면 추가정보 입력란으로 이동한다.
      // if ..
    } catch (error) {
      print('카카오계정으로 로그인 실패 $error');

    }
  }

  Row LoginText() {
    return const Row(
      children: [
        Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              "Login",
              textAlign: TextAlign.start,
              style: TextStyle(fontWeight: FontWeight.bold, fontSize: 40),
            ),
            SizedBox(
              height: 8,
            ),
            Text("Login to access your Hi-Fes account",
                style: TextStyle(color: Colors.grey)),
            SizedBox(
              height: 40,
            ),
          ],
        ),
      ],
    );
  }
}

class LoginInfo extends StatelessWidget {
  const LoginInfo({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisSize: MainAxisSize.max,
          children: [
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                LoginTitle(),
                Text("추가 정보 입력",
                    style:
                        TextStyle(fontSize: 40, fontWeight: FontWeight.bold)),
                SizedBox(
                  height: 20,
                ),
                InfoTextField(),
                SizedBox(
                  height: 40,
                ),
                ElevatedButton(
                    style: ButtonStyle(
                      backgroundColor: MaterialStateProperty.all<Color>(
                          AppColor.PrimaryPink),
                      minimumSize:
                          MaterialStateProperty.all<Size>(Size(400, 48)),
                    ),
                    onPressed: () {
                      Get.rootDelegate.toNamed(Routes.MYPAGE);
                    },
                    child: Text(
                      "등록하기",
                      style: TextStyle(color: Colors.white),
                    ))
              ],
            ),
            SizedBox(
              width: 100,
            ),
            Center(
                child: ClipRRect(
                    borderRadius: BorderRadius.circular(200),
                    child: Image.network(
                      "https://i.imgur.com/05SMxGu.jpeg",
                      width: 400,
                      height: 600,
                      fit: BoxFit.cover,
                    )))
          ],
        ),
      ),
    );
  }

  Column InfoTextField() {
    return Column(
      children: [
        SizedBox(
          width: 400,
          child: TextField(
            decoration: InputDecoration(
              border: OutlineInputBorder(),
              labelText: '기관명',
            ),
          ),
        ),
        SizedBox(
          height: 20,
        ),
        SizedBox(
          width: 400,
          child: TextField(
            keyboardType: TextInputType.number,
            inputFormatters: [FilteringTextInputFormatter.digitsOnly],
            // 숫자만 입력하도록 필터링
            decoration: InputDecoration(
              border: OutlineInputBorder(),
              labelText: '전화번호',
            ),
          ),
        ),
        SizedBox(
          height: 20,
        ),
        SizedBox(
          width: 400,
          child: TextField(
            decoration: InputDecoration(
              border: OutlineInputBorder(),
              labelText: '이메일',
            ),
          ),
        ),
      ],
    );
  }
}

Row LoginTitle() {
  return Row(
    children: [
      const Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            "축제를 즐겨요",
            style: TextStyle(
                color: Colors.grey, fontSize: 20, fontWeight: FontWeight.bold),
          ),
          Text(
            "HI-FES",
            style: TextStyle(
                fontSize: 80,
                fontWeight: FontWeight.bold,
                color: AppColor.PrimaryPink),
          )
        ],
      ),
      SizedBox(
        width: 80,
      ),
      SvgPicture.asset(
        '/logo.svg',
        width: 200,
        height: 160,
      ),
      SizedBox(
        width: 80,
      ),
    ],
  );
}
