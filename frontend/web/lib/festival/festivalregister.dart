import 'dart:typed_data';

import 'package:file_picker/file_picker.dart';
import 'package:flutter/material.dart';
import 'package:web/common.dart';
import 'dart:html';

class FestivalRegister extends StatefulWidget {
  @override
  State<FestivalRegister> createState() => _FestivalRegisterState();
}

class _FestivalRegisterState extends State<FestivalRegister> {
  // 포스터 이미지
  FilePickerResult? poster;
  // timeTable
  FilePickerResult? timetable;

  // 데이트 피커
  // 날짜를 선택하는 걸로 변경
  DateTime startDate = DateTime.now();

  Future<void> _selectDate(BuildContext context) async {
    final DateTime? picked = await showDatePicker(
      context: context,
      initialDate: startDate,
      firstDate: DateTime(2000), // 선택 가능한 가장 이른 날짜
      lastDate: DateTime(2100), // 선택 가능한 가장 늦은 날짜
    );
    if (picked != null && picked != startDate) {
      setState(() {
        startDate = picked;
      });
    }
  }

 @override
  void initState() {
    super.initState();
    timetable = null;
    poster = null;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: TopBar(),
      body: SingleChildScrollView(
        child: Center(
          child: Padding(
            padding: const EdgeInsets.all(80.0),
            child: Column(
              children: [
                Text("이미지 첨부"),
                Text('포스터 업로드'),
                InkWell(
                  onTap: () async {
                    var picked = await FilePicker.platform.pickFiles();

                    if (picked != null) {
                      setState(() {
                        poster = picked;
                      });
                    }
                  },
                  child: Container(
                    child: Center(
                      child: poster != null
                          ? Image.memory(Uint8List.fromList(poster!.files.first.bytes!))
                          : Text('이미지가 선택되지 않았습니다.'),
                    ),
                    width: 200,
                    height: 200,
                    decoration: BoxDecoration(
                      border: Border.all(color: Colors.black),
                    ),
                  ),
                ),
                Text("행사명"),
                Container(
                  width: 400,
                  child: TextField(
                    onChanged: (text) {
                      // 입력 값이 변경될 때 호출되는 콜백 함수
                      print('입력 값 변경: $text');
                    },
                    onSubmitted: (text) {
                      // 입력이 완료되고 제출(Enter 키를 누름)될 때 호출되는 콜백 함수
                      print('입력 완료: $text');
                    },
                    decoration: InputDecoration(
                      hintText: '행사명 작성', // 입력란에 표시될 힌트 텍스트
                      border: OutlineInputBorder(), // 입력란의 테두리 스타일 지정
                    ),
                  ),
                ),
                Text('개요'),
                Container(
                  width: 400,
                  child: TextField(
                    onChanged: (text) {
                      // 입력 값이 변경될 때 호출되는 콜백 함수
                      print('입력 값 변경: $text');
                    },
                    onSubmitted: (text) {
                      // 입력이 완료되고 제출(Enter 키를 누름)될 때 호출되는 콜백 함수
                      print('입력 완료: $text');
                    },
                    decoration: InputDecoration(
                      hintText: '개요명 작성', // 입력란에 표시될 힌트 텍스트
                      border: OutlineInputBorder(), // 입력란의 테두리 스타일 지정
                    ),
                  ),
                ),
                Text('주소'),
                Container(
                  width: 400,
                  child: TextField(
                    onChanged: (text) {
                      // 입력 값이 변경될 때 호출되는 콜백 함수
                      print('입력 값 변경: $text');
                    },
                    onSubmitted: (text) {
                      // 입력이 완료되고 제출(Enter 키를 누름)될 때 호출되는 콜백 함수
                      print('입력 완료: $text');
                    },
                    decoration: InputDecoration(
                      hintText: '주소명 작성', // 입력란에 표시될 힌트 텍스트
                      border: OutlineInputBorder(), // 입력란의 테두리 스타일 지정
                    ),
                  ),
                ),
                Text('기간'),
                Row(
                  children: [
                    InkWell(
                      onTap: (){_selectDate(context);},
                        child: Container(
                          child: Center(child: Text('${startDate.year} ${startDate.month} ${startDate.day}')),
                      color: Colors.red,
                      width: 100,
                      height: 100,
                    )),
                    InkWell(
                      onTap: (){_selectDate(context);},
                      child: Container(
                        child: Center(
                          child: Text('${startDate.year} ${startDate.month} ${startDate.day}'),
                        ),
                        color: Colors.blue,
                        width: 100,
                        height: 100,
                      ),
                    )
                  ],
                ),

                // 엑셀 파일로 시간표를 입력받음.
                Text('시간표 등록'),
                InkWell(
                  onTap: () async {
                        var picked = await FilePicker.platform.pickFiles();

                        setState(() {
                          if (picked != null) {
                            print(picked.files.first.name);
                            timetable = picked;
                          }
                        });

                  },
                  child: Container(
                    child: Center(
                      child: Text(
                          timetable?.files.first.name ?? ""),
                    ),
                    width: 100,
                    height: 100,
                  ),
                ),
                Divider(height: 10,),
                Text("부스 및 마커 등록"),

              ],
            ),
          ),
        ),
      ),
    );
  }
}
