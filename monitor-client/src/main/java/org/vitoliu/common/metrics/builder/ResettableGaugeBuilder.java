package org.vitoliu.common.metrics.builder;

import org.vitoliu.ccommon.metrics.config.MetricConfig;
import org.vitoliu.common.metrics.metric.ResettableGauge;
import org.vitoliu.common.metrics.metric.impl.MetricsFactory;

/**
 *
 * @author yukun.liu
 * @since 05 一月 2019
 */
public class ResettableGaugeBuilder extends AbstractResetBuilder<ResettableGauge> {

	ResettableGaugeBuilder(String metricName) {
		super(metricName, true);
	}

	public ResettableGaugeBuilder tag(String key, String value) {
		withTag(key, value);
		return this;
	}

	public ResettableGaugeBuilder reset(boolean reset) {
		withReset(reset);
		return this;
	}

	public ResettableGaugeBuilder delta() {
		withReset(true);
		return this;
	}

	@Override
	protected ResettableGauge buildWithConfig(MetricConfig metricConfig) {
		return MetricsFactory.newResettableGauge(metricConfig, isReset());
	}

	public ResettableGauge getMax() {
		return MetricsFactory.newMaxGauge(createMetricConfig(), isReset());
	}

	public ResettableGauge getMin() {
		return MetricsFactory.newMinGauge(createMetricConfig(), isReset());
	}

	public ResettableGauge getAvg() {
		return MetricsFactory.newAvgGauge(createMetricConfig(), isReset());
	}
}
