package com.coco.service.imp;

import com.coco.mapper.LogsMapper;
import com.coco.mapper.UsersMapper;
import com.coco.pojo.Logs;
import com.coco.pojo.Users;
import com.coco.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class GuestServiceImp implements GuestService {
    @Autowired
    private LogsMapper logsMapper;
    @Autowired
    private UsersMapper usersMapper;

    @Transactional
    @Override
    public void registUser(Users u) {

        usersMapper.addUser(u);
        Logs l = new Logs();
        l.setMsg("新注册了一个用户---"+new Date());
        logsMapper.addMsg(l);
    }

    public LogsMapper getLogsMapper() {
        return logsMapper;
    }

    public void setLogsMapper(LogsMapper logsMapper) {
        this.logsMapper = logsMapper;
    }

    public UsersMapper getUsersMapper() {
        return usersMapper;
    }

    public void setUsersMapper(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }
}
