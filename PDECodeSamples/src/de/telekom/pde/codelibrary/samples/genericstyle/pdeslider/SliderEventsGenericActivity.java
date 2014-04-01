/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.genericstyle.pdeslider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.samples.basescreens.DialogHelper;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.sliders.PDEEventSliderControllerState;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESlider;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESliderController;
import de.telekom.pde.codelibrary.ui.elements.common.PDEDrawableDelimiter;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;
import de.telekom.pde.codelibrary.ui.helpers.PDEUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class SliderEventsGenericActivity extends PDEActionBarActivity {

    // global names of the different sliders
    private final static String SLIDER_NAME_PROGRESSBAR = "ProgessBar";
    private final static String SLIDER_NAME_SLIDERBAR_MEDIUM = "SliderBar Medium";

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = SliderEventsGenericActivity.class.getName();

    // slider
    private PDESlider mSlider;

    // different slider contents
    private ArrayList<String> mSliderChoiceArrayList;
    private DialogHelper mChooseDialog;

    // store viewgroups
    private ArrayList<ViewGroup> mRegulatorArray;

    //variables
    private ArrayList<String> mSliderEvents = new ArrayList<String>();
    private ListView mSliderEventList = null;

    private Button mClearButton = null;
    private Button mChooseButton = null;

    private ArrayAdapter<String> mSliderEventListAdapter = null;

    // style flag
    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;


    class SliderArrayAdapter<T> extends ArrayAdapter<T> {
        @SuppressWarnings("unused")
        SliderArrayAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @SuppressWarnings("unused")
        SliderArrayAdapter(Context context, int resource, int textViewResourceId) {
            super(context, resource, textViewResourceId);
        }

        @SuppressWarnings("unused")
        SliderArrayAdapter(Context context, int textViewResourceId, T[] objects) {
            super(context, textViewResourceId, objects);
        }

        @SuppressWarnings("unused")
        SliderArrayAdapter(Context context, int resource, int textViewResourceId, T[] objects) {
            super(context, resource, textViewResourceId, objects);
        }

        SliderArrayAdapter(Context context, int textViewResourceId, List<T> objects) {
            super(context, textViewResourceId, objects);
        }

        @SuppressWarnings("unused")
        SliderArrayAdapter(Context context, int resource, int textViewResourceId, List<T> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = super.getView(position, convertView, parent);
            ((TextView)v).setTextColor(PDEColor.DTUITextColor().getIntegerColor());
            return v;
        }
    }


    // ---------------------------------- intialize --------------------------------------------------------------------


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

        setContentView(R.layout.slider_eventlist_generic_screen);

        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            getSupportActionBar().setTitle("Haptic style/Slider events");
        } else {
            getSupportActionBar().setTitle("Flat style/Slider events");
        }

        // get the root view and set background color (different when darkstyle is on or of in library)
        LinearLayout rootView = (LinearLayout)findViewById(R.id.slider_eventlist_linearlayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        // set top and bottom delimiter
        PDEUtils.setViewBackgroundDrawable(findViewById(R.id.slider_eventlist_top_line), new PDEDrawableDelimiter());
        PDEUtils.setViewBackgroundDrawable(findViewById(R.id.slider_eventlist_topseperator_line), new PDEDrawableDelimiter());
        PDEUtils.setViewBackgroundDrawable(findViewById(R.id.slider_eventlist_bottom_line), new PDEDrawableDelimiter());

        // get the list where we show the slider events
        mSliderEventList = (ListView)findViewById(R.id.slider_eventlist_fieldevents);
        // set the adapter for the list with information about the source and the layout of the elements
        mSliderEventListAdapter = new SliderArrayAdapter<String>(this, R.layout.slider_eventlistitem_generic, mSliderEvents);
        mSliderEventList.setAdapter(mSliderEventListAdapter);
        mSliderEventList.setDivider(new PDEDrawableDelimiter());

        // store slider regulator helpers
        mRegulatorArray = new ArrayList<ViewGroup>();

        // get the clear button and set listener to react on click
        // if the button is clicked -> clear the slider event list
        mClearButton = (Button)findViewById(R.id.slider_eventlist_clearlist_button);
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSliderEvents.clear();
                // inform about change
                mSliderEventListAdapter.notifyDataSetChanged();
            }
        });

        // create and show slider choice dialog
        createSliderChoiceDialog();

        // get the choose button
        mChooseButton = (Button)findViewById(R.id.slider_eventlist_changeslidertype_button);
        mChooseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // show choose dialog
                mChooseDialog.show();
            }
        });

        // setup slider choice
        createSliderChoiceDialog();

        // *************************
        // configure simple PDESlider
        // *************************
        mSlider = (PDESlider) findViewById(R.id.slider_eventlist_pdeSlider);
        mSlider.addListener(this,"onSliderEvent", PDESliderController.PDE_SLIDER_CONTROLLER_EVENT_MASK);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mChooseDialog.setSelectionPos(0);
    }


    // ---------------------- slider event processing -----------------------------------------------------------------


    /**
     * @brief Function called when the PDESlider changes
     *
     *  @param event a PDEEventSliderControllerState object with information about the state of the slider
     *
     */
    @SuppressWarnings("unused")
    public void onSliderEvent(PDEEvent event)
    {
        PDEEventSliderControllerState sliderEvent = (PDEEventSliderControllerState)event;
        String currentValue;
        String timeString;

        // show different output strings on different event types
        if (sliderEvent.getType().equals(PDESliderController.PDE_SLIDER_CONTROLLER_EVENT_MASK_DATA_WILL_CHANGE)) {
            currentValue = "Id "+sliderEvent.getSliderControllerId()+" WillChange";
        } else if (event.getType().equals(PDESliderController.PDE_SLIDER_CONTROLLER_EVENT_MASK_DATA_HAS_CHANGED)) {
            currentValue = "Id "+sliderEvent.getSliderControllerId()+" HasChanged";
        } else {
            currentValue = "<"+event.getType()+">";
        }

        // something to do?
        if(!TextUtils.isEmpty(currentValue))
        {
            // show current time
            timeString = String.format("%02d:%02d:%02d:%03d", Calendar.getInstance().get(Calendar.HOUR_OF_DAY),Calendar.getInstance().get(Calendar.MINUTE),Calendar.getInstance().get(Calendar.SECOND), Calendar.getInstance().get(Calendar.MILLISECOND));
            // add list entry to the list array
            mSliderEvents.add(timeString+" - "+currentValue);
            // inform list about some changes
            mSliderEventListAdapter.notifyDataSetChanged();
        }
    }


    // ---------------------------------- helper -----------------------------------------------------------------------


    /**
     * @brief Add choice list with possible slider contents
     */
    private void createSliderChoiceDialog() {

        // create arraylist
        mSliderChoiceArrayList = new ArrayList<String>();

        // add names
        mSliderChoiceArrayList.add(SLIDER_NAME_PROGRESSBAR);
        mSliderChoiceArrayList.add(SLIDER_NAME_SLIDERBAR_MEDIUM);

        mChooseDialog = new DialogHelper(this, mChooseButton);

        // set the title
        mChooseDialog.getDialog().setTitle("Change Content");

        //set choice information and listener
        DialogHelper.ChoiceListOnItemClickListener choiceListener =
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
                            SliderRegulatorHelperGenericView progressbarPositionOne = new SliderRegulatorHelperGenericView(SliderEventsGenericActivity.this, mStyle);
                            progressbarPositionOne.setSliderControllerId(1);
                            progressbarPositionOne.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.Postion);
                            progressbarPositionOne.setSlider(mSlider);
                            mRegulatorArray.add(progressbarPositionOne);

                            // control postions for controller 0
                            SliderRegulatorHelperGenericView progressbarPositionZero = new SliderRegulatorHelperGenericView(SliderEventsGenericActivity.this, mStyle);
                            progressbarPositionZero.setSliderControllerId(0);
                            progressbarPositionZero.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.Postion);
                            progressbarPositionZero.setSlider(mSlider);
                            mRegulatorArray.add(progressbarPositionZero);

                        }



                        // ----- SliderBar Medium -----

                        else if (TextUtils.equals(itemContentString, SLIDER_NAME_SLIDERBAR_MEDIUM)) {

                            // set type to scrollbar horizontal
                            if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
                                mSlider.setSliderContentType(PDESlider.PDESliderContentType.SliderBarHaptic);
                            } else {
                                mSlider.setSliderContentType(PDESlider.PDESliderContentType.SliderBarFlat);
                            }

                            // control postion for controller 0
                            SliderRegulatorHelperGenericView sliderBarMediumPosition = new SliderRegulatorHelperGenericView(SliderEventsGenericActivity.this, mStyle);
                            sliderBarMediumPosition.setSlider(mSlider);
                            sliderBarMediumPosition.setSliderControllerId(0);
                            sliderBarMediumPosition.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.Postion);
                            mRegulatorArray.add(sliderBarMediumPosition);
                        }

                        // add new regulator views
                        for (ViewGroup vg : mRegulatorArray) {
                            ((ViewGroup) findViewById(R.id.slider_eventlist_linearlayout)).addView(vg, 2);
                        }
                    }
                };

        // setup dialog helper values
        mChooseDialog.setArrayList(mSliderChoiceArrayList);
        mChooseDialog.setChoiceListOnItemClickListener(choiceListener);
    }
}