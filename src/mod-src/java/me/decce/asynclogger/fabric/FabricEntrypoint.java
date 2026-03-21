package me.decce.asynclogger.fabric;

//? if fabric {
import me.decce.asynclogger.AsyncLoggerMod;
import net.fabricmc.api.ModInitializer;

public class FabricEntrypoint implements ModInitializer {
    @Override
    public void onInitialize() {
        AsyncLoggerMod.init();
    }
}
//?}
