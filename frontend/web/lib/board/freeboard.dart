
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:web/common.dart';

import '../MainController.dart';
import '../constants.dart';
import 'PostDto.dart';
import 'PostWithCommentDto.dart';
import 'board.dart';

class FreePage extends StatefulWidget {
  @override
  State<FreePage> createState() => _FreePageState();
}

class _FreePageState extends State<FreePage> {

  final MainController _mainController =
  Get.find<MainController>(tag: 'MainController');
  late PostWithCommentDto free = PostWithCommentDto.empty();

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
          free = PostWithCommentDto.fromJson(response.data);
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
                children: [FreeItem(context, free)]),
          ),
        ),
      )
    );
  }
}

Column FreeItem(BuildContext context, PostWithCommentDto free) {
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
                  children: [Text("${free.title}", style: TextStyle(fontSize: 20),), Text("조회수 ${free.views}")],
                ),
                SizedBox(height: 10,),
                Divider(
                  color: Colors.black, // 수평선 색상 설정
                  thickness: 2, // 수평선 두께 설정
                ),
                SizedBox(height: 10,),

                Align(
                  alignment: Alignment.topLeft,
                  child: Text("${free.content}", style: TextStyle(fontSize: 16)),
                ),
                SizedBox(
                  height: 40,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.end,
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [Text("${free.createdAt.date}", style: TextStyle(color: Colors.black, fontWeight: FontWeight.w300),),
                        SizedBox(height: 4,),
                        Container(child: Text("${free.createdAt.time}", style: TextStyle(color: Colors.black, fontWeight: FontWeight.w300),)),
                      SizedBox(height: 20,)],

                    ),
                  ],
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    ElevatedButton(
                        style: ButtonStyle(
                          backgroundColor: MaterialStateProperty.all<Color>(
                              AppColor.PrimaryPink),
                          minimumSize:
                          MaterialStateProperty.all<Size>(Size(200, 48)),
                          shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                            RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(8.0),
                            ),
                          ),
                        ),
                        onPressed: () {
                          showDialog(
                              context: context,
                              builder: (BuildContext context) {
                                return RemovePostDialog(context, free);
                              });
                        },
                        child: Text(
                          "삭제하기",
                          style: TextStyle(color: Colors.white, fontSize: 16),
                        )),
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
                // 댓글, 대댓글 가져오기
                for (var comment in free.topLevelComments)
                  Column(
                    children: [
                      Comment(context, comment, free.postType),
                      for (var reply in comment.childComments)
                        Reply(context, reply, free.postType)
                    ],
                  )
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

Column FreeBoardList(BuildContext context, List<PostDto> free, MainController _mainController) {
  return Column(
    children: [
      Padding(
        padding: const EdgeInsets.all(20.0),
        child: Row(children: [
          Text('자유게시판',
              style: TextStyle(
                  fontWeight: FontWeight.bold, fontSize: 40)),
        ]),
      ),
      Padding(
        padding: const EdgeInsets.all(20.0),
        child: Column(
            children : free.map((post) => FreePostItem(post, _mainController)).toList()
        ),
      )
    ],
  );
}

InkWell FreePostItem(PostDto free, MainController _mainController) {
  return InkWell(
    onTap: () {
      _mainController.pid.value = free.id;
      Get.rootDelegate.toNamed(Routes.FREE);
    },
    child: Container(
      child: Column(
        children: [
          SizedBox(height: 10),
          Row(
            children: [
              Text(free.title, style: TextStyle(fontSize: 20),),
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
                    Text('${free.writer}'),
                    SizedBox(
                      width: 10,
                    ),
                    Text('${free.createdAt.date}      ${free.createdAt.time}', style: TextStyle(color: Colors.black, fontWeight: FontWeight.w300),)
                  ],
                ),
              ),
              Container(
                child: Row(
                  children: [
                    Text('조회수  ${free.views}'),
                    SizedBox(width: 10,),
                    Text('댓글수  ${free.commentsCount}')
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

