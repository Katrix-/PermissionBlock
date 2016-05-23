/*
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
package io.github.katrix.permissionblock.editor.components

import org.spongepowered.api.entity.living.player.Player
import org.spongepowered.api.text.{Text, format}
import org.spongepowered.api.text.format.TextColors

import com.google.common.collect.ImmutableList

import io.github.katrix.permissionblock.editor.Editor

class CompTextCursor(editor: Editor, string: String) extends ComponentText(editor) {

	private val commandBuilder = new StringBuilder
	commandBuilder.append(string)
	private var _cursor = commandBuilder.length
	private var _select = _cursor

	def this(editor: Editor) {
		this(editor, "")
	}

	def addString(string: String): Unit = {
		commandBuilder.insert(_cursor, string)
		_cursor += string.length
	}

	def deleteCharacters(amount: Int): Unit = {
		commandBuilder.delete(_cursor, _cursor + amount)
		_cursor = validateCursorPos(_cursor)
	}

	override def pos: Int = _cursor

	override def pos_=(cursor: Int): Unit = _cursor = validateCursorPos(cursor)

	override def pos_+=(amount: Int): Unit = _cursor = validateCursorPos(_cursor + amount)

	override def pos_-=(amount: Int): Unit = _cursor = validateCursorPos(_cursor - amount)

	override def select: Int = _select

	override def select_=(selectPos: Int): Unit = _select = selectPos

	override def select_+=(amount: Int): Unit = _select = validateSelectPos(_select + amount)

	override def select_-=(amount: Int): Unit = _select = validateSelectPos(_select - amount)

	private def validateCursorPos(orig: Int): Int = validatePos(0, commandBuilder.length, orig)

	private def validateSelectPos(orig: Int): Int = validatePos(_cursor, commandBuilder.length, orig)

	private def validatePos(min: Int, max: Int, orig: Int): Int = {
		if(orig > max) {
			max
		}
		else if(orig < min) {
			min
		}
		else {
			orig
		}
	}

	override def builtString: String = commandBuilder.toString

	def sendFormatted(player: Player): Unit = {
		player.sendMessage(formatted.head)
	}

	def formatted: Seq[Text] = {
		val firstPart = commandBuilder.substring(0, _cursor)
		val selected = commandBuilder.substring(_cursor, if(_select == _cursor) _select else _select + 1)
		val secondPart = commandBuilder.substring(if(_select == _cursor) _select else _select + 1, commandBuilder.length)
		Seq(Text.of(firstPart, TextColors.BLUE, "[", selected, "]", TextColors.RESET, secondPart))
	}
}