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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDESherlockActivity;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.sliders.PDEEventSliderControllerState;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESlider;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESliderController;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESliderScrollHandlerSliderBar;
import de.telekom.pde.codelibrary.ui.elements.common.PDEDrawableDelimiter;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;
import de.telekom.pde.codelibrary.ui.helpers.PDEUtils;


public class ProgressBarProgrammingSampleGenericActivity extends PDESherlockActivity {

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = ProgressBarProgrammingSampleGenericActivity.class.getName();


    // pde slider - progressBar
    private PDESlider mPDEProgressBar;

    // native progressBar
    private ProgressBar mNativeProgressBar;

    // regulator sliderBar
    private PDESlider regulatorSliderbar;

    // style flag
    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;

    /**
     * @brief Create the Activity.
     *
     * Creates the  Activity which compares PDESlider with native Seekbar.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        TextView descriptionText;

        super.onCreate(savedInstanceState);

        Intent callIntent = getIntent();
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
            setContentView(R.layout.progressbar_programming_sample_haptic_screen);
            getSupportActionBar().setTitle("Haptic style/Progressbar programming sample");
        } else {
            setContentView(R.layout.progressbar_programming_sample_flat_screen);
            getSupportActionBar().setTitle("Flat style/Progressbar programming sample");
        }




        ///setContentView(R.layout.slider_xml_sample_screen_progressbar);

        // get the root view and set background color (different when darkstyle is on or of in library)
        RelativeLayout rootView = (RelativeLayout)findViewById(R.id.slider_sample_progressbar_relativelayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        // setup seperator delimiter
        PDEUtils.setViewBackgroundDrawable(findViewById(R.id.slider_sample_progressbar_bottom_line),
                                           new PDEDrawableDelimiter());

        // setup description text color
        descriptionText = (TextView) findViewById(R.id.slider_sample_progressbar_text);
        descriptionText.setTextColor(PDEColor.DTUITextColor().getIntegerColor());

        // reference both progressbars
        mPDEProgressBar =  (PDESlider) findViewById(R.id.pdeSlider_progressBar);
        mNativeProgressBar = (ProgressBar) findViewById(R.id.native_progressBar);

        // make native progressbar range more precise
        mNativeProgressBar.setMax(10000);


        //
        //--------------------------------------------------------------------------------------------------------------
        // PDESlider - Sliderbar
        //
        // A Slidebar is setup which will regulate both progress bars.
        // This Sliderbar is made touch interactive via a costum Scroller.
        // A Listener is added which will call "cbRegulatorSliderbar" with all DATA_HAS_CHANGED events from the slider.
        //
        //--------------------------------------------------------------------------------------------------------------
        regulatorSliderbar = (PDESlider) findViewById(R.id.slider_sample_progressbar_regulator);
        regulatorSliderbar.setScrollHandler(new PDESliderScrollHandlerSliderBar());
        regulatorSliderbar.addListener(this, "cbRegulatorSliderbar", PDESliderController.PDE_SLIDER_CONTROLLER_EVENT_MASK);
    }


    /**
     * @brief Handle callbacks from regulator sliderbar and
     *        pass postions to progressBars
     */
    @SuppressWarnings("unused")
    public void cbRegulatorSliderbar(PDEEvent event) {

        PDEEventSliderControllerState sliderEvent;
        float pdeProgress;
        int nativeProgress;

        // process Event
        sliderEvent = (PDEEventSliderControllerState) event;
        pdeProgress = sliderEvent.getSliderPosition();
        nativeProgress = (int)(10000* sliderEvent.getSliderPosition());

        // set value to pde progressbar
        mPDEProgressBar.getSliderControllerForId(0).setSliderPosition(pdeProgress);

        // set value to native progressbar
        mNativeProgressBar.setProgress(nativeProgress);
    }
}
