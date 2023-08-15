import 'package:flutter/material.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:get/get_instance/src/bindings_interface.dart';
import 'package:get/get_navigation/src/root/get_material_app.dart';
import 'package:get/get_navigation/src/routes/transitions_type.dart';
import 'package:kakao_flutter_sdk/kakao_flutter_sdk.dart';
import 'package:web/login/login.dart';

import 'GetMaterialApp.dart';
import 'MainController.dart';
import 'constants.dart';

void main() async{
  // 웹 환경에서 카카오 로그인을 정상적으로 완료하려면 runApp() 호출 전 아래 메서드 호출 필요
  WidgetsFlutterBinding.ensureInitialized();
  await dotenv.load(fileName: ".env");	// 추가

  KakaoSdk.init(
    javaScriptAppKey: dotenv.env['YOUR_JAVASCRIPT_APP_KEY'],
  );

  runApp(GetMaterialApp.router(
    debugShowCheckedModeBanner: false,
    defaultTransition: Transition.fade,
    getPages: AppPages.pages,
    routerDelegate: AppRouterDelegate(),theme: ThemeData(fontFamily: 'Pretendard'),
  ));
}


