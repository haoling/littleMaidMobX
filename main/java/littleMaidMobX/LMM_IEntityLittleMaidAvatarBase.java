package littleMaidMobX;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;

public interface LMM_IEntityLittleMaidAvatarBase {

	boolean isItemTrigger = false;
	boolean isItemReload = false;

	void getValueVectorFire(double atx, double aty, double atz, double atl);

	//float getEyeHeight();

	Vec3 getLook(float pDelta);

	void stopUsingItem();

	void attackTargetEntityWithCurrentItem(Entity par1Entity);

	IIcon getItemIcon(ItemStack par1ItemStack, int par2);

	void getValue();

	boolean isUsingItemLittleMaid();

	boolean isUsingItem();

	void setValueVector();

	void clearItemInUse();

	void damageArmor(float pDamage);

	float applyArmorCalculations(DamageSource par1DamageSource, float par2);

	float applyPotionDamageCalculations(DamageSource par1DamageSource,
			float par2);

}
