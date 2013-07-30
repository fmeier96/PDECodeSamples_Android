/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.pdetext;


// imports
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.inputfields.PDEInputField;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;


/**
 * @brief Activity for sample screen to compare Deutsche Telekom Button and Android native Button.
 */
public class InputFieldSampleActivity extends PDEActivity implements View.OnClickListener {

    /**
     * @brief Global tag for log outputs.
     */
    private final static String LOG_TAG = InputFieldSampleActivity.class.getName();

    /**
     * @brief Create the Activity.
     *
     * Creates the  Activity which compares PDEButton with android native button.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.inputfield_sample_screen);

        //get the root view and set background color (different when darkstyle is on or of in library)
        RelativeLayout rootView = (RelativeLayout)findViewById(R.id.inputfieldsample_relativelayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        //get container view which contains the buttons
        LinearLayout containerView = (LinearLayout)findViewById(R.id.inputfieldsample_container);

        //variable for linear layout params
        LinearLayout.LayoutParams btnLinearLayoutParams = null;

        //set button size and gaps
        float buttonWidth = PDEBuildingUnits.pixelFromBU(17);
        float buttonHeight = PDEBuildingUnits.pixelFromBU(3);
        float gapY = PDEBuildingUnits.pixelFromBU(5);


        // *************************
        // Insert simple PDEInputField
        // *************************
        PDEInputField pdeInputfield= new PDEInputField(this);
        pdeInputfield.setHint("PDEInputField hint");
        //set some linear layout parameter to have correct position and size of button
        btnLinearLayoutParams = new LinearLayout.LayoutParams((int)buttonWidth,(int)buttonHeight);
        btnLinearLayoutParams.setMargins(0,0,0,(int)gapY);
        //add button to view
        containerView.addView(pdeInputfield, btnLinearLayoutParams );


        // *************************
        // Insert native EditText
        // *************************
        EditText nativeEditText = new EditText(this);
        nativeEditText.setHint("Native Hint");
        nativeEditText.setSingleLine();
        //set some linear layout parameter to have correct position and size of button
        btnLinearLayoutParams = new LinearLayout.LayoutParams((int)buttonWidth,(int)buttonHeight);
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
    public void dtButtonPressed(PDEEvent event)
    {
        Log.d(LOG_TAG,"Telekom CodeComponent Button (PDEButton) was pressed!");
    }
}
