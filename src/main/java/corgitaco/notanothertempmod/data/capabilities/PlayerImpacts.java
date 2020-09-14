package corgitaco.notanothertempmod.data.capabilities;

import net.minecraft.nbt.CompoundNBT;

public class PlayerImpacts implements IPlayerImpacts {

    private double playerTemperature = 73;
    private double biomeTemperature = 0.5;

    private double sleepiness = 0.5;

    private double hydration = 0.5;


    @Override
    public void setBiomeTemperature(double biomeTemperature) {
        this.biomeTemperature = biomeTemperature;
    }

    @Override
    public double getBiomeTemperature() {
        return biomeTemperature;
    }

    @Override
    public void setPlayerTemperature(double playerTemperature) {
        this.playerTemperature = playerTemperature;
    }

    @Override
    public double getPlayerTemperature() {
        return playerTemperature;
    }

    @Override
    public void setPlayerSleepiness(double playerSleepiness) {
        this.sleepiness = playerSleepiness;
    }

    @Override
    public double getPlayerSleepiness() {
        return sleepiness;
    }

    @Override
    public void setPlayerHydration(double playerHydration) {
        this.hydration = playerHydration;
    }

    @Override
    public double getPlayerHydration() {
        return hydration;
    }

    @Override
    public CompoundNBT saveNBTData() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putDouble("PlayerTemperature", getPlayerTemperature());
        nbt.putDouble("BiomeTemperature", getBiomeTemperature());
        nbt.putDouble("PlayerSleepiness", getPlayerSleepiness());
        nbt.putDouble("PlayerHydration", getPlayerHydration());
        return nbt;
    }

    @Override
    public void loadNBTData(CompoundNBT nbtTag) {
        this.setPlayerTemperature(nbtTag.getDouble("PlayerImpacts"));
        this.setBiomeTemperature(nbtTag.getDouble("BiomeTemperature"));
        this.setPlayerSleepiness(nbtTag.getDouble("PlayerSleepiness"));
        this.setPlayerHydration(nbtTag.getDouble("PlayerHydration"));
    }
}
