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
package nl.knokko.customitems.editor.set.recipe.result;

import java.util.Collection;

import nl.knokko.customitems.editor.set.ItemSet;
import nl.knokko.customitems.editor.set.item.CustomItem;
import nl.knokko.customitems.encoding.RecipeEncoding;
import nl.knokko.util.bits.BitInput;
import nl.knokko.util.bits.BitOutput;

public class CustomItemResult extends Result {
	
	private final CustomItem item;

	public CustomItemResult(CustomItem item, byte amount) {
		super(amount);
		this.item = item;
		initInfo();
	}
	
	public CustomItemResult(BitInput input, ItemSet set) {
		super(input);
		String name = input.readJavaString();
		Collection<CustomItem> items = set.getItems();
		for (CustomItem item : items) {
			if (item.getName().equals(name)) {
				this.item = item;
				initInfo();
				return;
			}
		}
		throw new IllegalArgumentException("There is no item with name " + name);
	}
	
	public CustomItem getItem() {
		return item;
	}

	@Override
	protected void saveOwn(BitOutput output) {
		output.addJavaString(item.getName());
	}

	@Override
	public byte getID() {
		return RecipeEncoding.Result.CUSTOM;
	}

	@Override
	protected String[] createInfo() {
		return new String[] {
				"Custom Item:",
				"Name: " + item.getName()
		};
	}

	@Override
	public String getString() {
		return item.getName();
	}

	@Override
	public Result amountClone(byte amount) {
		return new CustomItemResult(item, amount);
	}
}