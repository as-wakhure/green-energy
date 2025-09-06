package com.ultragreenenery.UltraGreenEnergy.repository.user;

import com.ultragreenenery.UltraGreenEnergy.entity.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByLoginName(String userName);

    void deleteByLoginName(String userName);

}
