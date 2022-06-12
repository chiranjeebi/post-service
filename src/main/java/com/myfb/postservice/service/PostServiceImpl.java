package com.myfb.postservice.service;

import com.myfb.postservice.dto.PostDTO;
import com.myfb.postservice.entity.PostEntity;
import com.myfb.postservice.repository.PostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service                 // don't forget to add this otherwise it not gonna created Singleton Bean for postservice
public class PostServiceImpl  implements PostService{

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
}
