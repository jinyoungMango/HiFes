import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';

import 'constants.dart';

AppBar TopBar() {
  return AppBar(
    backgroundColor: Colors.white,
    leading: SvgPicture.asset(
      '/logo.svg',
      width: 200,
      height: 160,
    ), // 로고 아이콘 또는 이미지를 넣어주면 됩니다.
    title: Text(
      'HIFES',
      style: TextStyle(
          color: AppColor.PrimaryPink,
          fontWeight: FontWeight.bold,
          fontSize: 40),
    ), // 회사명 텍스트를 넣어주면 됩니다.
    actions: [
      Row(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Text(
            "로그아웃",
            style: TextStyle(color: Colors.black),
          ),
          SizedBox(
            width: 16,
          ),
          Text(
            "|",
            style: TextStyle(color: Colors.black),
          ),
          SizedBox(
            width: 16,
          ),
          Text(
            "사용자 이름",
            style: TextStyle(color: Colors.black),
          ),
          SizedBox(
            width: 16,
          ),
        ],
      )
    ],
  );
}

BottomAppBar BottomBar() {
  return BottomAppBar(
    child: Container(
      color: AppColor.PrimaryPink,
      height: 80.0,
    ),
  );
}