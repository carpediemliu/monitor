package org.vitoliu.common.metrics.metric.impl;

import org.vitoliu.ccommon.metrics.config.MetricConfig;
import org.vitoliu.common.metrics.metric.Gauge;
import org.vitoliu.common.metrics.metric.MetricType;
import org.vitoliu.common.metrics.metric.ValueType;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public abstract class AbstractGauge extends AbstractMetricItem<Double> implements Gauge {

	AbstractGauge(MetricConfig metricConfig) {
		super(metricConfig);
	}

	@Override
	public MetricType getType() {
		return MetricType.GAUGE;
	}

	@Override
	public double resolveValue(Double value, ValueType valueType) {
		return value;
	}
}
