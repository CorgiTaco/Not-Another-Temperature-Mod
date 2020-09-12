package corgitaco.notanothertempmod.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class PlayerTemperatureProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundNBT> {

	@CapabilityInject(PlayerTemperature.class)
	public static Capability<PlayerTemperature> PLAYER_TEMP = null;
	
	//The instance of the capability? I think?
	private PlayerTemperature instance = (PlayerTemperature) PLAYER_TEMP.getDefaultInstance();


	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == PLAYER_TEMP) {
			if(instance == null) {
				instance = new PlayerTemperature();
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