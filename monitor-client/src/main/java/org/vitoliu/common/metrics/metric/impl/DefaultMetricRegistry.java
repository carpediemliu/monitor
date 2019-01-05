package org.vitoliu.common.metrics.metric.impl;

import java.util.Iterator;

import org.vitoliu.ccommon.metrics.config.MetricConfig;
import org.vitoliu.common.metrics.metric.MetricItem;
import org.vitoliu.common.metrics.metric.MetricRegistry;

/**
 *
 * @author yukun.liu
 * @since 05 一月 2019
 */
public class DefaultMetricRegistry implements MetricRegistry {

	private static final DefaultMetricRegistry INSTANCE = new DefaultMetricRegistry();


	public static DefaultMetricRegistry getInstance() {
		return INSTANCE;
	}

	@Override
	public MetricItem get(MetricConfig metricConfig) {
		return null;
	}

	@Override
	public void register(MetricItem metricItem) {

	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Iterator<MetricItem> iterator() {
		return null;
	}
}
