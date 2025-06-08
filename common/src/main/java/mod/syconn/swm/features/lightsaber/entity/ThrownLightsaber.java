package mod.syconn.swm.features.lightsaber.entity;

import mod.syconn.swm.core.ModDamageSources;
import mod.syconn.swm.core.ModEntities;
import mod.syconn.swm.core.ModItems;
import mod.syconn.swm.features.lightsaber.data.LightsaberTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ThrownLightsaber extends AbstractArrow {

    private static final EntityDataAccessor<CompoundTag> LIGHTSABER_DATA = SynchedEntityData.defineId(ThrownLightsaber.class, EntityDataSerializers.COMPOUND_TAG);
    private final int returnLevel = 2;
    private boolean returning = false;

    public ThrownLightsaber(EntityType<? extends ThrownLightsaber> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownLightsaber(Level level, LivingEntity shooter, ItemStack stack) {
        super(ModEntities.THROWN_LIGHTSABER.get(), shooter, level);
        this.entityData.set(LIGHTSABER_DATA, LightsaberTag.getOrCreate(stack).save());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(LIGHTSABER_DATA, new CompoundTag());
    }

    @Override
    public void tick() {
        if (this.inGroundTime > 2) this.returning = true;

        Entity entity = this.getOwner();
        if ((this.returning || this.isNoPhysics()) && entity != null) {
            if (this.distanceTo(getOwner()) >= 8) this.returning = true;

            if (!this.isAcceptableReturnOwner()) {
                if (!this.level().isClientSide && this.pickup == AbstractArrow.Pickup.ALLOWED) this.spawnAtLocation(this.getPickupItem(), 0.1F);
                this.discard();
            } else {
                this.setNoPhysics(true);
                Vec3 vec3 = entity.getEyePosition().subtract(this.position());
                this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.015 * returnLevel, this.getZ());
                if (this.level().isClientSide) this.yOld = this.getY();

                double d = 0.05 * returnLevel;
                this.setDeltaMovement(this.getDeltaMovement().scale(0.95).add(vec3.normalize().scale(d)));
            }
        }


        super.tick();
    }

    private boolean isAcceptableReturnOwner() {
        Entity entity = this.getOwner();
        return entity != null && entity.isAlive() && (!(entity instanceof ServerPlayer) || !entity.isSpectator());
    }

    @Override
    protected ItemStack getPickupItem() {
        ItemStack stack = new ItemStack(ModItems.LIGHTSABER.get());
        return new LightsaberTag(this.entityData.get(LIGHTSABER_DATA)).change(stack);
    }

    public ItemStack getItem() {
        return LightsaberTag.update(getPickupItem(), tag -> tag.transition = -8);
    }

    public boolean isNoGravity() {
        return true;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        float f = 8.0F;

        Entity entity2 = this.getOwner();
        DamageSource damageSource = ModDamageSources.lightsaber(level());
        SoundEvent soundEvent = SoundEvents.TRIDENT_HIT; // TODO CHANGE THIS TOO
        if (entity.hurt(damageSource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) return;

            if (entity instanceof LivingEntity livingEntity2) {
                if (entity2 instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingEntity2, entity2);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity)entity2, livingEntity2);
                }

                this.doPostHurtEffects(livingEntity2);
            }
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01, -0.1, -0.01));
        this.playSound(soundEvent, 1.0F, 1.0F);
    }

    @Override
    protected boolean tryPickup(Player player) {
        return super.tryPickup(player) || this.isNoPhysics() && this.ownedBy(player) && player.getInventory().add(this.getPickupItem());
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() { // TODO CHANGE AUDIO
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    @Override
    public void playerTouch(Player player) {
        if (this.ownedBy(player) || this.getOwner() == null) super.playerTouch(player);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.returning = compound.getBoolean("return");
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("return", this.returning);
    }

    @Override
    public void tickDespawn() {
        if (this.pickup != Pickup.ALLOWED) super.tickDespawn();
    }

    @Override
    public boolean shouldRender(double x, double y, double z) {
        return true;
    }
}
