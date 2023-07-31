import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:web/constants.dart';
import 'common.dart';

class MyPage extends StatefulWidget {
  @override
  _MyPageState createState() => _MyPageState();
}

class _MyPageState extends State<MyPage> with SingleTickerProviderStateMixin {
  late TabController _tabController;

  @override
  void initState() {
    super.initState();
    _tabController = TabController(
        length: 2,
        vsync: this); // 탭 개수를 2로 설정, SingleTickerProviderStateMixin 사용
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
              SizedBox(height: 40,),
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
                              style:
                                  TextStyle(color: Colors.black, fontSize: 40),
                            ),
                          ),
                          SizedBox(
                            height: 40,
                          ),
                          Material(
                            elevation: 4,
                            borderRadius: BorderRadius.circular(8),
                            child: Padding(
                              padding: const EdgeInsets.all(20.0),
                              child: Container(
                                child: Column(
                                  children: [
                                    AccountItem("기관명"),
                                    SizedBox(
                                      height: 40,
                                    ),
                                    AccountItem("이메일"),
                                    SizedBox(
                                      height: 40,
                                    ),
                                    AccountItem("전화번호")
                                  ],
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

                          Row(mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: [
                              Align(
                                  alignment: Alignment.topLeft,
                                  child: Text(
                                    "My Festivals",
                                    style: TextStyle(
                                        color: Colors.black, fontSize: 40),
                                  )
                              ),
                              ElevatedButton(
                                  style: ButtonStyle(
                                    backgroundColor: MaterialStateProperty.all<Color>(
                                        AppColor.PrimaryPink),
                                    minimumSize:
                                    MaterialStateProperty.all<Size>(Size(200, 48)),
                                  ),
                                  onPressed: () {Get.rootDelegate.toNamed(Routes.LOGIN);},
                                  child: Text(
                                    "등록하기",
                                    style: TextStyle(color: Colors.white),
                                  ))
                            ],
                          ),
                          SizedBox(
                            height: 40,
                          ),
                          Column(
                            children: [
                              FestivalItem(),
                              SizedBox(
                                height: 20,
                              ),
                              FestivalItem(),
                              SizedBox(
                                height: 20,
                              ),
                              FestivalItem(),
                              SizedBox(
                                height: 20,
                              ),
                              FestivalItem(),
                              SizedBox(
                                height: 20,
                              ),
                              FestivalItem(),
                              SizedBox(
                                height: 20,
                              ),
                              FestivalItem()
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
      bottomNavigationBar: BottomBar(),
    );
  }

  Material FestivalItem() {
    return Material(
      elevation: 4,
      borderRadius: BorderRadius.circular(8),
      child: Padding(
        padding: const EdgeInsets.all(20.0),
        child: Container(
          child: Row(
            children: [
              Expanded(
                  child: Row(
                children: [
                  Container(
                    width: 100, height: 100, color: AppColor.PrimaryPink,
                  ),
                  SizedBox(
                    width: 40,
                  ),
                  SizedBox(
                    height: 30,
                    width: 2,
                    child: Container(
                      color: AppColor.PrimaryPink,
                    ),
                  ),
                  SizedBox(
                    width: 40,
                  ),
                  Text("축제명"),
                  SizedBox(
                    width: 40,
                  ),
                  SizedBox(
                    height: 30,
                    width: 2,
                    child: Container(
                      color: AppColor.PrimaryPink,
                    ),
                  ),
                  SizedBox(
                    width: 40,
                  ),
                  Text("축제 위치"),
                  SizedBox(
                    width: 40,
                  ),
                  SizedBox(
                    height: 30,
                    width: 2,
                    child: Container(
                      color: AppColor.PrimaryPink,
                    ),
                  ),
                  SizedBox(
                    width: 40,
                  ),
                  Column(
                    children: [Text("날짜"), Text("시간")],
                  ),
                ],
              )),
              ElevatedButton(
                  style: ButtonStyle(
                    backgroundColor: MaterialStateProperty.all<Color>(
                        AppColor.PrimaryPink),
                    minimumSize:
                    MaterialStateProperty.all<Size>(Size(100, 48)),
                  ),
                  onPressed: () {Get.rootDelegate.toNamed(Routes.LOGIN);},
                  child: Text(
                    "Detail",
                    style: TextStyle(color: Colors.white),
                  ))
            ],
          ),
        ),
      ),
    );
  }

  Row AccountItem(String name) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              name,
              style: TextStyle(fontWeight: FontWeight.bold, color: Colors.grey),
            ),
            Text(name,
                style: TextStyle(
                    fontWeight: FontWeight.bold,
                    color: Colors.black,
                    fontSize: 20))
          ],
        ),
        Container(
          alignment: Alignment.center,
          width: 80,
          decoration: BoxDecoration(
            border: Border.all(color: Colors.black, width: 1),
            // Border color and width
            borderRadius: BorderRadius.circular(8), // Border radius
          ),
          child: Padding(
            padding: const EdgeInsets.all(8.0),
            child: Text(
              "Change",
            ),
          ),
        )
      ],
    );
  }
}
