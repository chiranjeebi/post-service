package com.myfb.postservice.service;

import com.myfb.postservice.client.CommentDTO;
import com.myfb.postservice.dto.PostDTO;
import com.myfb.postservice.entity.PostEntity;
import com.myfb.postservice.repository.PostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service                 // don't forget to add this otherwise it not gonna created Singleton Bean for postservice
public class PostServiceImpl  implements PostService{


    @Value("${rest.call.connection.timeout:}") //configure in application local profile
    private String postBaseUrl;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PostRepository postRepository;
    @Override
    public PostDTO createPostDTO(PostDTO postDTO) {

        PostEntity postEntity= new PostEntity();
        BeanUtils.copyProperties(postDTO,postEntity);//convert post  dto to entitiy and save to postrepository
    postEntity =    postRepository.save(postEntity); //after converting save entity obj to post entity
        BeanUtils.copyProperties(postEntity,postDTO);

        return postDTO;
    }

    @Override
    public PostDTO getPostDetail(Long postId) {
        PostEntity postEntity=null;

       Optional<PostEntity> optEntity= postRepository.findById(postId); //create optional obj to check whether id is present or not
      PostDTO postDTO=null;
        if (optEntity.isPresent()){
          postDTO = new PostDTO();
BeanUtils.copyProperties(optEntity.get(),postDTO);

        }
        optEntity.orElseThrow(()-> new RuntimeException("no post  found"+postId));//using lambda to throw exception
        return postDTO;
    }


    @Override
    public List<PostDTO> getAllPostUserId(@PathVariable Long userId) {

        List<PostEntity> posts= postRepository.findAllByUserId(userId);
        List<PostDTO> postDTOS=null;
        PostDTO postDTO=null;

        if (posts !=null && !posts.isEmpty()){
       postDTOS = new ArrayList<>();
       for (PostEntity pe :posts){
         postDTO=new PostDTO();
           BeanUtils.copyProperties(pe,postDTO);
           postDTOS.add(postDTO);
       }
        }

        return postDTOS;
    }

@GetMapping("/post/comments/{postId}")
    public CommentDTO[] getAllCommentsForPostId(Long postId){

        HttpHeaders headers= new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
  //HttpEntity<CommentDTO[]> httpEntity =new HttpEntity<>(headers );

    CommentDTO[]  comments= restTemplate.getForObject(this.postBaseUrl"https://jsonplaceholder.typicode.com/post/{postId}/comments", CommentDTO[].class,postId); //doing concatination with post baseurl
System.out.println(comments.length);
return comments;
    }
}
