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

import java.util.Arrays;

public class NBTByteArray extends NBTTag {

	private byte[] values;

	public NBTByteArray(byte[] values) {
		this.values = values;
	}

	public byte[] get() {
		return values;
	}

	@Override
	public NBTTag copy() {
		byte[] bytes = new byte[values.length];
		System.arraycopy(values, 0, bytes, 0, values.length);
		return new NBTByteArray(bytes);
	}

	@Override
	public NBTType getType() {
		return NBTType.TAG_BYTE_ARRAY;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		NBTByteArray that = (NBTByteArray)o;

		return Arrays.equals(values, that.values);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(values);
	}

	//Is this correct? It's what minecraft uses.
	@Override
	public String toString() {
		return "[" + values.length + " bytes]";
	}
}