package com.sinosoft.one.monitor.exception;


import com.sinosoft.one.monitor.log.TraceModel;
import com.sinosoft.one.monitor.log.TraceUtils;
import com.sinosoft.one.monitor.log.UrlTraceLog;
import com.sinosoft.one.monitor.notification.NotificationServiceFactory;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异常处理工具类.
 * User: carvin
 * Date: 12-12-21
 * Time: 上午9:32
 *
 */
public final class Exceptions {
	private static final Logger logger = LoggerFactory.getLogger(Exceptions.class);
	/**
	 * 处理异常信息
	 * @param throwable 异常对象
	 */
    public static void handleThrowable(Throwable throwable) {
		TraceModel traceModel = TraceUtils.getTraceModel();
	    UrlTraceLog urlTraceLog = traceModel.getUrlTraceLog();
	    String urlTraceId = urlTraceLog == null ? "" : urlTraceLog.getId();
	    ExceptionModel exceptionModel = new ExceptionModel(urlTraceId , throwable.getMessage(), getExceptionStackTrace(throwable));
	    exceptionModel.setUrl(traceModel.getUrl());
	    exceptionModel.setRequestParams(traceModel.getRequestParams());
	    logger.error(ExceptionModel.FORMAT_STRING, exceptionModel.toObjectArray());
	    NotificationServiceFactory.buildNotificationService().notification(exceptionModel);
    }

	/**
	 * 模仿throwable的printStackTrace
	 *
	 * @return 异常信息
	 */
	public static String getExceptionStackTrace(Throwable throwable) {
		StringBuffer sb = new StringBuffer();
		if (throwable != null) {
			try {
				sb.append(throwable.toString() + "\n");
				StackTraceElement causedTrace[] = throwable
						.getStackTrace();
				for (StackTraceElement trace : throwable.getStackTrace()) {
					sb.append("\tat " + trace.toString() + "\n");
				}
				Throwable ourCause = throwable.getCause();
				while (ourCause != null) {
					StackTraceElement[] trace = ourCause.getStackTrace();
					int m = trace.length - 1, n = causedTrace.length - 1;
					while (m >= 0 && n >= 0 && trace[m].equals(causedTrace[n])) {
						m--;
						n--;
					}
					int framesInCommon = trace.length - 1 - m;

					sb.append("Caused by: " + ourCause + "\n");
					for (int i = 0; i <= m; i++)
						sb.append("\tat " + trace[i] + "\n");
					if (framesInCommon != 0)
						sb.append("\t... " + framesInCommon + " more");
					causedTrace = ourCause.getStackTrace();
					ourCause = ourCause.getCause();
				}
				sb.toString();
			} catch (Exception e) {
				sb = new StringBuffer();
				sb.append("分析异常时,产生异常: " + e.getMessage());
			}
		} else
			sb.append("no exception reason");
		return sb.toString();
	}
}
