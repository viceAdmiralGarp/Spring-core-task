package exception;

public class EntityDoesntExistByUserName extends RuntimeException{

	public EntityDoesntExistByUserName(String message){
		super(message);
	}
}
