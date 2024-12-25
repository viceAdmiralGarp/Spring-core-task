package service;

import entity.Trainer;
import lombok.RequiredArgsConstructor;
import mapper.trainer.TrainerMapper;
import model.TrainerDTO;
import org.springframework.stereotype.Service;
import repository.TrainerDAO;

@Service
@RequiredArgsConstructor
public class TrainerService {

	private final TrainerDAO trainerDAO;
	private final TrainerMapper trainerMapper;

//	public void updateTrainerNameByUserName(String userName, String newName){
//		trainerDAO.findTrainerByUsername(userName)
//				.ifPresent(trainer -> trainer.setFirstName(newName));
//	}
//
//	public Trainer findByTrainerByUsername(String userName){
//		 return trainerDAO.findTrainerByUsername(userName)
//				 .orElseThrow(() -> new NullPointerException(
//						 "A trainer with this username: '%s' was not found".formatted(userName)));
//
//	}
//
//	public Trainer createTrainer(TrainerDTO trainer){
//		Trainer entity = TrainerMapper.toEntity(trainer);
//	}

//	void updateEntityFirstNameByUserName(String userName, String firstName);
//
//	void updateEntityLastNameByUserName(String userName, String lastName);
//
//	void updateEntityPasswordByUserName(String userName, String password);
//
//	void updateEntityActiveStatusByUserName(String userName, boolean isActive);
}
