package org.vitoliu.common.concurrent;

import java.util.concurrent.ScheduledExecutorService;

import com.alibaba.dubbo.common.extension.SPI;
import org.vitoliu.common.constants.CommonConstants;

/**
 *
 * @author yukun.liu
 * @since 06 一月 2019
 */
@SPI(CommonConstants.DEFAULT)
public interface NamedScheduleExecutorService extends ScheduledExecutorService, NamedExecutorService {
}

