package org.vitoliu.common.metrics.metric;

import org.vitoliu.ccommon.metrics.config.MetricConfig;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public interface MetricItem<T> {


	MetricConfig getMetricConfig();

	T getValue();

	T peekValue();

	MetricType getType();

	double resolveValue(T value, ValueType valueType);
}
