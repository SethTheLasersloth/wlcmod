package com.lasertest.wlcmod.mixin;

import com.lasertest.wlcmod.WLC_Mod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


// Made with help from BillBodkins on 3/10/25! TY Bill!

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(boolean slowDown, float slowDownFactor, CallbackInfo ci) {

        if (WLC_Mod.blockInputs) {
            KeyboardInput thisObject = (KeyboardInput)(Object)this;
            thisObject.pressingForward = false;
            thisObject.pressingBack = false;
            thisObject.pressingLeft = false;
            thisObject.pressingRight = false;
            thisObject.movementForward = 0;
            thisObject.movementSideways = 0;
//            thisObject.jumping = false;
            thisObject.sneaking = false;
        }

    }
}
