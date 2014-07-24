/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2014. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.datavisualisation.usagebar;

import android.os.Bundle;
import android.widget.LinearLayout;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.color.PDEColor;


//----------------------------------------------------------------------------------------------------------------------
//  UsageBarOverviewActivity
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief UsageBarOverviewActivity just shows two simple bars (both available styles)
 */
public class UsageBarOverviewActivity extends PDEActionBarActivity {


    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = UsageBarFullColorsActivity.class.getName();


    /**
     * @brief Create the Activity.
     *
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.usagebar_overview_screen);

        // get root view and set background color
        LinearLayout rootView = (LinearLayout) findViewById(R.id.usagebar_root);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());
    }
}