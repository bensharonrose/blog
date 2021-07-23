package com.ben.blog.Controller;

import com.ben.blog.Model.Comment;
import com.ben.blog.Service.BlogService;
import com.ben.blog.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:3000" )
public class BlogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    BlogService blogService;

    @GetMapping(path="/usersAndPosts", produces = "application/json")
    public List<User> getUsersAndPosts()
    {
        return blogService.mergeUsersAndPostAPI();
    }

    @GetMapping(path="/comments", produces = "application/json")
    @ResponseBody
    public List<Comment> getComments(@RequestParam(required = false)String postId)
    {
        return blogService.proxyCommentsAPI(postId);
    }
}
