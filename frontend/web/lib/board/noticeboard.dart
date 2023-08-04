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
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [NoticeItem()],
        ),
      ),
    );
  }
}

Column NoticeItem() {
  return Column(
    children: [
      Padding(
        padding: const EdgeInsets.all(80.0),
        child: Column(
          children: [
            Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: [Text('data')],),
            SizedBox(height: 20,),
            Container(
              height: 500,
              child: Column(
                children: [
                  Divider(
                    height: 10,
                  ),
                  SizedBox(height: 20,),
                  Column(
                    children: [
                      Text('공지 제목', style: TextStyle(fontSize: 24),),
                      SizedBox(height: 10,),
                      Row(mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Text('조회수'),
                          SizedBox(width: 20,),
                          Text('시간')
                        ],
                      ),
                      SizedBox(height: 20,),

                    ],
                  ),
                  Divider(
                    height: 10,
                  ),
                  Text('data'),
                ],
              ),
            ),
          ],
        ),
      ),
      SizedBox(
        height: 40,
      )
    ],
  );
}
