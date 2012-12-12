/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.PDEButtonSamples;


// imports
import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.samples.R;


/**
 * @brief Activity class to show all button types on one screen below each other.
 */
public class ButtonXMLShowcaseActivity extends Activity{

    /**
     * @brief Global tag for log outputs.
     */
    private final static String LOG_TAG = ButtonXMLShowcaseActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.button_xml_showcase_screen);

        //get the root view and set background color (different when darkstyle is on or of in library)
        RelativeLayout rootView = (RelativeLayout)findViewById(R.id.buttonshowcase_relativelayout);
        rootView.setBackgroundColor(PDEColor.valueOf("DTUIBackground").getIntegerColor());

    }


}
