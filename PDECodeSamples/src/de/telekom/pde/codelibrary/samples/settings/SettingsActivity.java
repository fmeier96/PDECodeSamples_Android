/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.settings;


// imports
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.PDECodeLibrary;
import de.telekom.pde.codelibrary.ui.color.PDEColor;


/**
 * @brief Activity class for the setting, to enable changes to some library values.
 */
public class SettingsActivity extends PDEActivity {

    /**
     * @brief Global tag for log outputs.
     */
	@SuppressWarnings("unused")
    private final static String LOG_TAG = SettingsActivity.class.getName();

    //variables
    private LinearLayout rootView = null;


    /**
     * @brief Create the Activity.
     *
     * Creates the  Activity  to change settings of the library.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings_screen);

        //get the root view and set background color (different when darkstyle is on or of in library)
        rootView = (LinearLayout)findViewById(R.id.settingsscreen_relativelayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        //get toggle button and add listener to change the darkstyle attribute of the library
        ToggleButton darkstyleToggleButton = (ToggleButton)findViewById(R.id.darkstyle_togglebutton);
        darkstyleToggleButton.setChecked( PDECodeLibrary.getInstance().isDarkStyle());
        darkstyleToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                PDECodeLibrary.getInstance().setDarkStyle(b);
                rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());
            }
        });

//        //get toggle button and add listener to change the darkstyle attribute of the library
//        ToggleButton softwarerenderingButtonToggleButton = (ToggleButton)findViewById(R.id.softwarerendering_button_togglebutton);
//        softwarerenderingButtonToggleButton.setChecked( PDECodeLibrary.getInstance().isSoftwareRenderingButton() );
//        softwarerenderingButtonToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                PDECodeLibrary.getInstance().setSoftwareRenderingButton(b);
//            }
//        });
//
//        //get toggle button and add listener to change the darkstyle attribute of the library
//        ToggleButton softwarerenderingParentToggleButton = (ToggleButton)findViewById(R.id.softwarerendering_parent_togglebutton);
//        softwarerenderingParentToggleButton.setChecked( PDECodeLibrary.getInstance().isSoftwareRenderingParent() );
//        softwarerenderingParentToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                PDECodeLibrary.getInstance().setSoftwareRenderingParent(b);
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PDECodeSamplesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
