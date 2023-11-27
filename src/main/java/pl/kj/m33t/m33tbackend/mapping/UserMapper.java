package pl.kj.m33t.m33tbackend.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.kj.m33t.m33tbackend.dto.request.UserRequest;
import pl.kj.m33t.m33tbackend.dto.response.UserResponse;
import pl.kj.m33t.m33tbackend.entity.User;

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