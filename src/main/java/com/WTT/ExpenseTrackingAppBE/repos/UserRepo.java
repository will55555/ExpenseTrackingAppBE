package com.WTT.ExpenseTrackingAppBE.repos;

import com.WTT.ExpenseTrackingAppBE.entities.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
//This interface defines the data access operations for the UserCredential entity.
//It extends ListCrudRepository, providing a set of CRUD operations that return List instead of Iterable.
@Repository
public interface UserRepo extends ListCrudRepository<User, String> {
    Optional<User> findByUserName(String userName);
}