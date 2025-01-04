package com.spring.mapper;

import com.spring.entity.Trainer;
import com.spring.model.TrainerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TrainerMapper {

	TrainerMapper INSTANCE = Mappers.getMapper(TrainerMapper.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "username", ignore = true)
	@Mapping(target = "password", ignore = true)
	Trainer toEntity(TrainerDTO trainerDTO);

	TrainerDTO toDTO(Trainer trainer);
}
