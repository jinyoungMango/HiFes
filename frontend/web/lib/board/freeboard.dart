
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:web/common.dart';

import '../constants.dart';
import 'PostDto.dart';
import 'board.dart';

class FreePage extends StatefulWidget {
  @override
  State<FreePage> createState() => _FreePageState();
}

class _FreePageState extends State<FreePage> {
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
                children: [FreeItem(context)]),
          ),
        ),
      )
    );
  }
}

Column FreeItem(BuildContext context) {
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

Expanded FreeBoardList(List<PostDto> free) {
  return Expanded(
    child: Column(
      children: [
        Padding(
          padding: const EdgeInsets.all(20.0),
          child: Row(children: [
            Text('자유게시판',
                style: TextStyle(
                    fontWeight: FontWeight.bold, fontSize: 40)),
          ]),
        ),
        Column(
            children : free.map((post) => FreePostItem(post)).toList()
        )
      ],
    ),
  );
}

InkWell FreePostItem(PostDto free) {
  return InkWell(
    onTap: () {
      Get.rootDelegate.toNamed(Routes.FREE);
    },
    child: Container(
      child: Column(
        children: [
          SizedBox(height: 10),
          Row(
            children: [
              Text(free.title),
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
                    Text('조회수 ${free.views}'),
                    SizedBox(width: 10,),
                    Text('댓글수 ${free.commentsCount}')
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

