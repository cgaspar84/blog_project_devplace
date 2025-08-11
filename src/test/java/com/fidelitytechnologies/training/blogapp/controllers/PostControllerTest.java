package com.fidelitytechnologies.training.blogapp.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fidelitytechnologies.training.blogapp.model.Category;
import com.fidelitytechnologies.training.blogapp.model.Tag;
import com.fidelitytechnologies.training.blogapp.model.dto.CategoryDto;
import com.fidelitytechnologies.training.blogapp.model.dto.PostDto;
import com.fidelitytechnologies.training.blogapp.model.dto.TagDto;
import com.fidelitytechnologies.training.blogapp.repositories.CategoryRepository;
import com.fidelitytechnologies.training.blogapp.repositories.PostRepository;
import com.fidelitytechnologies.training.blogapp.repositories.TagRepository;
import com.fidelitytechnologies.training.blogapp.services.PostService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostService postService;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void setup() {
        postRepository.deleteAll();
        tagRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    private PostDto buildPostDto(String title, Long tagId, Long categoryId) {
        TagDto tagDto = new TagDto();
        tagDto.setId(tagId);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryId);
        PostDto dto = new PostDto();
        dto.setTitle(title);
        dto.setMetaTitle("meta");
        dto.setSummary("summary");
        dto.setContent("content");
        dto.setTags(Arrays.asList(tagDto));
        dto.setCategories(Arrays.asList(categoryDto));
        return dto;
    }

    @Test
    void testCreatePostEndpoint() throws Exception {
        Tag tag = tagRepository.save(new Tag("spring", "meta", "spring", "desc"));
        Category category = new Category();
        category.setName("tech");
        category.setDescription("desc");
        category = categoryRepository.save(category);
        PostDto dto = buildPostDto("New Post", tag.getId(), category.getId());
        mockMvc.perform(post("/v1/blog_manager_area/post/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Post"));
    }

    @Test
    void testEditPostEndpoint() throws Exception {
        Tag tag = tagRepository.save(new Tag("spring", "meta", "spring", "desc"));
        Category category = new Category();
        category.setName("tech");
        category.setDescription("desc");
        category = categoryRepository.save(category);
        PostDto created = postService.createPost(null, buildPostDto("Old", tag.getId(), category.getId()));

        PostDto changes = new PostDto();
        changes.setTitle("Updated");
        changes.setMetaTitle("meta2");
        changes.setSummary("summary2");
        changes.setContent("content2");

        mockMvc.perform(put("/v1/blog_manager_area/post/edit/" + created.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(changes)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated"));
    }

    @Test
    void testDeletePostEndpoint() throws Exception {
        Tag tag = tagRepository.save(new Tag("spring", "meta", "spring", "desc"));
        Category category = new Category();
        category.setName("tech");
        category.setDescription("desc");
        category = categoryRepository.save(category);
        PostDto created = postService.createPost(null, buildPostDto("ToDelete", tag.getId(), category.getId()));

        mockMvc.perform(delete("/v1/blog_manager_area/post/delete/" + created.getId()))
                .andExpect(status().isOk());
        assertEquals(0, postRepository.count());
    }

    @Test
    void testSearchByTagEndpoint() throws Exception {
        Tag tag = tagRepository.save(new Tag("spring", "meta", "spring", "desc"));
        Category category = new Category();
        category.setName("tech");
        category.setDescription("desc");
        category = categoryRepository.save(category);
        postService.createPost(null, buildPostDto("Searchable", tag.getId(), category.getId()));

        mockMvc.perform(get("/v1/blog_manager_area/post/search_by_tag/spring"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Searchable"));
    }
}
