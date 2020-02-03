package com.rongqi.common.tool;

import com.rongqi.common.utils.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

public class DateConverter implements Converter<String, Date> {
    public DateConverter() {
    }

    public Date convert(String source) {
        return DateUtils.valueOf(source);
    }
}
