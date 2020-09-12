package corgitaco.notanothertempmod.capabilities;

import net.minecraft.nbt.CompoundNBT;

public interface ITemperature {

    void setBiomeTemperature(double biomeTemperature);
    double getBiomeTemperature();

    void setPlayerTemperature(double playerTemperature);
    double getPlayerTemperature();

    CompoundNBT saveNBTData();
    void loadNBTData(CompoundNBT nbtTag);
}
