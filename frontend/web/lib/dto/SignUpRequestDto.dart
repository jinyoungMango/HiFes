class SignUpRequestDto {
  final String organization;
  final String orgCode;
  final String orgNo;
  final String phoneNo;
  final String accessToken;

  SignUpRequestDto({
    required this.organization,
    required this.orgCode,
    required this.orgNo,
    required this.phoneNo,
    required this.accessToken
  });

  Map<String, dynamic> toJson() {
    return {
      'organization': organization,
      'orgCode': orgCode,
      'orgNo': orgNo,
      'phoneNo': phoneNo,
      'accessToken': accessToken
    };
  }
}
