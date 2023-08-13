import 'dart:html';

class ScheduleDto {
  final double programId;
  final String programOutline;
  final String ProgramTitle;
  final DateModel startTime;
  final DateModel endTime;

  ScheduleDto({
    required this.programId,
    required this.programOutline,
    required this.ProgramTitle,
    required this.startTime,
    required this.endTime,
  });

  factory ScheduleDto.fromJson(Map<String, dynamic> json) {
    return ScheduleDto(
      programId: json['programId'],
      programOutline: json['programOutline'],
      ProgramTitle: json['programTitle'],
      startTime: DateModel.fromJson(json['startTime']),
      endTime: DateModel.fromJson(json['endTime']),
    );
  }

}

class DateModel {
  final DayModel date;
  final TimeModel time;

  DateModel({
    required this.date,
    required this.time,
  });

  factory DateModel.fromJson(Map<String, dynamic> json) {
    return DateModel(
      date: DayModel.fromJson(json['date']),
      time: TimeModel.fromJson(json['time']),
    );
  }

  @override
  String toString() {
    // TODO: implement toString
    return '''${date.toString()}
   ${time.toString()} 
    ''';
  }
}

class DayModel {
  final int year;
  final int month;
  final int day;

  DayModel({
    required this.year,
    required this.month,
    required this.day
  });

  factory DayModel.fromJson(Map<String, dynamic> json) {
    return DayModel(
      year: json['year'],
      month: json['month'],
      day: json['day']
    );
  }

  @override
  String toString() {
    // TODO: implement toString
    return '$year - $month - $day';
  }
}

class TimeModel {
  final int hour;
  final int minute;
  final int second;
  final double nano;

  TimeModel({
    required this.hour,
    required this.minute,
    required this.second,
    required this.nano
  });

  factory TimeModel.fromJson(Map<String, dynamic> json) {
    return TimeModel(
      hour: json['hour'],
      minute: json['minute'],
      second: json['second'],
      nano: json['nano'],
    );
  }

  @override
  String toString() {
    return '$hour - $minute - $second';
  }
}