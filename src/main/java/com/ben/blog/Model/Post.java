package com.ben.blog.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;
    private List<Comment> comments;
}
