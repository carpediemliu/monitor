package org.vitoliu.common.metrics.metric.impl;

import org.vitoliu.ccommon.metrics.config.MetricConfig;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public class MaxGauge extends DefaultResettableGauge {

	MaxGauge(MetricConfig metricConfig, boolean reset) {
		super(metricConfig, reset, Integer.MIN_VALUE);
	}

	@Override
	public void update(double value) {
		if (justGetValue() < value) {
			super.update(value);
		}
	}
}
