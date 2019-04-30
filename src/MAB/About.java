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

public class About extends Form implements CommandListener {
    private Command backCommand = new Command("Back", Command.BACK, 1);

    private final ImageItem image = new ImageItem("", null, ImageItem.LAYOUT_CENTER, "");

    private final StringItem stringItem0 = new StringItem("Copyright (c) 2010 License: ", "GNU GENERAL PUBLIC LICENSE Version 2");
    private final StringItem stringItem1 = new StringItem("WebSite Address: ", "http://richard-allen.homelinux.com/");
    private final StringItem stringItem2 = new StringItem("Micro Address Book Created By ", "Richard Allen");


    private HelpOptions helpOptions = null;

    /** Constructor **/
    public About(HelpOptions help) {
        super("About Micro Address Book.");
        helpOptions = help;
        try {
            Init();
        }
        catch(Exception e) {
            MAB.instance.Alert("Error in About", "There was a error when initializing About Class. Error Message "  + e.getMessage(), AlertType.ERROR);
        }
    }

    /** Methods **/
    /** Component initialization **/
    private void Init() throws Exception {
        // Set up this Displayable to listen to command events
        image.setImage(Image.createImage("/MAB/about.png"));
        setCommandListener(this);
        // add the Exit command
        addCommand(backCommand);

        // Displays the Image and Text
        this.append(image);
        this.append(stringItem0);
        this.append(stringItem1);
        this.append(stringItem2);
    }

    /** Handle command event **/
    public void commandAction(Command command, Displayable displayable) {
        // Send user back to help options screen
        /** @todo Add command handling code **/
        if(command == backCommand)
            MAB.instance.Display(helpOptions);
    }
}
