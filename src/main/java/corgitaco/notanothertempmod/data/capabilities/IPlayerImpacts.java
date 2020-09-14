package corgitaco.notanothertempmod.data.capabilities;

import net.minecraft.nbt.CompoundNBT;

public interface IPlayerImpacts {

    void setBiomeTemperature(double biomeTemperature);

    double getBiomeTemperature();

    void setPlayerTemperature(double playerTemperature);

    double getPlayerTemperature();

    CompoundNBT saveNBTData();

    void loadNBTData(CompoundNBT nbtTag);
}
