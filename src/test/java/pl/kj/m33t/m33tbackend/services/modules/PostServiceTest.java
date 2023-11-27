package pl.kj.m33t.m33tbackend.services.modules;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.kj.m33t.m33tbackend.entity.Event;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import pl.kj.m33t.m33tbackend.dao.modules.PostRepository;
import pl.kj.m33t.m33tbackend.dto.request.modules.PostRequest;
import pl.kj.m33t.m33tbackend.entity.modules.Post;
import pl.kj.m33t.m33tbackend.service.EventService;
import pl.kj.m33t.m33tbackend.service.modules.PostService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private EventService eventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        Long postId = 1L;
        Post post = new Post();
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        Post result = postService.findById(postId);

        verify(postRepository).findById(postId);
        assertNotNull(result);
        assertEquals(post, result);
    }

    @Test
    public void testCreatePost() {
        Long eventId = 1L;
        PostRequest postRequest = new PostRequest("Test", "test desc");
        Event event = new Event();
        when(eventService.findById(eventId)).thenReturn(event);

        Post newPost = new Post();
        when(postRepository.save(any(Post.class))).thenReturn(newPost);

        Post result = postService.createPost(eventId, postRequest);

        verify(eventService).findById(eventId);
        verify(postRepository).save(any(Post.class));
        assertNotNull(result);
        assertEquals(newPost, result);
    }

    @Test
    public void testSave() {
        Post post = new Post();
        when(postRepository.save(post)).thenReturn(post);

        Post result = postService.save(post);

        verify(postRepository).save(post);
        assertNotNull(result);
        assertEquals(post, result);
    }

    @Test
    public void testDeleteById() {
        Long postId = 1L;
        postService.deleteById(postId);

        verify(postRepository).deleteById(postId);
    }
}
