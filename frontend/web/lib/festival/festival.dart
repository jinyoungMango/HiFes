import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:web/MainController.dart';
import 'package:web/common.dart';
import 'package:web/festival/ScheduleDto.dart';

import '../constants.dart';
import 'FestivalDto.dart';

class Festival extends StatefulWidget {
  const Festival({super.key});

  @override
  State<Festival> createState() => _FestivalState();
}

class _FestivalState extends State<Festival> {
  final MainController _mainController =
      Get.find<MainController>(tag: 'MainController');

  late FestivalDto festival = FestivalDto.empty();

  // 자기 일정
  late List<ScheduleDto> myschedules;

  @override
  void initState() {
    super.initState();
    // 축제 정보 가져오기
    Future.delayed(Duration.zero, () async {
      var url = dotenv.env['YOUR_SERVER_URL']! +
          'api/festival/${_mainController.fid.value}';

      // 축제 정보 받아오기
      var response = await Dio().get(url);

      if (response.statusCode == 200) {
        // 요청 성공 처리
        print('Request succeeded: ${response.data}');
        // 사용자 정보 json을 파싱해서 토큰 저장

        setState(() {
          festival = FestivalDto.fromJson(response.data);
        });
      } else {
        // 요청 실패 처리
        print('Request failed with status: ${response.statusCode}');
        print('Error message: ${response.data}');
      }
    });
  }

  Future<List<ScheduleDto>> fetchSchedules() async {
    try {
      var url =
          '${dotenv.env['YOUR_SERVER_URL']!}api/festival/${_mainController.fid}/festivalTables';

      var response = await Dio().get(url);

      if (response.statusCode == 200) {
        List<ScheduleDto> schedules = [];
        for (var scheduleJson in response.data) {
          print(scheduleJson);
          print('============');
          var schedule = ScheduleDto.fromJson(scheduleJson);
          print(schedule);
          schedules.add(schedule);
        }
        return schedules;
      } else {
        throw Exception('Failed to load festivals');
      }
    } catch (error) {
      throw Exception('Error fetching festivals: $error');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: TopBar(),
      body: SingleChildScrollView(
        child: Container(
          child: Center(
            child: Container(
              width: 800,
              child: Padding(
                padding: const EdgeInsets.symmetric(vertical: 40),
                child: Column(
                  children: [
                    Center(
                        child: ClipRRect(
                            borderRadius: BorderRadius.circular(100),
                            child: Image.network(
                              "${festival.fesPosterPath}",
                              width: 800,
                              height: 400,
                              fit: BoxFit.cover,
                            ))),
                    SizedBox(
                      height: 40,
                    ),
                    Container(
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Text(
                            festival.fesTitle,
                            style: TextStyle(fontSize: 40),
                          ),
                          ElevatedButton(
                              style: ButtonStyle(
                                backgroundColor:
                                    MaterialStateProperty.all<Color>(
                                        AppColor.PrimaryPink),
                                minimumSize: MaterialStateProperty.all<Size>(
                                    Size(200, 48)),
                                shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                                  RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(8.0),
                                  ),
                                ),
                              ),
                              onPressed: () {
                                Get.rootDelegate.toNamed(Routes.BOARD);
                              },
                              child: Text(
                                "게시판",
                                style: TextStyle(
                                    color: Colors.white, fontSize: 16),
                              ))
                        ],
                      ),
                    ),
                    SizedBox(
                      height: 20,
                    ),
                    Row(
                      children: [
                        Text(
                          festival.fesAddress,
                          style: TextStyle(fontSize: 16),
                        )
                      ],
                    ),
                    SizedBox(
                      height: 10,
                    ),
                    Row(
                      children: [
                        Container(
                          padding:
                              EdgeInsets.symmetric(horizontal: 8, vertical: 4),
                          decoration: BoxDecoration(
                            border: Border.all(
                              color: Colors.black, // 테두리 색상
                              width: 1.0, // 테두리 두께
                            ),
                            borderRadius: BorderRadius.circular(8.0),
                          ),
                          child: Text('평점 ${festival.avgRating}'),
                        ),
                        SizedBox(
                          width: 10,
                        ),
                      ],
                    ),
                    SizedBox(
                      height: 10,
                    ),
                    Align(
                        alignment: Alignment.centerRight,
                        child: Text(
                            '${festival.fesStartDate.toString()}      ~      ${festival.fesEndDate.toString()}')),
                    SizedBox(
                      height: 10,
                    ),
                    Divider(
                      height: 10,
                    ),
                    SizedBox(
                      height: 10,
                    ),
                    Align(
                        alignment: Alignment.centerLeft,
                        child: Text(
                          "개요",
                          style: TextStyle(fontSize: 20),
                        )),
                    SizedBox(
                      height: 10,
                    ),
                    Align(
                        alignment: Alignment.centerLeft,
                        child: Text(festival.fesOutline)),
                    SizedBox(
                      height: 10,
                    ),
                    Divider(
                      height: 10,
                    ),
                    SizedBox(
                      height: 10,
                    ),
                    Align(
                        alignment: Alignment.centerLeft,
                        child: Text(
                          "일정",
                          style: TextStyle(fontSize: 20),
                        )),
                    FutureBuilder<List<ScheduleDto>>(
                        future: fetchSchedules(),
                        builder: (context, snapshot) {
                          if (snapshot.connectionState ==
                              ConnectionState.waiting) {
                            return CircularProgressIndicator(); // 로딩 중인 경우 로딩 인디케이터 표시
                          } else if (snapshot.hasError) {
                            return Text('Error: ${snapshot.error}');
                          } else if (!snapshot.hasData ||
                              snapshot.data!.isEmpty) {
                            return Text('No festivals data available.');
                          } else {
                            myschedules = snapshot!.data!;
                            print(myschedules.runtimeType);
                            return Column(
                              children: myschedules.map((schedule) => ScheduleItem(schedule)).toList(),
                            );
                          }
                        })
                  ],
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}

Padding ScheduleItem(ScheduleDto scheduleItem) {
  return Padding(
    padding: const EdgeInsets.all(8.0),
    child: Material(
      elevation: 4,
      borderRadius: BorderRadius.circular(8),
      child: Padding(
        padding: const EdgeInsets.all(20.0),
        child: Container(
          child: Row(
            children: [
              Expanded(
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text(
                        scheduleItem.ProgramTitle,
                        style: TextStyle(fontSize: 20),
                      ),
                      SizedBox(
                        height: 30,
                        width: 2,
                        child: Container(
                          color: AppColor.PrimaryPink,
                        ),
                      ),
                      Text(scheduleItem.programOutline),
                      SizedBox(
                        height: 30,
                        width: 2,
                        child: Container(
                          color: AppColor.PrimaryPink,
                        ),
                      ),
                      Column(
                        children: [
                          Text(
                              "${scheduleItem.startTime.toString()}      ~      ${scheduleItem.endTime.toString()}"),
                        ],
                      ),
                    ],
                  )),
            ],
          ),
        ),
      ),
    ),
  );
}