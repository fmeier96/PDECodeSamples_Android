/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.genericstyle.pdebutton;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.elements.common.PDEDrawableDelimiter;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;
import de.telekom.pde.codelibrary.ui.helpers.PDEUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ButtonEventsGenericActivity extends PDEActionBarActivity {

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = ButtonEventsGenericActivity.class.getName();

    //variables
    private final ArrayList<String> mButtonEvents = new ArrayList<String>();

    private PDEButton mTelekomButton = null;
    private Button mClearButton = null;
    private Button mChangeTypeButton = null;

    private final int[] mSelectedSpinnerIndexes = new int[]{0, 0};
    //private ArrayAdapter<ButtonType> mBackgroundTypeAdapter = null;
    private ArrayAdapter<ButtonContent> mContentAdapter = null;
    private ArrayAdapter<ButtonColor> mColorAdapter = null;
    private ArrayAdapter<String> mButtonEventListAdapter = null;
    private int mDefaultWidth;
    private int mDefaultHeight;
    private int mDefaultMarginTop;
    private int mDefaultMarginBottom;
    private int mDefaultMarginLeft;
    private int mDefaultMarginRight;
    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;


    @SuppressWarnings("FieldCanBeLocal")
    private static final String TELEKOM_BUTTON_TITLE = "Telekom Button";


    class ButtonSelectorArrayAdapter<T> extends ArrayAdapter<T> {
        @SuppressWarnings("unused")
        ButtonSelectorArrayAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }


        @SuppressWarnings("unused")
        ButtonSelectorArrayAdapter(Context context, int resource, int textViewResourceId) {
            super(context, resource, textViewResourceId);
        }


        @SuppressWarnings("unused")
        ButtonSelectorArrayAdapter(Context context, int textViewResourceId, T[] objects) {
            super(context, textViewResourceId, objects);
        }


        @SuppressWarnings("unused")
        ButtonSelectorArrayAdapter(Context context, int resource, int textViewResourceId, T[] objects) {
            super(context, resource, textViewResourceId, objects);
        }


        ButtonSelectorArrayAdapter(Context context, int textViewResourceId, List<T> objects) {
            super(context, textViewResourceId, objects);
        }


        @SuppressWarnings("unused")
        ButtonSelectorArrayAdapter(Context context, int resource, int textViewResourceId, List<T> objects) {
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
            // text != null is a double check but prevents a warning
            if (text != null && !TextUtils.isEmpty(text)) {
                if (PDEString.contains(text.toUpperCase(Locale.US), "haptic".toUpperCase(Locale.US))) {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleHaptic;
                } else if (PDEString.contains(text.toUpperCase(Locale.US), "flat".toUpperCase(Locale.US))) {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;
                }
            }
        }

        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            getSupportActionBar().setTitle("Haptic style/Button events");
        } else {
            getSupportActionBar().setTitle("Flat style/Button events");
        }


        setContentView(R.layout.buttonselector_generic_screen);

        //get the root view and set background color (different when darkstyle is on or of in library)
        LinearLayout rootView = (LinearLayout) findViewById(R.id.buttonselector_linearlayout);
        // set the background to the library background (which depends on the dark/light setting)
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        // set top and bottom delimiter
        PDEUtils.setViewBackgroundDrawable(findViewById(R.id.buttonselector_top_line), new PDEDrawableDelimiter());
        PDEUtils.setViewBackgroundDrawable(findViewById(R.id.buttonselector_bottom_line), new PDEDrawableDelimiter());

        //create the adapter for the content type of the button
        mContentAdapter = new ArrayAdapter<ButtonContent>(this,
                                                          android.R.layout.simple_spinner_item, new ButtonContent[]{
                new ButtonContent("Text", TELEKOM_BUTTON_TITLE, null),
                new ButtonContent("Icon", null, "synchronize_generic_plain_center"),
                new ButtonContent("Icon & Text", TELEKOM_BUTTON_TITLE, "synchronize_generic_plain_center")
        });

        //create the adapter for the color of the button
        mColorAdapter = new ArrayAdapter<ButtonColor>(this,
                                                      android.R.layout.simple_spinner_item, new ButtonColor[]{
                new ButtonColor("Default", null),
                new ButtonColor("LightGray", "DTLightUIInteractive"),
                new ButtonColor("DarkGray", "DTDarkUIInteractive"),
                new ButtonColor("Red", "DTFunctionalRed"),
                new ButtonColor("Green", "DTFunctionalGreen"),
                new ButtonColor("Magenta", "DTMagenta"),
                new ButtonColor("Transparent", "DTTransparentBlack")
        });


        //get the list where we show the button events
        ListView buttonEventList = (ListView) findViewById(R.id.buttonselector_buttoneventslist);
        //set the adapter for the list with information about the source and the layout of the elements
        mButtonEventListAdapter = new ButtonSelectorArrayAdapter<String>(this,
                                                                         R.layout
                                                                                 .buttonselector_generic_eventlistitem,
                                                                         mButtonEvents);
        buttonEventList.setAdapter(mButtonEventListAdapter);
        buttonEventList.setDivider(new PDEDrawableDelimiter());

        //get the clear button and set listener to react on click
        // if the button is clicked -> clear the button event list
        mClearButton = (Button) findViewById(R.id.clearlist_button);
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == mClearButton) {
                    mButtonEvents.clear();
                    //inform about change
                    mButtonEventListAdapter.notifyDataSetChanged();
                }
            }
        });

        //get the change button type button and set click listener
        mChangeTypeButton = (Button) findViewById(R.id.changebuttontype_button);
        mChangeTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == mChangeTypeButton) {
                    // create custom dialog
                    final Dialog dialog = new Dialog(ButtonEventsGenericActivity.this);
                    dialog.setContentView(R.layout.buttonselector_changebutton_generic_dialog);
                    dialog.setTitle("Change Button Style");

                    //get content spinner from the dialog
                    final Spinner contentSpinner = (Spinner) dialog.findViewById(R.id.spinner_1);
                    contentSpinner.setVisibility(View.VISIBLE);
                    // Specify the layout to use when the list of choices appears
                    mContentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Apply the adapter to the spinner*/
                    contentSpinner.setAdapter(mContentAdapter);

                    //get color spinner from the dialog
                    final Spinner colorSpinner = (Spinner) dialog.findViewById(R.id.spinner_2);
                    colorSpinner.setVisibility(View.VISIBLE);
                    // Specify the layout to use when the list of choices appears
                    mColorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Apply the adapter to the spinner
                    colorSpinner.setAdapter(mColorAdapter);

                    //set default selections to the spinners
                    contentSpinner.setSelection(mSelectedSpinnerIndexes[0]);
                    colorSpinner.setSelection(mSelectedSpinnerIndexes[1]);

                    //get the ok button from the dialog and set click listener
                    // if button is clicked -> close the custom dialog and assing new values to PDEButton
                    Button dialogButtonOk = (Button) dialog.findViewById(R.id.dialogButtonOK);
                    dialogButtonOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();

                            mSelectedSpinnerIndexes[0] = contentSpinner.getSelectedItemPosition();
                            mSelectedSpinnerIndexes[1] = colorSpinner.getSelectedItemPosition();
                            onButtonChange((ButtonContent) contentSpinner.getSelectedItem(),
                                           (ButtonColor) colorSpinner.getSelectedItem());
                        }
                    });

                    //get the cancel button from the dialog and set click listener
                    // if button is clicked, close the custom dialog and ignore new values
                    Button dialogButtonCancel = (Button) dialog.findViewById(R.id.dialogButtonCancel);
                    dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    //show the dialog
                    dialog.show();
                }
            }
        });


        // *************************
        // configure simple PDEButton
        // *************************
        mTelekomButton = (PDEButton) findViewById(R.id.telekomButton);
        mTelekomButton.setTitle((mContentAdapter.getItem(mSelectedSpinnerIndexes[0]))
                                        .mButtonTitle);
        mTelekomButton.setIconColored(true);
        mTelekomButton.addListener(this,
                                   "onButtonEventFromAgentController",
                                   PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_MASK_ACTION);
        mDefaultWidth = 0;
        mDefaultHeight = 0;
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            mTelekomButton.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundHaptic);
        } else {
            mTelekomButton.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundFlat);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        if (mDefaultHeight == 0 && mDefaultWidth == 0) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTelekomButton.getLayoutParams();
            if (lp != null) {
                mDefaultWidth = lp.width;
                mDefaultHeight = lp.height;
                mDefaultMarginTop = lp.topMargin;
                mDefaultMarginBottom = lp.bottomMargin;
                mDefaultMarginLeft = lp.leftMargin;
                mDefaultMarginRight = lp.rightMargin;
            }
        }
    }


    /**
     * @param event a PDEEvent object with information about the state of the button
     * @brief Function called when the PDEButton is clicked
     */
    @SuppressWarnings("unused")
    public void onButtonEventFromAgentController(PDEEvent event) {
        String currentValue;
        String timeString;


        //show different output strings on different event types
        if (event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_ACTIVATED)) {
            currentValue = "Activated";
        } else if (event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_DEACTIVATED)) {
            currentValue = "Deactivated";
        } else if (event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)) {
            currentValue = "Selected";
        } else if (event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED)) {
            currentValue = "WillBeSelected";
        } else if (event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_BEGIN_INTERACTION)) {
            currentValue = "BeginInteraction";
        } else if (event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_END_INTERACTION)) {
            currentValue = "EndInteraction";
        } else {
            currentValue = "<" + event.getType() + ">";
        }


        //something to do?
        if (!TextUtils.isEmpty(currentValue)) {
            //show current time
            timeString = String.format(Locale.US,
                                       "%02d:%02d:%02d:%03d",
                                       Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                                       Calendar.getInstance().get(Calendar.MINUTE),
                                       Calendar.getInstance().get(Calendar.SECOND),
                                       Calendar.getInstance().get(Calendar.MILLISECOND));
            //add list entry to the list array
            mButtonEvents.add(timeString + " - " + currentValue);
            //inform list about some changes
            mButtonEventListAdapter.notifyDataSetChanged();
        }
    }


    /**
     * @param buttonContent the new content type of the button
     * @param buttonColor   the new color of the button
     * @brief Function called when the ok button was clicked in the selector dialog
     */
    public void onButtonChange(/*ButtonType buttonType,*/ ButtonContent buttonContent, ButtonColor buttonColor) {
        Drawable icon = null;
        int tmpID = 0;

        //check if button should show an image -> try to get resource id
        if (buttonContent.mImageName != null) {
            tmpID = getResources().getIdentifier(buttonContent.mImageName, "drawable", getPackageName());

        }
        //when there is a resource id try to get the drawable
        if (tmpID != 0) {
            icon = getResources().getDrawable(tmpID);
        }

        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            mTelekomButton.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundHaptic);
        } else {
            mTelekomButton.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundFlat);
        }
        mTelekomButton.setColorWithString(buttonColor.mColor);
        mTelekomButton.setIcon(icon, true);
        mTelekomButton.setTitle(buttonContent.mButtonTitle);

        //set new values to the button
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTelekomButton.getLayoutParams();
        if (lp != null) {
            lp.width = mDefaultWidth + mTelekomButton.getNeededPadding().left + mTelekomButton.getNeededPadding().right;
            lp.height = mDefaultHeight + mTelekomButton.getNeededPadding().top
                        + mTelekomButton.getNeededPadding().bottom;
            lp.topMargin = mDefaultMarginTop - mTelekomButton.getNeededPadding().top;
            lp.bottomMargin = mDefaultMarginBottom - mTelekomButton.getNeededPadding().bottom;
            lp.leftMargin = mDefaultMarginLeft - mTelekomButton.getNeededPadding().left;
            lp.rightMargin = mDefaultMarginRight - mTelekomButton.getNeededPadding().right;
            mTelekomButton.setLayoutParams(lp);
        }


        //clear event list if button changed
        mButtonEvents.clear();
        //inform list about some changes
        mButtonEventListAdapter.notifyDataSetChanged();

    }


    /**
     * @brief Helper Class for the content information of the button.
     */
    private class ButtonContent {
        //variables
        public String mName = "";
        public String mButtonTitle = null;
        public String mImageName = null;


        /**
         * @brief A simple constructor for populating our member variables.
         */
        public ButtonContent(String name, String buttonTitle, String imageName) {
            mName = name;
            mButtonTitle = buttonTitle;
            mImageName = imageName;
        }


        /**
         * @brief return name of this object.
         *
         * The toString method is extremely important to making this class work with a Spinner
         * (or ListView) object because this is the method called when it is trying to represent
         * this object within the control.  If you do not have a toString() method, you WILL
         * get an exception.
         */
        public String toString() {
            return (mName);
        }
    }


    /**
     * @brief Helper Class for the color information of the button.
     */
    private class ButtonColor {
        //variables
        public String mName = "";
        public String mColor = null;


        /**
         * @brief A simple constructor for populating our member variables.
         */
        public ButtonColor(String name, String color) {
            mName = name;
            mColor = color;
        }


        /**
         * @brief return name of this object.
         *
         * The toString method is extremely important to making this class work with a Spinner
         * (or ListView) object because this is the method called when it is trying to represent
         * this object within the control.  If you do not have a toString() method, you WILL
         * get an exception.
         */
        public String toString() {
            return (mName);
        }
    }
}

