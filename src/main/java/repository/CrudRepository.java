package repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<E, U> {

	List<E> getAll();

	Optional<E> findEntityByUserName(U userName);

	E save(E entity);

	void deleteEntityByUserName(String userName);

	void entityDoesntExistsByUserName(String userName);

	void entityExistsByUserName(String userName);

}
