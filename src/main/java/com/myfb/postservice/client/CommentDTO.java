package com.myfb.postservice.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)   //how we do certain value is null this way we do that
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentDTO {

    private Long postId;
    private Long id;
    private String name;
    private String email;
    private String body;

}
