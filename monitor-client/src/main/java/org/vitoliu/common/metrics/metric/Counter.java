package org.vitoliu.common.metrics.metric;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public interface Counter extends MetricItem<Long> {

	void inc();

	void dec();

	void inc(long count);

	void dec(long count);

	Long getValue();
}
