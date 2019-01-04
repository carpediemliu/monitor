package org.vitoliu.common.metrics.metric.impl;

import org.vitoliu.ccommon.metrics.config.MetricConfig;
import org.vitoliu.common.metrics.metric.Counter;
import org.vitoliu.common.metrics.metric.MetricType;
import org.vitoliu.common.metrics.metric.Resettable;
import org.vitoliu.common.metrics.metric.ValueType;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public abstract class AbstractCounter extends AbstractMetricItem<Long> implements Counter, Resettable {

	private final boolean reset;

	AbstractCounter(MetricConfig metricConfig, boolean reset) {
		super(metricConfig);
		this.reset = reset;
	}

	@Override
	public void inc() {
		inc(1);
	}

	@Override
	public void dec() {
		dec(1);
	}

	@Override
	public MetricType getType() {
		return MetricType.COUNTER;
	}

	@Override
	public double resolveValue(Long value, ValueType valueType) {
		return value.doubleValue();
	}

	@Override
	public boolean isReset() {
		return reset;
	}
}
