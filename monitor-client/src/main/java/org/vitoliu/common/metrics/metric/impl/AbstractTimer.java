package org.vitoliu.common.metrics.metric.impl;

import org.vitoliu.ccommon.metrics.config.MetricConfig;
import org.vitoliu.common.metrics.metric.MetricType;
import org.vitoliu.common.metrics.metric.TimeValue;
import org.vitoliu.common.metrics.metric.Timer;
import org.vitoliu.common.metrics.metric.ValueType;

/**
 *
 * @author yukun.liu
 * @since 06 一月 2019
 */
public abstract class AbstractTimer extends AbstractMetricItem<TimeValue> implements Timer {

	AbstractTimer(MetricConfig metricConfig){
		super(metricConfig);
	}

	@Override
	public MetricType getType() {
		return MetricType.TIMER;
	}

	@Override
	public double resolveValue(TimeValue value, ValueType valueType) {
		return value.resolveValue(valueType);
	}
}
