package pl.kj.m33t.m33tbackend.service.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.kj.m33t.m33tbackend.model.entity.Event;
import pl.kj.m33t.m33tbackend.service.dto.request.EventRequest;
import pl.kj.m33t.m33tbackend.service.dto.response.EventResponse;

@Mapper
public interface EventMapper {
        EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

        @Mapping(target = "id", source = "eventId")
        @Mapping(target = "owner", source = "owner")
        @Mapping(target = "name", source = "name")
        @Mapping(target = "date", source = "date")
        @Mapping(target = "eventType", source = "eventType")
        @Mapping(target = "participants", source = "participants")
        @Mapping(target = "description", source = "description")
        @Mapping(target = "eventModulesList", source = "eventModulesList")
        EventResponse fromEventToEventResponse(Event event);

        @Mapping(target = "name", source = "name")
        @Mapping(target = "date", source = "date")
        @Mapping(target = "eventType", source = "eventType")
        @Mapping(target = "description", source = "description")
        Event fromEventRequestToEvent(EventRequest eventRequest);

}