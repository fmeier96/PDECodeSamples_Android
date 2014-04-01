/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.genericstyle.pdeinputfields;


// imports

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.inputfields.PDEInputField;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;


/**
 * @brief Activity for sample screen to compare Deutsche Telekom Button and Android native Button.
 */
public class InputFieldProgrammingSampleActivity extends PDEActionBarActivity implements View.OnClickListener {

    /**
     * @brief Global tag for log outputs.
     */
    private final static String LOG_TAG = InputFieldProgrammingSampleActivity.class.getName();

    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;

    /**
     * @brief Create the Activity.
     *
     * Creates the  Activity which compares PDEButton with android native button.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //variables
        Intent callIntent;
        LinearLayout.LayoutParams btnLinearLayoutParams;
        RelativeLayout rootView;
        LinearLayout containerView;
        PDEInputField pdeInputfield;
        EditText nativeEditText;

        //set button size and gaps
        float buttonWidth = PDEBuildingUnits.pixelFromBU(17);
        float buttonHeight = PDEBuildingUnits.pixelFromBU(3);
        float gapY = PDEBuildingUnits.pixelFromBU(5);

        //get intent
        callIntent = getIntent();

        if (callIntent != null) {
            String text = callIntent.getStringExtra(PDECodeSamplesActivity.PDE_CODELIB_SAMPLE_EXTRA_PREFIX);
            if (!TextUtils.isEmpty(text)){
                if (PDEString.contains(text.toUpperCase(), "haptic".toUpperCase()))  {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleHaptic;
                } else if (PDEString.contains(text.toUpperCase(), "flat".toUpperCase())) {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;
                }
            }
        }

        // set the title
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            getSupportActionBar().setTitle("Haptic style/Inputfield programming sample");
        } else {
            getSupportActionBar().setTitle("Flat style/Inputfield programming sample");
        }

        setContentView(R.layout.inputfield_programming_sample_screen);

        //get the root view and set background color (different when dark-style is on or of in library)
        rootView = (RelativeLayout)findViewById(R.id.inputfieldsample_relativelayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        //get container view which contains the buttons
        containerView = (LinearLayout)findViewById(R.id.inputfieldsample_container);

        // *************************
        // Insert simple PDEInputField
        // *************************
        pdeInputfield= new PDEInputField(this);
        pdeInputfield.setHint("PDEInputField hint");
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            pdeInputfield.setInputFieldBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundTextHaptic);
        }

        //set some linear layout parameter to have correct position and size of button
        btnLinearLayoutParams = new LinearLayout.LayoutParams((int)buttonWidth, (int)buttonHeight);
        btnLinearLayoutParams.setMargins(0, 0, 0, (int)gapY);
        //add button to view
        containerView.addView(pdeInputfield, btnLinearLayoutParams );


        // *************************
        // Insert native EditText
        // *************************
        nativeEditText = new EditText(this);
        nativeEditText.setHint("Native Hint");
        nativeEditText.setSingleLine();
        //set some linear layout parameter to have correct position and size of button
        btnLinearLayoutParams = new LinearLayout.LayoutParams((int)buttonWidth, (int)buttonHeight);
        //add button to view
        containerView.addView(nativeEditText, btnLinearLayoutParams);
    }


    /**
     * @brief Called when the native button was clicked
     */
    @Override
    public void onClick(View view) {
        Log.d(LOG_TAG, "Native Android Button (Button) was pressed!");
    }


    /**
     * @brief Called on changes from agentController
     */
    @SuppressWarnings("unused")
    public void dtButtonPressed(PDEEvent event)
    {
        Log.d(LOG_TAG,"Telekom CodeComponent Button (PDEButton) was pressed!");
    }
}
