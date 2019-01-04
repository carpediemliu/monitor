package org.vitoliu.common.metrics.metric;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public interface Rate extends MetricItem<Double> {

	Double getValue();

	void mark();

	void mark(long n);

}
