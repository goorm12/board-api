package com.example.board.post.dto;

// 게시글 생성 API의 요청 데이터를 전달하는 DTO입니다.
// 프론트에서 보낸 JSON의 값들이 같은 이름의 구성 요소에 들어갑니다.
// record는 private final 필드, 생성자, 접근 메서드 등을 자동으로 만들어줍니다.
public record PostCreateRequest(
        // 게시글 제목이며 request.title()로 읽습니다.
        String title,
        // 게시글 본문이며 request.content()로 읽습니다.
        String content,
        // 게시글 작성자이며 request.author()로 읽습니다.
        String author
) {
}
