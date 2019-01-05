package org.vitoliu.common.metrics.metric.impl;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author yukun.liu
 * @since 06 一月 2019
 */
public class SimplerRater {

	private AtomicLong counter = new AtomicLong();

	private volatile long lastValue = 0;

	private volatile long lastTime = getTime();

	private long getTime() {
		return System.currentTimeMillis();
	}

	void mark(long count) {
		counter.addAndGet(count);
	}

	void mark() {
		mark(1);
	}

	public double getValue() {
		long last = lastValue;
		long lastT = lastTime;
		lastTime = getTime();
		lastValue = counter.get();
		long delta = lastValue - last;
		if (delta < 0) {
			delta = 0;
		}
		long duration = lastTime - lastT;
		if (duration == 0) {
			return 0;
		}
		return delta * 1.0 / (duration / 1000.0);
	}
}

