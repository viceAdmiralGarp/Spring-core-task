package com.spring.data;

import com.spring.entity.Entity;
import com.spring.entity.Trainee;
import com.spring.entity.Trainer;
import com.spring.entity.Training;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class DataContainer {
	private List<Entity<?>> entities = new ArrayList<>();

	public void setTraining(List<Training> training) {entities.addAll(training);}

	public void setTrainers(List<Trainer> trainers) {entities.addAll(trainers);}

	public void setTrainees(List<Trainee> trainees) {entities.addAll(trainees);}
}