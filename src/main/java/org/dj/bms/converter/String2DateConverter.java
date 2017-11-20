package org.dj.bms.converter;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

/**
 * @author pufangfei
 */
@Component
public class String2DateConverter implements Converter<String, Date> {
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
	public Date convert(String source) {
		try {
			return DateUtils.parseDate(source, "yyyy-MM-dd");
		} catch (ParseException e) {
            logger.error(e);
        }
		return null;
	}

}