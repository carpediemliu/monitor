package org.vitoliu.common.concurrent;

import java.util.concurrent.ExecutorService;

/**
 * labeled {@link ExecutorService}
 * @author yukun.liu
 * @since 03 一月 2019
 */
public interface NamedExecutorService extends ExecutorService {

	String getName();

	ExecutorService getDelegate();
}
