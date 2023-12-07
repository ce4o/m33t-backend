package pl.kj.m33t.m33tbackend.service.modules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kj.m33t.m33tbackend.model.entity.Event;
import pl.kj.m33t.m33tbackend.model.entity.modules.Post;
import pl.kj.m33t.m33tbackend.model.repository.modules.PostRepository;
import pl.kj.m33t.m33tbackend.service.EventService;
import pl.kj.m33t.m33tbackend.service.dto.request.modules.PostRequest;
import pl.kj.m33t.m33tbackend.service.mapping.modules.PostMapper;

@Service
@RequiredArgsConstructor
public class PostService{

    private final PostRepository postRepository;
    private final EventService eventService;

    public Post findById(Long id){
        return postRepository.findById(id).orElse(null);
    }

    public Post createPost(Long eventId, PostRequest postDtoRequest) {
        Event event = eventService.findById(eventId);
        Post newPost = PostMapper.INSTANCE.fromPostRequestToPost(postDtoRequest);
        newPost.setEvent(event);
        return postRepository.save(newPost);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void deleteById(Long id){
        postRepository.deleteById(id);
    }
}