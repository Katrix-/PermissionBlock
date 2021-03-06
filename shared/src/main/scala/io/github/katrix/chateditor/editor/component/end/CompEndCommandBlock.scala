package io.github.katrix.chateditor.editor.component.end

import org.spongepowered.api.Sponge
import org.spongepowered.api.block.tileentity.TileEntityTypes
import org.spongepowered.api.data.key.Keys
import org.spongepowered.api.text.format.TextColors._
import org.spongepowered.api.world.{Location, World}

import io.github.katrix.chateditor.EditorPlugin
import io.github.katrix.chateditor.editor.Editor
import io.github.katrix.chateditor.editor.component.EndComponent
import io.github.katrix.katlib.helper.Implicits._

class CompEndCommandBlock(location: Location[World])(implicit plugin: EditorPlugin) extends EndComponent {

  override def end(editor: Editor): Option[Editor] =
    //No either as I can't be bothered with the ifs in the for comprehension
    editor.player.get match {
      case Some(player) =>
        location.getTileEntity.toOption match {
          case Some(tileEntity) if tileEntity.getType == TileEntityTypes.COMMAND_BLOCK =>
            val builtString = editor.text.builtString
            Sponge.getCommandManager.get(builtString.split(' ').head, player).toOption match {
              case Some(mapping) if mapping.getCallable.testPermission(player) =>
                val result = tileEntity.offer(Keys.COMMAND, builtString)
                if (result.isSuccessful) {
                  player.sendMessage(t"${GREEN}Set command for commandblock")
                  None
                } else {
                  tileEntity.undo(result)
                  player.sendMessage(t"${RED}Error when setting command for commandblock")
                  Some(editor)
                }
              case Some(_) =>
                player.sendMessage(t"${RED}You don't have the permission for that command")
                Some(editor)
              case None =>
                player.sendMessage(t"${RED}Command not found")
                Some(editor)
            }
          case None | Some(_) =>
            player.sendMessage(t"${RED}Commandblock at that location not found")
            Some(editor)
        }
      case None => None //If no player is found, just remove the editor and call it done
    }
}
