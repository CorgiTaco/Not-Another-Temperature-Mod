package corgitaco.notanothertempmod.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class PlayerTemperatureCapability {
	//the capability itself
	@CapabilityInject(ITemperature.class)
	public static Capability<ITemperature> PLAYER_DATA = null;

	public static void register() {
		CapabilityManager.INSTANCE.register(ITemperature.class, new Capability.IStorage<ITemperature>() {
			@Nullable
			@Override
			public INBT writeNBT(Capability<ITemperature> capability, ITemperature instance, Direction side) {
				return instance.saveNBTData();
			}


			public void readNBT(Capability<ITemperature> capability, ITemperature instance, Direction side, INBT nbt)
			{
				instance.loadNBTData((CompoundNBT) nbt);
			}
		}, PlayerTemperature::new);
	}
}