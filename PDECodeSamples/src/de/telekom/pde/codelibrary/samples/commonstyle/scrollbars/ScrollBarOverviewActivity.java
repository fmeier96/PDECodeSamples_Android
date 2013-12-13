/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.commonstyle.scrollbars;

import android.os.Bundle;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDESherlockActivity;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESlider;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESliderContentInterface.PDESliderContentOrientation;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESliderScrollHandlerScrollBar;


public class ScrollBarOverviewActivity extends PDESherlockActivity {

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = ScrollBarOverviewActivity.class.getName();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        PDESlider scrollBar;
        PDESlider scrollBarHandleOnly;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.scrollbars_overview_common_screen);

        getSupportActionBar().setTitle("Common Style/Scrollbar overview");

        // get the root view and set background color (different when darkstyle is on or of in library)
        RelativeLayout rootView = (RelativeLayout)findViewById(R.id.scrollbar_overview_relativelayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        //
        //--------------------------------------------------------------------------------------------------------------
        // Scrollbar Horizontal
        //
        // Setup scrollbar with custom scrollHandler to enable touch interaction.
        //
        //--------------------------------------------------------------------------------------------------------------

        scrollBar = (PDESlider)findViewById(R.id.slider_overview_scrollbar);
        scrollBar.setScrollHandler(new PDESliderScrollHandlerScrollBar(PDESliderContentOrientation.PDESliderContentOrientationHorizontal));

        //
        //--------------------------------------------------------------------------------------------------------------
        // Scrollbar Horizontal Handle Only
        //
        // Setup scrollbar with custom scrollHandler to enable touch interaction.
        //
        //--------------------------------------------------------------------------------------------------------------

        scrollBarHandleOnly = (PDESlider)findViewById(R.id.slider_overview_scrollbarHandleOnly);
        scrollBarHandleOnly.setScrollHandler(new PDESliderScrollHandlerScrollBar(PDESliderContentOrientation.PDESliderContentOrientationHorizontal));
    }

}