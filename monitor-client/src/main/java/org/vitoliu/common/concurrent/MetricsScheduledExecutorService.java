package org.vitoliu.common.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.common.collect.Collections2;
import org.vitoliu.common.metrics.Metrics;
import org.vitoliu.common.metrics.metric.Counter;
import org.vitoliu.common.metrics.metric.Timer;

/**
 *
 *
 * {@link NamedScheduleExecutorService} add simple metric
 * @author yukun.liu
 * @since 06 一月 2019
 */
public class MetricsScheduledExecutorService implements NamedScheduleExecutorService {

	static final String THREAD_POOL_TASK_STATUS = "thread.scheduler.pool.task.status";

	static final String THREAD_POOL_STATUS = "thread.scheduler.pool.status";

	static final String THREAD_POOL_DURATION = "thread.scheduler.pool.task.duration";

	/**
	 *
	 * must not be static cause one thread may shutdown this job while another thread may execute a new job!
	 * {@link java.util.concurrent.RejectedExecutionException}
	 */
	private final ScheduledExecutorService delegate;

	private final String name;

	private final Timer duration;

	private final Counter submitted;

	private final Counter running;

	private final Counter completed;

	private final Counter scheduledOnce;

	private final Counter scheduledRepetitively;

	private final Counter scheduledOverrun;

	public MetricsScheduledExecutorService(NamedScheduleExecutorService delegate) {
		this(delegate, delegate.getName());
	}

	public MetricsScheduledExecutorService(ScheduledExecutorService delegate, String name) {
		this.delegate = delegate;
		this.name = name;
		String metricName = THREAD_POOL_DURATION;
		duration = Metrics.timer(metricName).tag("name", name).get();
		metricName = THREAD_POOL_TASK_STATUS;
		submitted = Metrics.counter(metricName).tag("name", name).tag("type", "submitted").delta().get();
		running = Metrics.counter(metricName).tag("name", name).tag("type", "running").delta().get();
		completed = Metrics.counter(metricName).tag("name", name).tag("type", "completed").delta().get();
		scheduledOnce = Metrics.counter(metricName).tag("name", name).tag("type", "scheduledOnce").delta().get();
		scheduledRepetitively = Metrics.counter(metricName).tag("name", name).tag("type", "scheduledRepetitively").delta().get();
		scheduledOverrun = Metrics.counter(metricName).tag("name", name).tag("type", "scheduledOverrun").delta().get();
		ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = getScheduledThreadPoolExecutor(delegate);
		if (scheduledThreadPoolExecutor != null) {
			metricName = THREAD_POOL_TASK_STATUS;
			Metrics.gauge(metricName, scheduledThreadPoolExecutor::getActiveCount).tag("name", name).tag("type", "activeCount").get();
			Metrics.gauge(metricName, scheduledThreadPoolExecutor::getPoolSize).tag("name", name).tag("type", "poolSize").get();
			Metrics.gauge(metricName, () -> scheduledThreadPoolExecutor.getQueue().size()).tag("name", name).tag("type", "queuedTasks").get();
		}
	}

	private static ScheduledThreadPoolExecutor getScheduledThreadPoolExecutor(ExecutorService delegate) {
		if (delegate == null) {
			return null;
		}
		if (delegate instanceof ScheduledExecutorService) {
			return (ScheduledThreadPoolExecutor) delegate;
		}
		if (delegate instanceof NamedExecutorService) {
			return getScheduledThreadPoolExecutor(((NamedExecutorService) delegate).getDelegate());
		}
		return null;
	}

	@Override
	public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
		scheduledOnce.inc();
		return delegate.schedule(new MetricsRunnable(command), delay, unit);
	}

	@Override
	public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
		scheduledOnce.inc();
		return delegate.schedule(new MetricCallable<>(callable), delay, unit);
	}

	@Override
	public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
		scheduledRepetitively.inc();
		return delegate.scheduleAtFixedRate(new MetricsRunnable(command), initialDelay, period, unit);
	}

	@Override
	public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
		scheduledOnce.inc();
		return delegate.scheduleWithFixedDelay(new MetricsRunnable(command), initialDelay, delay, unit);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ExecutorService getDelegate() {
		return delegate;
	}

	@Override
	public void shutdown() {
		delegate.shutdown();
	}

	@Override
	public List<Runnable> shutdownNow() {
		return delegate.shutdownNow();
	}

	@Override
	public boolean isShutdown() {
		return delegate.isShutdown();
	}

	@Override
	public boolean isTerminated() {
		return delegate.isTerminated();
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		return delegate.awaitTermination(timeout, unit);
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		submitted.inc();
		return delegate.submit(new MetricCallable<>(task));
	}

	@Override
	public <T> Future<T> submit(Runnable task, T result) {
		submitted.inc();
		return delegate.submit(new MetricsRunnable(task), result);
	}

	@Override
	public Future<?> submit(Runnable task) {
		submitted.inc();
		return delegate.submit(new MetricsRunnable(task));
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
		submitted.inc(tasks.size());
		return delegate.invokeAll(Collections2.transform(tasks, MetricCallable::new));
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
		submitted.inc(tasks.size());
		return delegate.invokeAll(Collections2.transform(tasks, MetricCallable::new), timeout, unit);
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
		submitted.inc(tasks.size());
		return delegate.invokeAny(Collections2.transform(tasks, MetricCallable::new));
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		submitted.inc(tasks.size());
		return delegate.invokeAny(Collections2.transform(tasks, MetricCallable::new), timeout, unit);
	}

	@Override
	public void execute(Runnable command) {
		submitted.inc();
		delegate.execute(new MetricsRunnable(command));
	}

	private class MetricsRunnable implements Runnable {

		private final Runnable runnable;

		MetricsRunnable(Runnable task) {
			this.runnable = task;
		}

		@Override
		public void run() {
			running.inc();
			final Timer.Context context = duration.time();
			try {
				runnable.run();
			}
			finally {
				context.stop();
				running.dec();
				completed.inc();
			}
		}
	}

	private class MetricCallable<T> implements Callable<T> {

		private final Callable<T> callable;

		MetricCallable(Callable<T> callable) {
			this.callable = callable;
		}

		@Override
		public T call() throws Exception {
			running.inc();
			final Timer.Context context = duration.time();
			try {
				return callable.call();
			}
			finally {
				context.stop();
				running.dec();
				completed.inc();
			}
		}
	}
}
