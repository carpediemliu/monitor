package org.vitoliu.common.metrics.metric.impl;

import org.vitoliu.ccommon.metrics.config.MetricConfig;
import org.vitoliu.common.metrics.metric.ResettableGauge;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public abstract class AbstractResettableGauge extends AbstractGauge implements ResettableGauge {

	private boolean reset;

	AbstractResettableGauge(MetricConfig metricConfig, boolean reset) {
		super(metricConfig);
		this.reset = reset;
	}

	@Override
	public boolean isReset() {
		return reset;
	}
}
