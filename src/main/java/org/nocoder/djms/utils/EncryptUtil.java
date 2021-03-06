package org.nocoder.djms.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.nocoder.djms.model.User;

/**
 * 加密工具
 *
 * @author Created by jason on 17/11/11.
 */
public class EncryptUtil {
    private static final String ALGORITHM_NAME = "md5";
    private static final int HASH_ITERATIONS = 2;

    public static void encryptPassword(User user) {
        if (user == null) {
            return;
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            user.setPassword("123456");
        }
        String newPassword = new SimpleHash(
                ALGORITHM_NAME,
                user.getPassword(),
                ByteSource.Util.bytes(user.getName()),
                HASH_ITERATIONS).toHex();
        user.setPassword(newPassword);
    }
}

