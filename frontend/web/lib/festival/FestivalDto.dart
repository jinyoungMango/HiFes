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
  final double? avgRating;

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
    required this.avgRating
  });

  // 기본 생성자 (매개변수 없는 생성자)
  FestivalDto.empty()
      : fesTitle = "",
        fesOutline = "",
        fesAddress = "",
        fesPosterPath = "",
        fesStartDate = DateModel.empty(),
        fesEndDate = DateModel.empty(),
        fesLatitude = 0,
        fesLongitude = 0,
        festivalId = 0,
        avgRating = 0;


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
      avgRating: json['avgRating']
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

  // 기본 생성자 (매개변수 없는 생성자)
  DateModel.empty()
      : year = 0,
        month = 0,
        day = 0;

  @override
  String toString() {
    return '$year-$month-$day';
  }
}