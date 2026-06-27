package me.decce.transformingbase.core;

import org.apache.logging.log4j.Level;

import java.util.regex.Pattern;

public record FilteringInfo(Level[] levels, String[] loggers, String[] strings, Pattern[] regexes) {
}
