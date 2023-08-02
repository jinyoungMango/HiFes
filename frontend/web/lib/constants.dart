import 'package:flutter/material.dart';
import 'package:get/get_navigation/src/routes/get_route.dart';
import 'package:web/board/board.dart';
import 'package:web/board/noticeboard.dart';
import 'package:web/mypage/mypage.dart';

import 'board/askboard.dart';
import 'board/freeboard.dart';
import 'board/reviewboard.dart';
import 'login/login.dart';

class AppColor {
  static const Color PrimaryPink = Color(0xFFF11A7B);
}

abstract class Routes {
  static const LOGIN = '/';
  static const LOGININFO = '/logininfo';
  static const MYPAGE = '/mypage';
  static const BOARD = '/board';
  static const NOTICE = '/board/notice';
  static const ASK = '/board/ask';
  static const FREE = '/board/free';
  static const REVIEW = '/board/review';
}

abstract class AppPages {
  static final pages = [
    GetPage(name: Routes.LOGIN, page: () => LoginPage(),),
    GetPage(name: Routes.LOGININFO, page: () => LoginInfo(),),
    GetPage(name: Routes.MYPAGE, page: () => MyPage()),
    GetPage(name: Routes.BOARD, page: () => Board(),),
    GetPage(name: Routes.NOTICE, page: () => NoticePage()),
    GetPage(name: Routes.ASK, page: () => AskPage()),
    GetPage(name: Routes.FREE, page: () => FreePage()),
    GetPage(name: Routes.REVIEW, page: () => ReviewPage()),
  ];
}
