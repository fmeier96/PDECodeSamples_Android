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
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.basescreens.DialogHelper;
import de.telekom.pde.codelibrary.samples.basescreens.ResizeBaseActivity;
import de.telekom.pde.codelibrary.samples.genericstyle.pdeslider.SliderRegulatorHelperGenericView;
import de.telekom.pde.codelibrary.ui.components.sliders.PDEEventSliderControllerState;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESlider;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESliderController;

import java.util.ArrayList;


/**
 * @brief Test Slider Sizing
 */
public class SliderSizingTestActivity extends ResizeBaseActivity {

    //global names of the different sliders
    private final static String SLIDER_NAME_PROGRESSBAR = "ProgessBar";
    private final static String SLIDER_NAME_SCROLLBAR_HORIZONTAL = "Scrollbar Horizontal";
    private final static String SLIDER_NAME_SLIDERBAR_MEDIUM = "SliderBar Medium";
    private final static String SLIDER_NAME_SCROLLBAR_VERTICAL = "Scrollbar Vertical";


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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create the slider
        mSlider = new PDESlider(this);
        //mSlider.setSliderContentType(PDESlider.PDESliderContentType.ProgressBarFlat);

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
        mSliderChoiceArrayList.add(SLIDER_NAME_SCROLLBAR_VERTICAL);
        mSliderChoiceArrayList.add(SLIDER_NAME_SLIDERBAR_MEDIUM);

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
                            mSlider.setSliderContentType(PDESlider.PDESliderContentType.ProgressBarFlat);

                            // control postions for controller 1
                            SliderRegulatorHelperGenericView progressbarPositionOne = new SliderRegulatorHelperGenericView(SliderSizingTestActivity.this);
                            progressbarPositionOne.setSlider(mSlider);
                            progressbarPositionOne.setSliderControllerId(1);
                            progressbarPositionOne.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.Postion);
                            mRegulatorArray.add(progressbarPositionOne);

                            // control postions for controller 0
                            SliderRegulatorHelperGenericView progressbarPositionZero = new SliderRegulatorHelperGenericView(SliderSizingTestActivity.this);
                            progressbarPositionZero.setSlider(mSlider);
                            progressbarPositionZero.setSliderControllerId(0);
                            progressbarPositionZero.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.Postion);
                            mRegulatorArray.add(progressbarPositionZero);

                        }


                        // ----- Scrollbar Horizontal -----

                        else if (TextUtils.equals(itemContentString, SLIDER_NAME_SCROLLBAR_HORIZONTAL)) {

                            // set type to scrollbar horizontal
                            mSlider.setSliderContentType(PDESlider.PDESliderContentType.ScrollBarHorizontal);

                            // control pagessize for controller 0
                            SliderRegulatorHelperGenericView scrollbarHorizontalPageSize = new SliderRegulatorHelperGenericView(SliderSizingTestActivity.this);
                            scrollbarHorizontalPageSize.setSlider(mSlider);
                            scrollbarHorizontalPageSize.setSliderControllerId(0);
                            scrollbarHorizontalPageSize.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.PageSize);
                            mRegulatorArray.add(scrollbarHorizontalPageSize);

                            // control postion for controller 0
                            SliderRegulatorHelperGenericView scrollbarHorizontalPosition = new SliderRegulatorHelperGenericView(SliderSizingTestActivity.this);
                            scrollbarHorizontalPosition.setSlider(mSlider);
                            scrollbarHorizontalPosition.setSliderControllerId(0);
                            scrollbarHorizontalPosition.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.Postion);
                            mRegulatorArray.add(scrollbarHorizontalPosition);
                        }


                        // ----- Scrollbar Vertical -----

                        else if (TextUtils.equals(itemContentString, SLIDER_NAME_SCROLLBAR_VERTICAL)) {

                            // set type to scrollbar horizontal
                            mSlider.setSliderContentType(PDESlider.PDESliderContentType.ScrollBarVertical);

                            // control pagessize for controller 0
                            SliderRegulatorHelperGenericView scrollbarVerticalPageSize = new SliderRegulatorHelperGenericView(SliderSizingTestActivity.this);
                            scrollbarVerticalPageSize.setSlider(mSlider);
                            scrollbarVerticalPageSize.setSliderControllerId(0);
                            scrollbarVerticalPageSize.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.PageSize);
                            mRegulatorArray.add(scrollbarVerticalPageSize);

                            // control postion for controller 0
                            SliderRegulatorHelperGenericView scrollbarVerticalPosition = new SliderRegulatorHelperGenericView(SliderSizingTestActivity.this);
                            scrollbarVerticalPosition.setSlider(mSlider);
                            scrollbarVerticalPosition.setSliderControllerId(0);
                            scrollbarVerticalPosition.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.Postion);
                            mRegulatorArray.add(scrollbarVerticalPosition);
                        }


                        // ----- SliderBar Medium -----

                        else if (TextUtils.equals(itemContentString, SLIDER_NAME_SLIDERBAR_MEDIUM)) {

                            // set type to scrollbar horizontal
                            mSlider.setSliderContentType(PDESlider.PDESliderContentType.SliderBarFlat);

                            // control postion for controller 0
                            SliderRegulatorHelperGenericView sliderBarMediumPosition = new SliderRegulatorHelperGenericView(SliderSizingTestActivity.this);
                            sliderBarMediumPosition.setSlider(mSlider);
                            sliderBarMediumPosition.setSliderControllerId(0);
                            sliderBarMediumPosition.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.Postion);
                            mRegulatorArray.add(sliderBarMediumPosition);
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
    @SuppressWarnings("unused")
    public void startMovingThread(){

        new Thread(new Runnable() {
            public void run() {

                PDEEventSliderControllerState sliderEvent;
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

                    sliderEvent = new PDEEventSliderControllerState();
                    sliderEvent.setSliderPosition(position);
                    sliderEvent.setType(PDESliderController.PDE_SLIDER_CONTROLLER_EVENT_MASK_ACTION);
                    sliderEvent.setSliderControllerId(0);
                    /*final PDEEventSliderControllerState finalSliderEvent = sliderEvent;
                    mSlider.post(new Runnable() {
                        public void run() {
                           mSlider.controllHelp(finalSliderEvent);
                        }
                    });*/

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
