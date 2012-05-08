package de.mindcrimeilab.rednotebot.test;

import android.test.ActivityInstrumentationTestCase2;
import de.mindcrimeilab.rednotebot.RedNotebotActivity;

public class RedNotebotActivityTest extends ActivityInstrumentationTestCase2<RedNotebotActivity> {

    private RedNotebotActivity tActivity;  // the activity under test

    public RedNotebotActivityTest() {
        super("de.mindcrimeilab.rednotebot",RedNotebotActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        tActivity = this.getActivity();
    }

    public void testPreconditions() {
        assertNotNull(tActivity);
    }
}
