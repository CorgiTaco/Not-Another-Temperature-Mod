package corgitaco.notanothertempmod.data.capabilities;

import corgitaco.notanothertempmod.NotAnotherTemperatureMod;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class PlayerImpactsCapabilityProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundNBT> {

    //The instance of the capability? I think?
    private PlayerImpacts instance = (PlayerImpacts) NotAnotherTemperatureMod.PLAYER_IMPACTS.getDefaultInstance();

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == NotAnotherTemperatureMod.PLAYER_IMPACTS) {
            if (instance == null) {
                instance = new PlayerImpacts();
            }

            return LazyOptional.of(() -> instance).cast();
        } else {
            return LazyOptional.empty();
        }
    }


    @Override
    public CompoundNBT serializeNBT() {
        return instance.saveNBTData();
    }


    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        instance.loadNBTData(nbt);
    }
}