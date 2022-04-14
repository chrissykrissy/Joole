package com.example.jooleproject.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.jooleproject.Entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{

    List<User>findByUserId(Integer userID);

    Optional<User> findByUsername(String username);
}
