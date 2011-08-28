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

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.mobicents.protocols.ss7.map.api.MAPException;
import org.mobicents.protocols.ss7.map.api.MAPParsingComponentException;
import org.mobicents.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.mobicents.protocols.ss7.map.api.primitives.USSDString;
import org.mobicents.protocols.ss7.map.api.service.lsm.LCSFormatIndicator;
import org.mobicents.protocols.ss7.map.api.service.lsm.LCSRequestorID;
import org.mobicents.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.mobicents.protocols.ss7.map.primitives.USSDStringImpl;

/**
 * @author amit bhayani
 * 
 */
public class LCSRequestorIDImpl implements LCSRequestorID, MAPAsnPrimitive {
	
	private static final int _TAG_DATA_CODING_SCHEME = 0;
	private static final int _TAG_NAME_STRING = 1;
	private static final int _TAG_LCS_FORMAT_INDICATOR = 2;

	private byte dataCodingScheme;
	private USSDString requestorIDString;
	private LCSFormatIndicator lcsFormatIndicator;

	/**
	 * 
	 */
	public LCSRequestorIDImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param dataCodingScheme
	 * @param requestorIDString
	 * @param lcsFormatIndicator
	 */
	public LCSRequestorIDImpl(byte dataCodingScheme, USSDString requestorIDString, LCSFormatIndicator lcsFormatIndicator) {
		super();
		this.dataCodingScheme = dataCodingScheme;
		this.requestorIDString = requestorIDString;
		this.lcsFormatIndicator = lcsFormatIndicator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mobicents.protocols.ss7.map.api.service.lsm.LCSRequestorID#
	 * getDataCodingScheme()
	 */
	@Override
	public byte getDataCodingScheme() {
		return this.dataCodingScheme;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mobicents.protocols.ss7.map.api.service.lsm.LCSRequestorID#
	 * getRequestorIDString()
	 */
	@Override
	public USSDString getRequestorIDString() {
		return this.requestorIDString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mobicents.protocols.ss7.map.api.service.lsm.LCSRequestorID#
	 * getLCSFormatIndicator()
	 */
	@Override
	public LCSFormatIndicator getLCSFormatIndicator() {
		return this.lcsFormatIndicator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.map.api.primitives.MAPAsnPrimitive#getTag()
	 */
	@Override
	public int getTag() throws MAPException {
		return Tag.SEQUENCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.map.api.primitives.MAPAsnPrimitive#getTagClass
	 * ()
	 */
	@Override
	public int getTagClass() {
		return Tag.CLASS_UNIVERSAL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.map.api.primitives.MAPAsnPrimitive#getIsPrimitive
	 * ()
	 */
	@Override
	public boolean getIsPrimitive() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.map.api.primitives.MAPAsnPrimitive#decodeAll
	 * (org.mobicents.protocols.asn.AsnInputStream)
	 */
	@Override
	public void decodeAll(AsnInputStream ansIS) throws MAPParsingComponentException {
		try {
			int length = ansIS.readLength();
			this._decode(ansIS, length);
		} catch (IOException e) {
			throw new MAPParsingComponentException("IOException when decoding LCSRequestorID: " + e.getMessage(), e,
					MAPParsingComponentExceptionReason.MistypedParameter);
		} catch (AsnException e) {
			throw new MAPParsingComponentException("AsnException when decoding LCSRequestorID: " + e.getMessage(), e,
					MAPParsingComponentExceptionReason.MistypedParameter);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.map.api.primitives.MAPAsnPrimitive#decodeData
	 * (org.mobicents.protocols.asn.AsnInputStream, int)
	 */
	@Override
	public void decodeData(AsnInputStream ansIS, int length) throws MAPParsingComponentException {
		try {
			this._decode(ansIS, length);
		} catch (IOException e) {
			throw new MAPParsingComponentException("IOException when decoding LCSRequestorID: " + e.getMessage(), e,
					MAPParsingComponentExceptionReason.MistypedParameter);
		} catch (AsnException e) {
			throw new MAPParsingComponentException("AsnException when decoding LCSRequestorID: " + e.getMessage(), e,
					MAPParsingComponentExceptionReason.MistypedParameter);
		}
	}

	private void _decode(AsnInputStream asnIS, int length) throws MAPParsingComponentException, IOException, AsnException {

		AsnInputStream ais = asnIS.readSequenceStreamData(length);

		int tag = ais.readTag();

		// Decode mandatory dataCodingScheme [0] USSD-DataCodingScheme,
		if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !ais.isTagPrimitive() || tag != _TAG_DATA_CODING_SCHEME) {
			throw new MAPParsingComponentException(
					"Error while decoding LCSRequestorID: Parameter 0[dataCodingScheme [0] USSD-DataCodingScheme] bad tag class, tag or not primitive",
					MAPParsingComponentExceptionReason.MistypedParameter);
		}

		int length1 = ais.readLength();
		this.dataCodingScheme = ais.readOctetStringData(length1)[0];

		tag = ais.readTag();

		// Decode mandatory nameString [1] NameString,
		if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !ais.isTagPrimitive() || tag != _TAG_NAME_STRING) {
			throw new MAPParsingComponentException(
					"Error while decoding LCSRequestorID: Parameter 1[requestorIDString [1] RequestorIDString] bad tag class, tag or not primitive",
					MAPParsingComponentExceptionReason.MistypedParameter);
		}

		this.requestorIDString = new USSDStringImpl();
		((USSDStringImpl)this.requestorIDString).decodeAll(ais);

		while (true) {
			if (ais.available() == 0)
				break;

			tag = ais.readTag();
			switch (tag) {
			case _TAG_LCS_FORMAT_INDICATOR:
				// Decode lcs-FormatIndicator [2] LCS-FormatIndicator OPTIONAL
				if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !ais.isTagPrimitive()) {
					throw new MAPParsingComponentException(
							"Error while decoding LCSRequestorID: Parameter 2[lcs-FormatIndicator [2] LCS-FormatIndicator OPTIONAL] bad tag class, tag or not primitive",
							MAPParsingComponentExceptionReason.MistypedParameter);
				}
				length1 = ais.readLength();
				this.lcsFormatIndicator = LCSFormatIndicator.getLCSFormatIndicator((int) ais.readIntegerData(length1));
				break;

			default:
				// throw new
				// MAPParsingComponentException("Decoding LCSClientExternalID failed. Expected externalAddress [0] or extensionContainer [1] but found "
				// + p.getTag(),
				// MAPParsingComponentExceptionReason.MistypedParameter);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.map.api.primitives.MAPAsnPrimitive#encodeAll
	 * (org.mobicents.protocols.asn.AsnOutputStream)
	 */
	@Override
	public void encodeAll(AsnOutputStream asnOs) throws MAPException {
		this.encodeAll(asnOs, Tag.CLASS_UNIVERSAL, Tag.SEQUENCE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.map.api.primitives.MAPAsnPrimitive#encodeAll
	 * (org.mobicents.protocols.asn.AsnOutputStream, int, int)
	 */
	@Override
	public void encodeAll(AsnOutputStream asnOs, int tagClass, int tag) throws MAPException {
		try {
			asnOs.writeTag(tagClass, false, tag);
			int pos = asnOs.StartContentDefiniteLength();
			this.encodeData(asnOs);
			asnOs.FinalizeContent(pos);
		} catch (AsnException e) {
			throw new MAPException("AsnException when encoding ProcessUnstructuredSSRequestIndication", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.map.api.primitives.MAPAsnPrimitive#encodeData
	 * (org.mobicents.protocols.asn.AsnOutputStream)
	 */
	@Override
	public void encodeData(AsnOutputStream asnOs) throws MAPException {

		if (this.requestorIDString == null)
			throw new MAPException("nameString must not be null");

		try {
			asnOs.writeOctetString(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_DATA_CODING_SCHEME, new byte[] { this.dataCodingScheme });

			((USSDStringImpl)this.requestorIDString).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_NAME_STRING);

			if (this.lcsFormatIndicator != null) {
				asnOs.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_LCS_FORMAT_INDICATOR, this.lcsFormatIndicator.getIndicator());
			}
		} catch (IOException e) {
			throw new MAPException("IOException when encoding ProcessUnstructuredSSRequestIndication", e);
		} catch (AsnException e) {
			throw new MAPException("AsnException when encoding ProcessUnstructuredSSRequestIndication", e);
		}
	}
}
