/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.pdetext;


// imports
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.R.drawable;
import de.telekom.pde.codelibrary.samples.R.id;
import de.telekom.pde.codelibrary.samples.R.layout;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;


/**
 * @brief Activity for sample screen to show different modes of EditText.
 */
public class NativeEditTextShowcaseActivity extends PDEActivity {

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = NativeEditTextShowcaseActivity.class.getName();

    // inputfield sample variables
    private EditText mEditText1;
    private EditText mEditText2;
    private EditText mEditText3;
    private EditText mEditText4;


    /**
     * @brief Create the Activity.
     *
     * Creates the  Activity to check native edit text focus handling.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout.LayoutParams btnLinearLayoutParams;
        RelativeLayout rootView;
        LinearLayout containerView;

        setContentView(layout.edittext_focustest_screen);

        //get the root view and set background color (different when darkstyle is on or of in library)
        rootView = (RelativeLayout)findViewById(id.edittext_focustest_relativelayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        //get container view which contains the buttons
        containerView = (LinearLayout)findViewById(id.edittext_focustest_container);

        //variable for linear layout params


        //set button size and gaps
        float buttonWidth = PDEBuildingUnits.pixelFromBU(17);
        float buttonHeight = PDEBuildingUnits.pixelFromBU(3);
        float gapY = PDEBuildingUnits.pixelFromBU(5);


        //
        //--------------------------------------------------------------------------------------------------------------
        // Create our first version of the edittext
        //--------------------------------------------------------------------------------------------------------------

        mEditText1 = new EditText(this);
        mEditText1.setHint("Hint");
        mEditText1.setMaxLines(1);
        mEditText1.setSingleLine();
        //set some linear layout parameter to have correct position and size of button
        btnLinearLayoutParams = new LinearLayout.LayoutParams((int)buttonWidth,(int)buttonHeight);
        btnLinearLayoutParams.setMargins(0,0,0,(int)gapY);
        //add button to view
        containerView.addView(mEditText1, btnLinearLayoutParams );


        //
        //--------------------------------------------------------------------------------------------------------------
        // Create our second version of the edittext
        //--------------------------------------------------------------------------------------------------------------

        mEditText2 = new EditText(this);
        mEditText2.setHint("Hint");
        mEditText2.setMaxLines(1);
        mEditText2.setSingleLine();
        mEditText2.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(drawable.synchronize_generic_plain_center),null,null,null);
        mEditText2.setCompoundDrawablePadding(PDEBuildingUnits.BU());
        //set some linear layout parameter to have correct position and size of button
        btnLinearLayoutParams = new LinearLayout.LayoutParams((int)buttonWidth,(int)buttonHeight);
        btnLinearLayoutParams.setMargins(0,0,0,(int)gapY);
        //add button to view
        containerView.addView(mEditText2, btnLinearLayoutParams );


        //
        //--------------------------------------------------------------------------------------------------------------
        // Create our third version of the edittext
        //--------------------------------------------------------------------------------------------------------------

        mEditText3 = new EditText(this);
        mEditText3.setHint("Hint");
        mEditText3.setMaxLines(1);
        mEditText3.setSingleLine();
        //set some linear layout parameter to have correct position and size of button
        btnLinearLayoutParams = new LinearLayout.LayoutParams((int)buttonWidth,(int)buttonHeight);
        btnLinearLayoutParams.setMargins(0,0,0,(int)gapY);
        //add button to view
        containerView.addView(mEditText3, btnLinearLayoutParams );


        //
        //--------------------------------------------------------------------------------------------------------------
        // Create our fourth version of the edittext
        //--------------------------------------------------------------------------------------------------------------

        mEditText4 = new EditText(this);
        mEditText4.setHint("Hint");
        mEditText4.setMaxLines(4);
        mEditText4.setSingleLine();
        mEditText4.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.synchronize_generic_plain_center),null,null,null);
        mEditText4.setCompoundDrawablePadding(PDEBuildingUnits.BU());
        //set some linear layout parameter to have correct position and size of button
        btnLinearLayoutParams = new LinearLayout.LayoutParams((int)buttonWidth,(int)buttonHeight);
        btnLinearLayoutParams.setMargins(0,0,0,(int)gapY);
        //add button to view
        containerView.addView(mEditText4, btnLinearLayoutParams );
    }
}
