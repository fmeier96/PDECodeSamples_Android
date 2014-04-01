package de.telekom.pde.codelibrary.samples.developerscreens;/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

import android.os.Bundle;
import android.widget.LinearLayout;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESlider;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESliderScrollHandlerSliderBar;


public class SliderScrollTest extends PDEActivity {

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private static final String LOG_TAG = SliderScrollTest.class.getName();


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.developer_slider_scrolltest);

        // get the root view and set background color (different when darkstyle is on or of in library)
        LinearLayout rootView = (LinearLayout)findViewById(R.id.developer_slider_scrolltest_linearLayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        PDESliderScrollHandlerSliderBar sliderBarTwoScroller = new PDESliderScrollHandlerSliderBar();
        sliderBarTwoScroller.setHandleHitOnlyEnabled(true);

        // get slider
        PDESlider scrollBarHorizontal = (PDESlider) findViewById(R.id.developer_slider_scrolltest_scrollbarHorizontal);
        PDESlider scrollBarVertical = (PDESlider) findViewById(R.id.developer_slider_scrolltest_scrollbarVertical);
        PDESlider sliderBarOne = (PDESlider) findViewById(R.id.developer_slider_scrolltest_sliderbarOne);
        PDESlider sliderBarTwo = (PDESlider) findViewById(R.id.developer_slider_scrolltest_sliderbarTwo);
        //PDESlider sliderbarThree = (PDESlider) findViewById(R.id.developer_slider_scrolltest_sliderbarThree);

        // couple slider
        scrollBarHorizontal.setSliderControllerForId(scrollBarVertical.getSliderControllerForId(0), 0);
        sliderBarOne.setSliderControllerForId(sliderBarTwo.getSliderControllerForId(0), 0);

        // set pageSize of scrollbars
        scrollBarHorizontal.getSliderControllerForId(0).setSliderPageSize(0.6f);

        // set scrollers
        sliderBarTwo.setScrollHandler(sliderBarTwoScroller);
    }
}



