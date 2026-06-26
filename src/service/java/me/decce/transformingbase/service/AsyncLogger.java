package me.decce.transformingbase.service;

import org.apache.logging.log4j.Level;

import java.util.regex.Pattern;

public class AsyncLogger {
    public static AsyncLoggerConfig config;

    public static FilteringInfo filteringInfo;

    public static void compileFilters() {
        Level[] levels = new Level[0];
        if (config.levels != null) {
            levels = new Level[config.levels.size()];
            for (int i = 0; i < config.levels.size(); i++) {
                levels[i] = Level.valueOf(config.levels.get(i));
            }
        }

        String[] loggers = new String[0];
        if (config.loggers != null) {
            loggers = config.loggers.toArray(new String[0]);
        }

        String[] strings = new String[0];
        if (config.strings != null) {
            strings = config.strings.toArray(new String[0]);
        }

        Pattern[] regexes = new Pattern[0];
        if (config.regexes != null) {
            regexes = new Pattern[config.regexes.size()];
            for (int i = 0; i < config.regexes.size(); i++) {
                regexes[i] = Pattern.compile(config.regexes.get(i));
            }
        }

        filteringInfo = new FilteringInfo(levels, loggers, strings, regexes);
    }
}
