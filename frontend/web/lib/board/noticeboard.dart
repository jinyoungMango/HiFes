import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:web/board/PostWithCommentDto.dart';
import 'package:web/common.dart';

import '../MainController.dart';
import '../constants.dart';
import 'board.dart';

class NoticePage extends StatefulWidget {
  @override
  State<NoticePage> createState() => _NoticePageState();
}

class _NoticePageState extends State<NoticePage> {
  final MainController _mainController =
      Get.find<MainController>(tag: 'MainController');
  late PostWithCommentDto notice = PostWithCommentDto.empty();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    // 게시글 정보 가져오기
    Future.delayed(Duration.zero, () async {
      var url = dotenv.env['YOUR_SERVER_URL']! +
          'api/post/get/${_mainController.pid.value}';

      // 공지 정보 받아오기
      var response = await Dio().get(url);

      if (response.statusCode == 200) {
        // print('Request succeeded: ${response.data}');
        // 사용자 정보 json을 파싱해서 토큰 저장
        setState(() {
          notice = PostWithCommentDto.fromJson(response.data);
        });
      } else {
        // 요청 실패 처리
        print('Request failed with status: ${response.statusCode}');
        print('Error message: ${response.data}');
      }
    });
  }

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
                NoticeItem(context, notice),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

Column NoticeItem(BuildContext context, PostWithCommentDto notice) {
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
                      notice.title,
                      style: TextStyle(fontSize: 20),
                    ),
                    Text('조회수 ${notice.views}')
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
                  child: Text('${notice.content}'),
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
                        Text("${notice.createdAt.date} "),
                        Container(child: Text("${notice.createdAt.time}")),
                        SizedBox(height: 20,),
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
                                    return RemovePostDialog(context, notice);
                                  }).then((value) {
                                    if (value != null) {
                                    }
                              });
                            },
                            child: Text(
                              "삭제하기",
                              style: TextStyle(color: Colors.white, fontSize: 16),
                            )),
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
