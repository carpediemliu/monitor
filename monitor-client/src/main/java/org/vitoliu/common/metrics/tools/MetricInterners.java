package org.vitoliu.common.metrics.tools;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import org.vitoliu.common.metrics.tag.Tag;

/**
 *	graphic obj
 * @author yukun.liu
 * @since 04 一月 2019
 */
public class MetricInterners {

	private static final Interner<String> STRING_INTERNER = Interners.newStrongInterner();

	private static final Interner<Tag> TAG_INTERNER = Interners.newStrongInterner();

	public static String intern(String item) {
		item = Names.checkNotEmpty(item);
		return STRING_INTERNER.intern(item);
	}

	public static Tag intern(Tag tag) {
		return TAG_INTERNER.intern(tag);
	}
}
