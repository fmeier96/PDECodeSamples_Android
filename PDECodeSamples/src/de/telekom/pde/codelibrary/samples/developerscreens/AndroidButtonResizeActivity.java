/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;


// imports
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.basescreens.ResizeBaseActivity;


/**
 * @brief Activity class for the sizing test screen.
 */
public class AndroidButtonResizeActivity extends ResizeBaseActivity {

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
	private final static String LOG_TAG = AndroidButtonResizeActivity.class.getName();
    Button mButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mButton = new Button(this);
        mButton.setText("Button");
        //set some linear layout parameter to have correct position and size of button
        RelativeLayout.LayoutParams btnLinearLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        //add button to view
        addViewToResizeContainer(mButton, btnLinearLayoutParams);
    }
}
