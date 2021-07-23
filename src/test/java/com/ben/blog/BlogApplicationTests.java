package com.ben.blog;

import com.ben.blog.Controller.BlogController;
import com.ben.blog.Model.*;
import com.ben.blog.Service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(BlogController.class)
class BlogApplicationTests {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;

	@MockBean
	BlogService blogService;

	//Test Data to verify getUsersAndPosts
	Geo geoForUser1=new Geo("-37.3159","81.1496");
	Address addressForUser1=new Address("Kulas Light","Apt. 556","Gwenborough","92998-3874",geoForUser1);
	Company companyForUser1=new Company("Romaguera-Crona",null,"harness real-time e-markets");
	List<Comment> comments;
	Post post1ForUser1=new Post(1,1,"sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
			"quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto",
			comments);
	List<Post> post1 = new ArrayList<Post>(Arrays.asList(post1ForUser1));
	User user1=new User(1,"Leanne Graham","Bret",addressForUser1,"1-770-736-8031 x56442","hildegard.org",companyForUser1,post1);

	Comment comment1=new Comment(1,1,"id labore ex et quam laborum","Eliseo@gardner.biz","laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium");
	Comment comment2=new Comment(1,2,"quo vero reiciendis velit similique earum","Jayne_Kuhic@sydney.com", "est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et");

	@Test
	public void getAllRecordsForGetUsersAndPosts_success() throws Exception {
		List<User> userRecords = new ArrayList<User>(Arrays.asList(user1));


		Mockito.when(blogService.mergeUsersAndPostAPI()).thenReturn(userRecords);

		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/usersAndPosts")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].name", is("Leanne Graham")));
	}

	@Test
	public void getAllRecordsForGetComments_success() throws Exception {
		List<Comment> commentRecords = new ArrayList<Comment>(Arrays.asList(comment1,comment2));

		Mockito.when(blogService.proxyCommentsAPI("1")).thenReturn(commentRecords);

		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/comments?postId=1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].email", is("Eliseo@gardner.biz")));
	}

}
