package corgitaco.notanothertempmod.mixin;

import corgitaco.notanothertempmod.NotAnotherTemperatureMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Inject(method = "writeAdditional", at = @At("HEAD"))
    private void writeTemp(CompoundNBT compound, CallbackInfo ci) {
        NotAnotherTemperatureMod.playerImpacts.saveNBTData(compound);
    }

    @Inject(method = "readAdditional", at = @At("HEAD"))
    private void readTemp(CompoundNBT compound, CallbackInfo ci) {
        NotAnotherTemperatureMod.playerImpacts.loadNBTData(compound);
    }
}
