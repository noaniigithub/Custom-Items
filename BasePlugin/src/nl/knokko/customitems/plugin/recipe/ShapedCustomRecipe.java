/*
 * The MIT License
 *
 * Copyright 2018 knokko.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
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
 */
package nl.knokko.customitems.plugin.recipe;

import nl.knokko.customitems.plugin.CustomItemsPlugin;
import nl.knokko.customitems.plugin.recipe.ingredient.Ingredient;

import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.material.MaterialData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class ShapedCustomRecipe implements CustomRecipe {
    
    private final Ingredient[] ingredients;
    private final ItemStack result;
    
    private final String id;
    
    public ShapedCustomRecipe(String id, ItemStack result, Ingredient[] ingredients){
        this.ingredients = ingredients;
        this.result = result;
        this.id = id;
    }

    @Override
    public void register() {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(CustomItemsPlugin.getInstance(), id), result);//use NameSpacedKey
        recipe.shape("abc", "def", "ghi");
        for(int index = 0; index < 9; index++)
        	recipe.setIngredient((char) ('a' + index), new MaterialData(ingredients[index].getType()));
        Bukkit.addRecipe(recipe);
        CustomRecipes.register(this);
    }

	@Override
	public ItemStack getResult() {
		return result;
	}

	@Override
	public boolean areMaterialsCorrect(Material[] ingredients) {
		for(int index = 0; index < 9; index++)
			if(ingredients[index] != this.ingredients[index].getType())
				return false;
		return true;
	}

	@Override
	public boolean shouldAccept(ItemStack[] ingredients) {
		for(int index = 0; index < 9; index++) 
			if(!this.ingredients[index].accept(ingredients[index]))
				return false;
		return true;
	}
}