package protocolsupport.protocol.packet.middle.clientbound.play;

import java.io.IOException;

import protocolsupport.protocol.PacketDataSerializer;

public abstract class MiddleEntityRelMove<T> extends MiddleEntity<T> {

	protected int relX;
	protected int relY;
	protected int relZ;
	protected boolean onGround;

	@Override
	public void readFromServerData(PacketDataSerializer serializer) throws IOException {
		super.readFromServerData(serializer);
		relX = serializer.readByte();
		relY = serializer.readByte();
		relZ = serializer.readByte();
		onGround = serializer.readBoolean();
	}

}
