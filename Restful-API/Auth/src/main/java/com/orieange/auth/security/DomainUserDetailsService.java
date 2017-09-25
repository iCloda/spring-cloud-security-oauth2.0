package com.orieange.auth.security;

import com.orieange.biz.auth.UserDetailService;
import com.orieange.vo.auth.Subscriber;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    @Resource
    private UserDetailService userDetailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Subscriber subscriber=userDetailService.loadUser(username);
        if(subscriber==null){
            throw  new UsernameNotFoundException("用户" + username + "不存在!");
        }
        Set<GrantedAuthority> grantedAuthorities =new HashSet<>();
        subscriber.getUserAuthotities().forEach(str->grantedAuthorities.add(new SimpleGrantedAuthority(str)));
        return new User(subscriber.getUsername(),subscriber.getPassword(),grantedAuthorities);
    }
}
