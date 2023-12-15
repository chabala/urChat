package frontend;

import static org.testng.AssertJUnit.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.UIManager;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import urChatBasic.frontend.UserGUI;
import urChatBasic.frontend.utils.URColour;
import urChatBasic.backend.utils.URStyle;
import urChatBasic.base.Constants;
import urChatBasic.frontend.DriverGUI;

public class LAFTests
{
    DriverGUI testDriver;
    UserGUI testGUI;
    final String testProfileName = "testingprofile" + (new SimpleDateFormat("yyMMdd")).format(new Date());
    // final String testLAFName

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception
    {
        Reporter.log("Creating test gui", true);
        testDriver = new DriverGUI();
        DriverGUI.initLAFLoader();
        DriverGUI.createGUI();
        testGUI = DriverGUI.gui;
        Reporter.log("Setting profile to " + testProfileName, true);
        testGUI.setProfileName(testProfileName);
        testGUI.getClientSettings(true);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown () throws Exception
    {
        if(testGUI.getProfileName().equals(testProfileName))
            testGUI.deleteProfile();
    }

    @Test(description = "Check that changing the Look and Feel, also correctly changes the style of the text")
    public void changingLAFChangesStyle() throws Exception
    {
        // Get current LAF name
        String currentLAF = UIManager.getLookAndFeel().getClass().getName();

        if(!currentLAF.equals("com.sun.java.swing.plaf.motif.MotifLookAndFeel"))
            testGUI.setNewLAF("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

        URStyle newStyle = testGUI.getStyle();

        assertEquals("New Style should have the background colour of "+Constants.DEFAULT_BACKGROUND_STRING, URColour.hexEncode(UIManager.getColor(Constants.DEFAULT_BACKGROUND_STRING)), URColour.hexEncode(newStyle.getBackground()));
    }
}
