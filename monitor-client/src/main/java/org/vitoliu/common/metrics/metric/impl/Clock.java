package org.vitoliu.common.metrics.metric.impl;

/**
 *
 * @author yukun.liu
 * @since 06 一月 2019
 */
public abstract class Clock {

	private static final Clock INSTANCE = new UserTimeClock();

	static Clock defaultClock() {
		return INSTANCE;
	}

	public abstract long getTick();

	public long getTime() {
		return System.currentTimeMillis();
	}

	@Override
	public String toString() {
		return Long.toString(this.getTime());
	}

	private static class UserTimeClock extends Clock {

		@Override
		public long getTick() {
			return System.nanoTime();
		}
	}
}
