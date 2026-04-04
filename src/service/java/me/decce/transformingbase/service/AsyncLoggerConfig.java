package me.decce.transformingbase.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal", "unused"})
public class AsyncLoggerConfig {
    @Comment("Specifies whether to enable the mod")
    public boolean enabled = true;
    @Comment("Specifies the value for log4j2.asyncLoggerRingBufferSize (0 = use mod default, -1 = use log4j2 default)")
    public int ringBufferSize = 0;
    @Comment("Specifies the value for log4j2.asyncLoggerRingBufferSize (leave empty for default)")
    public String waitStrategy = "";
    @Comment("Specifies the value for log4j2.asyncLoggerSynchronizeEnqueueWhenQueueFull (leave empty for default)")
    public String synchronizeEnqueueWhenQueueFull = "";
    @Comment("When enabled, compares the performance and logs the test results. Will cause a mass amount of messages to be printed at startup.")
    public boolean testPerformance = false;

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Comment {
        String value();
    }
}
