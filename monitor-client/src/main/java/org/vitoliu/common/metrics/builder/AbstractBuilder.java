package org.vitoliu.common.metrics.builder;

import org.vitoliu.ccommon.metrics.config.MetricConfig;
import org.vitoliu.common.metrics.tag.TagListBuilder;
import org.vitoliu.common.metrics.tools.Names;

/**
 *
 * @author yukun.liu
 * @since 05 一月 2019
 */
public abstract class AbstractBuilder<T> {

	private final String metricName;

	private TagListBuilder tagBuilder = TagListBuilder.newBuilder();

	AbstractBuilder(String metricName) {
		this.metricName = Names.checkNotEmpty(metricName);
	}

	AbstractBuilder withTag(String key, String value) {
		tagBuilder.addTag(key, value);
		return this;
	}

	private TagListBuilder getTagBuilder() {
		return tagBuilder;
	}

	protected MetricConfig createMetricConfig() {
		return MetricConfig.newBuilder(getMetricName()).tagList(getTagBuilder().build()).build();
	}

	protected String getMetricName() {
		return metricName;
	}

	public T get() {
		MetricConfig metricConfig = createMetricConfig();
		return buildWithConfig(metricConfig);
	}

	protected abstract T buildWithConfig(MetricConfig metricConfig);
}
