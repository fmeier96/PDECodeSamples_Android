/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.genericstyle.pdeslider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ScrollView;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.elementwrappers.PDETextView;
import de.telekom.pde.codelibrary.ui.components.sliders.*;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESlider.PDESliderContentType;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;


public class SliderProgrammingSampleGenericActivity extends PDEActionBarActivity {

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = SliderProgrammingSampleGenericActivity.class.getName();

    // pde slider samples


    // style flag
    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.slider_programming_sample_screen);

        PDETextView sliderBarHeaderStyle;
        PDESlider sliderBar;
        PDETextView sliderBarHitOnlyHeaderStyle;
        PDESlider sliderBarHitOnly;
        PDETextView sliderBarIntervalHeaderStyle;
        PDESlider sliderBarInterval;
        Intent callIntent;


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


        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            getSupportActionBar().setTitle("Haptic style/Slider programming sample");
        } else {
            getSupportActionBar().setTitle("Flat style/Slider programming sample");
        }


        // get the root view and set background color (different when darkStyle is on or of in library)
        ScrollView rootView = (ScrollView)findViewById(R.id.generic_slidershowcase2_screen_scrollview);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());


        //
        //--------------------------------------------------------------------------------------------------------------
        // SliderBar
        //
        // Setup slider bar with custom scroller which will care about touch events.
        //--------------------------------------------------------------------------------------------------------------

        sliderBar = (PDESlider)findViewById(R.id.generic_slidershowcase2_screen_slider);
        sliderBarHeaderStyle = (PDETextView)findViewById(R.id.generic_slidershowcase2_screen_header_slider_style);
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            sliderBar.setSliderContentType(PDESliderContentType.SliderBarHaptic);
            sliderBarHeaderStyle.setText("Haptic");
        } else {
            sliderBar.setSliderContentType(PDESliderContentType.SliderBarFlat);
            sliderBarHeaderStyle.setText("Flat");
        }

        //
        //--------------------------------------------------------------------------------------------------------------
        // SliderBarHitOnly
        //
        // Setup sliderBar with custom scroller which will only enable touch interaction on direct handle hits.
        //--------------------------------------------------------------------------------------------------------------

        sliderBarHitOnly = (PDESlider)findViewById(R.id.generic_slidershowcase2_screen_sliderHandleOnly);
        sliderBarHitOnlyHeaderStyle = (PDETextView)findViewById(
                                                    R.id.generic_slidershowcase2_screen_header_sliderHandleOnly_style);
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            sliderBarHitOnly.setSliderContentType(PDESliderContentType.SliderBarHaptic);
            sliderBarHitOnlyHeaderStyle.setText("Haptic");
        } else {
            sliderBarHitOnly.setSliderContentType(PDESliderContentType.SliderBarFlat);
            sliderBarHitOnlyHeaderStyle.setText("Flat");
        }
        ((PDESliderScrollHandlerSliderBar)sliderBarHitOnly.getScrollHandler()).setHandleHitOnlyEnabled(true);

        //
        //--------------------------------------------------------------------------------------------------------------
        // SliderBarInterval
        //
        // Setup sliderBar to enable touch interaction on direct hits.
        // Register a Listener function "cbSliderBarInterval" on this Slider which will add jump interval behaviour.
        // This Listener will only get "...DATA_WILL_CHANGE" Events that can be manipulated.
        //
        //--------------------------------------------------------------------------------------------------------------

        sliderBarInterval = (PDESlider)findViewById(R.id.generic_slidershowcase2_screen_sliderIntervals);
        sliderBarIntervalHeaderStyle = (PDETextView)findViewById(
                                                    R.id.generic_slidershowcase2_screen_header_sliderIntervals_style);
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            sliderBarInterval.setSliderContentType(PDESliderContentType.SliderBarHaptic);
            sliderBarIntervalHeaderStyle.setText("Haptic");
        } else {
            sliderBarInterval.setSliderContentType(PDESliderContentType.SliderBarFlat);
            sliderBarIntervalHeaderStyle.setText("Flat");
        }
        ((PDESliderScrollHandlerSliderBar)sliderBarInterval.getScrollHandler()).setHandleHitOnlyEnabled(true);
        sliderBarInterval.addListener(this, "cbSliderBarInterval",
                                       PDESliderController.PDE_SLIDER_CONTROLLER_EVENT_MASK_DATA_WILL_CHANGE);

    }


    /**
     * This enables position jump behaviour by manipulating listened slider events.
     *
     */
    @SuppressWarnings("unused")
    public void cbSliderBarInterval(PDEEvent event) {

        float positionToSet;
        int calcPosition;
        PDEEventSliderControllerState sliderEvent;

        // process the event
        sliderEvent = (PDEEventSliderControllerState) event;
        if(sliderEvent.getSliderControllerId() != 0) return;
        positionToSet = sliderEvent.getSliderPosition();

        // set 10 jump positions
        calcPosition = (int) (positionToSet * 100);
        if (calcPosition % 10 > 4) calcPosition += 10 - calcPosition % 10;
        else calcPosition -= calcPosition%10;
        positionToSet = (float) calcPosition / 100;

        // manipulate slider event with new value
        sliderEvent.setSliderPosition(positionToSet);
        sliderEvent.setProcessed();
    }
}