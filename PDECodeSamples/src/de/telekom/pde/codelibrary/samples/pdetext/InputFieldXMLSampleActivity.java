/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.pdetext;


// imports
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.R.layout;
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;


/**
 * @brief Activity for sample screen to compare Deutsche Telekom InputField and Android native EditText.
 */
public class InputFieldXMLSampleActivity extends PDEActivity implements View.OnClickListener {

    /**
     * @brief Global tag for log outputs.
     */
    private final static String LOG_TAG = InputFieldXMLSampleActivity.class.getName();

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

        setContentView(layout.inputfield_xml_sample_screen);

        //get the root view and set background color (different when darkstyle is on or of in library)
        RelativeLayout rootView = (RelativeLayout)findViewById(R.id.inputfieldsample_relativelayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        //get container view which contains the buttons
//        LinearLayout containerView = (LinearLayout)findViewById(R.id.inputfieldsample_container);

        //variable for linear layout params
//        LinearLayout.LayoutParams btnLinearLayoutParams = null;

        //set button size and gaps
//        float buttonWidth = PDEBuildingUnits.pixelFromBU(17);
//        float buttonHeight = PDEBuildingUnits.pixelFromBU(3);
//        float gapY = PDEBuildingUnits.pixelFromBU(5);
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
