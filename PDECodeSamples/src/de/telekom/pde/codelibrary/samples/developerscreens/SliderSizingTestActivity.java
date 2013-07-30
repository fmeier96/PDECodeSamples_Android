/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;

// imports
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.basescreens.DialogHelper;
import de.telekom.pde.codelibrary.samples.basescreens.ResizeBaseActivity;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESlider;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESliderController;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESliderControllerState;

import java.util.ArrayList;


/**
 * @brief Test Slider Sizing
 */
public class SliderSizingTestActivity extends ResizeBaseActivity {

    //global names of the different sliders
    private final static String SLIDER_NAME_PROGRESSBAR = "Progess bar";
    private final static String SLIDER_NAME_SCROLLBAR_HORIZONTAL = "Scrollbar Horizontal";

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = SliderSizingTestActivity.class.getName();

    // slider
    PDESlider mSlider;

    // different slider contents
    ArrayList<String> mSliderChoiceArrayList;

    // store viewgroups
    ArrayList<ViewGroup> mRegulatorArray;


    // ---------------------------------- intialize --------------------------------------------------------------------

    /**
     * @brief Create the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create the slider
        mSlider = new PDESlider(this);
        //mSlider.setSliderWithContentType(PDESlider.PDESliderContentType.ProgressBar);

        RelativeLayout.LayoutParams sliderLinearLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        //add slider to view
        addViewToResizeContainer(mSlider, sliderLinearLayoutParams);

        mRegulatorArray = new ArrayList<ViewGroup>();

        fillSliderChoiceArray();
        //startMovingThread();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSelectionPos(LEFT_RIGHT_BUTTON.LEFT, 0);
        // this.
    }


    // ---------------------------------- helper -----------------------------------------------------------------------


    private void fillSliderChoiceArray() {

        // create arraylist
        mSliderChoiceArrayList = new ArrayList<String>();

        // add names
        mSliderChoiceArrayList.add(SLIDER_NAME_PROGRESSBAR);
        mSliderChoiceArrayList.add(SLIDER_NAME_SCROLLBAR_HORIZONTAL);

        //set choice information and listener
        addChoiceArrayList(LEFT_RIGHT_BUTTON.LEFT, "Choose", mSliderChoiceArrayList,
                new DialogHelper.ChoiceListOnItemClickListener() {
                    @Override
                    public void onListItemClicked(String itemContentString) {

                        // remove old regulators
                        for (ViewGroup vg : mRegulatorArray) {
                            ((ViewGroup) vg.getParent()).removeView(vg);
                        }
                        mRegulatorArray.clear();


                        // react on list selection

                        // ----- Progressbar -----
                        if (TextUtils.equals(itemContentString, SLIDER_NAME_PROGRESSBAR)) {

                            // set type to progressbar
                            mSlider.setSliderWithContentType(PDESlider.PDESliderContentType.ProgressBar);

                            // controll postions for controller 1
                            SliderSizetestRegulatorView progressbarPositionOne = new SliderSizetestRegulatorView(SliderSizingTestActivity.this);
                            progressbarPositionOne.setSlider(mSlider);
                            progressbarPositionOne.setSliderControllerId(1);
                            progressbarPositionOne.setRegulatortype(SliderSizetestRegulatorView.SliderSizetestRegulatortype.Postion);
                            mRegulatorArray.add(progressbarPositionOne);

                            // controll postions for controller 0
                            SliderSizetestRegulatorView progressbarPositionZero = new SliderSizetestRegulatorView(SliderSizingTestActivity.this);
                            progressbarPositionZero.setSlider(mSlider);
                            progressbarPositionZero.setSliderControllerId(0);
                            progressbarPositionZero.setRegulatortype(SliderSizetestRegulatorView.SliderSizetestRegulatortype.Postion);
                            mRegulatorArray.add(progressbarPositionZero);

                        }

                        // ----- Scrollbar Horizontal -----
                        else if (TextUtils.equals(itemContentString, SLIDER_NAME_SCROLLBAR_HORIZONTAL)) {

                            // set type to scrollbar horizontal
                            mSlider.setSliderWithContentType(PDESlider.PDESliderContentType.ProgressBar);

                            // controll pagessize for controller 0
                            SliderSizetestRegulatorView scrollbarHorizontalPageSize = new SliderSizetestRegulatorView(SliderSizingTestActivity.this);
                            scrollbarHorizontalPageSize.setSlider(mSlider);
                            scrollbarHorizontalPageSize.setSliderControllerId(0);
                            scrollbarHorizontalPageSize.setRegulatortype(SliderSizetestRegulatorView.SliderSizetestRegulatortype.PageSize);
                            mRegulatorArray.add(scrollbarHorizontalPageSize);

                            // controll postion for controller 0
                            SliderSizetestRegulatorView scrollbarHorizontalPosition = new SliderSizetestRegulatorView(SliderSizingTestActivity.this);
                            scrollbarHorizontalPosition.setSlider(mSlider);
                            scrollbarHorizontalPosition.setSliderControllerId(0);
                            scrollbarHorizontalPosition.setRegulatortype(SliderSizetestRegulatorView.SliderSizetestRegulatortype.Postion);
                            mRegulatorArray.add(scrollbarHorizontalPosition);
                        }

                        // add new regulator views
                        for (ViewGroup vg : mRegulatorArray) {
                            ((ViewGroup) findViewById(R.id.resize_base_rootlayout)).addView(vg, 1);
                        }
                    }
                });
    }


    /**
     * @brief Move position 0 automaticly
     */
    public void startMovingThread(){

        int j=10;

        new Thread(new Runnable() {
            public void run() {

                PDESliderControllerState sliderEvent;
                int value = 100;
                float position;
                int stepValue = 10;
                int i = 0;

                while (i<7) {


                    value = value+stepValue;

                    if (value == 100) {
                        stepValue = -10;
                        i++;
                    }

                    else if (value == 0) stepValue = 10;

                    position = value/100.0f;

                    sliderEvent = new PDESliderControllerState();
                    sliderEvent.setSliderPosition(position);
                    sliderEvent.setType(PDESliderController.PDE_SLIDER_CONTROLLER_EVENT_MASK_ACTION);
                    sliderEvent.setSliderControllerId(0);
                    final PDESliderControllerState finalSliderEvent = sliderEvent;
                    mSlider.post(new Runnable() {
                        public void run() {
                           mSlider.controllHelp(finalSliderEvent);
                        }
                    });

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }
}
