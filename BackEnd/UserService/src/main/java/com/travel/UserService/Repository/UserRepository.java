package com.travel.UserService.Repository;

import com.travel.UserService.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByMobileNo(String mobileNo);

    boolean existsByPassword(String password);

    User findByFirstName(String firstName);
}
