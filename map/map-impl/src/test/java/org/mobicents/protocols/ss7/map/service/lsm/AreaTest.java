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

package org.mobicents.protocols.ss7.map.service.lsm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.mobicents.protocols.ss7.map.MapServiceFactoryImpl;
import org.mobicents.protocols.ss7.map.api.MapServiceFactory;
import org.mobicents.protocols.ss7.map.api.service.lsm.Area;
import org.mobicents.protocols.ss7.map.api.service.lsm.AreaType;

/**
 * @author amit bhayani
 *
 */
public class AreaTest {
	
	MapServiceFactory mapServiceFactory = new MapServiceFactoryImpl();

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testDecode() throws Exception {
		byte[] data = new byte[] { (byte)0xb0, 0x08, (byte) 0x80, 0x01, 0x05, (byte)0x81, 0x03, 0x09, 0x70, 0x71 };
		
		
		AsnInputStream asn = new AsnInputStream(data);
		int tag = asn.readTag();
		
		Area area = new AreaImpl();
		((AreaImpl)area).decodeAll(asn);
		
		assertNotNull(area.getAreaType());
		assertEquals(AreaType.utranCellId, area.getAreaType());
		
		assertNotNull(area.getAreaIdentification());
		assertTrue(Arrays.equals(new byte[]{0x09, 0x70, 0x71}, area.getAreaIdentification()));
		
	}

	@Test
	public void testEncode() throws Exception {
		
		byte[] data = new byte[] { (byte)0xb0, 0x08, (byte) 0x80, 0x01, 0x05, (byte)0x81, 0x03, 0x09, 0x70, 0x71 };

		Area area = new AreaImpl(AreaType.utranCellId, new byte[]{0x09, 0x70, 0x71});
		
		AsnOutputStream asnOS = new AsnOutputStream();
		((AreaImpl)area).encodeAll(asnOS, Tag.CLASS_CONTEXT_SPECIFIC, Tag.SEQUENCE);
		
		byte[] encodedData = asnOS.toByteArray();
		
		assertTrue(Arrays.equals(data, encodedData));

	}

}
