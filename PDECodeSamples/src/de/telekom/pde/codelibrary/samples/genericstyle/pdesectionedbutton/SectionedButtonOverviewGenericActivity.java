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
//  SectionedButtonOverviewGenericActivity
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief SectionedButtonOverviewGenericActivity for some overview of the sectioned button.
 */
public class SectionedButtonOverviewGenericActivity extends PDEActionBarActivity {


    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = SectionedButtonOverviewGenericActivity.class.getName();


    // screen variables
    private PDEButton mCheckboxButton = null;
    private PDESectionedButton mSectionedButtonStandard = null;
    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;


    /**
     * @brief Create the Activity.
     *
     * Creates the  Activity where the user can select the type of PDEButton he wants.
     */
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
            setContentView(R.layout.sectionedbutton_overview_haptic_screen);
            getSupportActionBar().setTitle("Haptic style/Sectioned buttons overview");
        } else {
            setContentView(R.layout.sectionedbutton_overview_flat_screen);
            getSupportActionBar().setTitle("Flat style/Sectioned buttons overview");
        }

        //get the root view and set background color (different when darkstyle is on or of in library)
        ScrollView rootView = (ScrollView) findViewById(R.id.headers_rootview);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        // *************************
        // Get the sectioned button
        // *************************

        mSectionedButtonStandard = (PDESectionedButton) findViewById(R.id.sectionedButton_standard);
        mSectionedButtonStandard.setSelectedSection(0);

        // *************************
        // Add button listener
        // *************************

        mCheckboxButton = (PDEButton) findViewById(R.id.checkbox_button);
        mCheckboxButton.addListener(this,
                                    "checkboxButtonPressed",
                                    PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
    }


    /**
     * Click listener for the checkbox button.
     *
     * @param event Initiating event
     */
    @SuppressWarnings("unused")
    public void checkboxButtonPressed(PDEEvent event) {
        if (event.getSender() == mCheckboxButton) {
            ((PDEButton) event.getSender()).setSelected(!((PDEButton) event.getSender()).isSelected());
            mSectionedButtonStandard.setToggle(((PDEButton) event.getSender()).isSelected());
        }
    }
}
