/*
 * Copyright (c) 2000, 2025, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
/*
 * @author    IBM Corp.
 *
 * Copyright IBM Corp. 1999-2000.  All rights reserved.
 */


package javax.management.modelmbean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;

/**
* This exception is thrown when an XML formatted string is being parsed into ModelMBean objects
* or when XML formatted strings are being created from ModelMBean objects.
*
* It is also used to wrapper exceptions from XML parsers that may be used.
*
* <p>The <b>serialVersionUID</b> of this class is <code>3176664577895105181L</code>.
*
* @deprecated This class exists only to support XML parsing implemented privately in this module,
* in DescriptorSupport.  That feature is deprecated for removal.
*
* @since 1.5
*/
@Deprecated(since="25", forRemoval=true)
public class XMLParseException
extends Exception
{
    private static final long serialVersionUID = 3176664577895105181L;

    /**
     * Default constructor .
     */
    public  XMLParseException ()
    {
      super("XML Parse Exception.");
    }

    /**
     * Constructor taking a string.
     *
     * @param s the detail message.
     */
    public  XMLParseException (String s)
    {
      super("XML Parse Exception: " + s);
    }
    /**
     * Constructor taking a string and an exception.
     *
     * @param e the nested exception.
     * @param s the detail message.
     */
    public  XMLParseException (Exception e, String s)
    {
      super("XML Parse Exception: " + s + ":" + e.toString());
    }

    /**
     * Deserializes an {@link XMLParseException} from an {@link ObjectInputStream}.
     */
    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
      // New serial form ignores extra field "msgStr"
      in.defaultReadObject();
    }


    /**
     * Serializes an {@link XMLParseException} to an {@link ObjectOutputStream}.
     */
    private void writeObject(ObjectOutputStream out)
            throws IOException {
      out.defaultWriteObject();
    }
}
