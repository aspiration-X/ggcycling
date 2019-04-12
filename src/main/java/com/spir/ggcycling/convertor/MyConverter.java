package com.spir.ggcycling.convertor;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * created by spir
 * Date2019/4/10 Time 23:53
 */
public class MyConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;
        try {
            java.util.Date parse1 = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
