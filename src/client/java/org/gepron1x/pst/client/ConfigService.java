package org.gepron1x.pst.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import net.fabricmc.loader.api.FabricLoader;
import org.gepron1x.pst.PersistentSprintToggle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ConfigService {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static final Path CONFIG_FILE =
            FabricLoader.getInstance().getConfigDir()
                    .resolve("persistent-sprint-toggle.json");


    public static PstConfig loadConfig() {
        if(!Files.exists(CONFIG_FILE)) {
            return new PstConfig();
        }
        try(var reader = Files.newBufferedReader(CONFIG_FILE)) {
            PstConfig config = gson.fromJson(reader, PstConfig.class);
            return config == null ? new PstConfig() : config;
        } catch (IOException | JsonParseException e) {
            PersistentSprintToggle.LOGGER.error("Could not read config.", e);
            return new PstConfig();
        }
    }

    public static void saveConfig(PstConfig config) {
        if(!config.isDirty()) return;
        try(var reader = Files.newBufferedWriter(CONFIG_FILE)) {
            gson.toJson(config, PstConfig.class, reader);
            config.markClean();
        } catch (IOException | JsonParseException e) {
            PersistentSprintToggle.LOGGER.error("Could not save persistent sprint toggle.", e);
        }

    }
}
