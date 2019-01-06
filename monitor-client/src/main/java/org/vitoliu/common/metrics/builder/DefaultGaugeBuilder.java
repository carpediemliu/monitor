package org.vitoliu.common.metrics.builder;

import org.vitoliu.ccommon.metrics.config.MetricConfig;
import org.vitoliu.common.metrics.metric.Gauge;
import org.vitoliu.common.metrics.metric.GaugeComputer;
import org.vitoliu.common.metrics.metric.impl.DeltaGauge;
import org.vitoliu.common.metrics.metric.impl.MetricsFactory;

/**
 *
 * @author yukun.liu
 * @since 05 一月 2019
 */
public class DefaultGaugeBuilder extends AbstractBuilder<Gauge> {

	private final GaugeComputer gaugeComputer;

	public DefaultGaugeBuilder(String metricName, GaugeComputer gaugeComputer) {
		super(metricName);
		this.gaugeComputer = gaugeComputer;
	}


	public DefaultGaugeBuilder tag(String key, String value) {
		withTag(key, value);
		return this;
	}

	@Override
	protected Gauge buildWithConfig(MetricConfig metricConfig) {
		return MetricsFactory.newDefaultGauge(metricConfig, gaugeComputer);
	}


	public DeltaGauge getDelta() {
		return MetricsFactory.newDeltaGauge(createMetricConfig(), gaugeComputer);
	}
}
