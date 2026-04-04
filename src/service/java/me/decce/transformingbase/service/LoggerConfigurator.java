package me.decce.transformingbase.service;

import me.decce.transformingbase.constants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.async.BasicAsyncLoggerContextSelector;
import org.apache.logging.log4j.core.impl.Log4jContextFactory;

import java.util.List;


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
        var test = AsyncLogger.config.testPerformance;
        List<LoggerTester.Result> before = null;
        List<LoggerTester.Result> after = null;
        if (test) {
            before = LoggerTester.testAll();
        }

        var selector = new BasicAsyncLoggerContextSelector();
        LogManager.setFactory(new Log4jContextFactory(selector));
        configureSysOutErr();
        var logger = LogManager.getLogger(Constants.MOD_NAME);
        logger.info("Successfully configured async logger context and wrapped System.out and System.err");

        if (test) {
            after = LoggerTester.testAll();
            logger.info("--- Test Results before applying AsyncLogger ---");
            for (var result : before) {
                logger.info("{}: {}ms", result.item(), result.elapsedTimeInMs());
            }
            System.out.println("println");
            logger.info("--- Test Results after applying AsyncLogger ---");
            for (var result : after) {
                logger.info("{}: {}ms", result.item(), result.elapsedTimeInMs());
            }
        }
    }

    private static void configureSysOutErr() {
        System.setOut(new WrappedPrintStream("STDOUT", System.out));
        System.setErr(new WrappedPrintStream("STDERR", System.err));
    }
}
