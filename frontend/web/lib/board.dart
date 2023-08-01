import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:get/get.dart';
import 'package:web/common.dart';

import 'constants.dart';

class Board extends StatefulWidget {
  @override
  _BoardState createState() => _BoardState();
}

class _BoardState extends State<Board> {
  String selectedValue = '공지';

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
                Expanded(
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
                                        return NoticeDialog(context);
                                      });
                                },
                                child: Text(
                                  "등록하기",
                                  style: TextStyle(color: Colors.white),
                                )),
                          ],
                        ),
                      ),
                      Expanded(
                        child: ListView.builder(
                          // ListView.builder로 변경
                          itemCount: 6, // 반복할 횟수를 지정
                          itemBuilder: (context, index) {
                            return Padding(
                              // Padding으로 감싸서 좌우 padding 적용
                              padding: EdgeInsets.symmetric(horizontal: 20),
                              child: NoticeItem(),
                            );
                          },
                        ),
                      ),
                    ],
                  ),
                )
              else if (selectedValue == '질문')
                Expanded(
                  child: Column(
                    children: [
                      Padding(
                        padding: const EdgeInsets.all(20.0),
                        child: Row(children: [
                          Text('질문게시판', style: TextStyle(
                            fontWeight: FontWeight.bold, fontSize: 40)),
                        ]),
                      ),
                      Expanded(
                        child: ListView.builder(
                          // ListView.builder로 변경
                          itemCount: 2, // 반복할 횟수를 지정
                          itemBuilder: (context, index) {
                            return Padding(
                              // Padding으로 감싸서 좌우 padding 적용
                              padding: EdgeInsets.symmetric(horizontal: 20),
                              child: QuestionItem(),
                            );
                          },
                        ),
                      ),
                    ],
                  ),
                )
              else if (selectedValue == '자유')
                Text('자유')
              else if (selectedValue == '후기')
                Text('후기')
            ],
          ),
        ),
      ),
      bottomNavigationBar: BottomBar(),
    );
  }

  AlertDialog NoticeDialog(BuildContext context) {
    return AlertDialog(
      title: Text('공지사항'),
      content: Column(
        mainAxisSize: MainAxisSize.min, // 다이얼로그의 높이를 내용에 맞게 조절
        children: [
          Container(
            width: 400, // 원하는 width 값으로 설정
            child: Expanded(
              child: TextField(
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
  Column NoticeItem() {
    return Column(
      children: [
        Material(
          elevation: 4,
          borderRadius: BorderRadius.circular(8),
          child: Padding(
            padding: const EdgeInsets.all(20.0),
            child: Container(
              child: Column(
                children: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [Text("공지 제목"), Text("조회수")],
                  ),
                  Divider(
                    color: Colors.black, // 수평선 색상 설정
                    thickness: 2, // 수평선 두께 설정
                  ),
                  Align(
                    alignment: Alignment.topLeft,
                    child: Text("내용입니다"),
                  ),
                  Align(alignment: Alignment.bottomRight, child: Text("연월일")),
                  Align(alignment: Alignment.bottomRight, child: Text("시간")),
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

  Column QuestionItem() {
    return Column(
      children: [
        Material(
          elevation: 4,
          borderRadius: BorderRadius.circular(8),
          child: Padding(
            padding: const EdgeInsets.all(20.0),
            child: Container(
              child: Column(
                children: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [Text("공지 제목"), Text("조회수")],
                  ),
                  Divider(
                    color: Colors.black, // 수평선 색상 설정
                    thickness: 2, // 수평선 두께 설정
                  ),
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
                            style: TextStyle(color: Colors.white),
                          )),
                      Column(
                        children: [Text("연월일"), Text("시간")],
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

  Container Comment() {
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
                        Text('월일시'),
                      ],
                    ),
                    SizedBox(height: 20),
                    Text(
                        "내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다."),
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

  Container Reply() {
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
                        Text('월일시'),
                      ],
                    ),
                    SizedBox(height: 20),
                    Text(
                        "내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다.내용입니다."),
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
}
