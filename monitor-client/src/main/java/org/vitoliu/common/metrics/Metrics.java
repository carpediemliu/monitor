package org.vitoliu.common.metrics;

import org.vitoliu.common.metrics.builder.CounterBuilder;
import org.vitoliu.common.metrics.builder.DefaultGaugeBuilder;
import org.vitoliu.common.metrics.builder.TimerBuilder;
import org.vitoliu.common.metrics.metric.GaugeComputer;

/**
 * 埋点工具类
 * @author yukun.liu
 * @since 05 一月 2019
 */
public class Metrics {

	public static DefaultGaugeBuilder gauge(String metricName, GaugeComputer gaugeComputer) {
		return new DefaultGaugeBuilder(metricName, gaugeComputer);
	}

	public static CounterBuilder counter(String metricName){
		return new CounterBuilder(metricName);
	}

	public static TimerBuilder timer(String metricName) {
		return new TimerBuilder(metricName);
	}



}
