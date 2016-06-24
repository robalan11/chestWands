package com.robalan.chestWands.handler;

import com.robalan.chestWands.reference.Reference;
import com.robalan.chestWands.utility.LogHelper;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigurationHandler {
    public static Configuration configuration;
    public static boolean testValue = true;

    public static void init(File configFile) {
        if (configuration == null) {
            Configuration configuration = new Configuration(configFile);
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(Reference.MOD_ID)) {
            loadConfiguration()
        }
    }

    public void loadConfiguration() {
        testValue = configuration.getBoolean("configValue", Configuration.CATEGORY_GENERAL, true, "This is a test value");

        try {
            configuration.load();

            configValue = configuration.get(Configuration.CATEGORY_GENERAL, "configValue", true, "").getBoolean(true);
        }
        catch (Exception e) {
            // Log the exception
        }
        finally {
            if (configuration.hasChanged()) {
                configuration.save();
            }
        }

        LogHelper.info(configValue);
    }

}
