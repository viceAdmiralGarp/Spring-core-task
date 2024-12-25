package validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import repository.CrudRepository;

@Component
@RequiredArgsConstructor
public class UniqueUserNameValidator<E,U> {

	public CrudRepository<E,U> repository;

	public boolean existsByUserName(U userName){
		return repository.findEntityByUserName(userName).isPresent();
	}
}
