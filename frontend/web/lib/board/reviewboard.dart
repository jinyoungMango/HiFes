import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';

import '../constants.dart';
import 'PostDto.dart';

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

Expanded ReviewBoardList(List<PostDto> review) {
  return Expanded(
    child: Column(
      children: [
        Padding(
          padding: const EdgeInsets.all(20.0),
          child: Row(children: [
            Text('후기게시판',
                style: TextStyle(
                    fontWeight: FontWeight.bold, fontSize: 40)),
          ]),
        ),
        Column(
            children : review.map((post) => ReviewPostItem(post)).toList()
        )
      ],
    ),
  );
}

InkWell ReviewPostItem(PostDto review) {
  return InkWell(
    onTap: () {Get.rootDelegate.toNamed(Routes.REVIEW);},
    child: Container(
      child: Column(
        children: [
          SizedBox(height: 10),
          Row(
            children: [
              Text(review.title),
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
                    Text('조회수 ${review.views}'),
                    SizedBox(width: 10,),
                    Text('댓글수 ${review.commentsCount}')
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