package com.doctusoft.gwt.light.client.util;

import com.doctusoft.common.core.bean.binding.Converter;

public class LongConverter implements Converter<Long, String> {
	
	@Override
	public String convertSource(Long source) {
		if (source == null)
			return "";
		return Long.toString(source);
	}
	
	@Override
	public Long convertTarget(String target) {
		return Long.parseLong(target);
	}

}
