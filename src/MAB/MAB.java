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

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class MAB extends MIDlet {
    public static MAB instance = null;
    private Menu displayable = null;

    /** Constructor **/
    public MAB() {
        instance = this;
        displayable = new Menu();
    }

    /** Methods **/
    public void Alert(String title, String text, AlertType alerttype) {
        Alert alert = new Alert(title, text, null, alerttype);
        alert.setTimeout(Alert.FOREVER);
        Display.getDisplay(this).setCurrent(alert);
        alert = null;
    }

    public void Display() {
        displayable = new Menu();
        Display.getDisplay(this).setCurrent(displayable);
    }

    public void Display(List list) {
        Display.getDisplay(this).setCurrent(list);
    }

    public void Display(Form form) {
        Display.getDisplay(this).setCurrent(form);
    }

    /** Quit the MIDlet */
    public static void quitApp() {
        instance.destroyApp(true);
        instance.notifyDestroyed();
        instance = null;
    }
    
    public void startApp() {
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
