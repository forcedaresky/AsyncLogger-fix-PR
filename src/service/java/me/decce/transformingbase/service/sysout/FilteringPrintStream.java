package me.decce.transformingbase.service.sysout;

import me.decce.transformingbase.service.FilterImpl;
import me.decce.transformingbase.service.FilteringInfo;
import org.apache.logging.log4j.Level;

import java.io.OutputStream;
import java.io.PrintStream;

public class FilteringPrintStream extends WrappedPrintStream {
    private final PrintStream original;
    private final FilterImpl filter;
    private final Level level;

    public FilteringPrintStream(PrintStream original, FilteringInfo info, Level level) {
        super(OutputStream.nullOutputStream());
        this.original = original;
        this.level = level;
        this.filter = new FilterImpl(info);
    }

    @Override
    public void print(String s) {
        if (this.filter.checkLevel(level) || this.filter.checkStringsSafe(s) || this.filter.checkRegexesSafe(s)) {
            return;
        }
        this.original.print(s);
    }

    @Override
    public void println() {
        this.original.println();
    }

    @Override
    public void println(String s) {
        if (this.filter.checkLevel(level) || this.filter.checkStringsSafe(s) || this.filter.checkRegexesSafe(s)) {
            return;
        }
        this.original.println(s);
    }
}
