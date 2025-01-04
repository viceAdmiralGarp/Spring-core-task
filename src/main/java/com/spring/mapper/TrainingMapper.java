package com.spring.mapper;

import com.spring.entity.Training;
import com.spring.model.TrainingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TrainingMapper {

	TrainingMapper INSTANCE = Mappers.getMapper(TrainingMapper.class);

	Training toEntity(TrainingDTO trainingDTO);

	TrainingDTO toDTO(Training Training);
}
