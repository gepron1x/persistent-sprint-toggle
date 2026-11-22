package org.gepron1x.pst.client.mixin;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.ToggleKeyMapping;
import org.gepron1x.pst.client.PersistentSprintToggleClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ToggleKeyMapping.class)
public abstract class ToggleKeyMappingMixing extends KeyMapping {


    public ToggleKeyMappingMixing(String name, int keysym, Category category) {
        super(name, keysym, category);
    }

    @Inject(method = "setDown", at = @At("TAIL"))
    private void pst$captureSprintToggle(boolean down, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        if (mc == null) return;

        Options o = mc.options;
        if (this != o.keySprint) return;
        if (!o.toggleSprint().get()) return;

        PersistentSprintToggleClient.pstConfig
                .setSprintingEnabled(this.isDown());
    }
}
