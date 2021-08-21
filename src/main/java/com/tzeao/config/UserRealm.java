package com.tzeao.config;

import com.tzeao.entity.User;
import com.tzeao.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 君子慎独
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权");
        return null;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
        User user = userService.checkUser(passwordToken.getUsername());

        if (user == null) {
            return null;
        }
        String dbPassword = user.getPassword();
        String salt = user.getSalt();
        return new SimpleAuthenticationInfo(user, dbPassword, ByteSource.Util.bytes(salt), "");
    }
}