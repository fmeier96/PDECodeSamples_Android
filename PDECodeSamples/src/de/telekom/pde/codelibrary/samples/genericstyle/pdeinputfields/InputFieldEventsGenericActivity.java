/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.genericstyle.pdeinputfields;


// imports

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.inputfields.PDEInputField;
import de.telekom.pde.codelibrary.ui.components.inputfields.PDEInputFieldEvent;
import de.telekom.pde.codelibrary.ui.elements.common.PDEDrawableDelimiter;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;
import de.telekom.pde.codelibrary.ui.helpers.PDEUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * @brief Activity to show events fired by PDEInputField.
 */
public class InputFieldEventsGenericActivity extends PDEActionBarActivity {

    /**
     * @brief Global tag for log outputs.
     */
	@SuppressWarnings("unused")
    private final static String LOG_TAG = InputFieldEventsGenericActivity.class.getName();

    //variables
    private ArrayList<String> mInputFieldEvents = new ArrayList<String>();

    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;

    private ArrayAdapter<String> mInputFieldEventListAdapter = null;


    class InputFieldArrayAdapter<T> extends ArrayAdapter<T> {
        @SuppressWarnings("unused")
        InputFieldArrayAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @SuppressWarnings("unused")
        InputFieldArrayAdapter(Context context, int resource, int textViewResourceId) {
            super(context, resource, textViewResourceId);
        }

        @SuppressWarnings("unused")
        InputFieldArrayAdapter(Context context, int textViewResourceId, T[] objects) {
            super(context, textViewResourceId, objects);
        }

        @SuppressWarnings("unused")
        InputFieldArrayAdapter(Context context, int resource, int textViewResourceId, T[] objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @SuppressWarnings("unused")
        InputFieldArrayAdapter(Context context, int textViewResourceId, List<T> objects) {
            super(context, textViewResourceId, objects);
        }

        @SuppressWarnings("unused")
        InputFieldArrayAdapter(Context context, int resource, int textViewResourceId, List<T> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = super.getView(position, convertView, parent);
            ((TextView)v).setTextColor(PDEColor.DTUITextColor().getIntegerColor());
            return v;
        }
    }

    /**
     * @brief Create the Activity.
     *
     * Creates the  Activity where the user can select the type of PDEButton he wants.
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

        // set the title
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            getSupportActionBar().setTitle("Haptic style/Inputfield events");
        } else {
            getSupportActionBar().setTitle("Flat style/Inputfield events");
        }


        setContentView(R.layout.inputfield_eventlist_screen);

        // get the root view and set background color (depending on color style of the library )
        LinearLayout rootView = (LinearLayout)findViewById(R.id.inputfield_eventlist_linearlayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        // set top and bottom delimiter
        PDEUtils.setViewBackgroundDrawable(findViewById(R.id.inputfield_top_line), new PDEDrawableDelimiter());
        PDEUtils.setViewBackgroundDrawable(findViewById(R.id.inputfield_bottom_line), new PDEDrawableDelimiter());

        // get the list where we show the button events
        ListView inputFieldEventList = (ListView)findViewById(R.id.inputfield_fieldevents);
        //set the adapter for the list with information about the source and the layout of the elements
        mInputFieldEventListAdapter = new InputFieldArrayAdapter<String>(this,
                R.layout.inputfield_eventlistitem,
                mInputFieldEvents);
        inputFieldEventList.setAdapter(mInputFieldEventListAdapter);
        inputFieldEventList.setDivider(new PDEDrawableDelimiter());

        // get the clear button and set listener to react on click
        // if the button is clicked -> clear the button event list
        Button clearButton = (Button)findViewById(R.id.clearlist_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInputFieldEvents.clear();
                //inform about change
                mInputFieldEventListAdapter.notifyDataSetChanged();
            }
        });

        // *************************
        // configure simple PDEInputField
        // *************************
        PDEInputField inputField = (PDEInputField)findViewById(R.id.pdeInputField);
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            inputField.setInputFieldBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundTextHaptic);
        }
        inputField.addListener(this,"onInputFieldEventFromAgentController",PDEInputField.PDE_INPUTFIELD_EVENT_MASK_ACTION);
    }


    /**
     * @brief Callback function of the PDETextField
     *
     *  @param event a PDEEvent object with information about the state of the button
     *
     */
    @SuppressWarnings("unused")
    public void onInputFieldEventFromAgentController(PDEEvent event)
    {
        PDEInputFieldEvent inputFieldEvent = (PDEInputFieldEvent)event;
        String currentValue;
        String timeString;

        //show different output strings on different event types
        if (event.getType().equals(PDEInputField.PDE_INPUTFIELD_EVENT_ACTION_GOT_FOCUS)) {
            currentValue = "GotFocus";
        } else if (event.getType().equals(PDEInputField.PDE_INPUTFIELD_EVENT_ACTION_LOST_FOCUS)) {
            currentValue = "LostFocus";
        } else if (event.getType().equals(PDEInputField.PDE_INPUTFIELD_EVENT_ACTION_BEFORE_TEXT_CHANGED)) {
            currentValue = String.format("%s\n\t\t\t\t\t\t(\"%s\" startPos:%d lengthAfter:%d)",
                    "BeforeTextChanged",
                    inputFieldEvent.getCurrentText(),
                    inputFieldEvent.getStartPos(),
                    inputFieldEvent.getLengthAfter());
        } else if (event.getType().equals(PDEInputField.PDE_INPUTFIELD_EVENT_ACTION_ON_TEXT_CHANGED)) {
            currentValue = String.format("%s\n\t\t\t\t\t\t(\"%s\" startPos:%d lengthBefore:%d)",
                    "OnTextChanged",
                    inputFieldEvent.getCurrentText(),
                    inputFieldEvent.getStartPos(),
                    inputFieldEvent.getLengthBefore());
        } else if (event.getType().equals(PDEInputField.PDE_INPUTFIELD_EVENT_ACTION_AFTER_TEXT_CHANGED)) {
            currentValue = String.format("%s\n\t\t\t\t\t\t(\"%s\")",
                    "AfterTextChanged",
                    inputFieldEvent.getCurrentText());
        } else if (event.getType().equals(PDEInputField.PDE_INPUTFIELD_EVENT_ACTION_SHOULD_DO_EDITOR_ACTION)) {
            currentValue = "ShouldDoEditorAction";
        } else if (event.getType().equals(PDEInputField.PDE_INPUTFIELD_EVENT_ACTION_SHOULD_CLEAR_TEXT)) {
            currentValue = "ShouldClearText";
        } else if (event.getType().equals(PDEInputField.PDE_INPUTFIELD_EVENT_ACTION_DID_CLEAR_TEXT)) {
            currentValue = "DidClearText";
        } else {
            currentValue = "<" + event.getType() + ">";
        }

        // something to do?
        if(!TextUtils.isEmpty(currentValue))
        {
            // show current time
            timeString = String.format("%02d:%02d:%02d:%03d",
                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                    Calendar.getInstance().get(Calendar.MINUTE),
                    Calendar.getInstance().get(Calendar.SECOND),
                    Calendar.getInstance().get(Calendar.MILLISECOND));
            // add list entry to the list array
            mInputFieldEvents.add(timeString + " - " + currentValue);
            // inform list about some changes
            mInputFieldEventListAdapter.notifyDataSetChanged();
        }
    }
}
