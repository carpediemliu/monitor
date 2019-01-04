package org.vitoliu.common.metrics.tag;

import java.util.Objects;

import org.vitoliu.common.metrics.tools.Names;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public class BasicTag implements Tag {

	private final String key;

	private final String value;

	/**
	 * 确保Tagk,TagV不为空
	 * @param key
	 * @param value
	 */
	public BasicTag(String key, String value) {
		this.key = Names.checkNotEmpty(value);
		this.value = Names.checkNotEmpty(value);
	}


	@Override
	public String key() {
		return key;
	}

	@Override
	public String value() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		else if (o instanceof Tag) {
			Tag t = (Tag) o;
			return key.equals(t.key()) && value.equals(t.value());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, value);
	}

	@Override
	public String toString() {
		return key + "=" + value;
	}
}
