package com.example.board.post.controller;

import com.example.board.post.dto.PostCreateRequest;
import com.example.board.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// HTTP 요청을 받아 JSON 등의 응답을 반환하는 REST 컨트롤러로 등록합니다.
@RestController
// 이 컨트롤러가 처리하는 모든 API 주소 앞에 api/posts를 공통으로 붙입니다.
@RequestMapping("api/posts")
public class PostController {
    // Controller는 DB에 직접 접근하지 않고 Service에 작업을 요청합니다.
    // final은 생성 이후 postService 참조가 다른 객체로 바뀌지 않도록 합니다.
    private final PostService postService;

    // 생성자 주입: Spring이 관리하는 PostService 객체를 Controller에 전달합니다.
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // HTTP POST /api/posts 요청을 이 메서드와 연결합니다.
    @PostMapping
    // @RequestBody는 요청 JSON을 PostCreateRequest 객체로 변환합니다.
    // ResponseEntity<Long>은 HTTP 응답 본문에 게시글 id(Long)를 담는다는 뜻입니다.
    public ResponseEntity<Long> create(@RequestBody PostCreateRequest request) {
        // 게시글 생성 처리를 Service에 위임하고 생성된 게시글 id를 받습니다.
        Long postId = postService.create(request);

        // HTTP 상태 코드 200 OK와 생성된 게시글 id를 반환합니다.
        return ResponseEntity.ok(postId);
    }
}


