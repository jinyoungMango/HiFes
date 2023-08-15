class MarkerDto {

  final String boothName;
  final String description;
  final int boothNo;
  final double boothLatitude;
  final double boothLongitude;


  MarkerDto({
    required this.boothName,
    required this.description,
    required this.boothNo,
    required this.boothLatitude,
    required this.boothLongitude,
  });

  Map<String, dynamic> toJson() {
    return {
      'boothName': boothName,
      'description': description,
      'boothNo': boothNo,
      'boothLatitude': boothLatitude,
      'boothLongitude': boothLongitude,
    };
  }

  @override
  String toString() {
    return 'MarkerDto{'
        'boothName: $boothName, '
        'description: $description, '
        'boothNo: $boothNo, '
        'boothLatitude: $boothLatitude, '
        'boothLongitude: $boothLongitude, }';
  }
}
