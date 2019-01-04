package org.vitoliu.ccommon.metrics.config;

import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.base.CharMatcher;
import lombok.Getter;
import lombok.Setter;
import org.vitoliu.common.metrics.tag.TagList;
import org.vitoliu.common.metrics.tools.MetricInterners;
import org.vitoliu.common.metrics.tools.Names;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
@Getter
@Setter
public class MetricConfig {

	private static final CharMatcher CHAR_MATCHER = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z')).or(CharMatcher.digit()).or(CharMatcher.anyOf("_-.")).negate();

	private final String metricName;

	private final TagList tags;

	/**
	 * 性能优化，避免重复计算hashCode
	 */
	private final AtomicInteger cachedHashCode = new AtomicInteger(0);

	private MetricConfig(String metricName, TagList tags) {
		this.metricName = Names.checkNotEmpty(metricName);
		this.tags = tags;
	}

	public static Builder newBuilder(String metricName) {
		Builder builder = new Builder();
		return builder.metricName(MetricInterners.intern(normalize(metricName)));
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MetricConfig)) {
			return false;
		}
		MetricConfig m = (MetricConfig) obj;
		return metricName.equals(m.getMetricName())
				&& tags.equals(m.getTags());
	}

	@Override
	public int hashCode() {
		int hash = cachedHashCode.get();
		if (hash == 0) {
			hash = metricName.hashCode();
			hash = 31 * hash + tags.hashCode();
			cachedHashCode.set(hash);
		}
		return hash;
	}

	/**
	 * 对不识别的字符串一律用"_"替换
	 * @param metricName
	 * @return
	 */
	private static String normalize(String metricName) {
		return CHAR_MATCHER.replaceFrom(metricName, "_");
	}

	public static class Builder {

		private String metricName;

		private TagList tags;

		public MetricConfig build() {
			//这里不能对metricConfig享元
			return new MetricConfig(metricName, tags);
		}

		public Builder metricName(String metricName) {
			this.metricName = metricName;
			return this;
		}

		public Builder tagList(TagList tags) {
			this.tags = tags;
			return this;
		}
	}
}
