package org.vitoliu.common.metrics.tools;

import com.google.common.base.Strings;
import org.vitoliu.common.constants.CommonConstants;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public class Names {


	public static String checkNotEmpty(String value) {
		return Strings.isNullOrEmpty(value) ? CommonConstants.EMPTY : value;
	}
}
