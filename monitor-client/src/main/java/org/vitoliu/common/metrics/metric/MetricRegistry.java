package org.vitoliu.common.metrics.metric;

import org.vitoliu.ccommon.metrics.config.MetricConfig;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public interface MetricRegistry extends Iterable<MetricItem> {

	MetricItem get(MetricConfig metricConfig);

	void register(MetricItem metricItem);

	int size();
}
