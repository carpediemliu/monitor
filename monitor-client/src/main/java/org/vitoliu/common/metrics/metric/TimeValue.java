package org.vitoliu.common.metrics.metric;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeValue {

	private long count;

	private double qps;

	private double avg;

	private double min;

	private double max;

	private double p99;

	private double p995;

	private double p999;

	private double[] extraPercentileValues;

	public double resolveValue(ValueType valueType) {
		switch (valueType) {
			case avg:
				return avg;
			case max:
				return max;
			case min:
				return min;
			case p99:
				return p99;
			case qps:
				return qps;
			case p995:
				return p995;
			case p999:
				return p999;
			case count:
				return count;
			default:
				//unknown valueType
				return 0;
		}
	}
}
