package com.myfb.postservice.service;

import com.myfb.postservice.client.CommentDTO;
import com.myfb.postservice.dto.PostDTO;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public interface PostService {

public PostDTO createPostDTO(PostDTO postDTO);
public PostDTO getPostDetail(Long postId);
public  List<PostDTO>  getAllPostUserId(Long userId);
    CommentDTO[] getAllCommentsForPostId(Long postId);


}
