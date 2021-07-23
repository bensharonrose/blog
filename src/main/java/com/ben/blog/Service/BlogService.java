package com.ben.blog.Service;

import com.ben.blog.Controller.BlogController;
import com.ben.blog.Model.Comment;
import com.ben.blog.Model.Post;
import com.ben.blog.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BlogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogService.class);

    private final String URI_USERS = "/users";
    private final String URI_POSTS = "/posts";
    private final String URI_COMMENTS = "/comments/";
    private final String URI_COMMENTS_WITH_PARAM = "/comments?postId={postId}";

    @Autowired
    RestTemplate restTemplate;

    public List<User> mergeUsersAndPostAPI()
    {
        //Using RestTemplate
        List<User> usersArray = Arrays.stream(restTemplate.getForObject(URI_USERS, User[].class)).collect(Collectors.toList());

        List<Post> postArray = Arrays.stream(restTemplate.getForObject(URI_POSTS, Post[].class)).collect(Collectors.toList());

        System.out.println("postArray.size():"+postArray.size());

        usersArray.forEach(u-> postArray.stream()
                .filter(p -> u.getId().equals(p.getUserId()))
                .forEach(p->u.getPosts().add(p)));

        return usersArray;
    }

    public List<Comment> proxyCommentsAPI(String postId)
    {
        List<Comment> commentsArray;
        if(postId==null)
        {
            LOGGER.info("came inside if");
            commentsArray = Arrays.stream(restTemplate.getForObject(URI_COMMENTS, Comment[].class)).collect(Collectors.toList());
        }
        else
        {
            LOGGER.info("postId value:"+postId);
            LOGGER.info("came inside else");
            Map<String, String> params = new HashMap<String, String>();
            params.put("postId", postId);
            commentsArray = Arrays.stream(restTemplate.getForObject(URI_COMMENTS_WITH_PARAM, Comment[].class,params)).collect(Collectors.toList());
        }

        return commentsArray;
    }
}
