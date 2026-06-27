package me.decce.transformingbase.core;

import me.decce.transformingbase.constants.Constants;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class AsyncLogger {
    public static AsyncLoggerConfig config;

    public static FilteringInfo filteringInfo;

    public static void compileFilters() {
        var logger = LogManager.getLogger(Constants.MOD_ID);

        List<Level> listLevels = new ArrayList<>();
        if (config.levels != null) {
            for (int i = 0; i < config.levels.size(); i++) {
                try {
                    listLevels.add(Level.valueOf(config.levels.get(i)));
                }catch (IllegalArgumentException e) {
                    logger.error("Invalid level {}, ignoring", config.levels.get(i));
                }
            }
        }
        Level[] levels = listLevels.toArray(new Level[0]);

        String[] loggers = new String[0];
        if (config.loggers != null) {
            loggers = config.loggers.toArray(new String[0]);
        }

        String[] strings = new String[0];
        if (config.strings != null) {
            strings = config.strings.toArray(new String[0]);
        }

        List<Pattern> listRegexes = new ArrayList<>();
        if (config.regexes != null) {
            for (int i = 0; i < config.regexes.size(); i++) {
                try {
                    listRegexes.add(Pattern.compile(config.regexes.get(i)));
                }
                catch (PatternSyntaxException e) {
                    logger.error("Invalid regex {}, ignoring", config.regexes.get(i), e);
                }
            }
        }
        Pattern[] regexes = listRegexes.toArray(new Pattern[0]);

        filteringInfo = new FilteringInfo(levels, loggers, strings, regexes);
    }
}
