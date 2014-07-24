/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2013. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.basescreens;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.helpers.GridBackgroundDrawable;
import de.telekom.pde.codelibrary.ui.helpers.PDEUtils;

import java.util.ArrayList;


/**
 * @brief Activity class for the sizing test screen.
 *
 * Ignore lint warning "not registered in the manifest" because this is not needed there.
 */
@SuppressLint("Registered")
public class DefaultResizeActivity extends ResizeBaseActivity {

    /**
     * @brief Global tag for log outputs.
     */
    //private final static String LOG_TAG = DefaultResizeActivity.class.getName();


    // Enum for the choice buttons to know which to use.
    public enum LEFT_RIGHT_BUTTON {
        LEFT,
        RIGHT
    }


    // Private variables to handle touches with the thumb
    private Button mLeftChoiceButton;
    private Button mRightChoiceButton;
    private ToggleButton mGridButton;
    private ToggleButton mBorderButton;

    // Two predefined dialogs
    private DialogHelper mLeftChoiceDialogHelper;
    private DialogHelper mRightChoiceDialogHelper;


    /**
     * @brief Create the Activity.
     * @param savedInstanceState The saved instance state to recreate.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.resize_base_screen_default_bottom_menu,
                                    (ViewGroup) findViewById(R.id.resize_base_screen_bottom_menu_container));

        // remember some values
        mLeftChoiceButton = ((Button) findViewById(R.id.leftChoice_button));
        mRightChoiceButton = ((Button) findViewById(R.id.rightChoice_button));
        mGridButton = (ToggleButton) findViewById(R.id.grid_button);
        mBorderButton = (ToggleButton) findViewById(R.id.border_button);

        // create some predefined dialog helpers for left and right button
        mLeftChoiceDialogHelper = new DialogHelper(this, mLeftChoiceButton);
        mRightChoiceDialogHelper = new DialogHelper(this, mRightChoiceButton);

        //default enable grid mode, and border mode button
        showGridModeButton(true);
        showBorderButton(true);

        // default container view offset
        setContainerOffset(PDEBuildingUnits.BU(), PDEBuildingUnits.BU());
    }


    /**
     * @brief Enable or disable the grid mode buttno.
     */
    public void showGridModeButton(boolean show) {
        if (mGridButton != null) {
            if (show) {
                mGridButton.setVisibility(View.VISIBLE);
                //set the button listeners
                mGridButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                        if (checked) {
                            PDEUtils.setViewBackgroundDrawable(mResizeLayoutContainer,
                                                               new GridBackgroundDrawable(PDEColor.valueOf("#4C3366AA")));
                        } else {
                            PDEUtils.setViewBackgroundDrawable(mResizeLayoutContainer, null);
                        }
                    }
                });
            } else {
                mGridButton.setVisibility(View.GONE);
                mGridButton.setOnCheckedChangeListener(null);
            }
        }
    }


    public void showBorderButton(boolean show) {
        if (mBorderButton != null) {
            if (show) {
                mBorderButton.setVisibility(View.VISIBLE);
                //set the button listeners
                mBorderButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                        if (checked) {
                            setShowModeOfViewBounds(VIEW_BOUNDS_SHOW_MODE.ALWAYS);
                        } else {
                            setShowModeOfViewBounds(VIEW_BOUNDS_SHOW_MODE.NEVER);
                        }
                    }
                });
            } else {
                mBorderButton.setVisibility(View.GONE);
                mBorderButton.setOnCheckedChangeListener(null);
            }
        }
    }


    /**
     * @brief Create a dialog with content for the left or right button and listen to listview item clicks.
     */
    protected void addChoiceArrayList(LEFT_RIGHT_BUTTON mode,
                                      String buttonText,
                                      ArrayList<String> arrayList,
                                      DialogHelper.ChoiceListOnItemClickListener clickListener) {
        final DialogHelper dialogHelper;

        // check left or right button
        if (mode == LEFT_RIGHT_BUTTON.LEFT) {
            dialogHelper = mLeftChoiceDialogHelper;
            mLeftChoiceButton.setText(buttonText);
        } else {
            dialogHelper = mRightChoiceDialogHelper;
            mRightChoiceButton.setText(buttonText);
        }

        // set the title
        dialogHelper.getDialog().setTitle(buttonText);

        //no content -> disable listeners and hide button again
        if (arrayList == null) {
            dialogHelper.setArrayList(null);
            dialogHelper.setChoiceListOnItemClickListener(null);
            dialogHelper.getInvokingButton().setVisibility(View.GONE);
            return;
        }

        // init dialog helper values
        dialogHelper.setArrayList(arrayList);
        dialogHelper.setChoiceListOnItemClickListener(clickListener);
        dialogHelper.getInvokingButton().setVisibility(View.VISIBLE);
        dialogHelper.getInvokingButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show dialog
                dialogHelper.show();
            }
        });
    }


    /**
     * @brief Set the selection of a listview item (predefined left or right listview).
     */
    protected void setSelectionPos(LEFT_RIGHT_BUTTON mode, int selectionPos) {
        if (mode == LEFT_RIGHT_BUTTON.LEFT) {
            mLeftChoiceDialogHelper.setSelectionPos(selectionPos);
        } else {
            mRightChoiceDialogHelper.setSelectionPos(selectionPos);
        }
    }
}
