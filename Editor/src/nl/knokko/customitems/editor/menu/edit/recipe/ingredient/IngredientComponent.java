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
package nl.knokko.customitems.editor.menu.edit.recipe.ingredient;

import java.awt.Color;

import nl.knokko.customitems.editor.set.ItemSet;
import nl.knokko.customitems.editor.set.recipe.ingredient.Ingredient;
import nl.knokko.customitems.editor.set.recipe.ingredient.NoIngredient;
import nl.knokko.gui.component.GuiComponent;
import nl.knokko.gui.component.text.TextButton;
import nl.knokko.gui.util.TextBuilder.Properties;

public class IngredientComponent extends TextButton {
	
	public static final Properties PROPS = Properties.createLabel(Color.BLACK, new Color(255, 150, 0), 512, 128);
	public static final Properties HOVER_PROPS = Properties.createLabel(Color.BLUE, new Color(255, 200, 0), 512, 128);
	
	private Ingredient current;
	private final GuiComponent menu;
	private final String emptyText;

	public IngredientComponent(String emptyText, Ingredient original, GuiComponent menu, ItemSet set) {
		super(original.toString(emptyText), PROPS, HOVER_PROPS, null);
		this.clickAction = () -> {
			state.getWindow().setMainComponent(new IngredientView(emptyText, this, set));
		};
		current = original;
		this.emptyText = emptyText;
		this.menu = menu;
	}
	
	public IngredientComponent(String emptyText, GuiComponent menu, ItemSet set) {
		this(emptyText, new NoIngredient(), menu, set);
	}
	
	public void setIngredient(Ingredient ingredient) {
		current = ingredient;
		setText(current.toString(emptyText));
	}
	
	public Ingredient getIngredient() {
		return current;
	}
	
	public GuiComponent getMenu() {
		return menu;
	}
}