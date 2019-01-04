package org.vitoliu.common.metrics.metric.impl;

import org.vitoliu.ccommon.metrics.config.MetricConfig;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public class AvgGauge extends AbstractResettableGauge {

	/**
	 * metrics value during times
	 */
	private volatile double sum;

	/**
	 *
	 */
	private volatile long times;

	AvgGauge(MetricConfig metricConfig, boolean reset) {
		super(metricConfig, reset);
	}

	@Override
	public Double getValue() {
		try {
			if (times == 0) {
				return 0.0d;
			}
			return sum / times;
		}
		finally {
			if (isReset()) {
				doReset();
			}
		}
	}

	private synchronized void doReset() {
		sum = 0;
		times = 0;
	}

	@Override
	public Double peekValue() {
		return null;
	}

	@Override
	public void update(double value) {
		times++;
		sum += value;
	}
}
