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
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.datavisualisation.usagebar.PDEUsageBar;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;

//----------------------------------------------------------------------------------------------------------------------
//  UsageBarTextVariationsActivity
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief UsageBarTextVariationsActivity
 */
public class UsageBarTextVariationsActivity extends PDEActionBarActivity {


    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = UsageBarTextVariationsActivity.class.getName();

    // screen variables
    private PDEButton mEmptyButton;
    private PDEButton mFillButton;
    private LinearLayout mUsageBarContainer;


    /**
     * @brief Create the Activity.
     *
     * Creates the  Activity where the user can select the type of PDEButton he wants.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.usagebar_text_variations_screen);

        // get root view and set background color
        LinearLayout rootView = (LinearLayout) findViewById(R.id.usagebar_root);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        // get the buttons
        mEmptyButton = (PDEButton) findViewById(R.id.empty_button);
        mFillButton = (PDEButton) findViewById(R.id.fill_button);

        // get the usage bar container
        mUsageBarContainer = (LinearLayout) findViewById(R.id.usagebar_container);

        // add button listeners
        mEmptyButton.addListener(this,
                                 "onEmptyButtonClicked",
                                 PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        mFillButton.addListener(this,
                                "onFillButtonClicked",
                                PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
    }


    /**
     * Callback function to empty all usage bars
     *
     * @param event Received event
     */
    @SuppressWarnings("unused")
    public void onEmptyButtonClicked(PDEEvent event) {
        // check sender
        if (event.getSender() == mEmptyButton) {
            int i, size;
            PDEUsageBar tmpUsageBar;

            size = mUsageBarContainer.getChildCount();
            for (i = 0; i < size; i++) {
                tmpUsageBar = ((PDEUsageBar) mUsageBarContainer.getChildAt(i));
                if (tmpUsageBar != null) {
                    tmpUsageBar.setTargetFillValue(0.0f);
                }
            }
        }
    }


    /**
     * Callback function to fill all usage bars
     *
     * @param event Received event
     */
    @SuppressWarnings("unused")
    public void onFillButtonClicked(PDEEvent event) {
        // check sender
        if (event.getSender() == mFillButton) {
            int i, size;
            PDEUsageBar tmpUsageBar;

            size = mUsageBarContainer.getChildCount();
            for (i = 0; i < size; i++) {
                tmpUsageBar = ((PDEUsageBar) mUsageBarContainer.getChildAt(i));
                if (tmpUsageBar != null) {
                    tmpUsageBar.setTargetFillValue(tmpUsageBar.getTotalFillValue());
                }
            }
        }
    }
}