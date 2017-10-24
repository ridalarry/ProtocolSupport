package api;

import org.junit.Assert;

import junit.framework.TestCase;
import protocolsupport.api.ProtocolType;
import protocolsupport.api.ProtocolVersion;

public class ProtocolVersionTests extends TestCase {

	public void testIsAfter() {
		assertFalse(ProtocolVersion.MINECRAFT_1_6_2.isAfter(ProtocolVersion.MINECRAFT_1_6_2));
		assertFalse(ProtocolVersion.MINECRAFT_1_7_10.isAfter(ProtocolVersion.MINECRAFT_1_8));
	}

	public void testIsAfterOrEq() {
		assertTrue(ProtocolVersion.MINECRAFT_1_6_2.isAfterOrEq(ProtocolVersion.MINECRAFT_1_6_2));
		assertFalse(ProtocolVersion.MINECRAFT_1_7_10.isAfterOrEq(ProtocolVersion.MINECRAFT_1_8));
	}

	public void testIsBefore() {
		assertFalse(ProtocolVersion.MINECRAFT_1_6_2.isBefore(ProtocolVersion.MINECRAFT_1_6_2));
		assertTrue(ProtocolVersion.MINECRAFT_1_7_10.isBefore(ProtocolVersion.MINECRAFT_1_8));
	}

	public void testIsBeforeOrEq() {
		assertTrue(ProtocolVersion.MINECRAFT_1_6_2.isBeforeOrEq(ProtocolVersion.MINECRAFT_1_6_2));
		assertTrue(ProtocolVersion.MINECRAFT_1_7_10.isBeforeOrEq(ProtocolVersion.MINECRAFT_1_8));
	}

	public void testIsBetween() {
		assertTrue(ProtocolVersion.MINECRAFT_1_6_4.isBetween(ProtocolVersion.MINECRAFT_1_4_7, ProtocolVersion.MINECRAFT_1_7_10));
		assertTrue(ProtocolVersion.MINECRAFT_1_6_4.isBetween(ProtocolVersion.MINECRAFT_1_6_4, ProtocolVersion.MINECRAFT_1_7_10));
		assertTrue(ProtocolVersion.MINECRAFT_1_6_4.isBetween(ProtocolVersion.MINECRAFT_1_4_7, ProtocolVersion.MINECRAFT_1_6_4));
		assertFalse(ProtocolVersion.MINECRAFT_1_6_4.isBetween(ProtocolVersion.MINECRAFT_1_7_5, ProtocolVersion.MINECRAFT_1_8));
	}

	public void testNext() {
		assertTrue(ProtocolVersion.MINECRAFT_1_4_7.next() == ProtocolVersion.MINECRAFT_1_5_1);
		assertFalse(ProtocolVersion.MINECRAFT_1_6_2.next() == ProtocolVersion.MINECRAFT_1_7_10);
	}

	public void testPrevious() {
		assertTrue(ProtocolVersion.MINECRAFT_1_5_1.previous() == ProtocolVersion.MINECRAFT_1_4_7);
		assertFalse(ProtocolVersion.MINECRAFT_1_6_2.previous() == ProtocolVersion.MINECRAFT_1_7_10);
	}

	public void testGetAllBetween() {
		Assert.assertArrayEquals(ProtocolVersion.getAllBetween(ProtocolVersion.MINECRAFT_1_6_4, ProtocolVersion.MINECRAFT_1_7_10), new ProtocolVersion[] {
			ProtocolVersion.MINECRAFT_1_6_4, ProtocolVersion.MINECRAFT_1_7_5, ProtocolVersion.MINECRAFT_1_7_10
		});
		Assert.assertArrayEquals(ProtocolVersion.getAllBetween(ProtocolVersion.MINECRAFT_1_7_10, ProtocolVersion.MINECRAFT_1_6_4), new ProtocolVersion[] {
			ProtocolVersion.MINECRAFT_1_6_4, ProtocolVersion.MINECRAFT_1_7_5, ProtocolVersion.MINECRAFT_1_7_10
		});
	}

	public void testGetAllAfterI() {
		Assert.assertArrayEquals(
			ProtocolVersion.getAllAfter(ProtocolVersion.MINECRAFT_1_7_10),
			ProtocolVersion.getAllBetween(ProtocolVersion.MINECRAFT_1_7_10, ProtocolVersion.getLatest())
		);
	}

	public void testGetAllAfterE() {
		Assert.assertArrayEquals(
			ProtocolVersion.getAllAfter(ProtocolVersion.MINECRAFT_1_7_10),
			ProtocolVersion.getAllBetween(ProtocolVersion.MINECRAFT_1_7_10, ProtocolVersion.getLatest())
		);
		Assert.assertArrayEquals(ProtocolVersion.getAllAfter(ProtocolVersion.getLatest()), new ProtocolVersion[0]);
	}

	public void testGetAllBeforeI() {
		Assert.assertArrayEquals(
			ProtocolVersion.getAllBefore(ProtocolVersion.MINECRAFT_1_7_10),
			ProtocolVersion.getAllBetween(ProtocolVersion.MINECRAFT_1_7_10, ProtocolVersion.getOldest())
		);
	}

	public void testGetAllBeforeE() {
		Assert.assertArrayEquals(
			ProtocolVersion.getAllBefore(ProtocolVersion.MINECRAFT_1_7_10),
			ProtocolVersion.getAllBetween(ProtocolVersion.MINECRAFT_1_6_2, ProtocolVersion.getOldest())
		);
		Assert.assertArrayEquals(ProtocolVersion.getAllBefore(ProtocolVersion.getOldest()), new ProtocolVersion[0]);
	}

}
