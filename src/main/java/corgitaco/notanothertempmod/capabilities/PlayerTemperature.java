package corgitaco.notanothertempmod.capabilities;

import net.minecraft.nbt.CompoundNBT;

public class PlayerTemperature implements ITemperature {


    private double playerTemperature = 73;
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
        nbt.putDouble("PlayerTemperature", getPlayerTemperature());
        nbt.putDouble("BiomeTemperature", getBiomeTemperature());

        return nbt;
    }

    @Override
    public void loadNBTData(CompoundNBT nbtTag) {
        this.setPlayerTemperature(nbtTag.getDouble("PlayerTemperature"));
        this.setBiomeTemperature(nbtTag.getDouble("BiomeTemperature"));
    }
}
