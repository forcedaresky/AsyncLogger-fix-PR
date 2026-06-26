package me.decce.transformingbase.service;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;

@SuppressWarnings("ForLoopReplaceableByForEach")
public class FilterImpl {
    private final FilteringInfo filter;
    public FilterImpl(FilteringInfo filter) {
        this.filter = filter;
    }

    public boolean checkLevel(Level level) {
        var levels = filter.levels();
        for (int i = 0; i < levels.length; i++) {
            if (level == levels[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLogger(String loggerName) {
        var loggers = filter.loggers();
        for (int i = 0; i < loggers.length; i++) {
            if (loggerName.equals(loggers[i])) {
                return true;
            }
        }
        return false;
    }

    public boolean checkStrings(String message) {
        var strings = filter.strings();
        for (int i = 0; i < strings.length; i++) {
            if (message.contains(strings[i])) {
                return true;
            }
        }
        return false;
    }

    public boolean checkStringsSafe(String message) {
        if (message == null) {
            return false;
        }
        return checkStrings(message);
    }

    public boolean checkRegexes(String message) {
        var regexes = filter.regexes();
        for (int i = 0; i < regexes.length; i++) {
            if (regexes[i].matcher(message).matches()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkRegexesSafe(String message) {
        if (message == null) {
            return false;
        }
        return checkRegexes(message);
    }

    public boolean checkMessage(Message message) {
        var formatted = message.getFormattedMessage();
        return checkStringsSafe(formatted) || checkRegexesSafe(formatted);
    }

    public boolean filter(String loggerName, Level level, String message) {
        return checkLevel(level) ||
                checkLogger(loggerName) ||
                checkStringsSafe(message) ||
                checkRegexesSafe(message);
    }

    public boolean filter(String loggerName, Level level, Message message) {
        return checkLevel(level) ||
                checkLogger(loggerName) ||
                checkMessage(message);
    }

    public boolean filter(Logger logger, Level level, String message) {
        return filter(logger.getName(), level, message);
    }

    public boolean filter(Logger logger, Level level, Message message) {
        return filter(logger.getName(), level, message);
    }

    public boolean filter(LogEvent event) {
        return filter(event.getLoggerName(), event.getLevel(), event.getMessage());
    }
}
