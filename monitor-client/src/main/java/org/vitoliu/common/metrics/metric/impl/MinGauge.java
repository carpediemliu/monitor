package org.vitoliu.common.metrics.metric.impl;

import org.vitoliu.ccommon.metrics.config.MetricConfig;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public class MinGauge extends DefaultResettableGauge {

	MinGauge(MetricConfig metricConfig, boolean reset) {
		super(metricConfig, reset);
	}

	@Override
	public void update(double value) {
		if (justGetValue() > value){
			super.update(value);
		}
	}
}
