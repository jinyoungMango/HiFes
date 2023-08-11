import 'package:get/get.dart';

class MainController extends GetxController {
  var id = 0.obs;

  // 카카오 토큰
  var kAccessToken = "".obs;
  var kRefreshToken = "".obs;

  // 스프링 JWT
  var jAccessToken = "".obs;
  var jRefreshToken = "".obs;
}
