package com.fidelitytechnologies.training.blogapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fidelitytechnologies.training.blogapp.model.*;
import com.fidelitytechnologies.training.blogapp.model.dto.*;
import com.fidelitytechnologies.training.blogapp.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostCommentRepository postCommentRepository;

    @Autowired
    private PostInteractionRepository postInteractionRepository;

    @BeforeEach
    void setup() {
        postInteractionRepository.deleteAll();
        postCommentRepository.deleteAll();
        postRepository.deleteAll();
        tagRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    private Tag createTag() {
        Tag tag = new Tag();
        tag.setName("tag");
        return tagRepository.save(tag);
    }

    private Category createCategory() {
        Category category = new Category();
        category.setName("category");
        return categoryRepository.save(category);
    }

    private Post createPostEntity() {
        Tag tag = createTag();
        Category category = createCategory();
        Post post = new Post();
        post.setTitle("Initial");
        post.setMetaTitle("Meta");
        post.setSummary("Summary");
        post.setContent("Content");
        post.setPublished(true);
        post.setCreatedAt(new Date());
        post.setPublishedAt(new Date());
        post.addTag(tag);
        post.addCategoy(category);
        return postRepository.save(post);
    }

    @Test
    void testCreatePost() throws Exception {
        Tag tag = createTag();
        Category category = createCategory();

        PostDto dto = new PostDto();
        dto.setTitle("My Post");
        dto.setMetaTitle("Meta");
        dto.setSummary("Summary");
        dto.setContent("Content");

        TagDto tagDto = new TagDto();
        tagDto.setId(tag.getId());
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());

        dto.setTags(Collections.singletonList(tagDto));
        dto.setCategories(Collections.singletonList(categoryDto));

        mockMvc.perform(post("/v1/blog_manager_area/post/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("My Post"));

        Assertions.assertEquals(1, postRepository.count());
    }

    @Test
    void testGetPostById() throws Exception {
        Post post = createPostEntity();

        mockMvc.perform(get("/v1/blog_manager_area/post/get/{id}", post.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Initial"));
    }

    @Test
    void testEditPost() throws Exception {
        Post post = createPostEntity();

        PostDto dto = new PostDto();
        dto.setTitle("Updated");
        dto.setMetaTitle("Meta");
        dto.setSummary("Summary");
        dto.setContent("Content");

        mockMvc.perform(put("/v1/blog_manager_area/post/edit/{id}", post.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated"));

        Assertions.assertEquals("Updated", postRepository.findById(post.getId()).get().getTitle());
    }

    @Test
    void testDeletePost() throws Exception {
        Post post = createPostEntity();

        mockMvc.perform(delete("/v1/blog_manager_area/post/delete/{id}", post.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Delete OK"));

        Assertions.assertEquals(0, postRepository.count());
    }

    @Test
    void testCommentEndpoints() throws Exception {
        Post post = createPostEntity();

        PostCommentDto dto = new PostCommentDto();
        dto.setTitle("First Comment");
        dto.setContent("Nice");
        dto.setPost_id(post.getId());

        MvcResult mvcResult = mockMvc.perform(post("/v1/blog_manager_area/post/comment/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("First Comment"))
                .andReturn();

        PostCommentDto response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), PostCommentDto.class);
        Long commentId = response.getId();
        Assertions.assertEquals(1, postCommentRepository.count());

        mockMvc.perform(get("/v1/blog_manager_area/post/comment/get/{id}", commentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(commentId));

        dto.setTitle("Updated Comment");
        mockMvc.perform(put("/v1/blog_manager_area/post/comment/edit/{id}", commentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Comment"));

        Assertions.assertEquals("Updated Comment", postCommentRepository.findById(commentId).get().getTitle());

        mockMvc.perform(delete("/v1/blog_manager_area/post/comment/delete/{id}", commentId))
                .andExpect(status().isOk())
                .andExpect(content().string("Delete OK"));

        Assertions.assertEquals(0, postCommentRepository.count());
    }

    @Test
    void testLikeRoute() throws Exception {
        Post post = createPostEntity();

        mockMvc.perform(put("/v1/blog_manager_area/post/like/{id}", post.getId()))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.flags").value(1));

        Assertions.assertEquals(1, postInteractionRepository.count());
    }

    @Test
    void testDislikeRoute() throws Exception {
        Post post = createPostEntity();

        mockMvc.perform(put("/v1/blog_manager_area/post/dislike/{id}", post.getId()))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.flags").value(2));

        Assertions.assertEquals(1, postInteractionRepository.count());
    }
}

