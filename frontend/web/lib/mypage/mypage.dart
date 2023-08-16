import 'dart:core';

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:get/get.dart';
import 'package:web/constants.dart';
import 'package:web/festival/FestivalDto.dart';
import 'package:web/login/LoginController.dart';

import '../MainController.dart';
import '../common.dart';
import '../festival/ScheduleDto.dart';

class MyPage extends StatefulWidget {
  @override
  _MyPageState createState() => _MyPageState();
}

class _MyPageState extends State<MyPage> with SingleTickerProviderStateMixin {
  late TabController _tabController;
  final MainController _mainController =
      Get.find(tag: 'MainController');
  late Future<List<FestivalDto>> festivalsFuture;

  // 사용자 정보 저장
  var organization = "";
  var email = "";
  var phoneNo = "";

  // 자기 축제
  late List<FestivalDto> myfestivals;

  @override
  void initState() {
    super.initState();

    _tabController = TabController(
        length: 2,
        vsync: this); // 탭 개수를 2로 설정, SingleTickerProviderStateMixin 사용

    Future.delayed(Duration.zero, () async {
      var url = dotenv.env['YOUR_SERVER_URL']! + 'api/host/myPage';

      print("hi");
      print(_mainController.jAccessToken.value);

      var response = await Dio().post(url,
          options: Options(
              headers: {'accessToken': _mainController.jAccessToken.value}));

      if (response.statusCode == 200) {
        // 요청 성공 처리
        print('Request succeeded: ${response.data}');

        // 사용자 정보 json을 파싱해서 토큰 저장
        organization = response.data['organization'];
        email = response.data['email'];
        phoneNo = response.data['phoneNo'];
        // phoneNo = response.data['phoneNo'];
        setState(() {});
      } else {
        // 요청 실패 처리
        print('Request failed with status: ${response.statusCode}');
        print('Error message: ${response.data}');
      }
    });

    // festivalsFuture = fetchFestivals(); // 비동기 작업 시작
  }

  Future<List<FestivalDto>> fetchFestivals() async {
    try {
      var url = '${dotenv.env['YOUR_SERVER_URL']!}api/${_mainController.id}/festivals';

      var response = await Dio().get(url);

      if (response.statusCode == 200) {
        List<FestivalDto> festivals = [];
        for (var festivalJson in response.data) {
          var festival = FestivalDto.fromJson(festivalJson);
          print(festival);
          festivals.add(festival);
        }
        return festivals;
      } else {
        throw Exception('Failed to load festivals');
      }
    } catch (error) {
      throw Exception('Error fetching festivals: $error');
    }
  }

  @override
  void dispose() {
    _tabController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: TopBar(),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 80.0),
          child: Column(
            children: [
              SizedBox(
                height: 40,
              ),
              TabBar(
                indicatorColor: AppColor.PrimaryPink,
                controller: _tabController,
                tabs: [
                  Text(
                    "내 정보",
                    style: TextStyle(
                        fontWeight: FontWeight.bold,
                        color: Colors.black,
                        fontSize: 24),
                  ),
                  Text("축제 정보",
                      style: TextStyle(
                          fontWeight: FontWeight.bold,
                          color: Colors.black,
                          fontSize: 24)),
                ],
              ),
              Expanded(
                child: TabBarView(
                  controller: _tabController,
                  children: [
                    Container(
                      alignment: Alignment.topLeft,
                      child: Column(
                        children: [
                          SizedBox(
                            height: 40,
                          ),
                          Align(
                            alignment: Alignment.topLeft,
                            // Align the text to the top left
                            child: Text(
                              "Account",
                              style: TextStyle(
                                  color: Colors.black,
                                  fontSize: 40,
                                  fontWeight: FontWeight.bold),
                            ),
                          ),
                          SizedBox(
                            height: 40,
                          ),
                          Padding(
                            padding: const EdgeInsets.all(8.0),
                            child: Material(
                              elevation: 4,
                              borderRadius: BorderRadius.circular(8),
                              child: Padding(
                                padding: const EdgeInsets.all(20.0),
                                child: Container(
                                  child: Column(
                                    children: [
                                      AccountItem("기관명", organization),
                                      SizedBox(
                                        height: 40,
                                      ),
                                      AccountItem("이메일", email),
                                      SizedBox(
                                        height: 40,
                                      ),
                                      AccountItem("전화번호", phoneNo)
                                    ],
                                  ),
                                ),
                              ),
                            ),
                          )
                        ],
                      ),
                    ),
                    SingleChildScrollView(
                      child: Column(
                        children: [
                          SizedBox(
                            height: 40,
                          ),
                          Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: [
                              Align(
                                  alignment: Alignment.topLeft,
                                  child: Text(
                                    "My Festivals",
                                    style: TextStyle(
                                        color: Colors.black,
                                        fontSize: 40,
                                        fontWeight: FontWeight.bold),
                                  )),
                              ElevatedButton(
                                  style: ButtonStyle(
                                    backgroundColor:
                                        MaterialStateProperty.all<Color>(
                                            AppColor.PrimaryPink),
                                    minimumSize:
                                        MaterialStateProperty.all<Size>(
                                            Size(200, 48)),
                                    shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                                      RoundedRectangleBorder(
                                        borderRadius: BorderRadius.circular(8.0),
                                      ),
                                    ),
                                  ),
                                  onPressed: () {
                                    Get.rootDelegate.toNamed(Routes.REGISTER);
                                  },
                                  child: Text(
                                    "등록하기",
                                    style: TextStyle(
                                        color: Colors.white, fontSize: 16),
                                  ))
                            ],
                          ),
                          SizedBox(
                            height: 40,
                          ),
                          Column(
                            children: [
                              FutureBuilder<List<FestivalDto>>(
                                  future: fetchFestivals(),
                                  builder: (context, snapshot) {
                                    if (snapshot.connectionState ==
                                        ConnectionState.waiting) {
                                      return CircularProgressIndicator(); // 로딩 중인 경우 로딩 인디케이터 표시
                                    } else if (snapshot.hasError) {
                                      return Text('Error: ${snapshot.error}');
                                    } else if (!snapshot.hasData ||
                                        snapshot.data!.isEmpty) {
                                      return Text(
                                          'No festivals data available.');
                                    } else {
                                        myfestivals = snapshot!.data!;
                                        print(myfestivals.runtimeType);
                                        return Column(
                                          children: myfestivals.map((festival) => FestivalItem(festival)).toList(),
                                        );
                                    }
                                  })
                            ],
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Padding FestivalItem(FestivalDto fesItem) {
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
                    Container(
                        width: 100,
                        height: 100,
                        child: ClipRRect(
                            borderRadius: BorderRadius.circular(20),
                            child: Image.network(
                              "${fesItem.fesPosterPath}",
                              fit: BoxFit.cover,
                            ))),
                    SizedBox(
                      height: 30,
                      width: 2,
                      child: Container(
                        color: AppColor.PrimaryPink,
                      ),
                    ),
                    Text(
                      fesItem.fesTitle,
                      style: TextStyle(fontSize: 20),
                    ),
                    SizedBox(
                      height: 30,
                      width: 2,
                      child: Container(
                        color: AppColor.PrimaryPink,
                      ),
                    ),
                    Text(fesItem.fesAddress),
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
                            "${fesItem.fesStartDate.toString()}      ~      ${fesItem.fesEndDate.toString()}"),
                      ],
                    ),
                    ElevatedButton(
                        style: ButtonStyle(
                          backgroundColor: MaterialStateProperty.all<Color>(
                              AppColor.PrimaryPink),
                          minimumSize:
                              MaterialStateProperty.all<Size>(Size(100, 48)),
                          shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                            RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(8.0),
                            ),
                          ),
                        ),
                        onPressed: () {
                          _mainController.fid.value = fesItem.festivalId as int;
                          Get.rootDelegate.toNamed(Routes.FESTIVAL);
                        },
                        child: Text(
                          'Detail',
                          style: TextStyle(color: Colors.white, fontSize: 16),
                        ))
                  ],
                )),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Row AccountItem(String title, String name) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              title,
              style: TextStyle(fontWeight: FontWeight.bold, color: Colors.grey),
            ),
            Text(name,
                style: TextStyle(
                    fontWeight: FontWeight.bold,
                    color: Colors.black,
                    fontSize: 20))
          ],
        ),
      ],
    );
  }
}
