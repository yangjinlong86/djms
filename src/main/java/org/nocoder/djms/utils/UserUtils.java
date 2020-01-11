package org.nocoder.djms.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.SecurityUtils;
import org.nocoder.djms.model.User;

/**
 * @author pufangfei@163.com
 * @ClassName: UserUtils
 * @Description: TODO
 * @date 2017年11月14日 下午10:31:40
 */

public class UserUtils {
    /**
     * 获取当前登录用户 @Title: getCurrentUser @return User @throws
     */
    public static User getCurrentUser() {
        Object u = SecurityUtils.getSubject().getPrincipal();
        return ObjectUtils.allNotNull(u) ? (User) u : null;
    }
}
