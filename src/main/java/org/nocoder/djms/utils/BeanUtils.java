package org.nocoder.djms.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author jason
 * @date 17/11/19.
 */
public class BeanUtils {
    protected static final Log logger = LogFactory.getLog("BeanUtils");

    /**
     * convertBean2Map
     *
     * @param obj
     * @return
     */
    public static Map<String, String> convertBean2Map(Object obj) {
        Map<String, String> paramsMap = null;
        try {
            paramsMap = org.apache.commons.beanutils.BeanUtils.describe(obj);
        } catch (IllegalAccessException e) {
            logger.error(e);
        } catch (InvocationTargetException e) {
            logger.error(e);
        } catch (NoSuchMethodException e) {
            logger.error(e);
        }
        return paramsMap;
    }
}
