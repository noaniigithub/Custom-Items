/*******************************************************************************
 * The MIT License
 *
 * Copyright (c) 2019 knokko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *  
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *******************************************************************************/
package nl.knokko.customitems.editor.menu.edit.item;

import nl.knokko.customitems.editor.menu.edit.EditMenu;
import nl.knokko.customitems.editor.menu.edit.EditProps;
import nl.knokko.customitems.editor.menu.edit.recipe.ingredient.IngredientComponent;
import nl.knokko.customitems.editor.set.item.CustomItem;
import nl.knokko.customitems.editor.set.item.CustomTool;
import nl.knokko.customitems.editor.set.recipe.ingredient.NoIngredient;
import nl.knokko.customitems.item.AttributeModifier;
import nl.knokko.customitems.item.AttributeModifier.Attribute;
import nl.knokko.customitems.item.AttributeModifier.Operation;
import nl.knokko.customitems.item.AttributeModifier.Slot;
import nl.knokko.customitems.item.CustomItemType;
import nl.knokko.customitems.item.CustomItemType.Category;
import nl.knokko.gui.component.image.CheckboxComponent;
import nl.knokko.gui.component.text.TextComponent;
import nl.knokko.gui.component.text.TextEditField;

public class EditItemTool extends EditItemBase {

	private final CustomTool previous;
	private final Category category;

	protected final CheckboxComponent allowEnchanting;
	protected final CheckboxComponent allowAnvil;
	protected final TextEditField durability;
	protected final IngredientComponent repairItem;

	public EditItemTool(EditMenu menu, CustomTool previous, Category toolCategory) {
		super(menu, previous);
		this.previous = previous;
		category = toolCategory;
		if (previous != null) {
			allowEnchanting = new CheckboxComponent(previous.allowEnchanting());
			allowAnvil = new CheckboxComponent(previous.allowAnvilActions());
			repairItem = new IngredientComponent("None", previous.getRepairItem(), this, menu.getSet());
			durability = new TextEditField(previous.getDurability() + "", EditProps.EDIT_BASE, EditProps.EDIT_ACTIVE);
		} else {
			allowEnchanting = new CheckboxComponent(true);
			allowAnvil = new CheckboxComponent(true);
			repairItem = new IngredientComponent("None", new NoIngredient(), this, menu.getSet());
			durability = new TextEditField("500", EditProps.EDIT_BASE, EditProps.EDIT_ACTIVE);
			if (toolCategory == Category.SWORD)
				internalType.currentType = CustomItemType.IRON_SWORD;
			else if (toolCategory == Category.PICKAXE)
				internalType.currentType = CustomItemType.IRON_PICKAXE;
			else if (toolCategory == Category.AXE)
				internalType.currentType = CustomItemType.IRON_AXE;
			else if (toolCategory == Category.SHOVEL)
				internalType.currentType = CustomItemType.IRON_SHOVEL;
			else if (toolCategory == Category.HOE)
				internalType.currentType = CustomItemType.IRON_HOE;
			else if (toolCategory == Category.SHEAR)
				internalType.currentType = CustomItemType.SHEARS;
			else if (toolCategory == Category.BOW)
				internalType.currentType = CustomItemType.BOW;
			else if (toolCategory == Category.HELMET)
				internalType.currentType = CustomItemType.IRON_HELMET;
			else if (toolCategory == Category.CHESTPLATE)
				internalType.currentType = CustomItemType.IRON_CHESTPLATE;
			else if (toolCategory == Category.LEGGINGS)
				internalType.currentType = CustomItemType.IRON_LEGGINGS;
			else if (toolCategory == Category.BOOTS)
				internalType.currentType = CustomItemType.IRON_BOOTS;
			else
				throw new Error("Unsupported category for EditItemTool: " + toolCategory);
			internalDamage.setDirectText(Short.toString(menu.getSet().nextAvailableDamage(internalType.currentType)));
		}
	}
	
	@Override
	protected AttributeModifier getExampleAttributeModifier() {
		CustomItemType i = internalType.currentType;
		double attackDamage;
		if  (i == CustomItemType.DIAMOND_AXE || i == CustomItemType.IRON_AXE || i == CustomItemType.STONE_AXE){
			attackDamage = 9;
		} else if (i == CustomItemType.DIAMOND_SWORD || i == CustomItemType.WOOD_AXE || i == CustomItemType.GOLD_AXE) {
			attackDamage = 7;
		} else if (i == CustomItemType.IRON_SWORD) {
			attackDamage = 6;
		} else if (i == CustomItemType.DIAMOND_SHOVEL) {
			attackDamage = 5.5;
		} else if (i == CustomItemType.DIAMOND_PICKAXE || i == CustomItemType.STONE_SWORD) {
			attackDamage = 5;
		} else if (i == CustomItemType.IRON_SHOVEL) {
			attackDamage = 4.5;
		} else if (i == CustomItemType.WOOD_SWORD || i == CustomItemType.GOLD_SWORD  || i == CustomItemType.IRON_PICKAXE) {
			attackDamage = 4;
		} else if (i == CustomItemType.STONE_SHOVEL) {
			attackDamage = 3.5;
		} else if (i == CustomItemType.STONE_PICKAXE) {
			attackDamage = 3;
		} else if (i == CustomItemType.WOOD_SHOVEL || i == CustomItemType.GOLD_SHOVEL) {
			attackDamage = 2.5;
		} else if (i == CustomItemType.WOOD_PICKAXE || i == CustomItemType.GOLD_PICKAXE) {
			attackDamage = 2;
		} else {
			attackDamage = 1;
		}
		return new AttributeModifier(Attribute.ATTACK_DAMAGE, Slot.MAINHAND, Operation.ADD, attackDamage);
	}

	@Override
	protected void addComponents() {
		super.addComponents();
		internalType.setText(internalType.currentType.toString());
		addComponent(allowEnchanting, 0.75f, 0.8f, 0.775f, 0.825f);
		addComponent(new TextComponent("Allow enchanting", EditProps.LABEL), 0.8f, 0.8f, 0.95f, 0.9f);
		addComponent(allowAnvil, 0.75f, 0.7f, 0.775f, 0.725f);
		addComponent(new TextComponent("Allow anvil actions", EditProps.LABEL), 0.8f, 0.7f, 0.95f, 0.8f);
		addComponent(durability, 0.85f, 0.6f, 0.9f, 0.7f);
		addComponent(new TextComponent("Max uses: ", EditProps.LABEL), 0.71f, 0.6f, 0.84f, 0.7f);
		addComponent(new TextComponent("Repair item: ", EditProps.LABEL), 0.71f, 0.5f, 0.84f, 0.6f);
		addComponent(repairItem, 0.85f, 0.5f, 0.99f, 0.6f);
		if (category == Category.SWORD) {
			errorComponent.setProperties(EditProps.LABEL);
			errorComponent.setText("Hint: Use attribute modifiers to set the damage this sword will deal.");
		}
	}
	
	protected String create(short damage, long maxUses) {
		return menu.getSet().addTool(
				new CustomTool(internalType.currentType, damage, name.getText(), getDisplayName(),
						lore, attributes, enchantments, maxUses, allowEnchanting.isChecked(),
						allowAnvil.isChecked(), repairItem.getIngredient(), textureSelect.currentTexture),
						true);
	}

	@Override
	protected String create(short damage) {
		String error = null;
		try {
			long maxUses = Long.parseLong(durability.getText());
			if (maxUses > 0 || maxUses == CustomItem.UNBREAKABLE_TOOL_DURABILITY) {
				error = create(damage, maxUses);
			} else {
				error = "The max uses must be greater than 0 or " + CustomItem.UNBREAKABLE_TOOL_DURABILITY
						+ " to become unbreakable";
			}
		} catch (NumberFormatException nfe) {
			error = "The max uses must be an integer";
		}
		return error;
	}
	
	protected String apply(short damage, long maxUses) {
		return menu.getSet().changeTool(previous, internalType.currentType, damage, name.getText(),
				getDisplayName(), lore, attributes, enchantments, allowEnchanting.isChecked(),
				allowAnvil.isChecked(), repairItem.getIngredient(), maxUses, textureSelect.currentTexture,
				true);
	}

	@Override
	protected String apply(short damage) {
		String error = null;
		try {
			long maxUses = Long.parseLong(durability.getText());
			if (maxUses > 0 || maxUses == CustomItem.UNBREAKABLE_TOOL_DURABILITY) {
				error = apply(damage, maxUses);
			} else
				error = "The max uses must be greater than 0 or " + CustomItem.UNBREAKABLE_TOOL_DURABILITY
						+ " to become unbreakable";
		} catch (NumberFormatException nfe) {
			error = "The max uses must be an integer";
		}
		return error;
	}

	@Override
	protected CustomItem previous() {
		return previous;
	}

	@Override
	protected Category getCategory() {
		return category;
	}
}