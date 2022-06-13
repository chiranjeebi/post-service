package com.myfb.postservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class PostDTO {

  private  Long Id;
    private String title;
    private String body;
    private Long userId;

}



