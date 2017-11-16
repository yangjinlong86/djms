package org.dj.bms.converter;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

@Component
public class String2DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		try {
			return DateUtils.parseDate(source, "yyyy-MM-dd");
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return null;
	}

}