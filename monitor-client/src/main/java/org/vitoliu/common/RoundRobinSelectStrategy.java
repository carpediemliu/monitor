package org.vitoliu.common;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author yukun.liu
 * @since 03 一月 2019
 */
public class RoundRobinSelectStrategy<T> implements SelectStrategy<T> {

	private static final AtomicLong index = new AtomicLong();
	@Override
	public T doSelect(List<T> container) {
		return container.get((int) (Math.abs(index.incrementAndGet()) % container.size()));
	}
}
