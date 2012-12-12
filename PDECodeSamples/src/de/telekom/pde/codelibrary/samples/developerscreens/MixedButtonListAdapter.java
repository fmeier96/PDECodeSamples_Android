/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;


// imports
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.PDECodeLibrary;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;

import java.util.ArrayList;


/**
 * @brief Adapter to get a list of all possible Text/Icon options of a PDEButton (Rect background).
 */
public class MixedButtonListAdapter extends BaseAdapter {
    private Context mContext=null;
    public ArrayList<ViewGroup> mButtons;

    /**
     * @brief Constructor.
     *
     * Creates and fills a array with all buttons.
     *
     * @param context
     */
    MixedButtonListAdapter(Context context)
    {
        PDEButton button;
        // remember context
        mContext = context;
        // create data array
        mButtons = new ArrayList<ViewGroup>();
        // create Buttons
        createButtons();
    }


    /**
     * @brief Function to create all different buttons and add the to array.
     *
     */
    private void createButtons() {

        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a default rect button
        //
        //------------------------------------------------------------------------------------------------------------------
        // create and configure
        PDEButton buttonDefault = new PDEButton(mContext);
        buttonDefault.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundRect);
        buttonDefault.setTitle("Default");
        //add button
        addButton(buttonDefault);



        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a rect rect button with left aligned text
        //
        //------------------------------------------------------------------------------------------------------------------
        // create and configure
        PDEButton buttonTextLeftAligned = new PDEButton(mContext);
        buttonTextLeftAligned.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundRect);
        buttonTextLeftAligned.setAlignment(PDEConstants.PDEAlignment.PDEAlignmentLeft);
        buttonTextLeftAligned.setTitle("Text left aligned");
        //add button
        addButton(buttonTextLeftAligned);



        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a rect rect button with right aligned text
        //
        //------------------------------------------------------------------------------------------------------------------
        // create and configure
        PDEButton buttonTextRightAligned = new PDEButton(mContext);
        buttonTextRightAligned.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundRect);
        buttonTextRightAligned.setAlignment(PDEConstants.PDEAlignment.PDEAlignmentRight);
        buttonTextRightAligned.setTitle("Text right aligned");
        //add button
        addButton(buttonTextRightAligned);



        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a rect button with default text and icon alignment
        //
        //  Set the icon. We are using a monochome icon, so we set the colored flag. The icon color is then adjusted to
        //  to the text color automatically.
        //
        //------------------------------------------------------------------------------------------------------------------
        // create and configure
        PDEButton buttonIconAndText = new PDEButton(mContext);
        buttonIconAndText.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundRect);
        buttonIconAndText.setTitle("Text with icon (default)");
        buttonIconAndText.setIcon( mContext.getResources().getDrawable(R.drawable.synchronize_generic_plain_center) );
        //add button
        addButton(buttonIconAndText);



        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a rect button with text and (left)icon left aligned.
        //
        //  Set the icon. We are using a monochome icon, so we set the colored flag. The icon color is then adjusted to
        //  to the text color automatically.
        //
        //------------------------------------------------------------------------------------------------------------------
        // create and configure
        PDEButton buttonIconAndTextLeft = new PDEButton(mContext);
        buttonIconAndTextLeft.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundRect);
        buttonIconAndTextLeft.setTitle("Text with icon (left)");
        buttonIconAndTextLeft.setIcon(mContext.getResources().getDrawable(R.drawable.synchronize_generic_plain_center));
        buttonIconAndTextLeft.setAlignment(PDEConstants.PDEAlignment.PDEAlignmentLeft);
        //add button
        addButton(buttonIconAndTextLeft);



        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a rect button with text and (left)icon right aligned.
        //
        //  Set the icon. We are using a monochome icon, so we set the colored flag. The icon color is then adjusted to
        //  to the text color automatically.
        //
        //------------------------------------------------------------------------------------------------------------------
        // create and configure
        PDEButton buttonIconAndTextRight = new PDEButton(mContext);
        buttonIconAndTextRight.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundRect);
        buttonIconAndTextRight.setTitle("Text with icon (right)");
        buttonIconAndTextRight.setIcon(mContext.getResources().getDrawable(R.drawable.synchronize_generic_plain_center));
        buttonIconAndTextRight.setAlignment(PDEConstants.PDEAlignment.PDEAlignmentRight);
        //add button
        addButton(buttonIconAndTextRight);



        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a rect button with default text and (left)icon alignment
        //
        //  Set the icon. We are using a monochome icon, so we set the colored flag. The icon color is then adjusted to
        //  to the text color automatically.
        //
        //------------------------------------------------------------------------------------------------------------------
        // create and configure
        PDEButton buttonIconRightOfText = new PDEButton(mContext);
        buttonIconRightOfText.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundRect);
        buttonIconRightOfText.setTitle("Text with right icon");
        buttonIconRightOfText.setIcon( mContext.getResources().getDrawable(R.drawable.synchronize_generic_plain_center) );
        buttonIconRightOfText.setIconAlignment(PDEButton.PDEButtonIconAlignment.PDEButtonIconAlignmentRightAttached);
        //add button
        addButton(buttonIconRightOfText);



        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a rect button with text and (right)icon left aligned.
        //
        //  Set the icon. We are using a monochome icon, so we set the colored flag. The icon color is then adjusted to
        //  to the text color automatically.
        //
        //------------------------------------------------------------------------------------------------------------------
        // create and configure
        PDEButton buttonIconRightOfTextLeft = new PDEButton(mContext);
        buttonIconRightOfTextLeft.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundRect);
        buttonIconRightOfTextLeft.setTitle("Text with right icon (left)");
        buttonIconRightOfTextLeft.setIcon( mContext.getResources().getDrawable(R.drawable.synchronize_generic_plain_center) );
        buttonIconRightOfTextLeft.setAlignment(PDEConstants.PDEAlignment.PDEAlignmentLeft);
        buttonIconRightOfTextLeft.setIconAlignment(PDEButton.PDEButtonIconAlignment.PDEButtonIconAlignmentRightAttached);
        //add button
        addButton(buttonIconRightOfTextLeft);



        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a rect button with text and (right)icon right aligned.
        //
        //  Set the icon. We are using a monochome icon, so we set the colored flag. The icon color is then adjusted to
        //  to the text color automatically.
        //
        //------------------------------------------------------------------------------------------------------------------
        // create and configure
        PDEButton buttonIconRightOfTextRight = new PDEButton(mContext);
        buttonIconRightOfTextRight.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundRect);
        buttonIconRightOfTextRight.setTitle("Text with right icon (right)");
        buttonIconRightOfTextRight.setIcon( mContext.getResources().getDrawable(R.drawable.synchronize_generic_plain_center) );
        buttonIconRightOfTextRight.setAlignment(PDEConstants.PDEAlignment.PDEAlignmentRight);
        buttonIconRightOfTextRight.setIconAlignment(PDEButton.PDEButtonIconAlignment.PDEButtonIconAlignmentRightAttached);
        //add button
        addButton(buttonIconRightOfTextRight);



        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a rect button with text center and (left)icon left aligned.
        //
        //  Set the icon. We are using a monochome icon, so we set the colored flag. The icon color is then adjusted to
        //  to the text color automatically.
        //
        //------------------------------------------------------------------------------------------------------------------
        // create and configure
        PDEButton buttonIconLeftTextCenter = new PDEButton(mContext);
        buttonIconLeftTextCenter.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundRect);
        buttonIconLeftTextCenter.setTitle("Icon left, text center");
        buttonIconLeftTextCenter.setIcon(mContext.getResources().getDrawable(R.drawable.synchronize_generic_plain_center));
        buttonIconLeftTextCenter.setIconAlignment(PDEButton.PDEButtonIconAlignment.PDEButtonIconAlignmentLeft);
        //add button
        addButton(buttonIconLeftTextCenter);


        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a rect button with text right and (left)icon left aligned.
        //
        //  Set the icon. We are using a monochome icon, so we set the colored flag. The icon color is then adjusted to
        //  to the text color automatically.
        //
        //------------------------------------------------------------------------------------------------------------------

        // create and configure
        PDEButton buttonIconLeftTextRight = new PDEButton(mContext);
        buttonIconLeftTextRight.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundRect);
        buttonIconLeftTextRight.setTitle("Icon left, text right");
        buttonIconLeftTextRight.setIcon(mContext.getResources().getDrawable(R.drawable.synchronize_generic_plain_center));
        buttonIconLeftTextRight.setAlignment(PDEConstants.PDEAlignment.PDEAlignmentRight);
        buttonIconLeftTextRight.setIconAlignment(PDEButton.PDEButtonIconAlignment.PDEButtonIconAlignmentLeft);
        //add button
        addButton(buttonIconLeftTextRight);


        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a rect button with text center and (right)icon right aligned.
        //
        //  Set the icon. We are using a monochome icon, so we set the colored flag. The icon color is then adjusted to
        //  to the text color automatically.
        //
        //------------------------------------------------------------------------------------------------------------------
        // create and configure
        PDEButton buttonIconRightTextCenter = new PDEButton(mContext);
        buttonIconRightTextCenter.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundRect);
        buttonIconRightTextCenter.setTitle("Icon right, text center");
        buttonIconRightTextCenter.setIcon(mContext.getResources().getDrawable(R.drawable.synchronize_generic_plain_center));
        buttonIconRightTextCenter.setIconAlignment(PDEButton.PDEButtonIconAlignment.PDEButtonIconAlignmentRight);
        //add button
        addButton(buttonIconRightTextCenter);



        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a rect button with text left and (right)icon right aligned.
        //
        //  Set the icon. We are using a monochome icon, so we set the colored flag. The icon color is then adjusted to
        //  to the text color automatically.
        //
        //------------------------------------------------------------------------------------------------------------------
        // create and configure
        PDEButton buttonIconRightTextLeft = new PDEButton(mContext);
        buttonIconRightTextLeft.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundRect);
        buttonIconRightTextLeft.setTitle("Icon right, text left");
        buttonIconRightTextLeft.setIcon(mContext.getResources().getDrawable(R.drawable.synchronize_generic_plain_center));
        buttonIconRightTextLeft.setAlignment(PDEConstants.PDEAlignment.PDEAlignmentLeft);
        buttonIconRightTextLeft.setIconAlignment(PDEButton.PDEButtonIconAlignment.PDEButtonIconAlignmentRight);
        //add button
        addButton(buttonIconRightTextLeft);



        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a rect button with center aligned icon
        //
        //------------------------------------------------------------------------------------------------------------------
        // create and configure
        PDEButton buttonIconCenterAligned = new PDEButton(mContext);
        buttonIconCenterAligned.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundRect);
        buttonIconCenterAligned.setIcon(mContext.getResources().getDrawable(R.drawable.synchronize_generic_plain_center), true);
        buttonIconCenterAligned.setAlignment(PDEConstants.PDEAlignment.PDEAlignmentCenter);
        buttonIconCenterAligned.setIconAlignment(PDEButton.PDEButtonIconAlignment.PDEButtonIconAlignmentLeftAttached);
        //add button
        addButton(buttonIconCenterAligned);



        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a rect button with left aligned icon
        //
        //------------------------------------------------------------------------------------------------------------------
        // create and configure
        PDEButton buttonIconLeftAligned = new PDEButton(mContext);
        buttonIconLeftAligned.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundRect);
        buttonIconLeftAligned.setIcon(mContext.getResources().getDrawable(R.drawable.synchronize_generic_plain_center), true);
        buttonIconLeftAligned.setIconAlignment(PDEButton.PDEButtonIconAlignment.PDEButtonIconAlignmentLeft);
        //add button
        addButton(buttonIconLeftAligned);



        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a rect button with right aligned icon
        //
        //------------------------------------------------------------------------------------------------------------------
        // create and configure
        PDEButton buttonIconRightAligned = new PDEButton(mContext);
        buttonIconRightAligned.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundRect);
        buttonIconRightAligned.setIcon( mContext.getResources().getDrawable(R.drawable.synchronize_generic_plain_center) ,true);
        buttonIconRightAligned.setIconAlignment(PDEButton.PDEButtonIconAlignment.PDEButtonIconAlignmentRight);
        //add button
        addButton(buttonIconRightAligned);
    }


    /**
     * @brief Helper function add our buttons to the button array used by the list.
     *
     */
    void addButton(PDEButton btn) {
        View newButton = View.inflate(mContext,R.layout.list_mixed_button_container,null);
        if (newButton!=null) {
            Context context = PDECodeLibrary.getInstance().getApplicationContext();
            Resources res = context.getResources();
            btn.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, res.getDimensionPixelSize(R.dimen.StandardListItemHeight)));
            ((RelativeLayout)newButton.findViewById(R.id.list_button_conainter_root)).addView(btn);
            mButtons.add((ViewGroup)newButton);
        }
    }


    @Override
    public int getCount() {
        return mButtons.size();
    }


    @Override
    public Object getItem(int i) {
        return mButtons.get(i);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public int getViewTypeCount() {
        return mButtons.size();
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return mButtons.get(i);
    }
}
