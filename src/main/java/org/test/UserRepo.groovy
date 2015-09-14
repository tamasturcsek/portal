package org.test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>{
    public List<User> findByUserAndPassword(String user, String password)
}
