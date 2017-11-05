package org.personal.app.user.service;

import org.personal.app.commons.AppException;
import org.personal.app.user.model.User;
import org.springframework.stereotype.Service;

/**
 * Created at: 2017-11-05 23:38
 *
 * @author guojing
 */
@Service
public class UserServiceImpl implements IUserService {

    @Override
    public User show(String uid) {
        throw AppException.newParamException("异常测试");
//        return new User(uid);
    }

    @Override
    public User update(User user) {
        return user;
//        throw AppException.newParamException("无效的用户id: " + user.getUid());
    }
}
