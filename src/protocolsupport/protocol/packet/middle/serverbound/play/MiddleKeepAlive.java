package protocolsupport.protocol.packet.middle.serverbound.play;

import net.minecraft.server.v1_8_R3.Packet;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.packet.middle.ServerBoundMiddlePacket;
import protocolsupport.protocol.packet.middleimpl.PacketCreator;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public abstract class MiddleKeepAlive extends ServerBoundMiddlePacket {

	protected int keepAliveId;

	@Override
	public RecyclableCollection<? extends Packet<?>> toNative() throws Exception {
		PacketCreator creator = PacketCreator.create(ServerBoundPacket.PLAY_KEEP_ALIVE.get());
		creator.writeVarInt(keepAliveId);
		return RecyclableSingletonList.create(creator.create());
	}

}
