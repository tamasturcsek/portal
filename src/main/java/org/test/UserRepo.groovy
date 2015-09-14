package org.test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>{
    public List<User> findByUsername(String username)
    public List<User> findByUsernameAndPassword(String username, String password)
}
