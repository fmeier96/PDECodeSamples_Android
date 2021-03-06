/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.commonstyle.scrollbars;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.basescreens.DialogHelper;
import de.telekom.pde.codelibrary.samples.genericstyle.pdeslider.SliderRegulatorHelperGenericView;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.sliders.PDEEventSliderControllerState;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESlider;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESliderController;
import de.telekom.pde.codelibrary.ui.elements.common.PDEDrawableDelimiter;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.helpers.PDEUtils;


public class ScrollBarEventsActivity extends PDEActionBarActivity {

    // global names of the different sliders
//    private final static String SLIDER_NAME_PROGRESSBAR = "ProgessBar";
//    private final static String SLIDER_NAME_SLIDERBAR_MEDIUM = "SliderBar Medium";
    private final static String SLIDER_NAME_SCROLLBAR_HORIZONTAL = "Scrollbar Horizontal";
    private final static String SLIDER_NAME_SCROLLBAR_HANDLE_ONLY_HORIZONTAL = "Scrollbar Handle Only Horizontal ";

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = ScrollBarEventsActivity.class.getName();

    // slider
    private PDESlider mSlider;

    // different slider contents
    private ArrayList<String> mSliderChoiceArrayList;
    private DialogHelper mChooseDialog;

    // store view groups
    private ArrayList<ViewGroup> mRegulatorArray;

    //variables
    private final ArrayList<String> mSliderEvents = new ArrayList<String>();
    private ListView mSliderEventList = null;

    private Button mClearButton = null;
    private Button mChooseButton = null;

    private ArrayAdapter<String> mSliderEventListAdapter = null;


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
            if (v != null) {
                ((TextView) v).setTextColor(PDEColor.DTUITextColor().getIntegerColor());
            }
            return v;
        }
    }

    // ---------------------------------- initialize -------------------------------------------------------------------


    /**
     * @brief Create the activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.slider_eventlist_generic_screen);

        getSupportActionBar().setTitle("Common Style/Scrollbar events");

        // get the root view and set background color (different when darkstyle is on or of in library)
        LinearLayout rootView = (LinearLayout) findViewById(R.id.slider_eventlist_linearlayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        // set top and bottom delimiter
        PDEUtils.setViewBackgroundDrawable(findViewById(R.id.slider_eventlist_top_line), new PDEDrawableDelimiter());
        PDEUtils.setViewBackgroundDrawable(findViewById(R.id.slider_eventlist_topseperator_line),
                                           new PDEDrawableDelimiter());
        PDEUtils.setViewBackgroundDrawable(findViewById(R.id.slider_eventlist_bottom_line),
                                           new PDEDrawableDelimiter());

        // get the list where we show the slider events
        mSliderEventList = (ListView) findViewById(R.id.slider_eventlist_fieldevents);
        // set the adapter for the list with information about the source and the layout of the elements
        mSliderEventListAdapter = new SliderArrayAdapter<String>(this, R.layout.slider_eventlistitem_generic,
                                                                 mSliderEvents);
        mSliderEventList.setAdapter(mSliderEventListAdapter);
        mSliderEventList.setDivider(new PDEDrawableDelimiter());

        // store slider regulator helpers
        mRegulatorArray = new ArrayList<ViewGroup>();

        // get the clear button and set listener to react on click
        // if the button is clicked -> clear the slider event list
        mClearButton = (Button) findViewById(R.id.slider_eventlist_clearlist_button);
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
        mChooseButton = (Button) findViewById(R.id.slider_eventlist_changeslidertype_button);
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
        mSlider.addListener(this, "onSliderEvent", PDESliderController.PDE_SLIDER_CONTROLLER_EVENT_MASK);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mChooseDialog.setSelectionPos(0);
    }

    // ---------------------- slider event processing -----------------------------------------------------------------


    /**
     * @param event a PDEEventSliderControllerState object with information about the state of the slider
     * @brief Function called when the PDESlider changes
     */
    @SuppressWarnings("unused")
    public void onSliderEvent(PDEEvent event) {
        PDEEventSliderControllerState sliderEvent = (PDEEventSliderControllerState) event;
        String currentValue;
        String timeString;

        // show different output strings on different event types
        if (sliderEvent.getType().equals(PDESliderController.PDE_SLIDER_CONTROLLER_EVENT_DATA_WILL_CHANGE)) {
            currentValue = "Id " + sliderEvent.getSliderControllerId() + " WillChange";
        } else if (event.getType().equals(PDESliderController.PDE_SLIDER_CONTROLLER_EVENT_DATA_HAS_CHANGED)) {
            currentValue = "Id " + sliderEvent.getSliderControllerId() + " HasChanged";
        } else {
            currentValue = "<" + event.getType() + ">";
        }

        // something to do?
        if (!TextUtils.isEmpty(currentValue)) {
            // show current time
            timeString = String.format(Locale.US,
                                       "%02d:%02d:%02d:%03d",
                                       Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                                       Calendar.getInstance().get(Calendar.MINUTE),
                                       Calendar.getInstance().get(Calendar.SECOND),
                                       Calendar.getInstance().get(Calendar.MILLISECOND));
            // add list entry to the list array
            mSliderEvents.add(timeString + " - " + currentValue);
            // inform list about some changes
            mSliderEventListAdapter.notifyDataSetChanged();
        }
    }

    // ---------------------------------- helper -----------------------------------------------------------------------


    /**
     * @brief Add choice list with possible slider contents
     */
    private void createSliderChoiceDialog() {

        // create array list
        mSliderChoiceArrayList = new ArrayList<String>();

        // add names
//        mSliderChoiceArrayList.add(SLIDER_NAME_PROGRESSBAR);
        mSliderChoiceArrayList.add(SLIDER_NAME_SCROLLBAR_HANDLE_ONLY_HORIZONTAL);
        mSliderChoiceArrayList.add(SLIDER_NAME_SCROLLBAR_HORIZONTAL);
//        mSliderChoiceArrayList.add(SLIDER_NAME_SLIDERBAR_MEDIUM);

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
                            if (vg.getParent() != null) {
                                ((ViewGroup) vg.getParent()).removeView(vg);
                            }
                        }

                        // remove old regulators
                        mRegulatorArray.clear();

                        // react on list selection

                        if (TextUtils.equals(itemContentString, SLIDER_NAME_SCROLLBAR_HORIZONTAL)) {

                            // ----- Scrollbar Horizontal -----

                            // set type to scrollbar horizontal
                            mSlider.setSliderContentType(PDESlider.PDESliderContentType.ScrollBarHorizontal);

                            // control pages size for controller 0
                            SliderRegulatorHelperGenericView scrollbarHorizontalPageSize
                                    = new SliderRegulatorHelperGenericView(ScrollBarEventsActivity.this);
                            scrollbarHorizontalPageSize.setSlider(mSlider);
                            scrollbarHorizontalPageSize.setSliderControllerId(0);
                            scrollbarHorizontalPageSize.setRegulatortype(
                                    SliderRegulatorHelperGenericView.SliderRegulatorHelperType.PageSize);
                            mRegulatorArray.add(scrollbarHorizontalPageSize);

                            // control position for controller 0
                            SliderRegulatorHelperGenericView scrollbarHorizontalPosition
                                    = new SliderRegulatorHelperGenericView(ScrollBarEventsActivity.this);
                            scrollbarHorizontalPosition.setSlider(mSlider);
                            scrollbarHorizontalPosition.setSliderControllerId(0);
                            scrollbarHorizontalPosition.setRegulatortype(
                                    SliderRegulatorHelperGenericView.SliderRegulatorHelperType.Postion);
                            mRegulatorArray.add(scrollbarHorizontalPosition);
                        } else if (TextUtils.equals(itemContentString, SLIDER_NAME_SCROLLBAR_HANDLE_ONLY_HORIZONTAL)) {

                            // ----- Scrollbar Handle Only Horizontal -----

                            // set type to scrollbar horizontal
                            mSlider.setSliderContentType(PDESlider.PDESliderContentType.ScrollBarHandleOnlyHorizontal);

                            // control pages size for controller 0
                            SliderRegulatorHelperGenericView scrollbarHorizontalPageSize
                                    = new SliderRegulatorHelperGenericView(ScrollBarEventsActivity.this);
                            scrollbarHorizontalPageSize.setSlider(mSlider);
                            scrollbarHorizontalPageSize.setSliderControllerId(0);
                            scrollbarHorizontalPageSize.setRegulatortype(
                                    SliderRegulatorHelperGenericView.SliderRegulatorHelperType.PageSize);
                            mRegulatorArray.add(scrollbarHorizontalPageSize);

                            // control position for controller 0
                            SliderRegulatorHelperGenericView scrollbarHorizontalPosition
                                    = new SliderRegulatorHelperGenericView(ScrollBarEventsActivity.this);
                            scrollbarHorizontalPosition.setSlider(mSlider);
                            scrollbarHorizontalPosition.setSliderControllerId(0);
                            scrollbarHorizontalPosition.setRegulatortype(
                                    SliderRegulatorHelperGenericView.SliderRegulatorHelperType.Postion);
                            mRegulatorArray.add(scrollbarHorizontalPosition);
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
