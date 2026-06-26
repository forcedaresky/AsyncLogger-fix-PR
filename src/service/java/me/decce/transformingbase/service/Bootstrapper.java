package me.decce.transformingbase.service;

import org.apache.logging.log4j.Logger;

public class Bootstrapper {
    private static boolean bootstrapped;

    public static void bootstrap() {
        if (bootstrapped) {
            return;
        }
        bootstrapped = true;

        try (var classLoaderHandler = new ClassLoaderHandlerImpl(Logger.class.getClassLoader(), Bootstrapper.class.getClassLoader())) {
            //? if !fabric {
            /*classLoaderHandler.expandModuleReads(Logger.class.getModule(), org.apache.logging.log4j.core.Logger.class.getModule());
            classLoaderHandler.loadCoreClasses(Bootstrapper.class, "/com/lmax");
            classLoaderHandler.removeModClassesFromServiceLayer("com.lmax");
             *///?}
        }

        initConfig();

        if (AsyncLogger.config.enabled) {
            if (AsyncLogger.config.filtering) {
                AsyncLogger.compileFilters();
            }
            LoggerConfigurator.configure();
        }
    }

    private static void initConfig() {
        AsyncLogger.config = ConfigLoader.load();
        ConfigLoader.save(AsyncLogger.config);
    }
}
