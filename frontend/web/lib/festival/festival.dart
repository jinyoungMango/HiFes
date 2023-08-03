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
        child: Center(
          child: Padding(
            padding: const EdgeInsets.symmetric(horizontal: 80, vertical: 40),
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
                Container(
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text(
                        "축제 제목",
                        style: TextStyle(fontSize: 48),
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
                            style: TextStyle(color: Colors.white),
                          ))
                    ],
                  ),
                ),
                Row(
                  children: [Text('주소요')],
                ),
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
                    Text('100 reviews')
                  ],
                ),
                Align(alignment: Alignment.centerRight, child: Text('기간이요~')),
                Divider(
                  height: 10,
                ),
                Align(alignment: Alignment.centerLeft, child: Text("개요")),
                Align(alignment: Alignment.centerLeft ,child: Text("내용이다내용이다내용이다내용이다내용이다내용이다내용이다내용이다내용이다내용이다내용이다내용이다내용이다")),
                Divider(
                  height: 10,
                ),
                Text("지도", style: TextStyle(fontSize: 24),),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
