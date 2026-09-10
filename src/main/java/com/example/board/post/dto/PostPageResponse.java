package com.example.board.post.dto;

import java.util.List;

public record PostPageResponse(
        List<PostListResponse> data,
        int page,
        long totalElements,
        int totalPages
) {
}
