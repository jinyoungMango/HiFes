class MarkerDto {
  final double boothLatitude;
  final double boothLongitude;
  final String markerType;
  final int markerId;
  final String markerTitle;
  final String markerDescription;

  MarkerDto({
    required this.boothLatitude,
    required this.boothLongitude,
    required this.markerType,
    required this.markerId,
    required this.markerTitle,
    required this.markerDescription,
  });

  @override
  String toString() {
    return 'MarkerDto{'
        'boothLatitude: $boothLatitude, '
        'boothLongitude: $boothLongitude, '
        'markerType: $markerType, '
        'markerId: $markerId, '
        'markerTitle: $markerTitle, '
        'markerDescription: $markerDescription}';
  }
}
