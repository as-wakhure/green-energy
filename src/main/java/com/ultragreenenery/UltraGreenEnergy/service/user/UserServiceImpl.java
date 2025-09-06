package com.ultragreenenery.UltraGreenEnergy.service.user;

import com.ultragreenenery.UltraGreenEnergy.model.user.User;
import com.ultragreenenery.UltraGreenEnergy.exceptions.UserNotFoundException;
import com.ultragreenenery.UltraGreenEnergy.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository repository;
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public String saveUserData(User userDto) {
        com.ultragreenenery.UltraGreenEnergy.entity.user.User checkuser = repository.findByLoginName(userDto.getLoginName());
        if (checkuser != null) {
            throw new UserNotFoundException("The Username "+userDto.getLoginName() +" is already taken. Please try different Username");
        }
        com.ultragreenenery.UltraGreenEnergy.entity.user.User user=new com.ultragreenenery.UltraGreenEnergy.entity.user.User();
        user.setLoginName(userDto.getLoginName());
        user.setEmailId(userDto.getEmailId());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setMobileNo(userDto.getMobileNo());
        repository.save(user);
        return userDto.getLoginName()+" User Created Successfully..";
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userDtoList=new ArrayList<>();
       List<com.ultragreenenery.UltraGreenEnergy.entity.user.User> userList= repository.findAll();
        if(userList.isEmpty()){
            throw new UserNotFoundException("Users not found");
        }
            for (com.ultragreenenery.UltraGreenEnergy.entity.user.User user:userList){
                User userDto=new User();
                userDto.setLoginName(user.getLoginName());
                userDto.setName(user.getName());
                userDto.setEmailId(user.getEmailId());
                userDto.setMobileNo(user.getMobileNo());
                userDto.setPassword("hidden");
                userDtoList.add(userDto);
            }


        return userDtoList;
    }

    @Override
    public User getUserByName(String userName) {
        com.ultragreenenery.UltraGreenEnergy.entity.user.User user = repository.findByLoginName(userName);

        if (user == null) {
            throw new UserNotFoundException(userName + " User not found");
        }
        User userDto = new User();
        userDto.setLoginName(user.getLoginName());
        userDto.setName(user.getName());
        userDto.setEmailId(user.getEmailId());
        userDto.setMobileNo(user.getMobileNo());
        userDto.setPassword("hidden");


        return userDto;
    }

    @Override
    public String updateUser(User userDto) {

        Query query=new Query();
        query.addCriteria(Criteria.where("loginName").is(userDto.getLoginName()));
//        User existingUser = mongoTemplate.findOne(query, User.class);
//        if(existingUser==null){
//
//            throw new UserNotFoundException("User not found for update operation");
//        }
        Update update=new Update();
        update.set("emailId",userDto.getEmailId());
        update.set("name",userDto.getName());
        update.set("password",userDto.getPassword());
        update.set("mobileNo",userDto.getMobileNo());
        com.ultragreenenery.UltraGreenEnergy.entity.user.User existingUser = mongoTemplate.findAndModify(query,update,new FindAndModifyOptions().returnNew(true), com.ultragreenenery.UltraGreenEnergy.entity.user.User.class);
        System.out.println("existingUser updated ="+existingUser);
        if(existingUser==null){
            throw new UserNotFoundException(userDto.getLoginName()+" User not found for update operation");
        }
        return userDto.getLoginName()+" User Updated Successfully..";
    }

    @Override
    public String deleteUserByName(String userName) {
        Query query=new Query();
        query.addCriteria(Criteria.where("loginName").is(userName));
        com.ultragreenenery.UltraGreenEnergy.entity.user.User existingUser = mongoTemplate.findOne(query, com.ultragreenenery.UltraGreenEnergy.entity.user.User.class);
        if(existingUser==null){

            throw new UserNotFoundException(userName+" User not found for delete operation");
        }
        repository.deleteByLoginName(userName);
        return userName+" User Deleted Successfully..";
    }
}
