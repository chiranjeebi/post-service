package com.myfb.postservice.controller;

import com.myfb.postservice.client.CommentDTO;
import com.myfb.postservice.dto.PostDTO;
import com.myfb.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PostController {

    @Autowired
    private PostService postService;    //take singleton obj and refer bean from here

    @PostMapping("/post")
    public ResponseEntity<PostDTO> createPostDTO(@RequestBody PostDTO postDTO) {
        ResponseEntity<PostDTO> responseEntity=null;
      postDTO=postService.createPostDTO(postDTO);//calling interface for that method
       responseEntity= new ResponseEntity<>(postDTO,HttpStatus.CREATED);
       return responseEntity;
    }

@GetMapping("/post/{postId}")
    public  ResponseEntity<PostDTO> getPostDetail(@PathVariable Long postId) {

    ResponseEntity<PostDTO> responseEntity=null;
    PostDTO postDTO=postService.getPostDetail(postId);
    responseEntity= new ResponseEntity<>(postDTO,HttpStatus.CREATED);
    return responseEntity;
}


    @GetMapping("/post/comments/{postId}")
    public CommentDTO[] getAllCommentsForPostId(@PathVariable Long postId){

        CommentDTO[]  comments=postService.getAllCommentsForPostId(postId);
        System.out.println(comments.length);

    }


}
