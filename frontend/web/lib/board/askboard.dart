import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:web/common.dart';

import '../constants.dart';
import 'board.dart';

class AskPage extends StatefulWidget {

  @override
  State<AskPage> createState() => _AskPageState();
}

class _AskPageState extends State<AskPage> {
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
              children: [
                AskItem(context),
              ],
            ),
          ),
        ),
      ),
    );
  }
}


Column AskItem(BuildContext context) {
  return Column(
    children: [
      SizedBox(height: 40,),

      Material(
        elevation: 4,
        borderRadius: BorderRadius.circular(8),
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 20.0),
          child: Container(
            child: Column(
              children: [
                SizedBox(height: 40,),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [Text("질문입니다", style: TextStyle(fontSize: 20),), Text("조회수")],
                ),
                SizedBox(height: 10,),
                Divider(
                  color: Colors.black, // 수평선 색상 설정
                  thickness: 2, // 수평선 두께 설정
                ),
                SizedBox(height: 10,),

                Align(
                  alignment: Alignment.topLeft,
                  child: Text("내용입니다"),
                ),
                SizedBox(
                  height: 40,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    ElevatedButton(
                        style: ButtonStyle(
                          backgroundColor: MaterialStateProperty.all<Color>(
                              AppColor.PrimaryPink),
                          minimumSize:
                          MaterialStateProperty.all<Size>(Size(200, 48)),
                        ),
                        onPressed: () {
                          showDialog(
                              context: context,
                              builder: (BuildContext context) {
                                return CommentDialog(context);
                              });
                        },
                        child: Text(
                          "답변하기",
                          style: TextStyle(color: Colors.white, fontSize: 16),
                        )),
                    Column(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [Text("2023-08-09"),
                        Container(child: Text("00:00"))],
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
                Comment(),
                Reply(),
                Reply(),
                Reply(),
                Reply(),
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

Expanded AskBoardList() {
  return Expanded(
    child: Column(
      children: [
        Padding(
          padding: const EdgeInsets.all(20.0),
          child: Row(children: [
            Text('질문게시판',
                style: TextStyle(
                    fontWeight: FontWeight.bold, fontSize: 40)),
          ]),
        ),
        Expanded(
          child: ListView.builder(
            // ListView.builder로 변경
            itemCount: 10, // 반복할 횟수를 지정
            itemBuilder: (context, index) {
              return Padding(
                // Padding으로 감싸서 좌우 padding 적용
                padding: EdgeInsets.symmetric(horizontal: 20),
                child: AskPostItem(),
              );
            },
          ),
        ),
      ],
    ),
  );
}

InkWell AskPostItem() {
  return InkWell(
    onTap: () {Get.rootDelegate.toNamed(Routes.ASK);},
    child: Container(
      child: Column(
        children: [
          SizedBox(height: 10),
          Row(
            children: [
              Text('심심한 10기를 위한 9기의 꿀팁'),
            ],
          ),
          SizedBox(
            height: 20,
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Container(
                child: Row(
                  children: [
                    Text('사용자'),
                    SizedBox(
                      width: 10,
                    ),
                    Text('일시')
                  ],
                ),
              ),
              Container(
                child: Row(
                  children: [
                    Text('조회수'),
                    SizedBox(width: 10,),
                    Text('댓글수')
                  ],
                ),
              )
            ],
          ),
          SizedBox(height: 10),
          Divider(
            height: 10,
          ),
        ],
      ),
    ),
  );
}

