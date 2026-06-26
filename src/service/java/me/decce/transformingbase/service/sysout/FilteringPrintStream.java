package me.decce.transformingbase.service.sysout;

import me.decce.transformingbase.service.FilterImpl;
import me.decce.transformingbase.service.FilteringInfo;

import java.io.OutputStream;
import java.io.PrintStream;

public class FilteringPrintStream extends WrappedPrintStream {
    private final PrintStream original;
    private final FilterImpl filter;

    public FilteringPrintStream(PrintStream original, FilteringInfo info) {
        super(OutputStream.nullOutputStream());
        this.original = original;
        this.filter = new FilterImpl(info);
    }

    @Override
    public void print(String s) {
        if (this.filter.checkStringsSafe(s) || this.filter.checkRegexesSafe(s)) {
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
        if (this.filter.checkStringsSafe(s) || this.filter.checkRegexesSafe(s)) {
            return;
        }
        this.original.println(s);
    }
}
