import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:flutter_svg/svg.dart';
import 'package:get/get.dart';
import 'package:web/board/PostDto.dart';
import 'package:web/board/PostWithCommentDto.dart';
import 'package:web/board/freeboard.dart';
import 'package:web/board/reviewboard.dart';
import 'package:web/common.dart';
import 'package:http/http.dart' as http;
import '../MainController.dart';
import '../constants.dart';
import 'askboard.dart';
import 'package:http_parser/http_parser.dart'; // MediaType import 추가
import 'noticeboard.dart';

class Board extends StatefulWidget {
  @override
  _BoardState createState() => _BoardState();
}

class _BoardState extends State<Board> {
  String selectedValue = '공지';
  final MainController _mainController =
      Get.find<MainController>(tag: 'MainController');

  // 공지, 질문, 자유, 후기글을 종류별로 리스트에 담는다.
  List<PostDto> notice = [];
  List<PostDto> ask = [];
  List<PostDto> free = [];
  List<PostDto> review = [];

  @override
  void initState() {
    super.initState();

    // 게시글 정보 다 가져오기
    Future.delayed(Duration.zero, () async {
      var url = dotenv.env['YOUR_SERVER_URL']! +
          'api/post/${_mainController.fid.value}';

      // 축제 정보 받아오기
      var response = await Dio().get(url);

      if (response.statusCode == 200) {
        // 요청 성공 처리
        print('Request succeeded: ${response.data}');
        // 사용자 정보 json을 파싱해서 토큰 저장

        setState(() {
          for (var data in response.data) {
            if (data['postType'] == "notice") {
              notice.add(PostDto.fromJson(data));
            } else if (data['postType'] == "ask") {
              ask.add(PostDto.fromJson(data));
            } else if (data['postType'] == "free") {
              free.add(PostDto.fromJson(data));
            } else if (data['postType'] == "review") {
              review.add(PostDto.fromJson(data));
            }
          }
        });
      } else {
        // 요청 실패 처리
        print('Request failed with status: ${response.statusCode}');
        print('Error message: ${response.data}');
      }
    }).then((value) {
      setState(() {});
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: TopBar(),
      body: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 80),
        child: SingleChildScrollView(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              Center(
                child: Row(
                  children: [
                    SizedBox(
                      width: 20,
                    ),
                    Align(
                      alignment: Alignment.topLeft,
                      child: DropdownButton<String>(
                        value: selectedValue,
                        items: [
                          DropdownMenuItem<String>(
                            value: '공지',
                            child: Text('공지'),
                          ),
                          DropdownMenuItem<String>(
                            value: '질문',
                            child: Text('질문'),
                          ),
                          DropdownMenuItem<String>(
                            value: '자유',
                            child: Text('자유'),
                          ),
                          DropdownMenuItem<String>(
                            value: '후기',
                            child: Text('후기'),
                          ),
                        ],
                        onChanged: (newValue) {
                          setState(() {
                            selectedValue = newValue!;
                          });
                        },
                      ),
                    ),
                  ],
                ),
              ),
              SizedBox(height: 20),
              if (selectedValue == '공지')
                NoticeBoardList(context, notice, _mainController)
              else if (selectedValue == '질문')
                AskBoardList(context, ask, _mainController)
              else if (selectedValue == '자유')
                FreeBoardList(context, free, _mainController)
              else if (selectedValue == '후기')
                ReviewBoardList(context, review, _mainController)
            ],
          ),
        ),
      ),
    );
  }
}

Column NoticeBoardList(BuildContext context, List<PostDto> notice,
    MainController _mainController) {
  return Column(
    children: [
      Padding(
        padding: const EdgeInsets.all(20.0),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(
              '공지게시판',
              style: TextStyle(fontWeight: FontWeight.bold, fontSize: 40),
            ),
            ElevatedButton(
                style: ButtonStyle(
                  backgroundColor:
                      MaterialStateProperty.all<Color>(AppColor.PrimaryPink),
                  minimumSize: MaterialStateProperty.all<Size>(Size(200, 48)),
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
                        return NoticeDialog(context, _mainController);
                      });
                },
                child: Text(
                  "등록하기",
                  style: TextStyle(color: Colors.white, fontSize: 16),
                )),
          ],
        ),
      ),
      Padding(
        padding: const EdgeInsets.all(20.0),
        child: Column(
            children: notice
                .map((post) => NoticePostItem(post, _mainController))
                .toList()),
      ),
    ],
  );
}

AlertDialog NoticeDialog(BuildContext context, MainController _mainController) {
  String title = ''; // 변수로 제목 설정
  String content = ''; // 변수로 내용 설정

  return AlertDialog(
    title: Text('공지사항'),
    content: Column(
      mainAxisSize: MainAxisSize.min, // 다이얼로그의 높이를 내용에 맞게 조절
      children: [
        Container(
          width: 400, // 원하는 width 값으로 설정
          child: TextField(
            onChanged: (value) {
              title = value; // 제목 값 업데이트
            },
            maxLines: 1, // maxLines를 null로 설정하여 height를 가변적으로 만듦
            decoration: InputDecoration(
              border: OutlineInputBorder(),
              labelText: '제목',
            ),
          ),
        ),
        SizedBox(
          height: 40,
        ),
        Container(
          width: 400,
          height: 250, // 기본 height 값을 200으로 설정
          child: Column(
            children: [
              Expanded(
                child: TextField(
                  onChanged: (value) {
                    content = value; // 내용 값 업데이트
                  },
                  maxLines: 1000,
                  decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: '내용',
                  ),
                ),
              ),
            ],
          ),
        ),
        SizedBox(
          height: 20,
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
                style: ButtonStyle(
                  backgroundColor:
                      MaterialStateProperty.all<Color>(AppColor.PrimaryPink),
                  minimumSize: MaterialStateProperty.all<Size>(Size(200, 48)),
                  shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                    RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(8.0),
                    ),
                  ),
                ),
                onPressed: () async {
                  // 등록하고 pop
                  var url = Uri.parse(dotenv.env['YOUR_SERVER_URL']! + 'api/post/create');

                  var request = http.MultipartRequest('POST', url);

                  // JSON 데이터를 http.MultipartFile 형태로 생성하여 추가
                  var jsonBody = {
                      "postType": "notice",
                      "title": title,
                      "content": content,
                      "createdBy": _mainController.id.value,
                      "imagePath": null,
                      "isHidden": false,
                      "festivalId": _mainController.fid.value,
                      "rating": null
                  };

                  var jsonPart = http.MultipartFile.fromString(
                    'data',
                    jsonEncode(jsonBody),
                    filename: 'data',
                    contentType: MediaType('application', 'json'),
                  );

                  request.files.add(jsonPart);

                  // 축제 정보 받아오기
                  var response = await request.send();

                  if (response.statusCode == 201) {
                    // 푸시 알람 보내기
                    var url2 = dotenv.env['YOUR_SERVER_URL']! + 'api/fcm/for_all';

                    var request = await Dio().post(url2, data: {
                      'festivalId': _mainController.fid.value,
                      'title': title,
                      'detail': content,
                    });

                    if (request.statusCode == 200) {
                      // 요청 성공 처리
                      Navigator.pop(context, true);
                      Get.rootDelegate.offNamed(Routes.BOARD);
                    }

                  } else {
                    // 요청 실패 처리
                    print('Request failed with status: ${response.statusCode}');
                  }
                  Navigator.of(context).pop();

                },
                child: Text(
                  "등록하기",
                  style: TextStyle(color: Colors.white),
                )),
            SizedBox(
              width: 20,
            ),
            ElevatedButton(
              style: ButtonStyle(
                backgroundColor: MaterialStateProperty.all<Color>(Colors.white),
                minimumSize: MaterialStateProperty.all<Size>(Size(200, 48)),
                foregroundColor:
                    MaterialStateProperty.all<Color>(Colors.black), // 텍스트 색상
                shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                  RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(8.0),
                    // 버튼의 모서리를 둥글게 설정
                    side: BorderSide(color: AppColor.PrimaryPink), // 테두리 색상
                  ),
                ),
              ),
              onPressed: () async {
                Navigator.of(context).pop();
              },
              child: Text(
                "취소",
                style: TextStyle(color: Colors.black), // 텍스트 색상
              ),
            )
          ],
        )
      ],
    ),
  );
}

InkWell NoticePostItem(PostDto post, MainController _mainController) {
  return InkWell(
    onTap: () {
      _mainController.pid.value = post.id;
      Get.rootDelegate.toNamed(Routes.NOTICE);
    },
    child: Container(
      child: Column(
        children: [
          SizedBox(height: 10),
          Row(
            children: [
              Text(post.title),
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
                    Text('${post.writer}'),
                    SizedBox(
                      width: 10,
                    ),
                    Text('${post.createdAt.date}      ${post.createdAt.time}', style: TextStyle(color: Colors.black, fontWeight: FontWeight.w300),)
                  ],
                ),
              ),
              Container(
                child: Row(
                  children: [
                    Text('조회수  ${post.views}'),
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

Container Comment(BuildContext context, CommentDto comment, String type) {
  return Container(
    child: Column(
      children: [
        Row(
          mainAxisSize: MainAxisSize.max,
          children: [
            SvgPicture.asset('/profile.svg'),
            SizedBox(width: 20),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    children: [
                      Text('${comment.writer}'),
                      SizedBox(width: 10),
                      Text(
                          '${comment.createdAt.date.toString()}   ${comment.createdAt.time.toString()}', style: TextStyle(color: Colors.black, fontWeight: FontWeight.w300),),
                    ],
                  ),
                  SizedBox(height: 20),
                  Text("${comment.content}"),
                ],
              ),
            ),
            SizedBox(
              width: 20,
            ),
            Container(
              decoration: BoxDecoration(
                border: Border.all(
                  color: Colors.grey, // Border color
                  width: 2, // Border width
                ),
              ),
              child: Padding(
                padding: const EdgeInsets.all(8.0),
                child: Row(
                  children: [
                    Text('답글 달기'),
                    SizedBox(
                      width: 8,
                    ),
                    Container(
                      width: 1, // 구분선 두께
                      height: 20, // 구분선 높이
                      color: Colors.black, // 구분선 색상
                    ),
                    SizedBox(
                      width: 8,
                    ),
                    Text('수정'),
                    SizedBox(
                      width: 8,
                    ),
                    Container(
                      width: 1, // 구분선 두께
                      height: 20, // 구분선 높이
                      color: Colors.black, // 구분선 색상
                    ),
                    SizedBox(
                      width: 8,
                    ),
                    InkWell(
                      onTap: () async {
                        showDialog(
                          context: context,
                          builder: (BuildContext context) {
                            return RemoveCommentDialog(context, comment, type);
                          },
                        ).then((value) {
                          if (value != null && value) {

                          }
                        });
                      },
                      child: Text(
                        '삭제',
                      ),
                    )
                  ],
                ),
              ),
            )
          ],
        ),
        SizedBox(
          height: 20,
        ),
      ],
    ),
  );
}

Container Reply(BuildContext context, CommentDto reply, String type) {
  return Container(
    child: Column(
      children: [
        Row(
          mainAxisSize: MainAxisSize.max,
          children: [
            SizedBox(
              width: 20,
            ),
            SvgPicture.asset('reply.svg'),
            SizedBox(
              width: 20,
            ),
            SvgPicture.asset('/profile.svg'),
            SizedBox(width: 20),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    children: [
                      Text('${reply.writer}'),
                      SizedBox(width: 10),
                      Align(
                        alignment: Alignment.centerRight,
                        child: Text(
                            '${reply.createdAt.date.toString()}   ${reply.createdAt.time.toString()}', style: TextStyle(color: Colors.black)),
                      )
                    ],
                  ),
                  SizedBox(height: 20),
                  Text("${reply.content}"),
                ],
              ),
            ),
            SizedBox(
              width: 20,
            ),
            Container(
              decoration: BoxDecoration(
                border: Border.all(
                  color: Colors.grey, // Border color
                  width: 2, // Border width
                ),
              ),
              child: Padding(
                padding: const EdgeInsets.all(8.0),
                child: Row(
                  children: [
                    Text('답글 달기'),
                    SizedBox(
                      width: 8,
                    ),
                    Container(
                      width: 1, // 구분선 두께
                      height: 20, // 구분선 높이
                      color: Colors.black, // 구분선 색상
                    ),
                    SizedBox(
                      width: 8,
                    ),
                    Text('수정'),
                    SizedBox(
                      width: 8,
                    ),
                    Container(
                      width: 1, // 구분선 두께
                      height: 20, // 구분선 높이
                      color: Colors.black, // 구분선 색상
                    ),
                    SizedBox(
                      width: 8,
                    ),
                    InkWell(
                      onTap: () async {
                        showDialog(
                          context: context,
                          builder: (BuildContext context) {
                            return RemoveCommentDialog(context, reply, type);
                          },
                        ).then((value) {
                          if (value != null && value) {
                            Get.back();
                          }
                        });
                      },
                      child: Text(
                        '삭제',
                      ),
                    )
                  ],
                ),
              ),
            )
          ],
        ),
        SizedBox(
          height: 20,
        ),
      ],
    ),
  );
}

// ASK에서만 사용함.
AlertDialog CommentDialog(
    BuildContext context, MainController _mainController) {
  var content = "";

  return AlertDialog(
    title: Text('댓글 작성'),
    content: Column(
      mainAxisSize: MainAxisSize.min, // 다이얼로그의 높이를 내용에 맞게 조절
      children: [
        Container(
          width: 400,
          height: 200, // 기본 height 값을 200으로 설정
          child: Column(
            children: [
              Expanded(
                child: TextField(
                  onChanged: (value) {
                    content = value;
                  },
                  maxLines: 500,
                  decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: '내용',
                  ),
                ),
              ),
            ],
          ),
        ),
        SizedBox(
          height: 20,
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
                style: ButtonStyle(
                  backgroundColor:
                      MaterialStateProperty.all<Color>(AppColor.PrimaryPink),
                  minimumSize: MaterialStateProperty.all<Size>(Size(200, 48)),
                  shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                    RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(8.0),
                    ),
                  ),
                ),
                onPressed: () async {
                  // 댓글 작성하기
                  var url = dotenv.env['YOUR_SERVER_URL']! + 'api/comment/create';

                  var response = await Dio().post(url, data: {
                    'postId': _mainController.pid.value,
                    'content': content,
                    'parentId': null,
                    'createdBy': _mainController.id.value
                  });

                  if (response.statusCode == 200) {
                    Navigator.pop(context, true);
                    Get.rootDelegate.offNamed(Routes.ASK); // ASK 화면으로 이동
                  } else {
                    // 요청 실패 처리
                    print('Request failed with status: ${response.statusCode}');
                    print('Error message: ${response.data}');
                  }

                  Navigator.of(context).pop();
                },
                child: Text(
                  "등록하기",
                  style: TextStyle(color: Colors.white),
                )),
            SizedBox(
              width: 20,
            ),
            ElevatedButton(
              style: ButtonStyle(
                backgroundColor: MaterialStateProperty.all<Color>(Colors.white),
                minimumSize: MaterialStateProperty.all<Size>(Size(200, 48)),
                foregroundColor:
                    MaterialStateProperty.all<Color>(Colors.black), // 텍스트 색상
                shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                  RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(8.0),
                    // 버튼의 모서리를 둥글게 설정
                    side: BorderSide(color: AppColor.PrimaryPink), // 테두리 색상
                  ),
                ),
              ),
              onPressed: () async {
                Navigator.of(context).pop();
              },
              child: Text(
                "취소",
                style: TextStyle(color: Colors.black), // 텍스트 색상
              ),
            )
          ],
        )
      ],
    ),
  );
}

// post의 id값으로 게시글을 삭제하는 함수
AlertDialog RemovePostDialog(BuildContext context, PostWithCommentDto post) {
  return AlertDialog(
    title: Text('정말 삭제하시겠습니까?'),
    content: Column(
      mainAxisSize: MainAxisSize.min, // 다이얼로그의 높이를 내용에 맞게 조절
      children: [
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
                style: ButtonStyle(
                  backgroundColor:
                      MaterialStateProperty.all<Color>(AppColor.PrimaryPink),
                  minimumSize: MaterialStateProperty.all<Size>(Size(200, 48)),
                  shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                    RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(8.0),
                    ),
                  ),
                ),
                onPressed: () async {
                  var url = dotenv.env['YOUR_SERVER_URL']! +
                      'api/post/delete/${post.id}';

                  var response = await Dio().delete(url);

                  if (response.statusCode == 200) {
                    // 현재 화면을 종료하고 이전 화면으로 돌아감
                    Navigator.pop(context, true);
                    Get.rootDelegate.offNamed(Routes.BOARD);
                  } else {
                    // 요청 실패 처리
                    print('Request failed with status: ${response.statusCode}');
                    print('Error message: ${response.data}');
                  }
                  Navigator.of(context).pop();
                },
                child: Text(
                  "삭제하기",
                  style: TextStyle(color: Colors.white),
                )),
            SizedBox(
              width: 20,
            ),
            ElevatedButton(
              style: ButtonStyle(
                backgroundColor: MaterialStateProperty.all<Color>(Colors.white),
                minimumSize: MaterialStateProperty.all<Size>(Size(200, 48)),
                foregroundColor:
                    MaterialStateProperty.all<Color>(Colors.black), // 텍스트 색상
                shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                  RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(8.0),
                    // 버튼의 모서리를 둥글게 설정
                    side: BorderSide(color: AppColor.PrimaryPink), // 테두리 색상
                  ),
                ),
              ),
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: Text(
                "취소",
                style: TextStyle(color: Colors.black), // 텍스트 색상
              ),
            )
          ],
        )
      ],
    ),
  );
}

// comment의 id값으로 댓글,대댓글을 삭제하는 함수
AlertDialog RemoveCommentDialog(BuildContext context, CommentDto comment, String type) {
  return AlertDialog(
    title: Text('정말 삭제하시겠습니까?'),
    content: Column(
      mainAxisSize: MainAxisSize.min, // 다이얼로그의 높이를 내용에 맞게 조절
      children: [
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
                style: ButtonStyle(
                  backgroundColor:
                      MaterialStateProperty.all<Color>(AppColor.PrimaryPink),
                  minimumSize: MaterialStateProperty.all<Size>(Size(200, 48)),
                  shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                    RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(8.0),
                    ),
                  ),
                ),
                onPressed: () async {
                  var url = dotenv.env['YOUR_SERVER_URL']! +
                      'api/comment/delete/${comment.id}';

                  var response = await Dio().delete(url);

                  if (response.statusCode == 200) {
                    // 삭제하고 이동

                    if (type == "ask") {
                      Navigator.pop(context, true);
                      Get.rootDelegate.offNamed(Routes.ASK); // ASK 화면으로 이동
                    } else if (type == "free") {
                      Navigator.pop(context, true);
                      Get.rootDelegate.offNamed(Routes.FREE); // FREE 화면으로 이동
                    } else if (type == "review") {
                      Navigator.pop(context, true);
                      Get.rootDelegate.offNamed(Routes.REVIEW); // REVIEW 화면으로 이동
                    }

                  } else {
                    // 요청 실패 처리
                    print('Request failed with status: ${response.statusCode}');
                    print('Error message: ${response.data}');
                  }
                  Navigator.of(context).pop();
                },
                child: Text(
                  "삭제하기",
                  style: TextStyle(color: Colors.white),
                )),
            SizedBox(
              width: 20,
            ),
            ElevatedButton(
              style: ButtonStyle(
                backgroundColor: MaterialStateProperty.all<Color>(Colors.white),
                minimumSize: MaterialStateProperty.all<Size>(Size(200, 48)),
                foregroundColor:
                    MaterialStateProperty.all<Color>(Colors.black), // 텍스트 색상
                shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                  RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(8.0),
                    // 버튼의 모서리를 둥글게 설정
                    side: BorderSide(color: AppColor.PrimaryPink), // 테두리 색상
                  ),
                ),
              ),
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: Text(
                "취소",
                style: TextStyle(color: Colors.black), // 텍스트 색상
              ),
            )
          ],
        )
      ],
    ),
  );
}
