package org.vitoliu.common.metrics.builder;

/**
 *
 * @author yukun.liu
 * @since 05 一月 2019
 */
public abstract class AbstractResetBuilder<T> extends AbstractBuilder<T> {

	private boolean reset;

	AbstractResetBuilder(String metricName, boolean reset) {
		super(metricName);
		this.reset = reset;
	}

	void withReset(boolean reset) {
		this.reset = reset;
	}

	protected boolean isReset() {
		return reset;
	}
}

