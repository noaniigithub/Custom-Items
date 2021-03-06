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

import nl.knokko.customitems.editor.menu.edit.EditProps;
import nl.knokko.customitems.item.CustomItemType;
import nl.knokko.gui.color.GuiColor;
import nl.knokko.gui.component.GuiComponent;
import nl.knokko.gui.component.menu.GuiMenu;
import nl.knokko.gui.component.text.TextButton;

public class SelectItemType extends GuiMenu {
	
	private final ReturnAction returnAction;
	private final GuiComponent returnMenu;
	private final CustomItemType.Category category;
	
	public SelectItemType(GuiComponent returnMenu, ReturnAction returnAction, CustomItemType.Category category) {
		this.returnMenu = returnMenu;
		this.returnAction = returnAction;
		this.category = category;
	}
	
	@Override
	public GuiColor getBackgroundColor() {
		return EditProps.BACKGROUND;
	}

	@Override
	protected void addComponents() {
		addComponent(new TextButton("Cancel", EditProps.CANCEL_BASE, EditProps.CANCEL_HOVER, () -> {
			state.getWindow().setMainComponent(returnMenu);
		}), 0.1f, 0.6f, 0.3f, 0.7f);
		CustomItemType[] types = CustomItemType.values();
		int index = 0;
		for(CustomItemType type : types) {
			if (type.canServe(category)) {
				addComponent(new TextButton(type.toString(), EditProps.SELECT_BASE, EditProps.SELECT_HOVER, () -> {
					returnAction.onSelect(type);
					state.getWindow().setMainComponent(returnMenu);
				}), 0.5f, 0.9f - index * 0.1f, 0.8f, 1 - index * 0.1f);
				index++;
			}
		}
	}
	
	public static interface ReturnAction {
		
		void onSelect(CustomItemType type);
	}
}