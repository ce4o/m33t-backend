package pl.kj.m33t.m33tbackend.service.mapping.modules;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.kj.m33t.m33tbackend.service.dto.request.modules.PostRequest;
import pl.kj.m33t.m33tbackend.service.dto.response.modules.PostResponse;
import pl.kj.m33t.m33tbackend.model.entity.modules.Post;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "postId", source = "id")
    @Mapping(target = "eventId", source = "event.eventId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "date", source = "date")
    PostResponse fromPostToPostResponse(Post post);

    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    Post fromPostRequestToPost(PostRequest postRequest);

}