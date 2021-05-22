package com.belgieyt.stranded.entities;

import com.belgieyt.stranded.util.TradeBuild;
import com.google.common.collect.Sets;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Set;

public class StrandedTraderEntity extends WanderingTraderEntity {
    @Nullable
    private BlockPos wanderTarget;
    private int despawnDelay;
    private static final DataParameter<Boolean> EATING = EntityDataManager.createKey(StrandedTraderEntity.class, DataSerializers.BOOLEAN);

    public StrandedTraderEntity(EntityType<? extends StrandedTraderEntity> type, World worldIn) {
        super(type, worldIn);
        this.forceSpawn = true;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, ZombieEntity.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, EvokerEntity.class, 12.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, VindicatorEntity.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, VexEntity.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, PillagerEntity.class, 15.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, IllusionerEntity.class, 12.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, ZoglinEntity.class, 10.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new PanicGoal(this, 0.5D));
        this.goalSelector.addGoal(1, new LookAtCustomerGoal(this));
        this.goalSelector.addGoal(2, new StrandedTraderEntity.MoveToGoal(this, 2.0D, 0.35D));
        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 0.35D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 0.35D));
        this.goalSelector.addGoal(9, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
    }

    @Nullable
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
        return null;
    }

    public boolean hasXPBar() {
        return false;
    }

    public ActionResultType getEntityInteractionResult(PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        if (itemstack.getItem() != Items.VILLAGER_SPAWN_EGG && this.isAlive() && !this.hasCustomer() && !this.isChild()) {
            if (hand == Hand.MAIN_HAND) {
                playerIn.addStat(Stats.TALKED_TO_VILLAGER);
            }

            if (this.getOffers().isEmpty()) {
                return ActionResultType.func_233537_a_(this.world.isRemote);
            } else {
                if (!this.world.isRemote) {
                    this.setCustomer(playerIn);
                    this.openMerchantContainer(playerIn, this.getDisplayName(), 1);
                }

                return ActionResultType.func_233537_a_(this.world.isRemote);
            }
        } else {
            return super.getEntityInteractionResult(playerIn, hand);
        }
    }

    protected void populateTradeData() {
        TradeBuild.ITrade[] avillagertrades$itrade = TradeBuild.WANDERING_TRADER_TRADES.get(1);
        TradeBuild.ITrade[] avillagertrades$itrade1 = TradeBuild.WANDERING_TRADER_TRADES.get(2);
        if (avillagertrades$itrade != null && avillagertrades$itrade1 != null) {
            MerchantOffers merchantoffers = this.getOffers();
            this.addTrades(merchantoffers, avillagertrades$itrade, 5);
            int i = this.rand.nextInt(avillagertrades$itrade1.length);
            TradeBuild.ITrade villagertrades$itrade = avillagertrades$itrade1[i];
            MerchantOffer merchantoffer = villagertrades$itrade.getOffer(this, this.rand);
            if (merchantoffer != null) {
                merchantoffers.add(merchantoffer);
            }

        }
    }
    protected void addTrades(MerchantOffers givenMerchantOffers, TradeBuild.ITrade[] newTrades, int maxNumbers) {
        Set<Integer> set = Sets.newHashSet();
        if (newTrades.length > maxNumbers) {
            while(set.size() < maxNumbers) {
                set.add(this.rand.nextInt(newTrades.length));
            }
        } else {
            for(int i = 0; i < newTrades.length; ++i) {
                set.add(i);
            }
        }

        for(Integer integer : set) {
            TradeBuild.ITrade villagertrades$itrade = newTrades[integer];
            MerchantOffer merchantoffer = villagertrades$itrade.getOffer(this, this.rand);
            if (merchantoffer != null) {
                givenMerchantOffers.add(merchantoffer);
            }
        }

    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("DespawnDelay", this.despawnDelay);
        if (this.wanderTarget != null) {
            compound.put("WanderTarget", NBTUtil.writeBlockPos(this.wanderTarget));
        }

    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("DespawnDelay", 99)) {
            this.despawnDelay = compound.getInt("DespawnDelay");
        }

        if (compound.contains("WanderTarget")) {
            this.wanderTarget = NBTUtil.readBlockPos(compound.getCompound("WanderTarget"));
        }

        this.setGrowingAge(Math.max(0, this.getGrowingAge()));
    }

    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }

    protected void onVillagerTrade(MerchantOffer offer) {
        if (offer.getDoesRewardExp()) {
            int i = 3 + this.rand.nextInt(4);
            this.world.addEntity(new ExperienceOrbEntity(this.world, this.getPosX(), this.getPosY() + 0.5D, this.getPosZ(), i));
        }

    }

    protected SoundEvent getAmbientSound() {
        return this.hasCustomer() ? SoundEvents.ENTITY_WANDERING_TRADER_TRADE : SoundEvents.ENTITY_WANDERING_TRADER_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_WANDERING_TRADER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WANDERING_TRADER_DEATH;
    }

    protected SoundEvent getDrinkSound(ItemStack stack) {
        Item item = stack.getItem();
        return item == Items.MILK_BUCKET ? SoundEvents.ENTITY_WANDERING_TRADER_DRINK_MILK : SoundEvents.ENTITY_WANDERING_TRADER_DRINK_POTION;
    }

    protected SoundEvent getVillagerYesNoSound(boolean getYesSound) {
        return getYesSound ? SoundEvents.ENTITY_WANDERING_TRADER_YES : SoundEvents.ENTITY_WANDERING_TRADER_NO;
    }

    public SoundEvent getYesSound() {
        return SoundEvents.ENTITY_WANDERING_TRADER_YES;
    }

    public void setDespawnDelay(int delay) {
        this.despawnDelay = delay;
    }

    public int getDespawnDelay() {
        return this.despawnDelay;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void livingTick() {
        super.livingTick();
        if (!this.world.isRemote) {
            this.handleDespawn();
        }

    }
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 10.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, (double)0.2F);
    }

    private void handleDespawn() {
        if (this.despawnDelay > 0 && !this.hasCustomer() && --this.despawnDelay == 0) {
            this.remove();
        }

    }

    public void setWanderTarget(@Nullable BlockPos pos) {
        this.wanderTarget = pos;
    }

    @Nullable
    private BlockPos getWanderTarget() {
        return this.wanderTarget;
    }

    class MoveToGoal extends Goal {
        final StrandedTraderEntity traderEntity;
        final double maxDistance;
        final double speed;

        MoveToGoal(StrandedTraderEntity traderEntityIn, double distanceIn, double speedIn) {
            this.traderEntity = traderEntityIn;
            this.maxDistance = distanceIn;
            this.speed = speedIn;
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            this.traderEntity.setWanderTarget((BlockPos)null);
            StrandedTraderEntity.this.navigator.clearPath();
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            BlockPos blockpos = this.traderEntity.getWanderTarget();
            return blockpos != null && this.isWithinDistance(blockpos, this.maxDistance);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            BlockPos blockpos = this.traderEntity.getWanderTarget();
            if (blockpos != null && StrandedTraderEntity.this.navigator.noPath()) {
                if (this.isWithinDistance(blockpos, 10.0D)) {
                    Vector3d vector3d = (new Vector3d((double)blockpos.getX() - this.traderEntity.getPosX(), (double)blockpos.getY() - this.traderEntity.getPosY(), (double)blockpos.getZ() - this.traderEntity.getPosZ())).normalize();
                    Vector3d vector3d1 = vector3d.scale(10.0D).add(this.traderEntity.getPosX(), this.traderEntity.getPosY(), this.traderEntity.getPosZ());
                    StrandedTraderEntity.this.navigator.tryMoveToXYZ(vector3d1.x, vector3d1.y, vector3d1.z, this.speed);
                } else {
                    StrandedTraderEntity.this.navigator.tryMoveToXYZ((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), this.speed);
                }
            }

        }

        private boolean isWithinDistance(BlockPos pos, double distance) {
            return !pos.withinDistance(this.traderEntity.getPositionVec(), distance);
        }
    }
    @Override
    public ItemStack onFoodEaten(World world, ItemStack stack) {
        if (stack.isFood()) {
            this.heal(stack.getItem().getFood().getHealing());
        }
        super.onFoodEaten(world, stack);
        world.playSound(null, this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        this.setEating(false);
        return stack;
    }
    public boolean isEating() {
        return this.dataManager.get(EATING);
    }

    public void setEating(boolean eating) {
        this.dataManager.set(EATING, eating);
    }
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEquipmentBasedOnDifficulty(difficulty);
        if (this.rand.nextFloat() < (this.world.getDifficulty() == Difficulty.HARD ? 5.0F : 1.0F)) {
            int i = this.rand.nextInt(3);
            if (i == 0) {
                this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BREAD));

            }
        }

    }
}