package org.vitoliu.common.metrics.metric.impl;

import com.google.common.util.concurrent.AtomicDouble;
import org.vitoliu.ccommon.metrics.config.MetricConfig;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public class DefaultResettableGauge extends AbstractResettableGauge {

	private AtomicDouble valueHolder;

	private double initValue;

	DefaultResettableGauge(MetricConfig metricConfig, boolean reset, double initValue) {
		super(metricConfig, reset);
		this.initValue = initValue;
		valueHolder = new AtomicDouble(initValue);
	}


	DefaultResettableGauge(MetricConfig metricConfig, boolean reset) {
		this(metricConfig, reset, 0);
	}

	@Override
	public Double getValue() {
		if (isReset()) {
			return getReasonableValue(valueHolder.getAndSet(initValue));
		}
		else {
			return getUnResetValue();
		}
	}

	private Double getUnResetValue() {
		double value = justGetValue();
		return getReasonableValue(value);
	}

	double justGetValue() {
		return valueHolder.get();
	}

	private Double getReasonableValue(double andSet) {
		if (andSet == initValue) {
			return 0.0;
		}
		else {
			return andSet;
		}
	}

	@Override
	public Double peekValue() {
		return getUnResetValue();
	}

	@Override
	public void update(double value) {
		valueHolder.set(value);
	}
}
