package protocolsupport.protocol.packet.middleimpl.serverbound.play.v_4_5_6_7;

import java.io.IOException;

import net.minecraft.server.v1_8_R3.BlockPosition;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.serverbound.play.MiddleBlockPlace;

public class BlockPlace extends MiddleBlockPlace {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) throws IOException {
		position = new BlockPosition(serializer.readInt(), serializer.readUnsignedByte(), serializer.readInt());
		face = serializer.readUnsignedByte();
		itemstack = serializer.readItemStack();
		cX = serializer.readUnsignedByte();
		cY = serializer.readUnsignedByte();
		cZ = serializer.readUnsignedByte();
	}

}
