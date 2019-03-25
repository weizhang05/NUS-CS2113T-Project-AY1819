package seedu.address.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import guitests.guihandles.ResultDisplayHandle;

public class TextResultDisplayTest extends GuiUnitTest {

    private TextResultDisplay textResultDisplay;
    private ResultDisplayHandle resultDisplayHandle;

    @Before
    public void setUp() {
        textResultDisplay = new TextResultDisplay();
        uiPartRule.setUiPart(textResultDisplay);

        resultDisplayHandle = new ResultDisplayHandle(getChildNode(textResultDisplay.getRoot(),
                ResultDisplayHandle.RESULT_DISPLAY_ID));
    }

    @Test
    public void display() {
        // default result text
        guiRobot.pauseForHuman();
        assertEquals("", resultDisplayHandle.getText());

        // new result received
        guiRobot.interact(() -> textResultDisplay.setFeedbackToUser("Dummy feedback to user"));
        guiRobot.pauseForHuman();
        assertEquals("Dummy feedback to user", resultDisplayHandle.getText());
    }
}
