package org.vitoliu.common;

import java.util.List;

/**
 *
 * @author yukun.liu
 * @since 03 一月 2019
 */
public interface SelectStrategy<T> {

	T doSelect(List<T> container);
}
