package com.orieange.auth.service;

import com.orieange.auth.dao.SubscriberDao;
import com.orieange.biz.auth.UserDetailService;
import com.orieange.model.auth.CoreSubscriberInfo;
import com.orieange.vo.auth.Subscriber;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Resource
    private SubscriberDao subscriberDao;

    @Override
    public Subscriber loadUser(String loginName) {
        CoreSubscriberInfo subscriberInfo = subscriberDao.findOne((root, query, cb) ->
                cb.equal(root.<String>get("loginName"), loginName)
        );
        if(subscriberInfo!=null){
            Subscriber subscriber = new Subscriber();
            BeanUtils.copyProperties(subscriberInfo,subscriber,"roles");
            Set<String> userAuthotities=new HashSet<>();
            subscriberInfo.getRoles().forEach(role->{
                role.getMenus().forEach(menu -> {
                    menu.getPermissions().forEach(permission -> {
                        userAuthotities.add(permission.getValue());
                    });
                });
            });
            subscriber.setUserAuthotities(userAuthotities);
            return subscriber;
        }
        return null;
    }
}
