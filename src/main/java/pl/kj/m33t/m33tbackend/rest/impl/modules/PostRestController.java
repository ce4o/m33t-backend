package pl.kj.m33t.m33tbackend.rest.impl.modules;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kj.m33t.m33tbackend.service.dto.response.modules.PostResponse;
import pl.kj.m33t.m33tbackend.service.dto.request.modules.PostRequest;
import pl.kj.m33t.m33tbackend.model.entity.modules.Post;
import pl.kj.m33t.m33tbackend.service.mapping.modules.PostMapper;
import pl.kj.m33t.m33tbackend.service.modules.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events/{eventId}/posts")
public class PostRestController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@PathVariable Long eventId, @RequestBody PostRequest postRequest) {
        Post createdPost = postService.createPost(eventId, postRequest);
        PostResponse postResponse = PostMapper.INSTANCE.fromPostToPostResponse(createdPost);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(PostMapper.INSTANCE.fromPostToPostResponse(postService.findById(postId)));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long postId, @RequestBody PostRequest postRequest) {
        Post post = postService.save(PostMapper.INSTANCE.fromPostRequestToPost(postRequest));
        return ResponseEntity.ok(PostMapper.INSTANCE.fromPostToPostResponse(post));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deleteById(postId);
        return ResponseEntity.noContent().build();
    }
}