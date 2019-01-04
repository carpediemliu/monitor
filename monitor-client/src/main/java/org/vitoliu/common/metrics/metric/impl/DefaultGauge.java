package org.vitoliu.common.metrics.metric.impl;

import org.vitoliu.ccommon.metrics.config.MetricConfig;
import org.vitoliu.common.metrics.metric.GaugeComputer;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public class DefaultGauge extends AbstractGauge {

	private final GaugeComputer callable;

	DefaultGauge(MetricConfig metricConfig, GaugeComputer callable) {
		super(metricConfig);
		this.callable = callable;
	}

	@Override
	public Double getValue() {
		try {
			return callable.compute();
		}
		catch (Exception e) {
			return 0.0d;
		}
	}

	@Override
	public Double peekValue() {
		return getValue();
	}
}
