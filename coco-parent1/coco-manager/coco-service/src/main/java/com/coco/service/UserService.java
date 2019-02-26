package com.coco.service;

import com.coco.commom.ServerResponse;
import com.coco.pojo.Address;
import com.coco.pojo.User;
import net.sf.jsqlparser.schema.Server;

import java.util.List;

/**
 * @author Ocean
 * @date 2019/2/22 10:44
 */
public interface UserService {

    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str,String type);

    ServerResponse<String> selectQuestion(String username);

    ServerResponse<String> checkAnswer(String username,String question,String answer);

    ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);

    ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user);

    ServerResponse<User> updateInformation(User user);

    ServerResponse<User> getInfo(Integer id);

    ServerResponse<List<Address>> getAddress(Integer userId);

    ServerResponse<String> addAddress(Address address,User user);

    ServerResponse<Address> updateAddress(Address address,User user);

    ServerResponse<String> deleteAddress(Integer id);

//    ServerResponse<Integer> updateDefault(Integer def);
//
//    ServerResponse<>
}

