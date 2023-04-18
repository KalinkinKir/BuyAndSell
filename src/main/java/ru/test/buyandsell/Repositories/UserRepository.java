package ru.test.buyandsell.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.buyandsell.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email); 
}
