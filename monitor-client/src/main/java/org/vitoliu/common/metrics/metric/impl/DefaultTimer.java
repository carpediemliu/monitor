package org.vitoliu.common.metrics.metric.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

import org.vitoliu.ccommon.metrics.config.MetricConfig;
import org.vitoliu.common.metrics.metric.TimeValue;

/**
 *
 * @author yukun.liu
 * @since 05 一月 2019
 */
public class DefaultTimer extends AbstractTimer {

	private static final int SAMPLE_SIZE = 10000;

	private static final double[] DEFAULT_PERCENTILES = {99.9, 99, 99.5, 90, 50, 95, 75};

	private AtomicLong count = new AtomicLong();

	private AtomicLong timeSum = new AtomicLong();

	private SimplerRater rate;

	private TimerStatsBuffer statsBuffer;

	private Clock clock = Clock.defaultClock();

	DefaultTimer(MetricConfig metricConfig) {
		super(metricConfig);
		statsBuffer = newTimerStatsBuffer();
	}

	private TimerStatsBuffer newTimerStatsBuffer() {
		return new TimerStatsBuffer(SAMPLE_SIZE, DEFAULT_PERCENTILES);
	}


	@Override
	public Context time() {
		return new DefaultContext(this);
	}

	@Override
	public <T> T time(Callable<T> event) throws Exception {
		return null;
	}

	@Override
	public <T> T time(Supplier<T> supplier) {
		return null;
	}

	@Override
	public void time(Runnable runnable) {

	}

	@Override
	public void update(long time, TimeUnit timeUnit) {
		long mills = timeUnit.toMillis(time);
		updateMills(mills);
	}

	private void updateMills(long mills) {
		statsBuffer.record(mills);
		rate.mark();
		count.incrementAndGet();
		timeSum.addAndGet(mills);
	}

	@Override
	public TimeValue getValue() {
		return null;
	}

	@Override
	public TimeValue peekValue() {
		return null;
	}

	class DefaultContext implements Context {

		private volatile long start;

		private DefaultTimer timer;

		DefaultContext(DefaultTimer timer) {
			this.timer = timer;
			this.start = timer.clock.getTime();
		}

		@Override
		public long stop() {
			long duration = this.timer.clock.getTime() - start;
			timer.update(duration, TimeUnit.MILLISECONDS);
			return duration;
		}
	}
}
