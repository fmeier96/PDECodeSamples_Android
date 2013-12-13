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
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.samples.basescreens.DialogHelper;
import de.telekom.pde.codelibrary.samples.basescreens.ResizeBaseActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESlider;
import de.telekom.pde.codelibrary.ui.elements.common.PDEDrawableDelimiter;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;
import de.telekom.pde.codelibrary.ui.helpers.PDEUtils;

import java.util.ArrayList;


public class SliderResizeGenericActivity extends ResizeBaseActivity {

    // global names of the different sliders
    private final static String SLIDER_NAME_PROGRESSBAR = "ProgessBar";
    private final static String SLIDER_NAME_SLIDERBAR = "SliderBar";


    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = SliderResizeGenericActivity.class.getName();

    // slider
    private PDESlider mSlider;

    // different slider contents
    private ArrayList<String> mSliderChoiceArrayList;

    // store viewgroups
    private ArrayList<ViewGroup> mRegulatorArray;

    // structure layout
    private View mSeparatorTopLine;

    // style flag
    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;


    // ---------------------------------- initialize --------------------------------------------------------------------

    /**
     * @brief Create the activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
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
            getSupportActionBar().setTitle("Haptic style/Slider resizing");
        } else {
            getSupportActionBar().setTitle("Flat style/Slider resizing");
        }

        // create the slider
        mSlider = new PDESlider(this);

        // set layout
        RelativeLayout.LayoutParams sliderLinearLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                                               ViewGroup.LayoutParams.MATCH_PARENT);

        // add slider to resize container
        addViewToResizeContainer(mSlider, sliderLinearLayoutParams);

        // store slider regulator helpers
        mRegulatorArray = new ArrayList<ViewGroup>();

        // setup thin seperator line
        mSeparatorTopLine = new View(SliderResizeGenericActivity.this);
        mSeparatorTopLine.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        PDEUtils.setViewBackgroundDrawable(mSeparatorTopLine, new PDEDrawableDelimiter());
        ((ViewGroup) findViewById(R.id.resize_base_rootlayout)).addView(mSeparatorTopLine, 1);

        // setup slider choice
        fillSliderChoiceArray();
        setContainerSize(PDEBuildingUnits.exactPixelFromBU(10.0f),PDEBuildingUnits.exactPixelFromBU(6.0f));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSelectionPos(LEFT_RIGHT_BUTTON.LEFT, 0);
    }


    // ---------------------------------- helper -----------------------------------------------------------------------


    /**
     * @brief Add choice list with possible slider contents
     */
    private void fillSliderChoiceArray() {

        // create array list
        mSliderChoiceArrayList = new ArrayList<String>();

        // add names
        mSliderChoiceArrayList.add(SLIDER_NAME_PROGRESSBAR);
        mSliderChoiceArrayList.add(SLIDER_NAME_SLIDERBAR);

        // set choice information and listener
        addChoiceArrayList(LEFT_RIGHT_BUTTON.LEFT, "Choose", mSliderChoiceArrayList,
                           new DialogHelper.ChoiceListOnItemClickListener() {
                               @Override
                               public void onListItemClicked(String itemContentString) {

                                   // remove old regulators
                                   for (ViewGroup vg : mRegulatorArray) {
                                       ((ViewGroup) vg.getParent()).removeView(vg);
                                   }

                                   // remove old regulators
                                   mRegulatorArray.clear();


                                   // react on list selection


                                   // ----- Progressbar -----
                                   if (TextUtils.equals(itemContentString, SLIDER_NAME_PROGRESSBAR)) {

                                       // set type to progressbar
                                       if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
                                           mSlider.setSliderContentType(PDESlider.PDESliderContentType.ProgressBarHaptic);
                                       } else {
                                           mSlider.setSliderContentType(PDESlider.PDESliderContentType.ProgressBarFlat);
                                       }


                                       // control postions for controller 1
                                       SliderRegulatorHelperGenericView progressbarPositionOne = new SliderRegulatorHelperGenericView(SliderResizeGenericActivity.this, mStyle);
                                       progressbarPositionOne.setSlider(mSlider);
                                       progressbarPositionOne.setSliderControllerId(1);
                                       progressbarPositionOne.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.Postion);
                                       mRegulatorArray.add(progressbarPositionOne);

                                       // control postions for controller 0
                                       SliderRegulatorHelperGenericView progressbarPositionZero = new SliderRegulatorHelperGenericView(SliderResizeGenericActivity.this, mStyle);
                                       progressbarPositionZero.setSlider(mSlider);
                                       progressbarPositionZero.setSliderControllerId(0);
                                       progressbarPositionZero.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.Postion);
                                       mRegulatorArray.add(progressbarPositionZero);

                                   }

                                   // ----- SliderBar Medium -----
                                   else if (TextUtils.equals(itemContentString, SLIDER_NAME_SLIDERBAR)) {

                                       // set type to scrollBar horizontal
                                       if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
                                           mSlider.setSliderContentType(PDESlider.PDESliderContentType.SliderBarHaptic);
                                       } else {
                                           mSlider.setSliderContentType(PDESlider.PDESliderContentType.SliderBarFlat);
                                       }

                                       // control position for controller 0
                                       SliderRegulatorHelperGenericView sliderBarMediumPosition = new SliderRegulatorHelperGenericView(SliderResizeGenericActivity.this, mStyle);
                                       sliderBarMediumPosition.setSlider(mSlider);
                                       sliderBarMediumPosition.setSliderControllerId(0);
                                       sliderBarMediumPosition.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.Postion);
                                       mRegulatorArray.add(sliderBarMediumPosition);
                                   }

                                   // add new regulator views
                                   for (ViewGroup vg : mRegulatorArray) {
                                       ((ViewGroup) findViewById(R.id.resize_base_rootlayout)).addView(vg, 2);
                                   }
                               }
                           });
    }
}