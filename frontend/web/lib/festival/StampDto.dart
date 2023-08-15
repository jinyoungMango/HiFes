class StampDto {
  final String missionTitle;
  final String missionOutline;
  final double missionLatitude;
  final double missionLongitude;

  StampDto({
    required this.missionTitle,
    required this.missionOutline,
    required this.missionLatitude,
    required this.missionLongitude,
  });

  Map<String, dynamic> toJson() {
    return {
      'missionTitle': missionTitle,
      'missionOutline': missionOutline,
      'missionLatitude': missionLatitude,
      'missionLongitude': missionLongitude,
    };
  }

  @override
  String toString() {
    return 'StampDto{'
        'missionTitle: $missionTitle, '
        'missionOutline: $missionOutline, '
        'missionLatitude: $missionLatitude, '
        'missionLongitude: $missionLongitude, }';
  }
}
