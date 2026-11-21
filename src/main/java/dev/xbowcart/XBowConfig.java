package dev.xbowcart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import net.fabricmc.loader.api.FabricLoader;

/** Same settings the original module had: Place (Fire/Rail/Cart/Shoot), Auto Aim, Silent Rotations. */
public final class XBowConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("xbowcart.json");

    public boolean enabled = false;
    public boolean placeFire = true;
    public boolean placeRail = true;
    public boolean placeCart = true;
    public boolean placeShoot = true;
    public boolean autoAim = true;
    public boolean silentRotations = true;

    public static XBowConfig load() {
        try {
            if (Files.exists(FILE)) {
                XBowConfig loaded = GSON.fromJson(Files.readString(FILE), XBowConfig.class);
                if (loaded != null) return loaded;
            }
        } catch (Exception ignored) { }
        return new XBowConfig();
    }

    public void save() {
        try {
            Files.writeString(FILE, GSON.toJson(this));
        } catch (IOException ignored) { }
    }
}
