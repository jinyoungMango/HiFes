import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';

import '../constants.dart';

class ReviewPage extends StatefulWidget {

  @override
  State<ReviewPage> createState() => _ReviewPageState();
}

class _ReviewPageState extends State<ReviewPage> {
  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}

Expanded ReviewBoardList() {
  return Expanded(
    child: Column(
      children: [
        Padding(
          padding: const EdgeInsets.all(20.0),
          child: Row(children: [
            Text('리뷰게시판',
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
                child: ReviewPostItem(),
              );
            },
          ),
        ),
      ],
    ),
  );
}

InkWell ReviewPostItem() {
  return InkWell(
    onTap: () {Get.rootDelegate.toNamed(Routes.REVIEW);},
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