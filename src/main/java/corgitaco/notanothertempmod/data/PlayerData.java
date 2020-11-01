package corgitaco.notanothertempmod.data;

import net.minecraft.nbt.CompoundNBT;

public class PlayerData {

    private double playerTemperature = 73;
    private double biomeTemperature = 0.5;

    private double sleepiness = 0.5;

    private double hydration = 0.5;


    public void setBiomeTemperature(double biomeTemperature) {
        this.biomeTemperature = biomeTemperature;
    }

    public double getBiomeTemperature() {
        return biomeTemperature;
    }

    public void setPlayerTemperature(double playerTemperature) {
        this.playerTemperature = playerTemperature;
    }

    public double getPlayerTemperature() {
        return playerTemperature;
    }

    public void setPlayerSleepiness(double playerSleepiness) {
        this.sleepiness = playerSleepiness;
    }

    public double getPlayerSleepiness() {
        return sleepiness;
    }

    public void setPlayerHydration(double playerHydration) {
        this.hydration = playerHydration;
    }

    public double getPlayerHydration() {
        return hydration;
    }

    public void saveNBTData(CompoundNBT nbt) {
        nbt.putDouble("PlayerTemperature", getPlayerTemperature());
        nbt.putDouble("BiomeTemperature", getBiomeTemperature());
        nbt.putDouble("PlayerSleepiness", getPlayerSleepiness());
        nbt.putDouble("PlayerHydration", getPlayerHydration());
    }

    public void loadNBTData(CompoundNBT nbt) {
        this.setPlayerTemperature(nbt.getDouble("PlayerTemperature"));
        this.setBiomeTemperature(nbt.getDouble("BiomeTemperature"));
        this.setPlayerSleepiness(nbt.getDouble("PlayerSleepiness"));
        this.setPlayerHydration(nbt.getDouble("PlayerHydration"));
    }
}
