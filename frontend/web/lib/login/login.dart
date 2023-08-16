import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:kakao_flutter_sdk/kakao_flutter_sdk.dart';
import 'package:web/MainController.dart';
import 'package:web/constants.dart';
import 'package:http/http.dart' as http;
import 'package:web/dto/HostUserSignUpDto.dart';
import 'package:web/dto/SignUpRequestDto.dart';
import 'package:web/login/LoginController.dart';

class LoginPage extends StatelessWidget {
  final MainController _mainController = Get.put<MainController>(MainController(), permanent: true, tag: 'MainController');

  Dio dio = Dio();

  @override
  Widget build(BuildContext context) {
    final screenSize = MediaQuery.of(context).size;

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
                      width: screenSize.width * 0.26,
                      height: screenSize.height * 0.8,
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
                await KakaoLogin(_mainController, dio);
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
            SizedBox(
              height: 100,
            ),
          ],
        ),
      ],
    );
  }

  Future<void> KakaoLogin(MainController _mainController, Dio dio) async {
    try {
      OAuthToken token = await UserApi.instance.loginWithKakaoAccount();

      _mainController.kAccessToken.value = token.accessToken;
      _mainController.kRefreshToken.value = token.refreshToken!;

      // 여기서 토큰을 서버에 넘긴다
      var url = dotenv.env['YOUR_SERVER_URL']! + 'api/host/login';

      var response = await dio.post(
        url,
        data: {'accessToken': _mainController.kAccessToken.value},
      );

      if (response.statusCode == 200) {
        // json을 파싱해서 토큰 저장
        Map<String, dynamic> responseData = response.data;
        print(responseData.toString());

        if (responseData['result'] == true) {
          _mainController.jAccessToken.value = responseData['accessToken'];
          _mainController.jRefreshToken.value = responseData['refreshToken'];
          _mainController.id.value = responseData['id'];
          Get.rootDelegate.toNamed(Routes.MYPAGE);
        } else {
          Get.rootDelegate.toNamed(Routes.LOGININFO);
        }
      } else {
        // 요청 실패 처리
        print('Request failed with status: ${response.statusCode}');
        print('Error message: ${response.data}');
      }
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
              style: TextStyle(fontWeight: FontWeight.w600, fontSize: 40),
            ),
            SizedBox(
              height: 8,
            ),
            Text("Login to access your HiFes account",
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
  final LoginController _loginController =
      Get.put<LoginController>(LoginController()); // 상태 컨트롤러 인스턴스 생성
  final MainController _mainController =
      Get.find<MainController>(tag: 'MainController');
  Dio dio = Dio();

  @override
  Widget build(BuildContext context) {
    final screenSize = MediaQuery.of(context).size;

    return Center(
      child: Scaffold(
        body: Center(
          child: SingleChildScrollView(
            child: Center(
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
                          style: TextStyle(
                              fontSize: 40, fontWeight: FontWeight.bold)),
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
                            shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                              RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(8.0),
                              ),
                            ),
                          ),
                          onPressed: () async {
                            SignUpRequestDto signUpRequestDto =
                                SignUpRequestDto(
                                    organization:
                                        _loginController.organization.value,
                                    orgCode: _loginController.orgCode.value,
                                    orgNo: _loginController.orgNo.value,
                                    phoneNo: _loginController.phoneNo.value,
                                    accessToken:
                                        _mainController.kAccessToken.value);
                            // 객체 생성해서 통신을 진행한다.

                            print(signUpRequestDto.toJson());

                            var url =
                                dotenv.env['YOUR_SERVER_URL']! + 'api/host/sign-up';

                            var response = await dio.post(url,
                                data: signUpRequestDto.toJson());

                            if (response.statusCode == 200) {
                              // json을 파싱해서 토큰 저장
                              Map<String, dynamic> responseData = response.data;

                              // 메인 컨트롤러에 토큰 저장
                              _mainController.jAccessToken.value =
                                  responseData['accessToken'];
                              _mainController.jRefreshToken.value =
                                  responseData['refreshToken'];
                              _mainController.id.value =
                                  responseData['id'];
                              // 마이페이지로 이동
                              Get.rootDelegate.toNamed(Routes.MYPAGE);
                            } else {
                              // 요청 실패 처리
                              print(
                                  'Request failed with status: ${response.statusCode}');
                              print('Error message: ${response.data}');
                            }
                          },
                          child: Text(
                            "등록하기",
                            style: TextStyle(color: Colors.white, fontSize: 16),
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
                            width: screenSize.width * 0.26,
                            height: screenSize.height * 0.8,
                            fit: BoxFit.cover,
                          )))
                ],
              ),
            ),
          ),
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
            onChanged: (value) {
              _loginController.organization.value = value;
            },
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
            onChanged: (value) {
              _loginController.orgCode.value = value;
            },
            decoration: InputDecoration(
              border: OutlineInputBorder(),
              labelText: '기관코드',
            ),
          ),
        ),
        SizedBox(
          height: 20,
        ),
        SizedBox(
          width: 400,
          child: TextField(
            onChanged: (value) {
              _loginController.orgNo.value = value;
            },
            keyboardType: TextInputType.number,
            inputFormatters: [FilteringTextInputFormatter.digitsOnly],
            // 숫자만 입력하도록 필터링
            decoration: InputDecoration(
              border: OutlineInputBorder(),
              labelText: '기관 전화번호',
            ),
          ),
        ),
        SizedBox(
          height: 20,
        ),
        SizedBox(
          width: 400,
          child: TextField(
            onChanged: (value) {
              _loginController.phoneNo.value = value;
            },
            keyboardType: TextInputType.number,
            inputFormatters: [FilteringTextInputFormatter.digitsOnly],
            // 숫자만 입력하도록 필터링
            decoration: InputDecoration(
              border: OutlineInputBorder(),
              labelText: '개인 전화번호',
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
                color: Colors.grey, fontSize: 20, fontWeight: FontWeight.w600),
          ),
          Text(
            "HIFES",
            style: TextStyle(
                fontSize: 80,
                fontWeight: FontWeight.w900,
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
