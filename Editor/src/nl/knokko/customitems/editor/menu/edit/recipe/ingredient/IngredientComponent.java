package nl.knokko.customitems.editor.menu.edit.recipe.ingredient;

import java.awt.Color;

import nl.knokko.customitems.editor.set.recipe.ingredient.Ingredient;
import nl.knokko.customitems.editor.set.recipe.ingredient.NoIngredient;
import nl.knokko.gui.component.text.TextButton;
import nl.knokko.gui.util.TextBuilder.Properties;

public class IngredientComponent extends TextButton {
	
	public static final Properties PROPS = Properties.createLabel(Color.BLACK, new Color(255, 150, 0), 512, 128);
	public static final Properties HOVER_PROPS = Properties.createLabel(Color.BLUE, new Color(255, 200, 0), 512, 128);
	
	private Ingredient current;

	public IngredientComponent(Ingredient original) {
		super(original.toString(), PROPS, HOVER_PROPS, null);
		this.clickAction = () -> {
			state.getWindow().setMainComponent(new IngredientView(this));
		};
		current = original;
	}
	
	public IngredientComponent() {
		this(new NoIngredient());
	}
	
	public void setIngredient(Ingredient ingredient) {
		current = ingredient;
		setText(current.toString());
	}
	
	public Ingredient getIngredient() {
		return current;
	}
}