package org.vitoliu.common.metrics.metric.impl;

import org.vitoliu.ccommon.metrics.config.MetricConfig;
import org.vitoliu.common.metrics.metric.GaugeComputer;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public class DeltaGauge extends DefaultGauge {

	private volatile double lastValue;

	private volatile boolean init;

	DeltaGauge(MetricConfig metricConfig, GaugeComputer callable) {
		super(metricConfig, callable);
	}

	@Override
	public Double getValue() {
		Double value = super.getValue();
		double result;
		if (init) {
			result = value - lastValue;
		}
		else {
			result = 0;
			init = true;
		}
		lastValue = value;
		return result;
	}
}
