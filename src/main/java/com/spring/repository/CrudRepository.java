package com.spring.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<E, ID> {

	List<E> getAll();

	Optional<E> findEntityById(ID id);

	E save(E entity);

	void deleteEntityByUserName(String userName);

	void updateUserName(String userName, String newUserName);

	void entityDoesntExistsByUserName(String userName);

	void entityExistsByUserName(String userName);

}
