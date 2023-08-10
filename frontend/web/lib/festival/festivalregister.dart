import 'dart:typed_data';

import 'package:file_picker/file_picker.dart';
import 'package:flutter/material.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:web/common.dart';
import 'dart:html' as html;
import 'dart:ui' as ui;

import 'package:web/festival/MarkerDto.dart';

class FileData {
  String fileName;
  String fileUrl;

  FileData(this.fileName, this.fileUrl);
}

class FestivalRegister extends StatefulWidget {
  @override
  State<FestivalRegister> createState() => _FestivalRegisterState();
}

class _FestivalRegisterState extends State<FestivalRegister> {
  final html.IFrameElement _iFrameElement = html.IFrameElement();

  // 다운로드 버튼을 누를 때 호출되는 함수
  void downloadFile(FileData fileData) {
    html.AnchorElement anchorElement =
        html.AnchorElement(href: fileData.fileUrl)
          ..target = '_blank'
          ..download = fileData.fileName; // 파일 이름 설정
    anchorElement.click();
  }


  // 마커 리스트
  List<MarkerDto>? markers;

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
    markers = [];
    _iFrameElement.src = dotenv.env['YOUR_NAVER_MAP_URL'];
    _iFrameElement.style.border = 'none';

// ignore: undefined_prefixed_name
    ui.platformViewRegistry.registerViewFactory(
      'iframeElement',
      (int viewId) => _iFrameElement,
    );

    // 데이터를 받아오면 기존 배열을 비워주고 data를 순회하면서 축제 객체를 추가.
    html.window.addEventListener("message", (event) {
      // 마커 정보 초기화
      markers?.clear();

      // 마커 배열 추가
      List<dynamic> data = (event as html.MessageEvent).data ?? '-';
      for (var item in data) {
        markers?.add(MarkerDto(
            boothLatitude: item['boothLatitude'],
            boothLongitude: item['boothLongitude'],
            markerType: item['markerType'],
            markerId: item['markerId'],
            markerTitle: item['markerTitle'],
            markerDescription: item['markerDescription']));
      }

      // 마커아이템 확인
      for (var item in markers!) {
        print(item);
      }
    });
  }

  final Widget _iframeWidget = HtmlElementView(
    viewType: 'iframeElement',
    key: UniqueKey(),
  );

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: TopBar(),
      body: SingleChildScrollView(
        child: Center(
          child: Padding(
            padding: const EdgeInsets.symmetric(horizontal: 80, vertical: 40),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [

                Text(
                  "축제 정보 입력",
                  style: TextStyle(fontSize: 48),
                ),
                SizedBox(
                  height: 40,
                ),
                Center(
                  child: InkWell(
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
                            ? Image.memory(
                                Uint8List.fromList(poster!.files.first.bytes!))
                            : Text('업로드할 포스터를 선택해주세요.', style: TextStyle(fontSize: 20)),
                      ),
                      width: 800,
                      height: 400,
                      decoration: BoxDecoration(
                        border: Border.all(color: Colors.black),
                      ),
                    ),
                  ),
                ),
                SizedBox(
                  height: 20,
                ),
                Container(
                  width: 800,
                  height: 50,
                  child: Column(
                    children: [
                      Expanded(
                        child: TextField(
                          maxLines: null,
                          expands: true,
                          decoration: InputDecoration(
                            border: OutlineInputBorder(),
                            labelText: '행사명',
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
                SizedBox(
                  height: 20,
                ),
                Container(
                  width: 800,
                  height: 200, // 기본 height 값을 200으로 설정
                  child: Column(
                    children: [
                      Expanded(
                        child: TextField(
                          maxLines: null,
                          expands: true,
                          decoration: InputDecoration(
                            border: OutlineInputBorder(),
                            labelText: '개요',
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
                SizedBox(
                  height: 20,
                ),
                Container(
                  width: 800,
                  height: 50, // 기본 height 값을 200으로 설정
                  child: Column(
                    children: [
                      Expanded(
                        child: TextField(
                          maxLines: null,
                          expands: true,
                          decoration: InputDecoration(
                            border: OutlineInputBorder(),
                            labelText: '주소',
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
                    InkWell(
                        onTap: () {
                          _selectDate(context);
                        },
                        child: Container(
                          decoration: BoxDecoration(
                            border: Border.all(
                              color: Colors.black, // 테두리 색상
                              width: 2.0, // 테두리 두께
                            ),
                            borderRadius: BorderRadius.circular(
                                10.0), // 10.0의 반경을 가진 둥근 모서리
                          ),
                          child: Center(
                              child: Text(
                            '${startDate.year} ${startDate.month} . ${startDate.day}',
                            style: TextStyle(fontSize: 20),
                          )),
                          width: 140,
                          height: 40,
                        )),
                    SizedBox(
                      width: 40,
                    ),
                    Text(
                      "~",
                      style: TextStyle(fontSize: 20),
                    ),
                    SizedBox(
                      width: 40,
                    ),
                    InkWell(
                      onTap: () {
                        _selectDate(context);
                      },
                      child: Container(
                        decoration: BoxDecoration(
                          border: Border.all(
                            color: Colors.black, // 테두리 색상
                            width: 2.0, // 테두리 두께
                          ),
                          borderRadius: BorderRadius.circular(
                              10.0), // 10.0의 반경을 가진 둥근 모서리
                        ),
                        child: Center(
                          child: Text(
                            '${startDate.year} ${startDate.month} . ${startDate.day}',
                            style: TextStyle(fontSize: 20),
                          ),
                        ),
                        width: 140,
                        height: 40,
                      ),
                    )
                  ],
                ),
                SizedBox(
                  height: 20,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Container(
                      child: Column(
                        children: [

                          Text('일정 양식'),
                          SizedBox(
                            height: 10,
                          ),
                          InkWell(
                            onTap: () async {
                              String fileName = 'timetable.xlsx'; // 예시 파일명
                              String fileUrl = '/assets/hifes_timetable.xlsx'; // 예시 파일의 경로
                              downloadFile(FileData(fileName, fileUrl));
                            },
                            child: Container(
                              padding: EdgeInsets.symmetric(
                                  horizontal: 8, vertical: 4),
                              constraints: BoxConstraints(
                                maxWidth: 100,
                                minHeight: 20,
                              ),
                              decoration: BoxDecoration(
                                border: Border.all(
                                  color: Colors.black, // 테두리 색상
                                  width: 2.0, // 테두리 두께
                                ),
                                borderRadius: BorderRadius.circular(
                                    10.0), // 10.0의 반경을 가진 둥근 모서리
                              ),
                              child: Center(
                                child: Text('엑셀 양식'),
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                    SizedBox(
                      width: 40,
                    ),
                    Container(
                      child: Column(
                        children: [

                          Text('일정 업로드'),
                          SizedBox(
                            height: 10,
                          ),
                          InkWell(
                            onTap: () async {
                              var picked =
                                  await FilePicker.platform.pickFiles();

                              setState(() {
                                if (picked != null) {
                                  print(picked.files.first.name);
                                  timetable = picked;
                                }
                              });
                            },
                            child: Container(
                              padding: EdgeInsets.symmetric(
                                  horizontal: 8, vertical: 4),
                              constraints: BoxConstraints(
                                maxWidth: 100,
                                minHeight: 20,
                              ),
                              decoration: BoxDecoration(
                                border: Border.all(
                                  color: Colors.black, // 테두리 색상
                                  width: 2.0, // 테두리 두께
                                ),
                                borderRadius: BorderRadius.circular(
                                    10.0), // 10.0의 반경을 가진 둥근 모서리
                              ),
                              child: Center(
                                child: Text(timetable?.files.first.name ?? ""),
                              ),
                            ),
                          ),
                        ],
                      ),
                    )
                  ],
                ),
                // 엑셀 파일로 시간표를 입력받음.
                SizedBox(
                  height: 20,
                ),
                Divider(
                  height: 10,
                ),
                SizedBox(
                  height: 20,
                ),
                Text(
                  "마커 등록",
                  style: TextStyle(fontSize: 48),
                ),
                SizedBox(
                  height: 20,
                ),
                SizedBox(
                  height: MediaQuery.of(context).size.height,
                  width: MediaQuery.of(context).size.width,
                  child: _iframeWidget,
                ),
                TextButton(
                    onPressed: () async {
                      _iFrameElement.contentWindow?.postMessage(
                          "getMarkerData", "http://localhost:8080");
                    },
                    child: Text("getData"))
              ],
            ),
          ),
        ),
      ),
    );
  }
}
