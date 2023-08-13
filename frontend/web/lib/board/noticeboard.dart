import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:web/common.dart';

import '../constants.dart';

class NoticePage extends StatefulWidget {
  @override
  State<NoticePage> createState() => _NoticePageState();
}

class _NoticePageState extends State<NoticePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: TopBar(),
      body: SingleChildScrollView(
        child: Center(
          child: Container(
            width: 800,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [NoticeItem(context)],
            ),
          ),
        ),
      ),
    );
  }
}

Column NoticeItem(BuildContext context) {
  return Column(
    children: [
      SizedBox(
        height: 40,
      ),
      Material(
        elevation: 4,
        borderRadius: BorderRadius.circular(8),
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 20.0),
          child: Container(
            child: Column(
              children: [
                SizedBox(
                  height: 40,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(
                      "공지입니다",
                      style: TextStyle(fontSize: 20),
                    ),
                    Text("조회수")
                  ],
                ),
                SizedBox(
                  height: 10,
                ),
                Divider(
                  color: Colors.black, // 수평선 색상 설정
                  thickness: 2, // 수평선 두께 설정
                ),
                SizedBox(
                  height: 10,
                ),
                Align(
                  alignment: Alignment.topLeft,
                  child: Text(
                      "다람쥐쳇바퀴굴러간다\n다람쥐쳇바퀴굴러간다\n다람쥐쳇바퀴굴러간다\n다람쥐쳇바퀴굴러간다\n다람쥐쳇바퀴굴러간다\n다람쥐쳇바퀴굴러간다\n다람쥐쳇바퀴굴러간다\n다람쥐쳇바퀴굴러간다\n다람쥐쳇바퀴굴러간다\n다람쥐쳇바퀴굴러간다\n"),
                ),
                SizedBox(
                  height: 40,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    Column(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Text("2023-08-09"),
                        Container(child: Text("00:00"))
                      ],
                    ),
                  ],
                ),
                SizedBox(
                  height: 20,
                ),
                Divider(
                  color: Colors.grey, // 수평선 색상 설정
                  thickness: 20, // 수평선 두께 설정
                ),
                SizedBox(
                  height: 20,
                ),
              ],
            ),
          ),
        ),
      ),
      SizedBox(
        height: 40,
      )
    ],
  );
}
