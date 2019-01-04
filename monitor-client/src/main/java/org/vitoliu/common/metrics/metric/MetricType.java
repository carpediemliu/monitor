package org.vitoliu.common.metrics.metric;

import static org.vitoliu.common.metrics.metric.ValueType.avg;
import static org.vitoliu.common.metrics.metric.ValueType.max;
import static org.vitoliu.common.metrics.metric.ValueType.min;
import static org.vitoliu.common.metrics.metric.ValueType.p99;
import static org.vitoliu.common.metrics.metric.ValueType.p995;
import static org.vitoliu.common.metrics.metric.ValueType.p999;
import static org.vitoliu.common.metrics.metric.ValueType.qps;
import static org.vitoliu.common.metrics.metric.ValueType.value;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public enum MetricType {

	/**
	 * 不同维度的指标类型
	 */
	GAUGE(value),

	COUNTER(value),

	RATE(qps),

	TIMER(qps, avg, min, max, p999, p99, p995);

	private ValueType[] valueTypes;

	MetricType(ValueType... valueTypes) {
		this.valueTypes = valueTypes;
	}

	public ValueType[] getValueTypes() {
		return valueTypes;
	}

	public boolean contains(ValueType valueType) {
		return indexOf(valueType) > 0;
	}


	public int indexOf(ValueType valueType) {
		for (int i = 0; i < valueTypes.length; i++) {
			if (valueTypes[i] == valueType) {
				return i;
			}
		}
		return -1;
	}
}
