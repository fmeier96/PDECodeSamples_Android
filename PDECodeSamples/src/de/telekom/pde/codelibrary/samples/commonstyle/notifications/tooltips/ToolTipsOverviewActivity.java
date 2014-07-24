/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2014. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.commonstyle.notifications.tooltips;

import android.os.Bundle;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.color.PDEColor;


//----------------------------------------------------------------------------------------------------------------------
//  ToolTipsOverviewActivity
//----------------------------------------------------------------------------------------------------------------------



/**
 * @brief ToolTipsOverviewActivity for some sample tooltips.
 */
public class ToolTipsOverviewActivity extends PDEActionBarActivity {


    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = ToolTipsOverviewActivity.class.getName();


    /**
     * Create the Activity.
     * @param savedInstanceState The saved instance state to recreate.
     */
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.tooltips_overview_screen);

        // get the root view and set background color (different when darkstyle is on or of in library)
        RelativeLayout rootView = (RelativeLayout)findViewById(R.id.tooltips_overview_root);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());
    }

}
