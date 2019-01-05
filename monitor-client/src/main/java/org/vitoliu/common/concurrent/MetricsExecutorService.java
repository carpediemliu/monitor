package org.vitoliu.common.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author yukun.liu
 * @since 04 一月 2019
 */
public class MetricsExecutorService implements NamedExecutorService {

	private static final String THREAD_POOL_TASK_STATUS = "thread.pool.task.status";

	private static final String THREAD_POOL_STATUS = "thread.pool.status";

	private static final String THREAD_POOL_DURATION = "thread.pool.task.duration";

	private final ExecutorService delegate;

	private final String name;
//
//	private final Timer duration;
//
//	private final Counter submitted;
//
//	private final Counter running;
//
//	private final Counter completed;
//
//	private final Counter rejected;


	public MetricsExecutorService(NamedExecutorService delegate) {
		this(delegate, delegate.getName());
	}

	public MetricsExecutorService(ExecutorService delegate, String name) {
		this.delegate = delegate;
		this.name = name;

		String metricName = THREAD_POOL_DURATION;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public ExecutorService getDelegate() {
		return null;
	}

	@Override
	public void shutdown() {

	}

	@Override
	public List<Runnable> shutdownNow() {
		return null;
	}

	@Override
	public boolean isShutdown() {
		return false;
	}

	@Override
	public boolean isTerminated() {
		return false;
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		return false;
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		return null;
	}

	@Override
	public <T> Future<T> submit(Runnable task, T result) {
		return null;
	}

	@Override
	public Future<?> submit(Runnable task) {
		return null;
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
		return null;
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
		return null;
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
		return null;
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return null;
	}

	@Override
	public void execute(Runnable command) {

	}
}
