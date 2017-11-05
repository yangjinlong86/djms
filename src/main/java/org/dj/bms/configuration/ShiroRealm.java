package org.dj.bms.configuration;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.dj.bms.model.Resource;
import org.dj.bms.model.Role;
import org.dj.bms.model.User;
import org.dj.bms.service.ResourceService;
import org.dj.bms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by jason on 17/11/2.
 */
public class ShiroRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    public ShiroRealm() {
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("开始身份验证");
        String username = (String) token.getPrincipal(); //获取用户名，默认和login.html中的username对应。
        User user = userService.findByUsername(username);

        if (user == null) {
            // 没有返回登录用户名对应的SimpleAuthenticationInfo对象时,
            // 就会在LoginController中抛出UnknownAccountException异常
            return null;
        }

        //验证通过返回一个封装了用户信息的AuthenticationInfo实例即可。
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户信息
                user.getPassword(), //密码
                getName() //realm name
        );
        //authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getCredentialsSalt())); //设置盐

        return authenticationInfo;
    }

    /**
     * 当访问到页面的时候，链接配置了相应的权限或者shiro标签才会执行此方法否则不会执行
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("开始权限配置");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        // 查询当前用户拥有的角色
        for (Role role: userService.findCurrentUserRoles(user)) {
            authorizationInfo.addRole(role.getId());
            // 查询每个角色拥有的权限
            for (Resource resource: resourceService.findResourcesByRoleId(role.getId())){
                authorizationInfo.addStringPermission(resource.getId());
            }

        }

        return authorizationInfo;
    }
}
