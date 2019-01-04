package org.vitoliu.common.metrics.metric;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public interface Timer extends MetricItem<TimeValue> {

	Context time();

	<T> T time(Callable<T> event) throws Exception;

	<T> T time(Supplier<T> supplier);

	void time(Runnable runnable);

	TimeValue getValue();

	void update(long time, TimeUnit timeUnit);

	interface Context {
		long stop();
	}
}
