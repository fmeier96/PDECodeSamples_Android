/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2014. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.datavisualisation.usagebar;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.datavisualisation.PDEUsageEvent;
import de.telekom.pde.codelibrary.ui.components.datavisualisation.usagebar.PDEUsageBar;
import de.telekom.pde.codelibrary.ui.components.elementwrappers.PDETextView;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;

import java.util.ArrayList;


//----------------------------------------------------------------------------------------------------------------------
//  UsageBarAnimationExamplesActivity
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief UsageBarAnimationExamplesActivity
 */
public class UsageBarAnimationExamplesActivity extends PDEActionBarActivity {


    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = UsageBarAnimationExamplesActivity.class.getName();

    // screen variables
    private PDEButton mPercentButton_10;
    private PDEButton mPercentButton_90;
    private ArrayList<PDEUsageBar> mUsageBarArray;
    private PDEUsageBar mUsageBar1;
    private PDEUsageBar mUsageBar2;
    private PDEUsageBar mUsageBar3;
    private PDEUsageBar mUsageBar4;
    private PDETextView mResidualTextViewForSample1;
    private PDETextView mResidualTextViewForSample2;
    private PDETextView mResidualTextViewForSample3;
    private PDETextView mResidualTextViewForSample4;


    /**
     * @brief Create the Activity.
     *
     * Creates the  Activity where the user can select the type of PDEButton he wants.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set content view
        setContentView(R.layout.usagebar_animation_examples_screen);

        // get root view and set background color
        LinearLayout rootView = (LinearLayout) findViewById(R.id.usagebar_root);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        // create the usage bar and add to array list
        mUsageBarArray = new ArrayList<PDEUsageBar>();

        // get the buttons
        mPercentButton_10 = (PDEButton) findViewById(R.id.percent_button_10);
        mPercentButton_90 = (PDEButton) findViewById(R.id.percent_button_90);

        // get the usage bars, add listeners and add to array
        mUsageBar1 = (PDEUsageBar) findViewById(R.id.usageBarSample1);
        if (mUsageBar1 != null) {
            addListenerAndInsertInArray(mUsageBar1);
        }
        mUsageBar2 = (PDEUsageBar) findViewById(R.id.usageBarSample2);
        if (mUsageBar2 != null) {
            addListenerAndInsertInArray(mUsageBar2);
        }
        mUsageBar3 = (PDEUsageBar) findViewById(R.id.usageBarSample3);
        if (mUsageBar3 != null) {
            addListenerAndInsertInArray(mUsageBar3);
        }
        mUsageBar4 = (PDEUsageBar) findViewById(R.id.usageBarSample4);
        if (mUsageBar4 != null) {
            addListenerAndInsertInArray(mUsageBar4);
        }

        // get the residual text views
        mResidualTextViewForSample1 = (PDETextView) findViewById(R.id.residualTextForSample1);
        mResidualTextViewForSample2 = (PDETextView) findViewById(R.id.residualTextForSample2);
        mResidualTextViewForSample3 = (PDETextView) findViewById(R.id.residualTextForSample3);
        mResidualTextViewForSample4 = (PDETextView) findViewById(R.id.residualTextForSample4);

        // add button listeners
        mPercentButton_10.addListener(this,
                                      "onPercentButton10Clicked",
                                      PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        mPercentButton_90.addListener(this,
                                      "onPercentButton90Clicked",
                                      PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
    }


    /**
     * @brief Helper function to add usage bar into array and add event listener
     *
     * @param usageBar This usage bar is added to the array and calls the onUsageBarEventReceived function when the
     *                 EVENT_MASK_ACTION is occurs.
     */
    private void addListenerAndInsertInArray(PDEUsageBar usageBar) {
        usageBar.addListener(this, "onUsageBarEventReceived", PDEUsageEvent.EVENT_MASK_ACTION);
        mUsageBarArray.add(usageBar);
    }


    /**
     * @param event Received event
     * @brief Callback function to fill all usage bars to 10%
     */
    @SuppressWarnings("unused")
    public void onPercentButton10Clicked(PDEEvent event) {
        // check sender
        if (event.getSender()==mPercentButton_10) {
            // set all usage bars fill value to 10%
            for (PDEUsageBar tmpBar : mUsageBarArray) {
                tmpBar.setTargetFillValue(0.1f * tmpBar.getTotalFillValue());
            }
        }
    }


    /**
     * @param event Received event
     * @brief Callback function to fill all usage bars to 90%
     */
    @SuppressWarnings("unused")
    public void onPercentButton90Clicked(PDEEvent event) {
        // check sender
        if (event.getSender()==mPercentButton_90) {
            // set all usage bars fill value to 90%
            for (PDEUsageBar tmpBar : mUsageBarArray) {
                tmpBar.setTargetFillValue(0.9f * tmpBar.getTotalFillValue());
            }
        }
    }


    /**
     * @param event Received event
     * @brief Callback function to react on usage bar events
     */
    @SuppressWarnings("unused")
    public void onUsageBarEventReceived(PDEEvent event) {
        PDEUsageEvent usageEvent;

        usageEvent = (PDEUsageEvent) event;

        // check if the progress value changed
        if (usageEvent.isType(PDEUsageEvent.EVENT_ACTION_NEW_FILL_VALUE)) {
            // check which usage bar was the sender
            if (usageEvent.getSender() == mUsageBar1) {
                updateResidualText(mResidualTextViewForSample1,
                                   (int) Math.round(mUsageBar1.getTotalFillValue() - usageEvent.getCurrentFillValue()));
            } else if (usageEvent.getSender() == mUsageBar2) {
                updateResidualText(mResidualTextViewForSample2,
                                   (int) Math.round(mUsageBar2.getTotalFillValue() - usageEvent.getCurrentFillValue()));
            } else if (usageEvent.getSender() == mUsageBar3) {
                if (usageEvent.getCurrentFillValue() == mUsageBar3.getTargetFillValue()) {
                    updateResidualText(mResidualTextViewForSample3,
                                       (int) Math.round(
                                               mUsageBar3.getTotalFillValue() - usageEvent.getCurrentFillValue()));
                    showResidualText(mResidualTextViewForSample3);
                } else {
                    hideResidualText(mResidualTextViewForSample3, mUsageBar3);
                }
            } else if (usageEvent.getSender() == mUsageBar4) {
                if (usageEvent.getCurrentFillValue() == mUsageBar4.getTargetFillValue()) {
                    updateResidualText(mResidualTextViewForSample4,
                                       (int) Math.round(
                                               mUsageBar4.getTotalFillValue() - usageEvent.getCurrentFillValue()));
                    showResidualText(mResidualTextViewForSample4);
                } else {
                    hideResidualText(mResidualTextViewForSample4, mUsageBar4);
                }
            }
        }
    }


    /**
     * @param textView TextView to update
     * @param residual The Residual of the usage bar
     * @brief Private helper function to update the residual text for a specific textview
     */
    private void updateResidualText(PDETextView textView, int residual) {
        String residualText;

        residualText = "%d MB verbleibend";
        textView.setText(String.format(residualText, residual));
    }


    /**
     * @param textView The text view to hide
     * @param usageBar The usage bar of the text view
     * @brief Private helper function to hide the residual text views during animation
     */
    private void hideResidualText(PDETextView textView, PDEUsageBar usageBar) {
        // security
        if (usageBar == null || textView == null) return;
        if (usageBar.getCurrentFillValue() == usageBar.getTargetFillValue()) return;

        textView.setVisibility(View.INVISIBLE);
    }


    /**
     * @param textView The text view to show
     * @brief Private helper function to show the residual text view
     */
    private void showResidualText(PDETextView textView) {
        textView.setVisibility(View.VISIBLE);
    }
}