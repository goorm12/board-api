package com.example.board.post.service;

import com.example.board.post.dto.PostCreateRequest;
import com.example.board.post.dto.PostListResponse;
import com.example.board.post.dto.PostPageResponse;
import com.example.board.post.entity.Post;
import com.example.board.post.repository.PostRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

// 비즈니스 로직을 처리하는 Spring Bean으로 등록합니다.
@Service
// 이 클래스의 DB 작업을 트랜잭션 단위로 처리합니다.
// 작업 도중 예외가 발생하면 해당 트랜잭션의 DB 변경을 되돌릴 수 있습니다.
@Transactional
public class PostService {

    // 게시글을 저장하고 조회하는 Repository입니다.
    private final PostRepository postRepository;

    // 생성자 주입: Spring Data JPA가 만든 PostRepository 구현 객체를 전달받습니다.
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 생성 요청 DTO를 받아 게시글을 저장하고 생성된 id를 반환합니다.
    public Long create(PostCreateRequest request) {
        // DTO의 값을 꺼내 DB에 저장할 Post 엔티티 객체를 만듭니다.
        Post post = new Post(request.title(), request.content(), request.author());

        // save()를 호출하면 Hibernate가 INSERT SQL을 만들어 실행합니다.
        // 반환되는 savedPost에는 DB가 자동 생성한 id가 들어 있습니다.
        Post savedPost = postRepository.save(post);

        // Controller가 응답에 사용할 수 있도록 저장된 게시글 id를 반환합니다.
        return savedPost.getId();
    }

    @Transactional(readOnly = true)
    public PostPageResponse getPosts(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);

        List<PostListResponse> data = posts.getContent().stream().map(post -> new PostListResponse(post.getId(), post.getTitle(), post.getAuthor())).toList();

        return new PostPageResponse(data,posts.getNumber(), posts.getTotalElements(), posts.getTotalPages());
    }
}
