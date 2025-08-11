package com.fidelitytechnologies.training.blogapp.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.fidelitytechnologies.training.blogapp.model.Category;
import com.fidelitytechnologies.training.blogapp.model.Tag;
import com.fidelitytechnologies.training.blogapp.model.dto.CategoryDto;
import com.fidelitytechnologies.training.blogapp.model.dto.PostDto;
import com.fidelitytechnologies.training.blogapp.model.dto.TagDto;
import com.fidelitytechnologies.training.blogapp.repositories.CategoryRepository;
import com.fidelitytechnologies.training.blogapp.repositories.PostRepository;
import com.fidelitytechnologies.training.blogapp.repositories.TagRepository;

@SpringBootTest
@ActiveProfiles("test")
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        postRepository.deleteAll();
        tagRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    private PostDto buildPostDto(String title) {
        Tag tag = tagRepository.save(new Tag("tech", "meta", "tech", "desc"));
        Category category = new Category();
        category.setName("general");
        category.setDescription("desc");
        category = categoryRepository.save(category);

        TagDto tagDto = new TagDto();
        tagDto.setId(tag.getId());
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());

        PostDto post = new PostDto();
        post.setTitle(title);
        post.setMetaTitle("meta");
        post.setSummary("summary");
        post.setContent("content");
        post.setTags(Arrays.asList(tagDto));
        post.setCategories(Arrays.asList(categoryDto));
        return post;
    }

    @Test
    void testCreatePost() {
        PostDto post = buildPostDto("First Post");
        PostDto saved = postService.createPost(null, post);
        assertNotNull(saved.getId());
        assertEquals(1, postRepository.count());
    }

    @Test
    void testModifyPost() {
        PostDto post = postService.createPost(null, buildPostDto("Original"));
        PostDto changes = new PostDto();
        changes.setTitle("Updated");
        changes.setMetaTitle("meta2");
        changes.setSummary("summary2");
        changes.setContent("content2");
        PostDto updated = postService.modifyPost(null, post.getId(), changes);
        assertEquals("Updated", updated.getTitle());
    }

    @Test
    void testDeletePost() {
        PostDto post = postService.createPost(null, buildPostDto("To Delete"));
        assertEquals(1, postRepository.count());
        postService.deletePostByID(null, post.getId());
        assertEquals(0, postRepository.count());
    }

    @Test
    void testSearchPostByTitle() {
        postService.createPost(null, buildPostDto("Alpha"));
        postService.createPost(null, buildPostDto("Beta"));
        List<PostDto> found = postService.getPostByTitle("Alpha");
        assertEquals(1, found.size());
        assertEquals("Alpha", found.get(0).getTitle());
    }
}
