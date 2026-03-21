package me.decce.transformingbase.service;

import me.decce.transformingbase.constants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.async.BasicAsyncLoggerContextSelector;
import org.apache.logging.log4j.core.impl.Log4jContextFactory;


public class LoggerConfigurator {
    static {
        var config = AsyncLogger.config;
        if (config.ringBufferSize != 0) {
            System.setProperty("log4j2.asyncLoggerRingBufferSize", String.valueOf(config.ringBufferSize));
        }
        if (!config.waitStrategy.isEmpty()) {
            System.setProperty("log4j2.asyncLoggerWaitStrategy", config.waitStrategy);
        }
        if (!config.synchronizeEnqueueWhenQueueFull.isEmpty()) {
            System.setProperty("log4j2.asyncLoggerSynchronizeEnqueueWhenQueueFull", config.synchronizeEnqueueWhenQueueFull);
        }
    }
    static void configure() {
        var selector = new BasicAsyncLoggerContextSelector();
        LogManager.setFactory(new Log4jContextFactory(selector));
        configureSysOutErr();
        LogManager.getLogger(Constants.MOD_NAME).info("Successfully configured async logger context and wrapped System.out and System.err");
    }

    private static void configureSysOutErr() {
        System.setOut(new WrappedPrintStream("STDOUT", System.out));
        System.setErr(new WrappedPrintStream("STDERR", System.err));
    }
}
