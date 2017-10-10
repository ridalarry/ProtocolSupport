package protocolsupport.protocol.packet.middle.serverbound.play;

import net.minecraft.server.v1_8_R3.Packet;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.packet.middle.ServerBoundMiddlePacket;
import protocolsupport.protocol.packet.middleimpl.PacketCreator;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public abstract class MiddlePlayerAbilities extends ServerBoundMiddlePacket {

	protected int flags;
	protected float flySpeed;
	protected float walkSpeed;

	@Override
	public RecyclableCollection<? extends Packet<?>> toNative() throws Exception {
		PacketCreator creator = PacketCreator.create(ServerBoundPacket.PLAY_ABILITIES.get());
		creator.writeByte(flags);
		creator.writeFloat(flySpeed);
		creator.writeFloat(walkSpeed);
		return RecyclableSingletonList.create(creator.create());
	}

}
