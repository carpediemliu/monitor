package org.vitoliu.common.metrics.metric.impl;

import org.vitoliu.ccommon.metrics.config.MetricConfig;
import org.vitoliu.common.metrics.metric.MetricItem;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public abstract class AbstractMetricItem<T> implements MetricItem<T> {

	private MetricConfig metricConfig;

	AbstractMetricItem(MetricConfig metricConfig) {
		this.metricConfig = metricConfig;
	}

	@Override
	public MetricConfig getMetricConfig() {
		return metricConfig;
	}
}
