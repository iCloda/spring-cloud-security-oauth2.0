package com.orieange.biz.auth;

import com.orieange.vo.auth.Subscriber;

public interface UserDetailService {
    public Subscriber loadUser(String loginName);
}
