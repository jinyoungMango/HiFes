import 'package:get/get.dart';

class MainController extends GetxController {
  // 사용자 id
  var id = 0.obs;

  // 축제 id
  var fid = 0.obs;

  // 게시글 id
  var pid = 0.obs;

  // 카카오 토큰
  var kAccessToken = "".obs;
  var kRefreshToken = "".obs;

  // 스프링 JWT
  var jAccessToken = "".obs;
  var jRefreshToken = "".obs;
}
