package org.dj.bms.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.dj.bms.model.Resource;
import org.dj.bms.model.User;
import org.dj.bms.service.ResourceService;
import org.dj.bms.service.UserService;

/**
 * @author Created by jason on 17/11/2.
 */
public class MyShiroRealm extends AuthorizingRealm {

	@javax.annotation.Resource
	private UserService userService;

	@javax.annotation.Resource
	private ResourceService resourceService;

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getId());
		List<Resource> resourcesList = resourceService.findUserResources(map);
		// 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		for (Resource resource : resourcesList) {
			info.addStringPermission(resource.getUrl());
		}
		return info;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户的输入的账号.
		String username = (String) token.getPrincipal();
		User user = userService.selectByUserName(username);
		if (user == null) {
			throw new UnknownAccountException();
		}
		// if (0==user.getEnable()) {
		// throw new LockedAccountException(); // 帐号锁定
		// }
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, // 用户
				user.getPassword(), // 密码
				ByteSource.Util.bytes(username), getName() // realm name
		);
		// 当验证都通过后，把用户信息放在session里
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("userSession", user);
		session.setAttribute("userSessionId", user.getId());
		return authenticationInfo;
	}
}
