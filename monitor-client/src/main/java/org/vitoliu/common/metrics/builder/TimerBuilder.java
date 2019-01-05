package org.vitoliu.common.metrics.builder;

import org.vitoliu.ccommon.metrics.config.MetricConfig;
import org.vitoliu.common.metrics.metric.Timer;
import org.vitoliu.common.metrics.metric.impl.MetricsFactory;

/**
 *
 * @author yukun.liu
 * @since 05 一月 2019
 */
public class TimerBuilder extends AbstractBuilder<Timer> {

	public TimerBuilder(String metricName) {
		super(metricName);
	}


	public TimerBuilder tag(String key, String value) {
		withTag(key, value);
		return this;
	}

	@Override
	protected Timer buildWithConfig(MetricConfig metricConfig) {
		return MetricsFactory.newDefaultTimer(metricConfig);
	}
}
