package com.ben.blog.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private Integer id;
    private String name;
    private String username;
    private Address address;
    private String phone;
    private String website;
    private Company company;
    private List<Post> posts=new ArrayList<Post>();
}
