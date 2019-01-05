package org.vitoliu.common.metrics.metric.impl;

import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.Maps;
import org.vitoliu.ccommon.metrics.config.MetricConfig;
import org.vitoliu.common.metrics.metric.MetricItem;
import org.vitoliu.common.metrics.metric.MetricRegistry;

/**
 *
 * @author yukun.liu
 * @since 05 一月 2019
 */
public class DefaultMetricRegistry implements MetricRegistry {

	private static final DefaultMetricRegistry INSTANCE = new DefaultMetricRegistry();

	private ConcurrentMap<MetricConfig, MetricItem> metricItemMap = Maps.newConcurrentMap();

	private Supplier<Integer> sizeSupplier = Suppliers.memoizeWithExpiration(() -> metricItemMap.size(), 1, TimeUnit.MINUTES);

	private DefaultMetricRegistry() {
	}


	static MetricRegistry getInstance() {
		return INSTANCE;
	}

	@Override
	public MetricItem get(MetricConfig metricConfig) {
		return metricItemMap.get(metricConfig);
	}

	@Override
	public void register(MetricItem metricItem) {
		MetricConfig metricConfig = metricItem.getMetricConfig();
		metricItemMap.putIfAbsent(metricConfig, metricItem);
	}

	@Override
	public int size() {
		return sizeSupplier.get();
	}

	@Override
	public Iterator<MetricItem> iterator() {
		return metricItemMap.values().iterator();
	}
}
