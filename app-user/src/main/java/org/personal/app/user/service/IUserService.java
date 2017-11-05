package org.personal.app.user.service;

import org.personal.app.user.model.User;

/**
 * Created at: 2017-11-05 23:38
 *
 * @author guojing
 */
public interface IUserService {

    User show(String uid);

    User update(User user);

}
