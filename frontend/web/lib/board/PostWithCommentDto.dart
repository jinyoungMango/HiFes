import 'package:web/festival/ScheduleDto.dart';

class PostWithCommentDto {
  final int id;
  final int createdBy;
  final String title;
  final String content;
  final String postType;
  final bool isHidden;
  final DateModel createdAt;
  final int organizedFestivalId;
  final int views;
  final double rating;
  final List<CommentDto> topLevelComments;
  final int commentsCount;

  PostWithCommentDto({
    required this.id,
    required this.createdBy,
    required this.title,
    required this.content,
    required this.postType,
    required this.isHidden,
    required this.createdAt,
    required this.organizedFestivalId,
    required this.views,
    required this.rating,
    required this.topLevelComments,
    required this.commentsCount,
  });

  // 기본 생성자 (매개변수 없는 생성자)
  PostWithCommentDto.empty()
      : id = 0,
        createdBy = 0,
        title = "",
        content = "",
        postType = "",
        isHidden = false,
        createdAt = DateModel.empty(),
        organizedFestivalId = 0,
        views = 0,
        rating = 0,
        topLevelComments = [],
        commentsCount = 0;

  factory PostWithCommentDto.fromJson(Map<String, dynamic> json) {
    return PostWithCommentDto(
      id: json['id'],
      createdBy: json['createdBy'],
      title: json['title'],
      content: json['content'],
      postType: json['postType'],
      isHidden: json['isHidden'],
      createdAt: DateModel.fromJson(json['createdAt']),
      organizedFestivalId: json['organizedFestivalId'],
      views: json['views'],
      rating: json['rating'],
      topLevelComments: List<CommentDto>.from(json['topLevelComments']
          .map((commentJson) => CommentDto.fromJson(commentJson))),
      commentsCount: json['commentsCount'],
    );
  }

}

class CommentDto {
  final int id;
  final int? parentId;
  final String content;
  final DateModel createdAt;
  final List<CommentDto> childComments;

  CommentDto({
    required this.id,
    required this.parentId,
    required this.content,
    required this.createdAt,
    required this.childComments,
  });

  // 기본 생성자 (매개변수 없는 생성자)
  CommentDto.empty()
      : id = 0,
        parentId = 0,
        content = "",
        createdAt = DateModel.empty(),
        childComments = [];

  factory CommentDto.fromJson(Map<String, dynamic> json) {
    return CommentDto(
      id: json['id'],
      parentId: json['parentId'],
      content: json['content'],
      createdAt: DateModel.fromJson(json['createdAt']),
      childComments: List<CommentDto>.from(json['childComments']
          .map((commentJson) => CommentDto.fromJson(commentJson))),
    );
  }


}

