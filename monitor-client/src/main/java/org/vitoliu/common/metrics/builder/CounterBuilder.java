package org.vitoliu.common.metrics.builder;

import org.vitoliu.ccommon.metrics.config.MetricConfig;
import org.vitoliu.common.metrics.metric.Counter;
import org.vitoliu.common.metrics.metric.impl.MetricsFactory;

/**
 *
 * @author yukun.liu
 * @since 05 一月 2019
 */
public class CounterBuilder extends AbstractResetBuilder<Counter> {


	CounterBuilder(String metricName) {
		super(metricName, true);
	}

	public CounterBuilder tag(String key, String value) {
		withTag(key, value);
		return this;
	}

	public CounterBuilder reset(boolean reset) {
		withReset(reset);
		return this;
	}

	public CounterBuilder delta() {
		withReset(true);
		return this;
	}

	@Override
	protected Counter buildWithConfig(MetricConfig metricConfig) {
		return MetricsFactory.newCounter(metricConfig,isReset());
	}
}
