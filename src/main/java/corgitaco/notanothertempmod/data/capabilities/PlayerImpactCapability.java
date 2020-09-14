package corgitaco.notanothertempmod.data.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class PlayerImpactCapability {
    //the capability itself
    @CapabilityInject(IPlayerImpacts.class)
    public static Capability<IPlayerImpacts> PLAYER_IMPACT = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IPlayerImpacts.class, new Capability.IStorage<IPlayerImpacts>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<IPlayerImpacts> capability, IPlayerImpacts instance, Direction side) {
                return instance.saveNBTData();
            }


            public void readNBT(Capability<IPlayerImpacts> capability, IPlayerImpacts instance, Direction side, INBT nbt) {
                instance.loadNBTData((CompoundNBT) nbt);
            }
        }, PlayerImpacts::new);
    }
}