package de.mindcrimeilab.rednotebot.test;

import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;
import de.mindcrimeilab.rednotebot.R;
import de.mindcrimeilab.rednotebot.RedNotebotActivity;

public class RedNotebotActivityTest extends ActivityInstrumentationTestCase2<RedNotebotActivity> {

    private RedNotebotActivity testee;  // the activity under test

    public RedNotebotActivityTest() {
        super("de.mindcrimeilab.rednotebot",RedNotebotActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        testee = this.getActivity();
    }

    public void testPreconditions() {
        assertNotNull(testee);
        Fragment dayListFragment = testee.getSupportFragmentManager().findFragmentById(R.id.daylist);
        assertNotNull(dayListFragment);
    }
}
