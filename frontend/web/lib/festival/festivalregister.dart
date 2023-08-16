import 'dart:convert';
import 'dart:io';
import 'dart:typed_data';
import 'package:dio/dio.dart' as dio;
import 'package:file_picker/file_picker.dart';
import 'package:flutter/material.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:web/common.dart';
import 'package:web/festival/StampDto.dart';
import 'dart:html' as html;
import 'dart:ui' as ui;
import '../MainController.dart';
import '../constants.dart';
import 'MarkerDto.dart';
import 'package:http/http.dart' as http;
import 'package:http_parser/http_parser.dart'; // MediaType import 추가

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

  final MainController _mainController =
  Get.find<MainController>(tag: 'MainController');

  // 다운로드 버튼을 누를 때 호출되는 함수
  void downloadFile(FileData fileData) {
    html.AnchorElement anchorElement =
        html.AnchorElement(href: fileData.fileUrl)
          ..target = '_blank'
          ..download = fileData.fileName; // 파일 이름 설정
    anchorElement.click();
  }

  var fesTitle;
  var fesOutline;
  var fesAddress;

  // 마커 리스트
  List<MarkerDto>? markers;
  List<StampDto>? stampMarkers;

  // 포스터 이미지
  FilePickerResult? poster;
  // timeTable
  FilePickerResult? timetable;

  // 데이트 피커
  // 날짜를 선택하는 걸로 변경
  DateTime startDate = DateTime.now();
  DateTime endDate = DateTime.now();

  Future<void> _selectStartDate(BuildContext context) async {
    final DateTime? picked = await showDatePicker(
      context: context,
      initialDate: startDate,
      firstDate: DateTime(2000), // 선택 가능한 가장 이른 날짜
      lastDate: DateTime(2100), // 선택 가능한 가장 늦은 날짜
    );
    if (picked != null && picked != startDate) {
      setState(() {
        startDate = picked;

        if (startDate.isAfter(endDate)) {
          endDate = startDate;
        }
      });
    }
  }

  Future<void> _selectEndDate(BuildContext context) async {
    final DateTime? picked = await showDatePicker(
      context: context,
      initialDate: startDate,
      firstDate: DateTime(2000), // 선택 가능한 가장 이른 날짜
      lastDate: DateTime(2100), // 선택 가능한 가장 늦은 날짜
    );
    if (picked != null && picked != endDate) {
      setState(() {
        endDate = picked;

        if (startDate.isAfter(endDate)) {
          startDate = endDate;
        }
      });
    }
  }

  @override
  void initState() {
    super.initState();
    fesTitle = "";
    fesOutline = "";
    fesAddress = "";
    timetable = null;
    markers = [];
    stampMarkers = [];
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
      stampMarkers?.clear();

      // 마커 배열 추가
      List<dynamic> data = (event as html.MessageEvent).data ?? '-';
      for (var item in data) {
        if(item['markerType']=="stamp"){
          stampMarkers?.add(StampDto(
              missionTitle: item['markerTitle'],
              missionOutline: item['markerDescription'],
              missionLatitude: item['boothLatitude'],
              missionLongitude: item['boothLongitude'],
              ));
        }else{
          markers?.add(MarkerDto(
              boothName: item['markerTitle'],
              description: item['markerDescription'],
              boothLatitude: item['boothLatitude'],
              boothLongitude: item['boothLongitude'],
              boothNo: item['markerId']));
        }
      }
      // 마커아이템 확인
      for (var item in markers!) {
        print(item);
      }
      print("------스탬프-------");
      for (var item in stampMarkers!){
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
                  style: TextStyle(fontSize: 40, fontWeight: FontWeight.w600),
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
                            : Text('업로드할 포스터를 선택해주세요.',
                                style: TextStyle(fontSize: 20)),
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
                          onChanged: (value) {
                            fesTitle = value;
                          },
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
                  height: 400, // 기본 height 값을 200으로 설정
                  child: Column(
                    children: [
                      Expanded(
                        child: TextField(
                          maxLines: 1000,
                          decoration: InputDecoration(
                            border: OutlineInputBorder(),
                            labelText: '개요',
                          ),
                          onChanged: (value) {
                            fesOutline = value;
                          },
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
                          onChanged: (value) {
                            fesAddress = value;
                          },
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
                          _selectStartDate(context);
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
                        _selectEndDate(context);
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
                            '${endDate.year} ${endDate.month} . ${endDate.day}',
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
                          Text('일정 양식', style: TextStyle(fontSize: 20)),
                          SizedBox(
                            height: 10,
                          ),
                          InkWell(
                            onTap: () async {
                              String fileName = 'timetable.xlsx'; // 예시 파일명
                              String fileUrl =
                                  '/assets/hifes_timetable.xlsx'; // 예시 파일의 경로
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

                          Text(
                            '일정 업로드',
                            style: TextStyle(fontSize: 20),
                          ),
                          SizedBox(
                            height: 10,
                          ),
                          InkWell(
                            onTap: () async {
                              var picked =
                                  await FilePicker.platform.pickFiles();

                              setState(() {
                                if (picked != null) {
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

                  style: TextStyle(fontSize: 40),

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
                          "getMarkerData", "${dotenv.env['YOUR_SERVER_URL']!}");

                      showDialog(
                        context: context,
                        builder: (BuildContext context) {
                          return AlertDialog(
                            title: Text('저장 완료'),
                            content: Text('마커 정보가 저장되었습니다.'),
                            actions: <Widget>[
                              TextButton(
                                onPressed: () {
                                  Navigator.of(context).pop(); // 다이얼로그 닫기
                                },
                                child: Text('확인'),
                              ),
                            ],
                          );
                        },
                      );
                    },
                    child: Text("마커 정보 저장하기", style: TextStyle(fontSize: 16),)),
                SizedBox(height: 10,),
                ElevatedButton(
                    style: ButtonStyle(
                      backgroundColor: MaterialStateProperty.all<Color>(
                          AppColor.PrimaryPink),
                      minimumSize:
                      MaterialStateProperty.all<Size>(Size(800, 48)),
                      shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                        RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(8.0),
                        ),
                      ),
                    ),
                    onPressed: () async {
                      // Get.rootDelegate.toNamed(Routes.BOARD);
                      // 행사 등록

                      var url = Uri.parse('${dotenv.env['YOUR_SERVER_URL']!}api/${_mainController.id.value}/create-festival');

                      var request = http.MultipartRequest('POST', url);

                      // JSON 데이터를 http.MultipartFile 형태로 생성하여 추가
                      var jsonBody = {
                        "fesTitle": fesTitle,
                        "fesOutline": fesOutline,
                        "fesAddress": fesAddress,
                        "fesStartDate": { "year" : "${startDate.year}",
                          "month":"${startDate.month}",
                          "day":"${startDate.day}"},
                        "fesEndDate": { "year" : "${endDate.year}",
                          "month":"${endDate.month}",
                          "day":"${endDate.day}"},
                        "markers": markers?.map((marker) => marker.toJson()).toList(),
                        "stampMissions": stampMarkers?.map((marker) => marker.toJson()).toList(),
                      };

                      var jsonPart = http.MultipartFile.fromString(
                        'data',
                        jsonEncode(jsonBody),
                        filename: 'data',
                        contentType:  MediaType('application', 'json'),
                      );

                      request.files.add(jsonPart);

                      // 파일 선택
                      var file = timetable?.files.first;
                      if (file != null) {
                        var fileBytes = file.bytes;
                        if (fileBytes != null) {
                          request.files.add(http.MultipartFile(
                            'file',
                            http.ByteStream.fromBytes(fileBytes),
                            fileBytes.length,
                            filename: file.name,
                          ));
                        }
                      }

                      // 이미지 선택
                      var image = poster?.files.first;
                      if (image != null) {
                        var imageBytes = image.bytes;
                        if (imageBytes != null) {
                          request.files.add(http.MultipartFile(
                            'image',
                            http.ByteStream.fromBytes(imageBytes),
                            imageBytes.length,
                            filename: image.name,
                          ));
                        }
                      }

                      print(request.files);

                      var response = await request.send();

                      if (response.statusCode == 201) {
                        print('Festival data sent successfully');
                        // 성공 축제 화면으로 이동
                        Navigator.pop(context, true);
                        Get.rootDelegate.offNamed(Routes.BOARD);
                      } else {
                        print('Failed to send festival data. Status code: ${response.statusCode}');
                        // 실패 다이얼로그 표시
                        showDialog(
                          context: context,
                          builder: (BuildContext context) {
                            return AlertDialog(
                              title: Text("Failed"),
                              content: Text("Failed to send festival data. Status code: ${response.statusCode}"),
                              actions: <Widget>[
                                TextButton(
                                  child: Text("OK"),
                                  onPressed: () {
                                    Navigator.of(context).pop(); // 다이얼로그 닫기
                                  },
                                ),
                              ],
                            );
                          },
                        );
                      }
                    },
                    child: Text(
                      "등록하기",
                      style: TextStyle(color: Colors.white, fontSize: 16),
                    ))

              ],
            ),
          ),
        ),
      ),
    );
  }
}
