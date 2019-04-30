/************************************************************************
**  Program Name:   MAB (Micro Address Book)	      			**
**  Version Number: V1.0B                                     		**
**  Copyright (C):  March 2, 2010  Richard W. Allen			**
**  Date Started:   February 21, 2010	                    		**
**  Date Ended:     March 2, 2010	                 		**
**  Author:         Richard W. Allen                       		**
**  Webpage:        http://richard-allen.homelinux.com			**
**  IDE:            NetBeans 6.7.1					**
**  Compiler:	    Java 6 update 16					**
**  Langage:        Java ME						**
**  License:	    GNU GENERAL PUBLIC LICENSE Version 2		**
**		    see license.txt for for details	    		**
*************************************************************************/
package MAB;

/**
 * <p>Title: Micro Address Book</p>
 * <p>Description: Micro Address Book(MAB)</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: </p>
 * @author Richard Allen
 * @version 1.0B Demo
 */

import javax.microedition.lcdui.*;
import javax.microedition.rms.*;

public class Database {
    private RecordStore recordStore = null;

    /** Constructor **/
    public Database(String name) {
        // Open the record store using the specified name
        try {
            recordStore = Open(name);
            //recordStore.deleteRecordStore(name);
        }
        catch(RecordStoreException e) {
            MAB.instance.Alert("Error in Database " + e.getMessage(), "There was a error when trying to open " + name, AlertType.ERROR);
        }
    }

    /** Methods **/
    public RecordStore Open(String fileName) throws RecordStoreException {
        return RecordStore.openRecordStore(fileName, true);
    }

    public void Close() throws RecordStoreNotOpenException, RecordStoreException {
        // If the record store is empty, delete the file
        if (recordStore.getNumRecords() == 0) {
            String fileName = recordStore.getName();
            recordStore.closeRecordStore();
            RecordStore.deleteRecordStore(fileName);
        } else // Otherwise, close the record store
            recordStore.closeRecordStore();
    }

    public synchronized int AddContactRecord(String record) {
        // Convert the string record to an array of bytes
        byte[] bytes = record.getBytes();

        // Add the byte array to the record store
        try {
            return recordStore.addRecord(bytes, 0, bytes.length);
        }
        catch (RecordStoreException e) {
            MAB.instance.Alert("Error in Database", "There was a error when trying to add " + record + ". Error Message "  + e.getMessage(), AlertType.ERROR);
        }
        return -1;
    }

    public synchronized void DeleteContactRecord(int id) {
        // Delete the contact record from the record store
        try {
            recordStore.deleteRecord(id);
        }
        catch (RecordStoreException e) {
            MAB.instance.Alert("Error in Database", "There was a error when trying to delete " + id + ". Error Message "  + e.getMessage(), AlertType.ERROR);
        }
    }

    public synchronized RecordEnumeration EnumerateContactRecords() throws RecordStoreNotOpenException {
        return recordStore.enumerateRecords(null, null, false);
    }

    public byte[] getContactRecord(int id) {
        // Get the contact record from the record store
        try {
            return recordStore.getRecord(id);
        }
        catch (RecordStoreException e) {
            MAB.instance.Alert("Error in Database", "There was a error when trying to get " + id  + ". Error Message "  + e.getMessage(), AlertType.ERROR);
        }
        return null;
    }

    public synchronized void setContactRecord(int id, String record) {
        // Convert the string record to an array of bytes
        byte[] bytes = record.getBytes();

        // Set the record in the record store
        try {
            recordStore.setRecord(id, bytes, 0, bytes.length);
        }
        catch (RecordStoreException e) {
            MAB.instance.Alert("Error in Database", "There was a error when trying to set " + record + ". Error Message " + e.getMessage(), AlertType.ERROR);
        }
    }
}
