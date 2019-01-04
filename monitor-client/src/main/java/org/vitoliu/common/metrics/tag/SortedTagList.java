package org.vitoliu.common.metrics.tag;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public class SortedTagList implements TagList {

	private static final Joiner COMMA_JOINER = Joiner.on(",").skipNulls();

	private final List<Tag> tagList;

	private SortedTagList(Builder builder) {
		tagList = ImmutableList.copyOf(builder.tags);
	}

	public static Builder builder() {
		return new Builder();
	}

	@Override
	public int size() {
		return tagList.size();
	}

	@Override
	public Iterator<Tag> iterator() {
		return tagList.iterator();
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof SortedTagList)
				&& tagList.equals(((SortedTagList) obj).tagList);
	}

	@Override
	public int hashCode() {
		return tagList.hashCode();
	}

	@Override
	public String toString() {
		return "[" + COMMA_JOINER.join(tagList.iterator()) + "]";
	}

	static final class Builder {

		static final int MAX_TAG_SIZE = 6;

		private static final Comparator<Tag> TAG_COMPARATOR = Comparator.comparing(Tag::key);

		private final List<Tag> tags = Lists.newArrayListWithCapacity(MAX_TAG_SIZE);

		Builder withTag(Tag tag) {
			if (tags.size() >= MAX_TAG_SIZE) {
				return this;
			}
			final Tag t = Tags.internCustom(tag);
			tags.add(t);
			return this;
		}

		Builder withTag(String key, String value) {
			return withTag(Tags.newTag(key, value));
		}

		SortedTagList build() {
			tags.sort(TAG_COMPARATOR);
			return new SortedTagList(this);
		}
	}
}
