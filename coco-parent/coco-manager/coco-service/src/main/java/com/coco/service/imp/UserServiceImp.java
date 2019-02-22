package com.coco.service.imp;

import com.coco.common.Const;
import com.coco.common.ServerResponse;
import com.coco.common.TokenCache;
import com.coco.common.util.MD5Util;
import com.coco.mapper.UserMapper;
import com.coco.pojo.User;
import com.coco.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service("UserService")
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Transactional
    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultcount = userMapper.checkUsername(username);
        if(resultcount==0)
        {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        String md5password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username,md5password);
        //User user = userMapper.selectLogin(username,password);
        if(user==null)
        {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功",user);


    }

    @Override
    public ServerResponse<String> register(User user) {
        ServerResponse vaildresponse = this.checkValid(user.getUsername(),Const.USERNAME);
        if(!vaildresponse.isSuccess())
        {
            return vaildresponse;
        }
        vaildresponse = this.checkValid(user.getPhone(),Const.PHONE);
        if(!vaildresponse.isSuccess())
        {
            return vaildresponse;
        }

        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        user.setEmail(user.getEmail());
        user.setQuestion(user.getQuestion());
        user.setAnswer(user.getAnswer());
        user.setRole(1);
        if((user.getPassword()==null)&&(user.getEmail()==null)&&(user.getQuestion()==null)&&(user.getAnswer()==null))
        {
            return ServerResponse.createByErrorMessage("必须填入密码邮箱密保问题及答案！");
        }
        int resultcount = userMapper.insert(user);
        if(resultcount==0)
        {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");


    }

    @Override
    public ServerResponse<String> checkValid(String str ,String type)
    {
        if(org.apache.commons.lang3.StringUtils.isNotBlank(type))
        {
            if (Const.USERNAME.equals(type))
            {
                int resultcount = userMapper.checkUsername(str);
                if(resultcount>0)
                {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if (Const.PHONE.equals(type))
            {
                int resultcount = userMapper.checkPhone(str);
                if(resultcount>0)
                {
                    return ServerResponse.createByErrorMessage("手机号码已存在");
                }
            } else
            {
                return ServerResponse.createByErrorMessage("参数错误");
            }
        }
            return ServerResponse.createBySuccessMessage("注册成功");

    }


    @Override
    public ServerResponse<String> selectQuestion(String username)
    {
        ServerResponse vaildresponse = this.checkValid(username,Const.USERNAME);
        if(!vaildresponse.isSuccess())
        {
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String question=userMapper.selectQuestionByUsername(username);
        return ServerResponse.createBySuccess(question);

    }


    @Override
    public ServerResponse<String> checkAnswer(String username,String question,String answer)
    {
        int resultcount = userMapper.checkAnswer(username,question,answer);
        if(resultcount>0) {
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
        if(org.apache.commons.lang3.StringUtils.isBlank(forgetToken))
        {
            return ServerResponse.createByErrorMessage("参数错误，需要传递Token");

        }
        ServerResponse vaildresponse = this.checkValid(username,Const.USERNAME);
        if(vaildresponse.isSuccess())
        {
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        if(org.apache.commons.lang3.StringUtils.isBlank(token))
        {
            return ServerResponse.createByErrorMessage("Token无效或过期");
        }
        if(org.apache.commons.lang3.StringUtils.equals(forgetToken,token))
        {
            String md5Password  = MD5Util.MD5EncodeUtf8(passwordNew);
            int count = userMapper.updatePasswordByUsername(username,md5Password);
            if(count>0)
            {
                ServerResponse.createBySuccessMessage("找回并修改密码成功");
            }
        }
        return ServerResponse.createByErrorMessage("Token不匹配，无法修改");
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

        User updateuser = new User();
        updateuser.setId(user.getId());
        updateuser.setPhone(user.getPhone());
        updateuser.setEmail(user.getEmail());
        updateuser.setQuestion(user.getQuestion());
        updateuser.setAnswer(user.getAnswer());
        userMapper.updateByPrimaryKeySelective(updateuser);
        int updateCount = userMapper.updateByPrimaryKeySelective(updateuser);
        if(updateCount==0)
        {
            return ServerResponse.createByErrorMessage("更新个人信息失败");
        }
        return ServerResponse.createBySuccess("更新个人信息成功",updateuser);

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









}
