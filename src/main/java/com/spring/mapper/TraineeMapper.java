package com.spring.mapper;

import com.spring.entity.Trainee;
import com.spring.model.TraineeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TraineeMapper {

	TraineeMapper INSTANCE = Mappers.getMapper(TraineeMapper.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "username", ignore = true)
	@Mapping(target = "password", ignore = true)
	Trainee toEntity(TraineeDTO traineeDTO);

	TraineeDTO toDTO(Trainee trainee);
}
