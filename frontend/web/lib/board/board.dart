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

import '../MainController.dart';
import '../constants.dart';
import 'askboard.dart';
import 'noticeboard.dart';

class Board extends StatefulWidget {
  @override
  _BoardState createState() => _BoardState();
}

class _BoardState extends State<Board> {

  String selectedValue = '공지';
  final MainController _mainController = Get.find<MainController>(
      tag: 'MainController');

  // 공지, 질문, 자유, 후기글을 종류별로 리스트에 담는다.
  List<PostDto> notice = [];
  List<PostDto> ask = [];
  List<PostDto> free = [];
  List<PostDto> review = [];

  //

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
      } else {
        // 요청 실패 처리
        print('Request failed with status: ${response.statusCode}');
        print('Error message: ${response.data}');
      }
    }).then((value) {
      setState(() {

      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: TopBar(),
      body: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 80),
        child: Center(
          child: Column(
            children: [
              Row(
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
              SizedBox(height: 20),
              if (selectedValue == '공지')
                NoticeBoardList(context, notice, _mainController)
              else
                if (selectedValue == '질문')
                  AskBoardList(ask)
                else
                  if (selectedValue == '자유')
                    FreeBoardList(free)
                  else
                    if (selectedValue == '후기')
                      ReviewBoardList(review)
            ],
          ),
        ),
      ),
      bottomNavigationBar: BottomBar(),
    );
  }
}

Expanded NoticeBoardList(BuildContext context, List<PostDto> notice,
    MainController _mainController) {
  return Expanded(
    child: Column(
      children: [
        Padding(
          padding: const EdgeInsets.all(20.0),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(
                '공지게시판',
                style: TextStyle(
                    fontWeight: FontWeight.bold, fontSize: 40),
              ),
              ElevatedButton(
                  style: ButtonStyle(
                    backgroundColor:
                    MaterialStateProperty.all<Color>(
                        AppColor.PrimaryPink),
                    minimumSize: MaterialStateProperty.all<Size>(
                        Size(200, 48)),
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
        Column(
            children: notice.map((post) =>
                NoticePostItem(post, _mainController)).toList()
        ),
      ],
    ),
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
          child: Expanded(
            child: TextField(
              onChanged: (value) {
                title = value; // 제목 값 업데이트
              },
              maxLines: null, // maxLines를 null로 설정하여 height를 가변적으로 만듦
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                labelText: '제목',
              ),
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
                  maxLines: null,
                  expands: true,
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
                ),
                onPressed: () async {
                  // 등록하고 pop
                  var url = dotenv.env['YOUR_SERVER_URL']! +
                      'api/post/create';

                  var postData = {
                    "postType": "notice",
                    "title": title,
                    "content": content,
                    "createdBy": _mainController.id.value,
                    "isHidden": false,
                    "festivalId": _mainController.fid.value,
                    "rating": null
                  };

                  // 축제 정보 받아오기
                  var response = await Dio().post(url
                      , data: postData);

                  if (response.statusCode == 200) {
                    // 요청 성공 처리
                    print("게시글 등록 성공");
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
                backgroundColor:
                MaterialStateProperty.all<Color>(Colors.white),
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
                    Text('사용자'),
                    SizedBox(
                      width: 10,
                    ),
                    Text('2023-08-08      23:50')
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

Container Comment(CommentDto comment) {
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
                      Text('작성자'),
                      SizedBox(width: 10),
                      Text('${comment.createdAt.date.toString()}   ${comment
                          .createdAt.time.toString()}'),
                    ],
                  ),
                  SizedBox(height: 20),
                  Text(
                      "${comment.content}"),
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
                    Text('삭제', )
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

Container Reply(CommentDto reply) {
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
                      Text('작성자'),
                      SizedBox(width: 10),
                      Align(alignment: Alignment.centerRight,
                          child: Text(
                              '${reply.createdAt.date.toString()}   ${reply
                                  .createdAt.time.toString()}'),)

                    ],
                  ),
                  SizedBox(height: 20),
                  Text(
                      "${reply.content}"),
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
                    Text('삭제')
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

AlertDialog CommentDialog(BuildContext context) {
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
                  maxLines: null,
                  expands: true,
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
                ),
                onPressed: () {
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
                backgroundColor:
                MaterialStateProperty.all<Color>(Colors.white),
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

// post의 id값으로 게시글을 삭제하는 함수
AlertDialog RemoveDialog(BuildContext context, PostWithCommentDto post) {
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
                ),
                onPressed: () async {
                  var url =
                      dotenv.env['YOUR_SERVER_URL']! + 'api/post/delete/${post.id}';

                  var response = await Dio().delete(url);

                  if (response.statusCode == 200) {
                    // 마이페이지로 이동
                    Get.rootDelegate.toNamed(Routes.BOARD);
                  } else {
                    // 요청 실패 처리
                    print(
                        'Request failed with status: ${response.statusCode}');
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
                backgroundColor:
                MaterialStateProperty.all<Color>(Colors.white),
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