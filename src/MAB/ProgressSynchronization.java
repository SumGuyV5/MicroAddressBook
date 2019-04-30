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
import javax.microedition.io.*;
import java.io.*;

public class ProgressSynchronization extends Form implements CommandListener {
    private Gauge synchronizationGauge = new Gauge("", false, 300, 0);
    private Command backCommand = new Command("Back", Command.BACK, 1);

    private String url = "";
    private String username = "";
    private String password = "";

    private HttpConnection conn = null;
    private InputStream is = null;
    private OutputStream os = null;

    private Database database = null;

    /** Constructor **/
    public ProgressSynchronization(Database database) {
        super("Synchronizing with...");
        this.database = database;
        try {
            Init();
            Connect();
        }
        catch(IOException e) {
            MAB.instance.Alert("Error!", "There was a error when Synchronizing with " + url + ". Error Message " + e.getMessage(), AlertType.ERROR);
        }
        catch(Exception e) {
            MAB.instance.Alert("Error in ProgressSynchronization.", "There was a error when initializing ProgressSynchronization Class. Error Message "  + e.getMessage(), AlertType.ERROR);
        }
    }

    /** Methods **/
    /** Component initialization **/
    private void Init() throws Exception {
        // Set up this Displayable to listen to command events
        //stringItem1.setLayout(Item.LAYOUT_CENTER |Item.LAYOUT_VCENTER);
        //setCommandListener(this);
        // add the Exit command
        addCommand(backCommand);
        append(synchronizationGauge);
        //my_synchronizationGauge.setPreferredSize(200, 287);
    }

    private void Connect() throws IOException {
        synchronizationGauge.setLabel("Connecting.");

        String post = "?Username=" + username + "&Password=" + password + "&Submit=Submit";
        //String post = "?singleLineText=" + my_username + "&submit=Post";
        byte [] data = post.getBytes();

        //Setup
        //conn = (StreamConnection)Connector.open(my_url,Connector.READ_WRITE);
        conn = (HttpConnection)Connector.open(url, Connector.READ_WRITE);
        conn.setRequestMethod(HttpConnection.POST);
        conn.setRequestProperty("User-Agent", "Profile/MIDP-1.0 Configuration/CLDC-1.0");
        conn.setRequestProperty("Content-Language", "en-US");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        //Output
        Send(data);

        Read();
    }

    private void Disconnecting() throws IOException {
        synchronizationGauge.setLabel("Disconnecting.");
        conn.close();
    }

    private void Send(byte[] data) throws IOException {
       synchronizationGauge.setLabel("Sending Information.");

        //Output
        os = conn.openOutputStream();
        os.write(data);
        os.close();
    }

    private void Receive() throws IOException {
        synchronizationGauge.setLabel("Receiving Information.");
    }

    private void Read() throws IOException {
        StringBuffer data = new StringBuffer();

        is = conn.openInputStream();

        int ch;
        while ((ch = is.read()) != -1) {
          if (ch != '\n') {
            // Read the line a character at a time
            data.append( (char) ch);
          }
          else {

          }
        }
        is.close();
    }

    /** Handle command events **/
    public void commandAction(Command command, Displayable displayable) {
        /** @todo Add command handling code **/
        if (command == backCommand) {
            MAB.instance.Display();
        }
    }
}
