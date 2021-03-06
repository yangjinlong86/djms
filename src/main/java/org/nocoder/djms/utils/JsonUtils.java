package org.nocoder.djms.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * JsonUtils
 *
 * @author jason
 * @date 17/11/18.
 */
public class JsonUtils {
    private final static Log logger = LogFactory.getLog("JsonUtils");
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtils() {
    }

    public static ObjectMapper getInstance() {

        return OBJECT_MAPPER;
    }

    /**
     * conver object to json
     *
     * @param obj javabean, list, array
     * @return
     * @throws Exception
     */
    public static String obj2json(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error(e);
        }
        return null;
    }

}
