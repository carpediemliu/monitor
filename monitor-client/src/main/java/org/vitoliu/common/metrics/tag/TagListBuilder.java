package org.vitoliu.common.metrics.tag;

/**
 *
 * @author yukun.liu
 * @since 05 一月 2019
 */
public class TagListBuilder {

	private SortedTagList.Builder builder = SortedTagList.builder();

	private TagListBuilder() {

	}

	public static TagListBuilder newBuilder() {
		return new TagListBuilder();
	}

	public TagListBuilder addTag(String key, String value) {
		builder.withTag(key, value);
		return this;
	}

	public TagList build() {
		return builder.build();
	}
}
