package org.vitoliu.common.metrics.tag;

import org.vitoliu.common.metrics.tools.MetricInterners;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
final class Tags {

	private Tags() {

	}

	private static String intern(String v) {
		return MetricInterners.intern(v);
	}

	private static Tag intern(Tag tag) {
		return MetricInterners.intern(tag);
	}

	static Tag internCustom(Tag tag) {
		return (tag instanceof BasicTag) ? tag : newTag(tag.key(), tag.value());
	}

	static Tag newTag(String key, String value) {
		Tag newTag = new BasicTag(intern(key), intern(value));
		return intern(newTag);
	}

}
