package me.decce.transformingbase.service;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;

public class AsyncFilter extends AbstractFilter {
    private final FilterImpl filterImpl;

    public AsyncFilter(FilteringInfo info) {
        this.filterImpl = new FilterImpl(info);
    }

    private Result resultOf(boolean value) {
        return value ? Result.DENY : Result.NEUTRAL;
    }

    @Override
    public Result filter(LogEvent event) {
        if (filterImpl.filter(event)) {
            return Result.DENY;
        }
        return Result.NEUTRAL;
    }

    // Note: methods below are only used when using global filter

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Message msg, Throwable t) {
        return resultOf(filterImpl.filter(logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Object msg, Throwable t) {
        if (filterImpl.checkLevel(level) ||
                filterImpl.checkLogger(logger.getName())) {
            return Result.DENY;
        }
        if (msg instanceof Message message) {
            return resultOf(filterImpl.checkMessage(message));
        }
        return Result.NEUTRAL;
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object... params) {
        return resultOf(filterImpl.filter(logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0) {
        return resultOf(filterImpl.filter(logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1) {
        return resultOf(filterImpl.filter(logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2) {
        return resultOf(filterImpl.filter(logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3) {
        return resultOf(filterImpl.filter(logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4) {
        return resultOf(filterImpl.filter(logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        return resultOf(filterImpl.filter(logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        return resultOf(filterImpl.filter(logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        return resultOf(filterImpl.filter(logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        return resultOf(filterImpl.filter(logger, level, msg));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        return resultOf(filterImpl.filter(logger, level, msg));
    }
}
