import 'package:flutter/material.dart';
import 'package:get/get_navigation/src/routes/get_route.dart';

import 'login.dart';

class AppColor {
  static const Color PrimaryPink = Color(0xFFF11A7B);
}

abstract class Routes {
  static const LOGIN = '/';
  static const LOGININFO = '/logininfo';
}

abstract class AppPages {
  static final pages = [
    GetPage(
      name: Routes.LOGIN,
      page: () => LoginPage(),
    ),
    GetPage(
      name: Routes.LOGININFO,
      page: () => LoginInfo(),
    ),
  ];
}