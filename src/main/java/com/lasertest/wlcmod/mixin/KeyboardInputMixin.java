package com.lasertest.wlcmod.mixin;

import com.lasertest.wlcmod.WLC_Mod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.KeyboardInput;
//? >=1.21.2 {
//import net.minecraft.util.PlayerInput;
//?}
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


// Made with help from BillBodkins on 3/10/25! TY Bill!

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin {
    @Inject(method = "tick", at = @At("TAIL"))

    public void tick(CallbackInfo ci) {
        if (WLC_Mod.blockInputs) {
            KeyboardInput thisObject = (KeyboardInput)(Object)this;

            //? <=1.21.1 {
            thisObject.pressingForward = false;
            thisObject.pressingBack = false;
            thisObject.pressingLeft = false;
            thisObject.pressingRight = false;
            thisObject.sneaking = false;
            //?}
            //? >=1.21.2 {
//            thisObject.playerInput = new PlayerInput(false, false, false, false, thisObject.playerInput.jump(), false, thisObject.playerInput.sprint());
            //?}

            thisObject.movementForward = 0;
            thisObject.movementSideways = 0;

        }
    }
}
