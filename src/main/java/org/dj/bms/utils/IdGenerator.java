package org.dj.bms.utils;

import java.util.UUID;

/**
 * @author Created by jason on 17/11/5.
 */
public class IdGenerator {
    public static String generateUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
