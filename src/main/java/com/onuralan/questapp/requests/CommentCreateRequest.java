package com.onuralan.questapp.requests;

import lombok.Data;

@Data
public class CommentCreateRequest {
    Long id;
    Long postId;
    Long UserId;
    String text;
}
