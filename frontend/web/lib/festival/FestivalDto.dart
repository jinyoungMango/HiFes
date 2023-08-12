class FestivalDto {
  final String fesTitle;
  final String fesOutline;
  final String fesAddress;
  final String fesPosterPath;
  final DateModel fesStartDate;
  final DateModel fesEndDate;
  final double fesLatitude;
  final double fesLongitude;
  final double festivalId;

  FestivalDto({
    required this.fesTitle,
    required this.fesOutline,
    required this.fesAddress,
    required this.fesPosterPath,
    required this.fesStartDate,
    required this.fesEndDate,
    required this.fesLatitude,
    required this.fesLongitude,
    required this.festivalId,
  });

  factory FestivalDto.fromJson(Map<String, dynamic> json) {
    return FestivalDto(
      fesTitle: json['fesTitle'],
      fesOutline: json['fesOutline'],
      fesAddress: json['fesAddress'],
      fesPosterPath: json['fesPosterPath'],
      fesStartDate: DateModel.fromJson(json['fesStartDate']),
      fesEndDate: DateModel.fromJson(json['fesEndDate']),
      fesLatitude: json['fesLatitude'],
      fesLongitude: json['fesLongitude'],
      festivalId: json['festivalId'],
    );
  }

  @override
  String toString() {
    // TODO: implement toString
    return '''
      FesTivalTitle : ${fesTitle},
      fesStartDate : ${fesStartDate},
      fesEndDate : ${fesEndDate},
      festivalId : ${festivalId}
    ''';
  }
}

class DateModel {
  final int year;
  final int month;
  final int day;

  DateModel({
    required this.year,
    required this.month,
    required this.day,
  });

  factory DateModel.fromJson(Map<String, dynamic> json) {
    return DateModel(
      year: json['year'],
      month: json['month'],
      day: json['day'],
    );
  }

  @override
  String toString() {
    return '$year-$month-$day';
  }
}