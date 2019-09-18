package com.persipa.fs3.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;



public class DateConverter implements Converter<String,Date> {

	@Override
	public Date convert(String time) {
		SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return spf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	}


