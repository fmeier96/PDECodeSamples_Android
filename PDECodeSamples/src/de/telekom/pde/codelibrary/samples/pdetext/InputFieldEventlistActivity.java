/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.pdetext;


// imports
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.inputfields.PDEInputField;
import de.telekom.pde.codelibrary.ui.components.inputfields.PDEInputFieldEvent;
import de.telekom.pde.codelibrary.ui.elements.common.PDEDrawableDelimiter;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.helpers.PDEUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * @brief Activity class for sample screen to select all different types of Deutsche Telekom Button and set event output.
 */
public class InputFieldEventlistActivity extends PDEActivity {

    /**
     * @brief Global tag for log outputs.
     */
	@SuppressWarnings("unused")
    private final static String LOG_TAG = InputFieldEventlistActivity.class.getName();

    //variables
    private ArrayList<String> mInputFieldEvents = new ArrayList<String>();
    private ListView mInputFieldEventList = null;

    private PDEInputField mInputField = null;
    private Button mClearButton = null;

    private ArrayAdapter<String> mInputFieldEventListAdapter = null;


    class InputFieldArrayAdapter<T> extends ArrayAdapter<T> {
        InputFieldArrayAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        InputFieldArrayAdapter(Context context, int resource, int textViewResourceId) {
            super(context, resource, textViewResourceId);
        }

        InputFieldArrayAdapter(Context context, int textViewResourceId, T[] objects) {
            super(context, textViewResourceId, objects);
        }

        InputFieldArrayAdapter(Context context, int resource, int textViewResourceId, T[] objects) {
            super(context, resource, textViewResourceId, objects);
        }

        InputFieldArrayAdapter(Context context, int textViewResourceId, List<T> objects) {
            super(context, textViewResourceId, objects);
        }

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
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.inputfield_eventlist_screen);

        //get the root view and set background color (different when darkstyle is on or of in library)
        LinearLayout rootView = (LinearLayout)findViewById(R.id.inputfield_eventlist_linearlayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        // set top and bottom delimiter
        PDEUtils.setViewBackgroundDrawable(findViewById(R.id.inputfield_top_line), new PDEDrawableDelimiter());
        PDEUtils.setViewBackgroundDrawable(findViewById(R.id.inputfield_bottom_line), new PDEDrawableDelimiter());

        //get the list where we show the button events
        mInputFieldEventList = (ListView)findViewById(R.id.inputfield_fieldevents);
        //set the adapter for the list with information about the source and the layout of the elements
        mInputFieldEventListAdapter = new InputFieldArrayAdapter<String>(this, R.layout.inputfield_eventlistitem, mInputFieldEvents);
        mInputFieldEventList.setAdapter(mInputFieldEventListAdapter);
        mInputFieldEventList.setDivider(new PDEDrawableDelimiter());

        //get the clear button and set listener to react on click
        // if the button is clicked -> clear the button event list
        mClearButton = (Button)findViewById(R.id.clearlist_button);
        mClearButton.setOnClickListener(new View.OnClickListener() {
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
        mInputField = (PDEInputField)findViewById(R.id.pdeInputField);
        mInputField.addListener(this,"onInputFieldEventFromAgentController",PDEInputField.PDEInputFieldEventMask);
    }


    /**
     * @brief Function called when the PDEButton is clicked
     *
     *  @param event a PDEEvent object with information about the state of the button
     *
     */
    public void onInputFieldEventFromAgentController(PDEEvent event)
    {
        PDEInputFieldEvent inputFieldEvent = (PDEInputFieldEvent)event;
        String currentValue = null;
        String timeString = null;
        double val = 0;

        //show different output strings on different event types
        if (event.getType().equals(PDEInputField.PDEInputFieldEventActionGotFocus)) {
            currentValue = "GotFocus";
        } else if (event.getType().equals(PDEInputField.PDEInputFieldEventActionLostFocus)) {
            currentValue = "LostFocus";
        } else if (event.getType().equals(PDEInputField.PDEInputFieldEventActionBeforeTextChanged)) {
            currentValue = String.format("%s\n\t\t\t\t\t\t(\"%s\" startPos:%d lengthAfter:%d)","BeforeTextChanged",inputFieldEvent.getCurrentText(),inputFieldEvent.getStartPos(), inputFieldEvent.getLengthAfter());
        } else if (event.getType().equals(PDEInputField.PDEInputFieldEventActionOnTextChanged)) {
            currentValue = String.format("%s\n\t\t\t\t\t\t(\"%s\" startPos:%d lengthBefore:%d)","OnTextChanged",inputFieldEvent.getCurrentText(),inputFieldEvent.getStartPos(),inputFieldEvent.getLengthBefore());
        } else if (event.getType().equals(PDEInputField.PDEInputFieldEventActionAfterTextChanged)) {
            currentValue = String.format("%s\n\t\t\t\t\t\t(\"%s\")","AfterTextChanged",inputFieldEvent.getCurrentText());
        } else if (event.getType().equals(PDEInputField.PDEInputFieldEventActionShouldDoEditorAction)) {
            currentValue = "ShouldDoEditorAction";
        } else if (event.getType().equals(PDEInputField.PDEInputFieldEventActionShouldClearText)) {
            currentValue = "ShouldClearText";
        } else if (event.getType().equals(PDEInputField.PDEInputFieldEventActionDidClearText)) {
            currentValue = "DidClearText";
        } else {
            currentValue = "<"+event.getType()+">";
        }

        //something to do?
        if(!TextUtils.isEmpty(currentValue))
        {
            //show current time
            timeString = String.format("%02d:%02d:%02d:%03d",Calendar.getInstance().get(Calendar.HOUR_OF_DAY),Calendar.getInstance().get(Calendar.MINUTE),Calendar.getInstance().get(Calendar.SECOND), Calendar.getInstance().get(Calendar.MILLISECOND));
            //add list entry to the list array
            mInputFieldEvents.add(timeString+" - "+currentValue);
            //inform list about some changes
            mInputFieldEventListAdapter.notifyDataSetChanged();
        }
    }
}
