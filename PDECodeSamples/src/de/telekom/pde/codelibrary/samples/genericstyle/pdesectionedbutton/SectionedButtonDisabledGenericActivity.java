/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.genericstyle.pdesectionedbutton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;

import java.util.Locale;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.sectionedbuttons.PDESectionedButton;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;

//----------------------------------------------------------------------------------------------------------------------
//  SectionedButtonDisabledGenericActivity
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief SectionedButtonDisabledGenericActivity for some sample of disabled sectioned button.
 */
public class SectionedButtonDisabledGenericActivity extends PDEActionBarActivity {


    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = SectionedButtonDisabledGenericActivity.class.getName();


    // screen variables
    private PDESectionedButton mSectionedButton = null;
    private PDEButton mButtonCheckboxDisableAll = null;
    private PDEButton mButtonCheckboxDisableFirstSection = null;
    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;


    /**
     * @param savedInstanceState The saved instance state to recreate.
     * @brief Create the Activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get the content style mode
        Intent callIntent = getIntent();
        if (callIntent != null) {
            String text = callIntent.getStringExtra(PDECodeSamplesActivity.PDE_CODELIB_SAMPLE_EXTRA_PREFIX);
            if (text != null && text.length() != 0) {
                if (PDEString.contains(text.toUpperCase(Locale.US), "haptic".toUpperCase(Locale.US))) {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleHaptic;
                } else if (PDEString.contains(text.toUpperCase(Locale.US), "flat".toUpperCase(Locale.US))) {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;
                }
            }
        }

        // *************************
        // Configure the activity depending on the content style
        // *************************

        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            setContentView(R.layout.sectionedbutton_disabled_haptic_screen);
            getSupportActionBar().setTitle("Haptic style/Sectioned button disabled");
        } else {
            setContentView(R.layout.sectionedbutton_disabled_flat_screen);
            getSupportActionBar().setTitle("Flat style/Sectioned button disabled");
        }

        //get the root view and set background color (different when darkstyle is on or of in library)
        ScrollView rootView = (ScrollView) findViewById(R.id.sectionedbutton_disabled_rootview);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        // *************************
        // Configure sectioned button
        // *************************

        mSectionedButton = (PDESectionedButton) findViewById(R.id.sectionedbutton);
        mSectionedButton.addListener(this, "buttonTestPressed",
                                     PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

        // *************************
        // Add button listener for the disable button for all sections
        // *************************

        mButtonCheckboxDisableAll = (PDEButton) findViewById(R.id.button_checkbox_disable_all);
        mButtonCheckboxDisableAll.addListener(this,
                                              "disableSectionButtonCheckboxButtonPressed",
                                              PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

        // *************************
        // Add button listener for the disable button for the first section
        // *************************

        mButtonCheckboxDisableFirstSection = (PDEButton) findViewById(R.id.button_checkbox_disable_first);
        mButtonCheckboxDisableFirstSection.addListener(this,
                                                       "disableFirstSectionCheckboxButtonPressed",
                                                       PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
    }


    /**
     * @brief Listener on test button.
     */
    @SuppressWarnings("unused")
    public void buttonTestPressed(PDEEvent event) {
        Log.d(LOG_TAG, "Button Pressed");
    }


    /**
     * @brief Listener on checkbox button.
     *
     * Change enabled state with this listener.
     */
    @SuppressWarnings("unused")
    public void disableSectionButtonCheckboxButtonPressed(PDEEvent event) {
        // toggle enabled state
        mSectionedButton.setEnabled(!mSectionedButton.isEnabled());

        // update checkbox button state
        mButtonCheckboxDisableAll.setSelected(mSectionedButton.isEnabled());
        mButtonCheckboxDisableFirstSection.setSelected(mSectionedButton.isEnabled());
    }


    /**
     * @brief Listener on checkboxes.
     *
     * Detect which button was pressed by evaluating the tag id of the sender.
     */
    @SuppressWarnings("unused")
    public void disableFirstSectionCheckboxButtonPressed(PDEEvent event) {
        // toggle enabled state
        mSectionedButton.setSectionEnabled(!mSectionedButton.isSectionEnabled(0), 0);

        // update checkbox button state
        mButtonCheckboxDisableFirstSection.setSelected(mSectionedButton.isSectionEnabled(0));
    }

}