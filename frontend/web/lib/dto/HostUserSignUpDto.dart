class HostUserSignUpDto {
  final String organization;
  final String orgCode;
  final String orgNo;
  final String phoneNo;

  HostUserSignUpDto({
    required this.organization,
    required this.orgCode,
    required this.orgNo,
    required this.phoneNo
  });

  // factory UserDTO.fromJson(Map<String, dynamic> json) {
  //   return UserDTO(
  //     id: json['id'],
  //     username: json['username'],
  //     email: json['email'],
  //   );
  // }

  Map<String, dynamic> toJson() {
    return {
      'organization': organization,
      'orgCode': orgCode,
      'orgNo': orgNo,
      'phoneNo': phoneNo,
    };
  }
}
