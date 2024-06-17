package com.ekz.ekzweb.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ekz.ekzweb.domain.user.User;

public interface IUserService extends IService<User> {
    User getUserInfoByName(String id);
}
