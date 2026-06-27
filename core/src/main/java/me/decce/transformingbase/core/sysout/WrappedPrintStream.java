package me.decce.transformingbase.core.sysout;

import java.io.OutputStream;
import java.io.PrintStream;

public abstract class WrappedPrintStream extends PrintStream {
    public WrappedPrintStream(OutputStream out) {
        super(out);
    }

    @Override
    public void print(boolean b) {
        print(String.valueOf(b));
    }

    @Override
    public void print(char c) {
        print(String.valueOf(c));
    }

    @Override
    public void print(int i) {
        print(String.valueOf(i));
    }

    @Override
    public void print(long l) {
        print(String.valueOf(l));
    }

    @Override
    public void print(float f) {
        print(String.valueOf(f));
    }

    @Override
    public void print(double d) {
        print(String.valueOf(d));
    }

    @Override
    public void print(char[] s) {
        print(String.valueOf(s));
    }

    @Override
    public void print(Object obj) {
        print(String.valueOf(obj));
    }

    @Override
    public abstract void print(String s);

    @Override
    public abstract void println();

    @Override
    public void println(boolean x) {
        this.println(String.valueOf(x));
    }

    @Override
    public void println(char x) {
        this.println(String.valueOf(x));
    }

    @Override
    public void println(int x) {
        this.println(String.valueOf(x));
    }

    @Override
    public void println(long x) {
        this.println(String.valueOf(x));
    }

    @Override
    public void println(float x) {
        this.println(String.valueOf(x));
    }

    @Override
    public void println(double x) {
        this.println(String.valueOf(x));
    }

    @Override
    public void println(char[] x) {
        this.println(String.valueOf(x));
    }

    @Override
    public void println(Object x) {
        this.println(String.valueOf(x));
    }

    @Override
    public abstract void println(String x);
}
