/**
 * This file is part of PermissionBlock, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2016 Katrix
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.katrix_.permissionblock.nbt;

import io.github.katrix_.permissionblock.helper.LogHelper;

public class NBTString extends NBTTag {

	private String value;

	public NBTString(String value) {
		if(value == null) {
			value = "";
			LogHelper.error("NBT created with empty string. String cannot be empty");
		}

		this.value = value;
	}

	public NBTString() {
		this("");
	}

	public String get() {
		return value;
	}

	public void set(String value) {
		if(value == null) {
			value = "";
			LogHelper.error("NBT set to empty string. String cannot be empty");
		}

		this.value = value;
	}

	@Override
	public NBTTag copy() {
		return new NBTString(value);
	}

	@Override
	public NBTType getType() {
		return NBTType.TAG_STRING;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		NBTString nbtString = (NBTString)o;

		return value.equals(nbtString.value);

	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public String toString() {
		return "\"" + value + "\"";
	}
}