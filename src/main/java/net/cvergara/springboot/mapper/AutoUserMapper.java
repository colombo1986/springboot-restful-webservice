package net.cvergara.springboot.mapper;

import net.cvergara.springboot.dto.UserDto;
import net.cvergara.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class) ;
    UserDto mapToUserDto(User user) ;
    User mapToUser(UserDto userDto) ;

}
