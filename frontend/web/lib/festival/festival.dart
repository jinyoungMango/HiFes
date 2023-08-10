import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:web/common.dart';

import '../constants.dart';

class Festival extends StatefulWidget {
  const Festival({super.key});

  @override
  State<Festival> createState() => _FestivalState();
}

class _FestivalState extends State<Festival> {
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
                              "https://i.imgur.com/05SMxGu.jpeg",
                              width: 800,
                              height: 400,
                              fit: BoxFit.cover,
                            ))),
                    SizedBox(height: 40,),
                    Container(
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Text(
                            "진달래 축제",
                            style: TextStyle(fontSize: 40),
                          ),
                          ElevatedButton(
                              style: ButtonStyle(
                                backgroundColor: MaterialStateProperty.all<Color>(
                                    AppColor.PrimaryPink),
                                minimumSize:
                                    MaterialStateProperty.all<Size>(Size(200, 48)),
                              ),
                              onPressed: () {
                                Get.rootDelegate.toNamed(Routes.BOARD);
                              },
                              child: Text(
                                "게시판",
                                style: TextStyle(color: Colors.white, fontSize: 16),
                              ))
                        ],
                      ),
                    ),
                    SizedBox(height: 20,),
                    Row(
                      children: [Text('경북 구미시 인동가산로 1-1 1층', style: TextStyle(fontSize: 16),)],
                    ),
                    SizedBox(height: 10,),
                    Row(
                      children: [
                        Container(
                          padding: EdgeInsets.symmetric(horizontal: 8, vertical: 4),
                          decoration: BoxDecoration(border: Border.all(
                            color: Colors.black, // 테두리 색상
                            width: 1.0, // 테두리 두께
                          ),
                            borderRadius: BorderRadius.circular(8.0),),
                          child: Text('4.2'),
                        ),
                        SizedBox(width: 10,),
                        Text('100 reviews')
                      ],
                    ),
                    SizedBox(height: 10,),
                    Align(alignment: Alignment.centerRight, child: Text('2023-08-08      ~      2023-08-18')),
                    SizedBox(height: 10,),
                    Divider(
                      height: 10,
                    ),
                    SizedBox(height: 10,),
                    Align(alignment: Alignment.centerLeft, child: Text("개요", style: TextStyle(fontSize: 20),)),
                    SizedBox(height: 10,),
                    Align(alignment: Alignment.centerLeft ,child: Text("다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다다람쥐쳇바퀴굴러간다")),
                    SizedBox(height: 10,),
                    Divider(
                      height: 10,
                    ),
                    SizedBox(height: 10,),
                    Text("지도", style: TextStyle(fontSize: 24),),
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
