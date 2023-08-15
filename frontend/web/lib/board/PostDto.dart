class PostDto {
  final int id;
  final int createdBy;
  final String title;
  final String postType;
  final int organizedFestivalId;
  final int views;
  final double rating;
  final int commentsCount;

  PostDto({
    required this.id,
    required this.createdBy,
    required this.title,
    required this.postType,
    required this.organizedFestivalId,
    required this.views,
    required this.rating,
    required this.commentsCount,
  });

  factory PostDto.fromJson(Map<String, dynamic> json) {
    return PostDto(
      id: json['id'],
      createdBy: json['createdBy'],
      title: json['title'],
      postType: json['postType'],
      organizedFestivalId: json['organizedFestivalId'],
      views: json['views'],
      rating: json['rating'],
      commentsCount: json['commentsCount'],
    );
  }

}
