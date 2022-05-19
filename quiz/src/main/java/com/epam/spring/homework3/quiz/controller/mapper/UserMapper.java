package com.epam.spring.homework3.quiz.controller.mapper;

import com.epam.spring.homework3.quiz.controller.dto.UserDto;
import com.epam.spring.homework3.quiz.service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "repeatPasswd", ignore = true)
    @Mapping(target = "passwd", ignore = true)
    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

}
