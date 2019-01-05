package org.vitoliu.common.metrics.metric.impl;

import org.vitoliu.ccommon.metrics.config.MetricConfig;
import org.vitoliu.common.metrics.metric.Counter;
import org.vitoliu.common.metrics.metric.GaugeComputer;
import org.vitoliu.common.metrics.metric.MetricItem;
import org.vitoliu.common.metrics.metric.ResettableGauge;

/**
 *
 * @author yukun.liu
 * @since 05 一月 2019
 */
public class MetricsFactory {

	private static final ResettableFunction<MetricConfig, AvgGauge> AVG_GAUGE_RESETTABLE_FUNCTION = AvgGauge::new;

	private static final ResettableFunction<MetricConfig, DefaultResettableGauge> RESETTABLE_GAUGE_RESETTABLE_FUNCTION = DefaultResettableGauge::new;

	private static final ResettableFunction<MetricConfig, MaxGauge> METRIC_CONFIG_MAX_GAUGE_RESETTABLE_FUNCTION = MaxGauge::new;

	private static final ResettableFunction<MetricConfig, MinGauge> MIN_GAUGE_RESETTABLE_FUNCTION = MinGauge::new;

	private static final ResettableFunction<MetricConfig, DefaultCounter> COUNTER_RESETTABLE_FUNCTION = DefaultCounter::new;

	private static final Function<MetricConfig, DefaultTimer> TIMER_FUNCTION = DefaultTimer::new;

	public static ResettableGauge newResettableGauge(MetricConfig metricConfig, boolean reset) {
		return getOrCreate(metricConfig, DefaultResettableGauge.class, reset, RESETTABLE_GAUGE_RESETTABLE_FUNCTION);
	}

	private static <T extends MetricItem> T getOrCreate(MetricConfig metricConfig, Class<T> clazz, boolean reset, ResettableFunction<MetricConfig, T> function) {
		T genericMetricItem = getMetricItem(metricConfig, clazz);
		if (genericMetricItem != null) {
			return genericMetricItem;
		}
		T metricObj = function.apply(metricConfig, reset);
		return register(metricConfig, metricObj);

	}

	@SuppressWarnings("unchecked")
	private static <T extends MetricItem> T register(MetricConfig metricConfig, T metricObj) {
		MetricRegistries.register(metricObj);
		MetricItem item = MetricRegistries.getMetricItem(metricConfig);
		if (item.getClass() == metricObj.getClass()) {
			return (T) item;
		}
		else {
			return metricObj;
		}

	}

	private static <T extends MetricItem> T getMetricItem(MetricConfig metricConfig, Class<T> clazz) {
		final MetricItem metricItem = get(metricConfig);
		if (metricItem != null) {
			if (clazz.isInstance(metricItem)) {
				return clazz.cast(metricItem);
			}
		}
		return null;
	}

	private static MetricItem get(MetricConfig metricConfig) {
		return MetricRegistries.getMetricItem(metricConfig);
	}

	public static ResettableGauge newMaxGauge(MetricConfig metricConfig, boolean reset) {
		return getOrCreate(metricConfig, MaxGauge.class, reset, METRIC_CONFIG_MAX_GAUGE_RESETTABLE_FUNCTION);
	}

	public static ResettableGauge newMinGauge(MetricConfig metricConfig, boolean reset) {
		return getOrCreate(metricConfig, MinGauge.class, reset, MIN_GAUGE_RESETTABLE_FUNCTION);
	}

	public static ResettableGauge newAvgGauge(MetricConfig metricConfig, boolean reset) {
		return getOrCreate(metricConfig, AvgGauge.class, reset, AVG_GAUGE_RESETTABLE_FUNCTION);
	}

	public static DefaultGauge newDefaultGauge(MetricConfig metricConfig, GaugeComputer gaugeComputer) {
		DefaultGauge metricItem = getMetricItem(metricConfig, DefaultGauge.class);
		if (metricItem != null) {
			return metricItem;
		}
		DefaultGauge defaultGauge = new DefaultGauge(metricConfig, gaugeComputer);
		return register(metricConfig, defaultGauge);
	}

	public static DeltaGauge newDeltaGauge(MetricConfig metricConfig, GaugeComputer gaugeComputer) {
		DeltaGauge metricItem = getMetricItem(metricConfig, DeltaGauge.class);
		if (metricItem != null) {
			return metricItem;
		}
		DeltaGauge deltaGauge = new DeltaGauge(metricConfig, gaugeComputer);
		return register(metricConfig, deltaGauge);
	}

	public static Counter newCounter(MetricConfig metricConfig, boolean reset) {
		return getOrCreate(metricConfig, DefaultCounter.class, reset, COUNTER_RESETTABLE_FUNCTION);
	}

	public static DefaultTimer newDefaultTimer(MetricConfig metricConfig) {
		return getOrCreate(metricConfig, DefaultTimer.class, TIMER_FUNCTION);
	}


	private static <T extends MetricItem> T getOrCreate(MetricConfig metricConfig, Class<T> clazz, Function<MetricConfig, T> function) {
		MetricItem metricItem = get(metricConfig);
		if (metricItem != null) {
			if (clazz.isInstance(metricItem)) {
				return clazz.cast(metricItem);
			}
		}
		T metricObj = function.apply(metricConfig);
		return register(metricConfig, metricObj);
	}

	@FunctionalInterface
	public interface Function<T, R> {
		R apply(T t);
	}

	@FunctionalInterface
	public interface ResettableFunction<T, R> {
		/**
		 * functional interface
		 * @param t
		 * @param reset
		 * @return
		 */
		R apply(T t, boolean reset);
	}
}
