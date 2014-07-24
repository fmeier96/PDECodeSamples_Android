/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2014. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.commonstyle.notifications.infoflags;

import android.os.Bundle;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.color.PDEColor;



//----------------------------------------------------------------------------------------------------------------------
//  InfoFlagsOverviewActivity
//----------------------------------------------------------------------------------------------------------------------



/**
 * @brief InfoFlagsOverviewActivity for some info flag samples.
 */
public class InfoFlagsOverviewActivity extends PDEActionBarActivity {


    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = InfoFlagsOverviewActivity.class.getName();


    /**
     * Create the Activity.
     * @param savedInstanceState The saved instance state to recreate
     */
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.infoflags_overview_screen);

        // get the root view and set background color (different when darkstyle is on or of in library)
        RelativeLayout rootView = (RelativeLayout)findViewById(R.id.infoflags_overview_root);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());
    }

}
