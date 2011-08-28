/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mobicents.protocols.ss7.map.dialog;

import java.util.Arrays;

import junit.framework.TestCase;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.ss7.map.Utils;
import org.mobicents.protocols.ss7.map.api.dialog.MAPUserAbortChoice;
import org.mobicents.protocols.ss7.map.api.dialog.ProcedureCancellationReason;
import org.mobicents.protocols.ss7.map.api.dialog.ResourceUnavailableReason;
import org.mobicents.protocols.ss7.map.primitives.MAPExtensionContainerTest;

/**
 * 
 * @author amit bhayani
 * @author sergey vetyutnev
 * 
 */
public class MAPUserAbortInfoTest extends TestCase {

	
// TODO: to Amit: please check this commented tests. They contradict the specification and my live traces 
//
//	@org.junit.Test
//	public void testProcedureCancellationReasonDecode() throws Exception {
//
//		// The raw data is hand made
//		byte[] data = new byte[] { (byte) 0xa4, 0x05, 0x03, 0x03, 0x0a, 0x01,
//				0x04 };
//
//		ByteArrayInputStream baIs = new ByteArrayInputStream(data);
//		AsnInputStream asnIs = new AsnInputStream(baIs);
//
//		int tag = asnIs.readTag();
//
//		MAPUserAbortInfoImpl mapUserAbortInfo = new MAPUserAbortInfoImpl();
//		mapUserAbortInfo.decode(asnIs);
//
//		MAPUserAbortChoice mapUserAbortChoice = mapUserAbortInfo
//				.getMAPUserAbortChoice();
//
//		assertNotNull(mapUserAbortChoice);
//
//		assertFalse(mapUserAbortChoice.isUserSpecificReason());
//		assertTrue(mapUserAbortChoice.isProcedureCancellationReason());
//		assertFalse(mapUserAbortChoice.isResourceUnavailableReason());
//		assertFalse(mapUserAbortChoice.isUserResourceLimitation());
//
//		ProcedureCancellationReason procdCancellReasn = mapUserAbortChoice
//				.getProcedureCancellationReason();
//		
//		assertNotNull(procdCancellReasn);
//		
//		assertEquals(procdCancellReasn, ProcedureCancellationReason.associatedProcedureFailure);
//
//	}
//
//	@org.junit.Test
//	public void testResourceUnavailableEncode() throws Exception {
//		MAPUserAbortInfoImpl mapUserAbortInfo = new MAPUserAbortInfoImpl();
//
//		MAPUserAbortChoiceImpl mapUserAbortChoice = new MAPUserAbortChoiceImpl();
//		mapUserAbortChoice
//				.setResourceUnavailableReason(ResourceUnavailableReason.longTermResourceLimitation);
//
//		mapUserAbortInfo.setMAPUserAbortChoice(mapUserAbortChoice);
//
//		AsnOutputStream asnOS = new AsnOutputStream();
//
//		mapUserAbortInfo.encode(asnOS);
//
//		byte[] data = asnOS.toByteArray();
//
//		System.out.println(Utils.dump(data, data.length, false));
//
//		assertTrue(Arrays.equals(new byte[] { (byte) 0xA4, 0x05, (byte) 0x02,
//				0x03, 0x0A, 0x01, 0x01 }, data));
//	}
//
//	@org.junit.Test
//	public void testProcedureCancellationReasonEncode() throws Exception {
//		MAPUserAbortInfoImpl mapUserAbortInfo = new MAPUserAbortInfoImpl();
//
//		MAPUserAbortChoiceImpl mapUserAbortChoice = new MAPUserAbortChoiceImpl();
//		mapUserAbortChoice
//				.setProcedureCancellationReason(ProcedureCancellationReason.associatedProcedureFailure);
//
//		mapUserAbortInfo.setMAPUserAbortChoice(mapUserAbortChoice);
//
//		AsnOutputStream asnOS = new AsnOutputStream();
//
//		mapUserAbortInfo.encode(asnOS);
//
//		byte[] data = asnOS.toByteArray();
//
//		System.out.println(Utils.dump(data, data.length, false));
//
//		assertTrue(Arrays.equals(new byte[] { (byte) 0xA4, 0x05, (byte) 0x03,
//				0x03, 0x0A, 0x01, 0x04 }, data));
//	}
	
	
	private byte[] getDataUserSpecificReason() {
		return new byte[] { (byte) 164, 2, (byte) 128, 0 };
	}

	private byte[] getUserResourceLimitationReason() {
		return new byte[] { (byte) 164, 2, (byte) 129, 0 };
	}

	private byte[] getResourceUnavailableReason() {
		return new byte[] { (byte) 164, 3, (byte) 130, 1, 0 };
	}

	private byte[] getProcedureCancellationReason() {
		return new byte[] { (byte) 164, 3, (byte) 131, 1, 4 };
//		return new byte[] { (byte) 0xA4, 0x05, (byte) 0x03, 0x03, 0x0A, 0x01, 0x04 };
	}

	private byte[] getDataFull() {
		return new byte[] { -92, 44, -125, 1, 3, 48, 39, -96, 32, 48, 10, 6, 3, 42, 3, 4, 11, 12, 13, 14, 15, 48, 5, 6, 3, 42, 3, 6, 48, 11, 6, 3, 42, 3, 5,
				21, 22, 23, 24, 25, 26, -95, 3, 31, 32, 33 };
	}
	
	@org.junit.Test
	public void testUserSpecificReasonDecode() throws Exception {

		// The raw data is hand made
		byte[] data = getDataUserSpecificReason();
		AsnInputStream asnIs = new AsnInputStream(data);

		int tag = asnIs.readTag();
		assertEquals(4, tag);

		MAPUserAbortInfoImpl mapUserAbortInfo = new MAPUserAbortInfoImpl();
		mapUserAbortInfo.decodeAll(asnIs);

		MAPUserAbortChoice mapUserAbortChoice = mapUserAbortInfo
				.getMAPUserAbortChoice();

		assertNotNull(mapUserAbortChoice);

		assertTrue(mapUserAbortChoice.isUserSpecificReason());
		assertFalse(mapUserAbortChoice.isProcedureCancellationReason());
		assertFalse(mapUserAbortChoice.isResourceUnavailableReason());
		assertFalse(mapUserAbortChoice.isUserResourceLimitation());

	}

	@org.junit.Test
	public void testUserSpecificReasonEncode() throws Exception {
		MAPUserAbortInfoImpl mapUserAbortInfo = new MAPUserAbortInfoImpl();
		MAPUserAbortChoiceImpl mapUserAbortChoice = new MAPUserAbortChoiceImpl();
		mapUserAbortChoice.setUserSpecificReason();

		mapUserAbortInfo.setMAPUserAbortChoice(mapUserAbortChoice);

		AsnOutputStream asnOS = new AsnOutputStream();

		mapUserAbortInfo.encodeAll(asnOS);

		byte[] data = asnOS.toByteArray();

		System.out.println(Utils.dump(data, data.length, false));

		assertTrue(Arrays.equals(getDataUserSpecificReason(), data));
	}
	
	@org.junit.Test
	public void testUserResourceLimitationDecode() throws Exception {

		// The raw data is hand made
		byte[] data = getUserResourceLimitationReason();
		AsnInputStream asnIs = new AsnInputStream(data);

		int tag = asnIs.readTag();
		assertEquals(4, tag);

		MAPUserAbortInfoImpl mapUserAbortInfo = new MAPUserAbortInfoImpl();
		mapUserAbortInfo.decodeAll(asnIs);

		MAPUserAbortChoice mapUserAbortChoice = mapUserAbortInfo
				.getMAPUserAbortChoice();

		assertNotNull(mapUserAbortChoice);

		assertFalse(mapUserAbortChoice.isUserSpecificReason());
		assertFalse(mapUserAbortChoice.isProcedureCancellationReason());
		assertFalse(mapUserAbortChoice.isResourceUnavailableReason());
		assertTrue(mapUserAbortChoice.isUserResourceLimitation());

	}

	@org.junit.Test
	public void testUserResourceLimitationEncode() throws Exception {
		MAPUserAbortInfoImpl mapUserAbortInfo = new MAPUserAbortInfoImpl();
		MAPUserAbortChoiceImpl mapUserAbortChoice = new MAPUserAbortChoiceImpl();
		mapUserAbortChoice.setUserResourceLimitation();

		mapUserAbortInfo.setMAPUserAbortChoice(mapUserAbortChoice);

		AsnOutputStream asnOS = new AsnOutputStream();

		mapUserAbortInfo.encodeAll(asnOS);

		byte[] data = asnOS.toByteArray();

		System.out.println(Utils.dump(data, data.length, false));

		assertTrue(Arrays.equals(getUserResourceLimitationReason(), data));
	}
	
	@org.junit.Test
	public void testResourceUnavailableDecode() throws Exception {

		// The raw data is hand made
		byte[] data = getResourceUnavailableReason();
		AsnInputStream asnIs = new AsnInputStream(data);

		int tag = asnIs.readTag();
		assertEquals(4, tag);

		MAPUserAbortInfoImpl mapUserAbortInfo = new MAPUserAbortInfoImpl();
		mapUserAbortInfo.decodeAll(asnIs);

		MAPUserAbortChoice mapUserAbortChoice = mapUserAbortInfo
				.getMAPUserAbortChoice();

		assertNotNull(mapUserAbortChoice);

		assertFalse(mapUserAbortChoice.isUserSpecificReason());
		assertFalse(mapUserAbortChoice.isProcedureCancellationReason());
		assertTrue(mapUserAbortChoice.isResourceUnavailableReason());
		assertFalse(mapUserAbortChoice.isUserResourceLimitation());
		assertEquals(ResourceUnavailableReason.shortTermResourceLimitation, mapUserAbortChoice.getResourceUnavailableReason());

	}

	@org.junit.Test
	public void testResourceUnavailableEncode() throws Exception {
		MAPUserAbortInfoImpl mapUserAbortInfo = new MAPUserAbortInfoImpl();

		MAPUserAbortChoiceImpl mapUserAbortChoice = new MAPUserAbortChoiceImpl();
		mapUserAbortChoice
				.setResourceUnavailableReason(ResourceUnavailableReason.shortTermResourceLimitation);

		mapUserAbortInfo.setMAPUserAbortChoice(mapUserAbortChoice);

		AsnOutputStream asnOS = new AsnOutputStream();

		mapUserAbortInfo.encodeAll(asnOS);

		byte[] data = asnOS.toByteArray();

		System.out.println(Utils.dump(data, data.length, false));

		assertTrue(Arrays.equals(getResourceUnavailableReason(), data));
	}
	
	@org.junit.Test
	public void testProcedureCancellationReasonDecode() throws Exception {

		// The raw data is hand made
		byte[] data = getProcedureCancellationReason();

		AsnInputStream asnIs = new AsnInputStream(data);

		int tag = asnIs.readTag();
		assertEquals(4, tag);

		MAPUserAbortInfoImpl mapUserAbortInfo = new MAPUserAbortInfoImpl();
		mapUserAbortInfo.decodeAll(asnIs);

		MAPUserAbortChoice mapUserAbortChoice = mapUserAbortInfo
				.getMAPUserAbortChoice();

		assertNotNull(mapUserAbortChoice);

		assertFalse(mapUserAbortChoice.isUserSpecificReason());
		assertTrue(mapUserAbortChoice.isProcedureCancellationReason());
		assertFalse(mapUserAbortChoice.isResourceUnavailableReason());
		assertFalse(mapUserAbortChoice.isUserResourceLimitation());

		ProcedureCancellationReason procdCancellReasn = mapUserAbortChoice
				.getProcedureCancellationReason();
		
		assertNotNull(procdCancellReasn);
		
		assertEquals(procdCancellReasn, ProcedureCancellationReason.associatedProcedureFailure);

	}

	@org.junit.Test
	public void testProcedureCancellationReasonEncode() throws Exception {
		MAPUserAbortInfoImpl mapUserAbortInfo = new MAPUserAbortInfoImpl();

		MAPUserAbortChoiceImpl mapUserAbortChoice = new MAPUserAbortChoiceImpl();
		mapUserAbortChoice
				.setProcedureCancellationReason(ProcedureCancellationReason.associatedProcedureFailure);

		mapUserAbortInfo.setMAPUserAbortChoice(mapUserAbortChoice);

		AsnOutputStream asnOS = new AsnOutputStream();

		mapUserAbortInfo.encodeAll(asnOS);

		byte[] data = asnOS.toByteArray();

		System.out.println(Utils.dump(data, data.length, false));

		assertTrue(Arrays.equals(getProcedureCancellationReason(), data));
	}
	
	@org.junit.Test
	public void testFullDecode() throws Exception {

		// The raw data is hand made
		byte[] data = getDataFull();

		AsnInputStream asnIs = new AsnInputStream(data);

		int tag = asnIs.readTag();
		assertEquals(4, tag);

		MAPUserAbortInfoImpl mapUserAbortInfo = new MAPUserAbortInfoImpl();
		mapUserAbortInfo.decodeAll(asnIs);

		MAPUserAbortChoice mapUserAbortChoice = mapUserAbortInfo
				.getMAPUserAbortChoice();

		assertNotNull(mapUserAbortChoice);

		assertFalse(mapUserAbortChoice.isUserSpecificReason());
		assertTrue(mapUserAbortChoice.isProcedureCancellationReason());
		assertFalse(mapUserAbortChoice.isResourceUnavailableReason());
		assertFalse(mapUserAbortChoice.isUserResourceLimitation());

		ProcedureCancellationReason procdCancellReasn = mapUserAbortChoice
				.getProcedureCancellationReason();
		
		assertNotNull(procdCancellReasn);
		
		assertEquals(procdCancellReasn, ProcedureCancellationReason.callRelease);

		assertTrue(MAPExtensionContainerTest.CheckTestExtensionContainer(mapUserAbortInfo.getExtensionContainer()));
	}

	@org.junit.Test
	public void testFullEncode() throws Exception {
		MAPUserAbortInfoImpl mapUserAbortInfo = new MAPUserAbortInfoImpl();

		MAPUserAbortChoiceImpl mapUserAbortChoice = new MAPUserAbortChoiceImpl();
		mapUserAbortChoice.setProcedureCancellationReason(ProcedureCancellationReason.callRelease);

		mapUserAbortInfo.setMAPUserAbortChoice(mapUserAbortChoice);
		mapUserAbortInfo.setExtensionContainer(MAPExtensionContainerTest.GetTestExtensionContainer());

		AsnOutputStream asnOS = new AsnOutputStream();

		mapUserAbortInfo.encodeAll(asnOS);

		byte[] data = asnOS.toByteArray();

		System.out.println(Utils.dump(data, data.length, false));

		assertTrue(Arrays.equals(getDataFull(), data));
	}
}
