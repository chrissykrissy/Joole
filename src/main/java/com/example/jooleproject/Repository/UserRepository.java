package com.example.jooleproject.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.jooleproject.Entity.User;
import java.util.List;
public interface UserRepository extends JpaRepository<User,Integer>{

}
