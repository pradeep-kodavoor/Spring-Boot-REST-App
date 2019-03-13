package com.practice.springboot.basics.SpringBootRESTApp;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.practice.springboot.basics.SpringBootRESTApp.controller.BookController;
import com.practice.springboot.basics.SpringBootRESTApp.domain.Book;
import com.practice.springboot.basics.SpringBootRESTApp.service.IBookService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class, secure = false)
public class BookControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	IBookService bookService;
	
	String mockInput = "[ {\"id\":\"1\",\"name\":\"Effective JAVA\",\"author\":\"Joshua\"},	{\"id\":\"2\",\"name\":\"Clean Code\",\"author\":\"Robert C Martin\"}]";

	@Test
	public void testGetBook() throws Exception {
		
		Mockito.when(bookService.getBookById(Mockito.anyLong())).thenReturn(new Book(1L, "Effective Java", "Joshua"));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/book/1").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println("MVC Result::::::" + result.getResponse().getContentAsString());

		String expected = "{id:1,name:\"Effective Java\",author:\"Joshua\"}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	public void testSaveBooks() throws Exception {

		List<Book> list = new ArrayList<>();
		list.add(new Book(1L, "Effective JAVA", "Joshua"));
		list.add(new Book(2L, "Joshua", "Robert C Martin"));
		
		Mockito.when(bookService.saveBooks(Mockito.anyList())).thenReturn(list);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/books").accept(MediaType.APPLICATION_JSON)
				.content(mockInput).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("MVC Result::::::" + result.getResponse().getContentAsString());

		MockHttpServletResponse response = result.getResponse();

		System.out.println(response.getStatus());

		assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());
	}


}
