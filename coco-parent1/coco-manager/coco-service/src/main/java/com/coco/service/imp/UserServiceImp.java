package com.coco.service.imp;

import com.coco.commom.Const;
import com.coco.commom.ServerResponse;
import com.coco.commom.TokenCache;
import com.coco.mapper.AddressMapper;
import com.coco.mapper.UserMapper;
import com.coco.pojo.Address;
import com.coco.pojo.User;
import com.coco.service.UserService;

import com.coco.commom.util.MD5Util;
import net.sf.jsqlparser.schema.Server;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author Ocean
 * @date 2019/2/22 10:43
 */


@Service("UserService")
public class UserServiceImp implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AddressMapper addressMapper;


    @Transactional
    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if(resultCount == 0 ){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user  = userMapper.selectLogin(username,md5Password);
        if(user == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }

        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功",user);
    }

    @Override
    public ServerResponse<String> register(User user) {

        ServerResponse validResponse = this.checkValid(user.getUsername(),Const.USERNAME);
        if(!validResponse.isSuccess())
        {
            return validResponse;
        }
        validResponse = this.checkValid(user.getPhone(),Const.PHONE);
        if(!validResponse.isSuccess())
        {
            return validResponse;
        }

        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        user.setEmail(user.getEmail());
        user.setQuestion(user.getQuestion());
        user.setAnswer(user.getAnswer());
        user.setRole(1);
        int resultCount = userMapper.insert(user);
        if(resultCount==0)
        {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");

    }

//  public static void main(String args[])
//  {
//      System.out.println(Const.USERNAME);
//  }


    @Override
    public ServerResponse<String> checkValid(String str ,String type)
    {
        System.out.println("type");
        if(org.apache.commons.lang3.StringUtils.isNotBlank(type)){
            //开始校验
            if(Const.USERNAME.equals(type)){
                int resultCount = userMapper.checkUsername(str);
                if(resultCount > 0 ){
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if(Const.PHONE.equals(type)){
                int resultCount = userMapper.checkPhone(str);
                if(resultCount > 0 ){
                    return ServerResponse.createByErrorMessage("phone已存在");
                }
            }
        }else{
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }


    @Override
    public ServerResponse<String> selectQuestion(String username)
    {
        ServerResponse validResponse = this.checkValid(username,Const.USERNAME);
        if(validResponse.isSuccess())
        {
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String question=userMapper.selectQuestionByUsername(username);
        return ServerResponse.createBySuccess(question);

    }


    @Override
    public ServerResponse<String> checkAnswer(String username,String question,String answer)
    {
        int resultCount = userMapper.checkAnswer(username,question,answer);
        if(resultCount>0) {
            //说明问题及问题答案是这个用户的,并且是正确的
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }

        return ServerResponse.createByErrorMessage("问题答案错误");

    }


    @Override
    public ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken)
    {
        if(org.apache.commons.lang3.StringUtils.isBlank(forgetToken)){
        return ServerResponse.createByErrorMessage("参数错误,token需要传递");
    }
    ServerResponse validResponse = this.checkValid(username,Const.USERNAME);
        if(validResponse.isSuccess()){
        //用户不存在
        return ServerResponse.createByErrorMessage("用户不存在");
    }
    String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if(org.apache.commons.lang3.StringUtils.isBlank(token)){
        return ServerResponse.createByErrorMessage("token无效或者过期");
    }

        if(org.apache.commons.lang3.StringUtils.equals(forgetToken,token)){
        String md5Password  = MD5Util.MD5EncodeUtf8(passwordNew);
        int rowCount = userMapper.updatePasswordByUsername(username,md5Password);

        if(rowCount > 0){
            return ServerResponse.createBySuccessMessage("修改密码成功");
        }
    }else{
        return ServerResponse.createByErrorMessage("token错误,请重新获取重置密码的token");
    }
        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    @Override
    public ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user)
    {
        //防止横向越权,要校验一下这个用户的旧密码,一定要指定是这个用户.因为我们会查询一个count(1),如果不指定id,那么结果就是true啦count>0;
        int checkCount = userMapper.checkPasswordByUserId(MD5Util.MD5EncodeUtf8(passwordOld),user.getId());
        if(checkCount==0)
        {
            return ServerResponse.createByErrorMessage("旧密码错误");
        }
        String md5Password  = MD5Util.MD5EncodeUtf8(passwordNew);
        user.setPassword(md5Password);
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if(updateCount==0)
        {
            return ServerResponse.createBySuccessMessage("修改密码失败");
        }
        return ServerResponse.createBySuccessMessage("修改成功");

    }

    @Override
    public ServerResponse<User> updateInformation(User user){

        //username是不能被更新的
        //email也要进行一个校验,校验新的email是不是已经存在,并且存在的email如果相同的话,不能是我们当前的这个用户的.
        int phoneCount=userMapper.checkPhoneByUserId(user.getPhone(),user.getId());
        int emailCount=userMapper.checkEmailByUserId(user.getEmail(),user.getId());
        if (phoneCount>0)
        {
            return ServerResponse.createByErrorMessage("手机号码已被占用");
        }
        if (emailCount>0)
        {
            return ServerResponse.createByErrorMessage("邮箱已被占用");
        }

        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setPhone(user.getPhone());
        updateUser.setEmail(user.getEmail());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());
        userMapper.updateByPrimaryKeySelective(updateUser);
        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if(updateCount==0)
        {
            return ServerResponse.createByErrorMessage("更新个人信息失败");
        }
        return ServerResponse.createBySuccess("更新个人信息成功",updateUser);

    }

    @Override
    public ServerResponse<User> getInfo(Integer userId)
    {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user==null)
        {
            return ServerResponse.createByErrorMessage("找不到当前用户");
        }

        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }



    @Override
    public ServerResponse<List<Address>> getAddress(Integer userId) {
        List<Address> addressList = addressMapper.checkAddressByUserId(userId);
        if(addressList==null)
        {
            return ServerResponse.createByErrorMessage("找不到用户地址");
        }
        return ServerResponse.createBySuccess(addressList);
    }

    @Override
    public ServerResponse<String> addAddress(Address address,User user)
    {
        ServerResponse addressResponse = this.getAddress(address.getUserId());
        //ServerResponse addressResponse = this.getAddress(user.getId());
        if(addressResponse.isSuccess())
        {
            //如果找不到用户地址就将新增的地址设为默认收货地址
            address.setIsDefault(0);
        }else {
            address.setIsDefault(1);
        }
        address.setUserId(user.getId());
        address.setReceiverUsername(address.getReceiverUsername());
        address.setPostcode(address.getPostcode());
        address.setCity(address.getCity());
        address.setDistrict(address.getDistrict());
        address.setDetails(address.getDetails());

        int resultCount = addressMapper.insert(address);
        if(resultCount==0)
        {
            return ServerResponse.createByErrorMessage("新增地址失败");
        }

        return ServerResponse.createBySuccessMessage("新增地址成功");
    }

    @Override
    public ServerResponse<Address> updateAddress(Address address,User user)
    {
        Address updateAddress = new Address();
        updateAddress.setId(address.getId());
        updateAddress.setUserId(user.getId());
        updateAddress.setReceiverUsername(address.getReceiverUsername());
        updateAddress.setReceiverPhone(address.getReceiverPhone());
        updateAddress.setPostcode(address.getPostcode());
        updateAddress.setProvince(address.getProvince());
        updateAddress.setCity(address.getCity());
        updateAddress.setDistrict(address.getDistrict());
        updateAddress.setDetails(address.getDetails());
        updateAddress.setDetails(address.getDetails());
        updateAddress.setIsDefault(address.getIsDefault());
        int updateCount = addressMapper.updateByPrimaryKeySelective(updateAddress);
        if(updateCount==0)
        {
            return ServerResponse.createByErrorMessage("更新个人地址失败");
        }
        return ServerResponse.createBySuccess("更新个人地址成功",updateAddress);
    }


    @Override
    public ServerResponse<String> deleteAddress(Integer id)
    {
        int resultCount = addressMapper.deleteByPrimaryKey(id);
        if(resultCount>0)
        {
            return ServerResponse.createBySuccessMessage("删除成功");
        }

        return ServerResponse.createByErrorMessage("删除失败");
    }



//     public ServerResponse<Integer> updateDefault(Integer def)
//     {
//         if(def=0)
//         {
//
//             int resultCount = addressMapper.selectByDefault(def);
//             if(resultCount=0)
//            {
//
//            }
//
//         }
//     }



}
