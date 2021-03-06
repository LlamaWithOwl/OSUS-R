//==============================================================================
// This software is part of the Open Standard for Unattended Sensors (OSUS)
// reference implementation (OSUS-R).
//
// To the extent possible under law, the author(s) have dedicated all copyright
// and related and neighboring rights to this software to the public domain
// worldwide. This software is distributed without any warranty.
//
// You should have received a copy of the CC0 Public Domain Dedication along
// with this software. If not, see
// <http://creativecommons.org/publicdomain/zero/1.0/>.
//==============================================================================
package edu.udayton.udri.asset.raspi.pir;

import aQute.bnd.annotation.metatype.Meta.OCD;
import mil.dod.th.core.ConfigurationConstants;

/**
 * Holds the attributes for a Raspberry Pi PIR.
 * 
 * @author Nathaniel Rosenwald
 */
@OCD (description = ConfigurationConstants.PARTIAL_OBJECT_CLASS_DEFINITION)
public interface RasPIRAssetAttributes
{
    //The asset currently does not support any attributes.
}