/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */
package de.telekom.pde.codelibrary.samples.developerscreens;


// imports

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;
import de.telekom.pde.codelibrary.ui.color.PDEColor;


/**
 * @brief Activity for sample screen to compare Deutsche Telekom Button and Android native Button.
 */
public class ButtonSizeTextXMLSampleActivity extends PDEActivity {

    /**
     * @brief Global tag for log outputs.
     */
	@SuppressWarnings("unused")
    private final static String LOG_TAG = ButtonSizeTextXMLSampleActivity.class.getName();

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

        setContentView(R.layout.button_sizetest_xml_sample_screen);

        //get the root view and set background color (different when darkstyle is on or of in library)
        RelativeLayout rootView = (RelativeLayout)findViewById(R.id.buttonsample_relativelayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        //get container view which contains the buttons
        LinearLayout containerView = (LinearLayout)findViewById(R.id.buttonsample_container);
    }

}
