package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Trainer extends User {

	private TrainingType trainingType;

	public Trainer(long userId,
				   String firstName,
				   String lastName,
				   String username,
				   String password,
				   boolean isActive,
				   TrainingType trainingType) {
		super(userId, firstName, lastName, username, password, isActive);
		this.trainingType = trainingType;
	}
}
