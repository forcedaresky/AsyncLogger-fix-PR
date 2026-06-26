package me.decce.transformingbase.service;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;

@SuppressWarnings("ForLoopReplaceableByForEach")
public class AsyncFilter extends AbstractFilter {
    private boolean checkLevel(FilteringInfo filter, Level level) {
        var levels = filter.levels();
        for (int i = 0; i < levels.length; i++) {
            if (level == levels[i]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkLogger(FilteringInfo filter, String loggerName) {
        var loggers = filter.loggers();
        for (int i = 0; i < loggers.length; i++) {
            if (loggerName.equals(loggers[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkStrings(FilteringInfo filter, String message) {
        var strings = filter.strings();
        for (int i = 0; i < strings.length; i++) {
            if (message.contains(strings[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkStringsSafe(FilteringInfo filter, String message) {
        if (message == null) {
            return false;
        }
        return checkStrings(filter, message);
    }

    private boolean checkRegexes(FilteringInfo filter, String message) {
        var regexes = filter.regexes();
        for (int i = 0; i < regexes.length; i++) {
            if (regexes[i].matcher(message).matches()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkRegexesSafe(FilteringInfo filter, String message) {
        if (message == null) {
            return false;
        }
        return checkRegexes(filter, message);
    }

    private boolean checkMessage(FilteringInfo filter, Message message) {
        var formatted = message.getFormattedMessage();
        return checkStringsSafe(filter, formatted) || checkRegexesSafe(filter, formatted);
    }

    private boolean filter(FilteringInfo filter, String loggerName, Level level, String message) {
        return checkLevel(filter, level) ||
                checkLogger(filter, loggerName) ||
                checkStringsSafe(filter, message) ||
                checkRegexesSafe(filter, message);
    }

    private boolean filter(FilteringInfo filter, String loggerName, Level level, Message message) {
        return checkLevel(filter, level) ||
                checkLogger(filter, loggerName) ||
                checkMessage(filter, message);
    }

    private boolean filter(FilteringInfo filter, Logger logger, Level level, String message) {
        return filter(filter, logger.getName(), level, message);
    }

    private boolean filter(FilteringInfo filter, Logger logger, Level level, Message message) {
        return filter(filter, logger.getName(), level, message);
    }

    private boolean filter(FilteringInfo filter, LogEvent event) {
        return filter(filter, event.getLoggerName(), event.getLevel(), event.getMessage());
    }

    private Result resultOf(boolean value) {
        return value ? Result.DENY : Result.NEUTRAL;

    }

    @Override
    public Result filter(LogEvent event) {
        if (this.filter(AsyncLogger.filteringInfo, event)) {
            return Result.DENY;
        }
        return Result.NEUTRAL;
    }

    // Note: methods below are only used when using global filter

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Message msg, Throwable t) {
        return resultOf(filter(AsyncLogger.filteringInfo, logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Object msg, Throwable t) {
        if (checkLevel(AsyncLogger.filteringInfo, level) ||
                checkLogger(AsyncLogger.filteringInfo, logger.getName())) {
            return Result.DENY;
        }
        if (msg instanceof Message message) {
            return resultOf(checkMessage(AsyncLogger.filteringInfo, message));
        }
        return Result.NEUTRAL;
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object... params) {
        return resultOf(filter(AsyncLogger.filteringInfo, logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0) {
        return resultOf(filter(AsyncLogger.filteringInfo, logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1) {
        return resultOf(filter(AsyncLogger.filteringInfo, logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2) {
        return resultOf(filter(AsyncLogger.filteringInfo, logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3) {
        return resultOf(filter(AsyncLogger.filteringInfo, logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4) {
        return resultOf(filter(AsyncLogger.filteringInfo, logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        return resultOf(filter(AsyncLogger.filteringInfo, logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        return resultOf(filter(AsyncLogger.filteringInfo, logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        return resultOf(filter(AsyncLogger.filteringInfo, logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        return resultOf(filter(AsyncLogger.filteringInfo, logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        return resultOf(filter(AsyncLogger.filteringInfo, logger, level, msg));
    }
}
