package com.spring.mapper;

import com.spring.entity.Trainee;
import com.spring.entity.Trainer;
import com.spring.model.TraineeDTO;
import com.spring.model.TrainerDTO;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface TrainerMapper {

	TrainerMapper INSTANCE = Mappers.getMapper(TrainerMapper.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "username", ignore = true)
	@Mapping(target = "password", ignore = true)
	Trainer toEntity(TraineeDTO traineeDTO);

	TrainerDTO toDTO(Trainee trainee);
}
