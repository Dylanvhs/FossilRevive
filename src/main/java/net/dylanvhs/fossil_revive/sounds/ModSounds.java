package net.dylanvhs.fossil_revive.sounds;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FossilRevive.MOD_ID);


    public static final RegistryObject<SoundEvent> BONES = registerSoundEvents("bones");
    public static final RegistryObject<SoundEvent> DILO_AMBIENT = registerSoundEvents("dilo_ambient");
    public static final RegistryObject<SoundEvent> DILO_HURT = registerSoundEvents("dilo_hurt");
    public static final RegistryObject<SoundEvent> LIO_AMBIENT = registerSoundEvents("lio_ambient");
    public static final RegistryObject<SoundEvent> LIO_HURT = registerSoundEvents("lio_hurt");


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FossilRevive.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
