package me.decce.transformingbase.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal", "unused"})
public class AsyncLoggerConfig {
    @Comment("Specifies whether to enable the mod")
    public boolean enabled = true;
    @Comment("Specifies the value for log4j2.asyncLoggerRingBufferSize (0 = use default)")
    public int ringBufferSize = 0;
    @Comment("Specifies the value for log4j2.asyncLoggerRingBufferSize (leave empty for default)")
    public String waitStrategy = "";
    @Comment("Specifies the value for log4j2.asyncLoggerSynchronizeEnqueueWhenQueueFull (leave empty for default)")
    public String synchronizeEnqueueWhenQueueFull = "";

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Comment {
        String value();
    }
}
