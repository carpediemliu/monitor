package org.vitoliu.common.metrics.metric.impl;


import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.vitoliu.ccommon.metrics.config.MetricConfig;
import org.vitoliu.common.metrics.metric.MetricItem;
import org.vitoliu.common.metrics.metric.MetricRegistry;

/**
 *
 * @author yukun.liu
 * @since 05 一月 2019
 */
public class MetricRegistries {


	private static final Supplier<MetricRegistry> METRIC_REGISTRY_SUPPLIER = Suppliers.memoize(DefaultMetricRegistry::getInstance);

	static MetricItem getMetricItem(MetricConfig metricConfig) {
		return METRIC_REGISTRY_SUPPLIER.get().get(metricConfig);
	}

	public static void register(MetricItem metricItem) {
		METRIC_REGISTRY_SUPPLIER.get().register(metricItem);
	}


	public static Iterable<MetricItem> allMetric() {
		return METRIC_REGISTRY_SUPPLIER.get();
	}

	public static int size() {
		return METRIC_REGISTRY_SUPPLIER.get().size();
	}
}
