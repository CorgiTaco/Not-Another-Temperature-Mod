package corgitaco.notanothertempmod.data.capabilities;

import net.minecraft.nbt.CompoundNBT;

public class PlayerImpacts implements IPlayerImpacts {


    private double playerTemperature = 25;
    private double biomeTemperature = 0.5;

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
    public CompoundNBT saveNBTData() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putDouble("PlayerImpacts", getPlayerTemperature());
        nbt.putDouble("BiomeTemperature", getBiomeTemperature());

        return nbt;
    }

    @Override
    public void loadNBTData(CompoundNBT nbtTag) {
        this.setPlayerTemperature(nbtTag.getDouble("PlayerImpacts"));
        this.setBiomeTemperature(nbtTag.getDouble("BiomeTemperature"));
    }
}
