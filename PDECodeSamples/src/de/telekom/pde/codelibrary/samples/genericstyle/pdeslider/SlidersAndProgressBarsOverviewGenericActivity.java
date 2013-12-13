/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.genericstyle.pdeslider;


// imports

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants.PDEContentStyle;
import de.telekom.pde.codelibrary.ui.activity.PDESherlockActivity;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.sliders.*;
import de.telekom.pde.codelibrary.ui.components.elementwrappers.PDELayerTextView;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;


/**
 * @brief Activity for sample screen to show different modes of the slider.
 */
public class SlidersAndProgressBarsOverviewGenericActivity extends PDESherlockActivity {

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = SlidersAndProgressBarsOverviewGenericActivity.class.getName();

    // PDESlider samples
    private PDESlider mProgressBarSmall;
    private PDESlider mProgressBar;
    private PDESlider mSliderBar;
    private PDESlider mSliderBarBig;
    private PDEContentStyle mStyle = PDEContentStyle.PDEContentStyleFlat;



    /**
     * @brief Create the Activity.
     *
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        LinearLayout containerView;
        LinearLayout.LayoutParams sliderLinearLayoutParams;
        int scrollPadding;
        PDESliderContentProgressBar tmpContentProgressBar;
        PDESliderContentSliderBar tmpContentSliderBar;

        super.onCreate(savedInstanceState);

        Intent callIntent = getIntent();
        if (callIntent != null) {
            String text = callIntent.getStringExtra(PDECodeSamplesActivity.PDE_CODELIB_SAMPLE_EXTRA_PREFIX);
            if (!TextUtils.isEmpty(text)){
                if (PDEString.contains(text.toUpperCase(), "haptic".toUpperCase()))  {
                    mStyle = PDEContentStyle.PDEContentStyleHaptic;
                } else if (PDEString.contains(text.toUpperCase(), "flat".toUpperCase())) {
                    mStyle = PDEContentStyle.PDEContentStyleFlat;
                }
            }
        }

        setContentView(R.layout.sliders_and_progressbars_overview_screen);

        if (mStyle == PDEContentStyle.PDEContentStyleHaptic) {
            getSupportActionBar().setTitle("Haptic style/Sliders and progressbars overview");
        } else {
            getSupportActionBar().setTitle("Flat style/Sliders and progressbars overview");
        }

        findViewById(R.id.generic_sliderprogressbar_showcase_scrollview).setBackgroundColor(
                PDEColor.DTUIBackgroundColor().getIntegerColor());

        // get container view which contains the sliders
        containerView = (LinearLayout)findViewById(R.id.progressbar_container);

        // setup padding constant
        scrollPadding = PDEBuildingUnits.pixelFromBU(2.0f);


        //--------------------------------------------------------------------------------------------------------------
        // ProgressBarSmall
        //
        // Set slider content type to progressbar.
        // Set value to controller 0, this controller is responsible for the progress (preload would be controller 1).
        // Setup LayoutParams with margins to fit to other sample sliders and add view.
        // Get the content and set custom height so the progressBar.
        //
        //--------------------------------------------------------------------------------------------------------------

        mProgressBarSmall = new PDESlider(this);
        if (mStyle == PDEContentStyle.PDEContentStyleHaptic) {
            mProgressBarSmall.setSliderContentType(PDESlider.PDESliderContentType.ProgressBarHaptic);
            ((PDELayerTextView)findViewById(R.id.header_progressbar_style)).setText("Haptic");
        } else {
            mProgressBarSmall.setSliderContentType(PDESlider.PDESliderContentType.ProgressBarFlat);
            ((PDELayerTextView)findViewById(R.id.header_progressbar_style)).setText("Flat");
        }
        tmpContentProgressBar = (PDESliderContentProgressBar) mProgressBarSmall.getSliderContent();
        tmpContentProgressBar.setProgressBarHeight(PDEBuildingUnits.pixelFromBU(0.5f));
        mProgressBarSmall.getSliderControllerForId(0).setSliderPosition(0.3f);
        mProgressBarSmall.getSliderControllerForId(1).setSliderPosition(0.6f);
        // setup layout params for the slider
        sliderLinearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        sliderLinearLayoutParams.setMargins(scrollPadding, scrollPadding, scrollPadding, scrollPadding);
        // add slider to view
        containerView.addView(mProgressBarSmall,sliderLinearLayoutParams);


        //--------------------------------------------------------------------------------------------------------------
        // ProgressBar
        //
        // Set slider content type to progressbar.
        // Set value to controller 0, this controller is responsible for the progress (preload would be controller 1).
        // Setup LayoutParams with margins to fit to other sample sliders and add view.
        //
        //--------------------------------------------------------------------------------------------------------------

        mProgressBar = new PDESlider(this);
        if (mStyle == PDEContentStyle.PDEContentStyleHaptic) {
            mProgressBar.setSliderContentType(PDESlider.PDESliderContentType.ProgressBarHaptic);
            ((PDELayerTextView)findViewById(R.id.header_progressbar_style)).setText("Haptic");
        } else {
            mProgressBar.setSliderContentType(PDESlider.PDESliderContentType.ProgressBarFlat);
            ((PDELayerTextView)findViewById(R.id.header_progressbar_style)).setText("Flat");
        }
        mProgressBar.getSliderControllerForId(0).setSliderPosition(0.3f);
        mProgressBar.getSliderControllerForId(1).setSliderPosition(0.6f);
        // setup layout params for the slider
        sliderLinearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        sliderLinearLayoutParams.setMargins(scrollPadding, scrollPadding, scrollPadding, scrollPadding);
        // add slider to view
        containerView.addView(mProgressBar,sliderLinearLayoutParams);


        // switch to container view for sliderBar
        containerView = (LinearLayout)findViewById(R.id.sliderbar_container);


        //--------------------------------------------------------------------------------------------------------------
        // SliderBar
        //
        // Set slider content type to SliderBarFlat.
        // Setup SliderBar witch custom scroller which will care about touch events.
        // Add padding to allow better touch interaction around the element.
        // Setup LayoutParams and add view.
        //
        //--------------------------------------------------------------------------------------------------------------

        mSliderBar = new PDESlider(this);
        if (mStyle == PDEContentStyle.PDEContentStyleHaptic) {
            mSliderBar.setSliderContentType(PDESlider.PDESliderContentType.SliderBarHaptic);
            ((PDELayerTextView)findViewById(R.id.header_sliderbar_style)).setText("Haptic");
        } else {
            mSliderBar.setSliderContentType(PDESlider.PDESliderContentType.SliderBarFlat);
            ((PDELayerTextView)findViewById(R.id.header_sliderbar_style)).setText("Flat");
        }
        mSliderBar.setScrollHandler(new PDESliderScrollHandlerSliderBar());
        mSliderBar.setPadding(scrollPadding, scrollPadding, scrollPadding, scrollPadding);
        mSliderBar.getSliderControllerForId(0).setSliderPosition(0.3f);
        mSliderBar.getSliderControllerForId(1).setSliderPosition(0.6f);
        mSliderBar.addListener(this,
                "sliderEventFromController",
                PDESliderController.PDE_SLIDER_CONTROLLER_EVENT_MASK_DATA);

        // setup layout params for the slider
        sliderLinearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        // add slider to view
        containerView.addView(mSliderBar,sliderLinearLayoutParams);


        //--------------------------------------------------------------------------------------------------------------
        // SliderBar with bigger Handle
        //
        // Add padding to allow better touch interaction.
        // Setup LayoutParams and add view.
        //
        //--------------------------------------------------------------------------------------------------------------

        PDESliderScrollHandlerSliderBar sliderbarScroller;
        mSliderBarBig = new PDESlider(this);
        if (mStyle == PDEContentStyle.PDEContentStyleHaptic) {
            mSliderBarBig.setSliderContentType(PDESlider.PDESliderContentType.SliderBarHaptic);
        } else {
            mSliderBarBig.setSliderContentType(PDESlider.PDESliderContentType.SliderBarFlat);
        }
        tmpContentSliderBar = (PDESliderContentSliderBar) mSliderBarBig.getSliderContent();
        tmpContentSliderBar.setHandleSize(
                new Point(PDEBuildingUnits.pixelFromBU(2.0f), PDEBuildingUnits.pixelFromBU(2.0f)));
        sliderbarScroller = new PDESliderScrollHandlerSliderBar();
        mSliderBarBig.setScrollHandler(sliderbarScroller);
        mSliderBarBig.setPadding(scrollPadding, scrollPadding, scrollPadding, scrollPadding);
        mSliderBarBig.getSliderControllerForId(0).setSliderPosition(0.6f);
        mSliderBarBig.getSliderControllerForId(1).setSliderPosition(0.6f);
        mSliderBarBig.addListener(this,
                "sliderEventFromController",
                PDESliderController.PDE_SLIDER_CONTROLLER_EVENT_MASK_DATA);
        // setup layout params for the slider
        sliderLinearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        // add slider to view
        containerView.addView(mSliderBarBig, sliderLinearLayoutParams);
    }


    /**
     *@brief called on changes from Slider Controller
     */
    @SuppressWarnings("unused")
    public void sliderEventFromController(PDEEvent event) {

        PDEEventSliderControllerState sliderEvent = (PDEEventSliderControllerState)event;

        if (sliderEvent.getSlider() == mSliderBar) {
            if (sliderEvent.getSliderControllerId() == 0) {
                // set progress
                mProgressBarSmall.getSliderControllerForId(0).setSliderPosition(sliderEvent.getSliderPosition()) ;
                mProgressBar.getSliderControllerForId(0).setSliderPosition(sliderEvent.getSliderPosition()) ;
            }
        } else {
            if (sliderEvent.getSliderControllerId() == 0) {
                // set preload
                mProgressBarSmall.getSliderControllerForId(1).setSliderPosition(sliderEvent.getSliderPosition());
                mProgressBar.getSliderControllerForId(1).setSliderPosition(sliderEvent.getSliderPosition());
            }
        }
    }
}

