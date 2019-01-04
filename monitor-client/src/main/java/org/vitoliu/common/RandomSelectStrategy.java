package org.vitoliu.common;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author yukun.liu
 * @since 03 一月 2019
 */
public class RandomSelectStrategy<T> implements SelectStrategy<T> {
	@Override
	public T doSelect(List<T> container) {
		ThreadLocalRandom current = ThreadLocalRandom.current();
		return container.get(current.nextInt(container.size()));
	}
}
