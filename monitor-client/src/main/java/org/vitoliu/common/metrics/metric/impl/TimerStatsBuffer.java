package org.vitoliu.common.metrics.metric.impl;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.common.base.Preconditions;

/**
 *
 * @author yukun.liu
 * @since 06 一月 2019
 */
public class TimerStatsBuffer {

	private final double[] percentiles;

	private final double[] percentilesValues;

	private final int size;

	private final int[] values;

	private final AtomicBoolean statsComputed = new AtomicBoolean(false);

	private int pos;

	private int cursize;

	private int min;

	private int max;

	TimerStatsBuffer(int size, double[] percentiles) {
		Preconditions.checkArgument(size > 0, "size of the buffer must more than 0");
		Preconditions.checkArgument(percentiles != null, "percents array must not be null.set a 0-sized array if you don't want any percentileValues to be computed");
		Preconditions.checkArgument(validPercentiles(percentiles), "percentiles should in (0.0,100.0]");
		values = new int[size];
		this.size = size;
		this.percentiles = Arrays.copyOf(percentiles, percentiles.length);
		this.percentilesValues = new double[percentiles.length];

		reset();
	}

	/**
	 * reset local state : all values set to 0
	 */
	private void reset() {
		statsComputed.set(false);
		pos = 0;
		cursize = 0;
		min = 0;
		max = 0;
		for (int i = 0; i < percentilesValues.length; i++) {
			percentilesValues[i] = 0.0;
		}
	}

	private boolean validPercentiles(double[] percentiles) {
		for (double percentile : percentiles) {
			if (percentile <= 0.0 || percentile > 100.0) {
				return false;
			}
		}
		return true;
	}

	void record(long mills) {
		values[Integer.remainderUnsigned(pos++, size)] = castToInt(mills);
		if (cursize < size) {
			++cursize;
		}
	}

	private int castToInt(long mills) {
		if (mills > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		return (int) mills;
	}
}
