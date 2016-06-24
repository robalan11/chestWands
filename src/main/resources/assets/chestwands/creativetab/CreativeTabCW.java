package assets.chestwands.creativetab;

import com.robalan.chestWands.init.ModItems;
import com.robalan.chestWands.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabCW {
    public static final CreativeTabs CHESTWANDS_TAB = new CreativeTabs(Reference.MOD_ID) {
        @Override
        public Item getTabIconItem() {
            return ModItems.movingWand;
        }
    };
}
