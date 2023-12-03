package pl.kj.m33t.m33tbackend.service.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.kj.m33t.m33tbackend.service.dto.request.UserRequest;
import pl.kj.m33t.m33tbackend.service.dto.response.UserResponse;
import pl.kj.m33t.m33tbackend.model.entity.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nickname", source = "nickname")
    @Mapping(target = "email", source = "email")
    UserResponse fromUserToUserResponse(User user);

    @Mapping(target = "nickname", source = "nickname")
    @Mapping(target = "email", source = "email")
    User fromUserRequestToUser(UserRequest userRequest);

}