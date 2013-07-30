/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */
package de.telekom.pde.codelibrary.samples.pdebutton;


// imports

import android.os.Bundle;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.R.id;
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;


/**
 * @brief Activity class to show all button types on one screen below each other.
 */
public class ButtonShowcaseActivity extends PDEActivity {

    /**
     * @brief Global tag for log outputs.
     */
	@SuppressWarnings("unused")
    private final static String LOG_TAG = ButtonShowcaseActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.button_showcase_screen);

        //get the root view and set background color (different when darkstyle is on or of in library)
        RelativeLayout rootView = (RelativeLayout)findViewById(R.id.buttonshowcase_relativelayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        //get container view which contains the buttons
        RelativeLayout containerView = (RelativeLayout)findViewById(R.id.buttonshowcase_container);

        //variable for linear layout params
        RelativeLayout.LayoutParams btnRelativeLayoutParams;

        //set button size and gaps
        int buttonWidth = PDEBuildingUnits.pixelFromBU(15);
        int buttonHeight = PDEBuildingUnits.pixelFromBU(3);
        int gapX = PDEBuildingUnits.pixelFromBU(1);
        int gapY = PDEBuildingUnits.pixelFromBU(1.0f);


        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert default button
        //
        //  The default button is in default color and beveled style. No configuration (except button title) needs
        //  to be set. The font color and size are automatically set based on styleguide rules when using the
        //  default configuration.
        //
        //------------------------------------------------------------------------------------------------------------------

        // create and configure
        PDEButton buttonDefault = new PDEButton(this);
        buttonDefault.setId(R.id.button_default);
        buttonDefault.setTitle("Beveled (default)");
        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
        btnRelativeLayoutParams.setMargins(0, 0, 0, gapY - PDEBuildingUnits.pixelFromBU(0.5f));
        btnRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //add button to view
        containerView.addView(buttonDefault, btnRelativeLayoutParams );



        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert embossed button
        //
        //  Set the background style to embossed. Everything else is left to the default configuration. The font color
        //  and size are automatically set based on styleguide rules when using the default configuration.
        //
        //------------------------------------------------------------------------------------------------------------------

        // create and configure
        PDEButton buttonEmbossed = new PDEButton(this);
        buttonEmbossed.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundEmbossed);
        buttonEmbossed.setId(R.id.button_embossed);
        buttonEmbossed.setTitle("Embossed");
        //set some linear layout parameter to have correct position and size of button
        // this one has outer shadow, so it needs to be bigger
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams(buttonWidth + PDEBuildingUnits.pixelFromBU(1),
                                                                  buttonHeight + PDEBuildingUnits.pixelFromBU(1));
        // the button is bigger, so reduce gap
        btnRelativeLayoutParams.setMargins(0, 0, 0, gapY - buttonEmbossed.getNeededPadding().bottom);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, buttonDefault.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //add button to view
        containerView.addView(buttonEmbossed, btnRelativeLayoutParams);



        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert default flat button
        //
        //  Set the background style to flat. Everything else is left to the default configuration. The font color
        //  and size are automatically set based on styleguide rules when using the default configuration.
        //
        //------------------------------------------------------------------------------------------------------------------

        // create and configure
        PDEButton buttonFlat = new PDEButton(this);
        buttonFlat.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundFlat);
        buttonFlat.setId(R.id.button_flat);
        buttonFlat.setTitle("Flat");
        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
        btnRelativeLayoutParams.setMargins(0, 0, 0, gapY);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, buttonEmbossed.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //add button to view
        containerView.addView(buttonFlat, btnRelativeLayoutParams);



        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert default plate button
        //
        //  Set the background style to plate. Everything else is left to the default configuration. The font color
        //  and size are automatically set based on styleguide rules when using the default configuration.
        //
        //------------------------------------------------------------------------------------------------------------------

        // create and configure
        PDEButton buttonPlate = new PDEButton(this);
        buttonPlate.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundPlate);
        buttonPlate.setId(R.id.button_plate);
        buttonPlate.setTitle("Clean");
        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
        btnRelativeLayoutParams.setMargins(0, 0, 0, gapY);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, buttonFlat.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //add button to view
        containerView.addView(buttonPlate, btnRelativeLayoutParams);

        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert default indicative button
        //
        //  Set the background style to indicative. Everything else is left to the default configuration. The indicative
        //  button has a transparent background per default, so it can be used over structured content (e.g. a menu bar).
        //  The text color on transparent button automatically adjusts to the background color if left to default.
        //
        //------------------------------------------------------------------------------------------------------------------

        /*
        // create and configure
        PDEButton buttonIndicative = new PDEButton(this);
        buttonIndicative.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundIndicative);
        buttonIndicative.setId(R.id.button_indicative);
        buttonIndicative.setTitle("Outline");
        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams((int)buttonWidth,(int)buttonHeight);
        btnRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, buttonFlat.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //add button to view
        containerView.addView(buttonIndicative, btnRelativeLayoutParams);
        */

        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert an active (magenta) button
        //
        //  Set the color. Everything else is left to the default configuration.
        //
        //------------------------------------------------------------------------------------------------------------------

        // create and configure
        PDEButton buttonActive = new PDEButton(this);
        buttonActive.setId(R.id.button_active);
        buttonActive.setColorWithString("DTMagenta");
        buttonActive.setTitle("Call to Action");
        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
        btnRelativeLayoutParams.setMargins(0, 0, 0, gapY);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, buttonPlate.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //add button to view
        containerView.addView(buttonActive, btnRelativeLayoutParams);


        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a functional green colored button
        //
        //  Set the color. Everything else is left to the default configuration.
        //
        //------------------------------------------------------------------------------------------------------------------

        // create and configure
        PDEButton buttonGreen = new PDEButton(this);
        buttonGreen.setId(R.id.button_green);
        buttonGreen.setColorWithString("DTFunctionalGreen");
        buttonGreen.setTitle("Functional Green");
        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
        btnRelativeLayoutParams.setMargins(0, 0, 0, gapY);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, buttonActive.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //add button to view
        containerView.addView(buttonGreen, btnRelativeLayoutParams);


        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a button with text and an Icon.
        //
        //  Set the Icon. We are using a monochome Icon, so we set the colored flag. The Icon color is then adjusted to
        //  to the text color automatically.
        //
        //------------------------------------------------------------------------------------------------------------------

        // create and configure
        PDEButton buttonIconAndText = new PDEButton(this);
        buttonIconAndText.setId(R.id.button_icon_and_text);
        buttonIconAndText.setTitle("Text with Icon");
        buttonIconAndText.setIcon( getResources().getDrawable(R.drawable.synchronize_generic_plain_center),true );
        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
        btnRelativeLayoutParams.setMargins(0, 0, 0, gapY);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, buttonGreen.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //add button to view
        containerView.addView(buttonIconAndText, btnRelativeLayoutParams);


        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a button with text and an IconFont.
        //
        //  The Icon is set by a String because we use a Font to show the icon.
        //
        //------------------------------------------------------------------------------------------------------------------

        // create and configure
        PDEButton buttonIconFontAndText = new PDEButton(this);
        buttonIconFontAndText.setId(id.button_iconfont_and_text);
        buttonIconFontAndText.setTitle("Text with IconFont");
        buttonIconFontAndText.setIcon("#s");
        buttonIconFontAndText.setIconColored(true);
        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
        btnRelativeLayoutParams.setMargins(0, 0, 0, gapY);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, buttonIconAndText.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //add button to view
        containerView.addView(buttonIconFontAndText, btnRelativeLayoutParams);


        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a default Icon only button.
        //
        //  Default Icon only buttons will appear as bevelled buttons. Using a quadratic 3x3 BU button here to better fit
        //  the Icon.
        //
        //------------------------------------------------------------------------------------------------------------------

        // create and configure
        PDEButton buttonIconDefault = new PDEButton(this);
        buttonIconDefault.setId(R.id.button_icon_default);
        buttonIconDefault.setIcon( getResources().getDrawable(R.drawable.synchronize_generic_plain_center) ,true);
        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams(buttonHeight, buttonHeight);
        btnRelativeLayoutParams.setMargins(0, 0, gapX, 0);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, buttonIconFontAndText.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, buttonIconAndText.getId());
        //add button to view
        containerView.addView(buttonIconDefault, btnRelativeLayoutParams);


        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert an Icon only button, flat style.
        //
        //------------------------------------------------------------------------------------------------------------------

        // create and configure
        PDEButton buttonIconFlat = new PDEButton(this);
        buttonIconFlat.setId(id.button_icon_flat);
        buttonIconFlat.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundFlat);
        buttonIconFlat.setIcon( getResources().getDrawable(R.drawable.synchronize_generic_plain_center) ,true);
        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams(buttonHeight, buttonHeight);
        btnRelativeLayoutParams.setMargins(0, 0, gapX, 0);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, buttonIconFontAndText.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.RIGHT_OF, buttonIconDefault.getId());
        //add button to view
        containerView.addView(buttonIconFlat, btnRelativeLayoutParams);

        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert an Icon only button, indicative style.
        //
        //  Icon only buttons often have indicative style, so we're setting this style here. We also have a
        //  monochrome Icon, so the Icon mode is set to automatically color it.
        //
        //------------------------------------------------------------------------------------------------------------------
        /*
        // create and configure
        PDEButton buttonIconIndicative = new PDEButton(this);
        buttonIconIndicative.setId(R.id.button_icon_indicative);
        buttonIconIndicative.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundIndicative);
        buttonIconIndicative.setIcon( getResources().getDrawable(R.drawable.synchronize_generic_plain_center) ,true);
        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams((int)buttonHeight,(int)buttonHeight);
        btnRelativeLayoutParams.setMargins(0,0,(int)gapX,0);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, buttonIconFontAndText.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.RIGHT_OF, buttonIconDefault.getId());
        //add button to view
        containerView.addView(buttonIconIndicative, btnRelativeLayoutParams);
        */

        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert an Icon only button, plate style.
        //
        //  Plate buttons often occur in grids (or overlay grid choosers). The default background color of plate buttons
        //  is transparent, so it can be used over any background color.
        //
        //------------------------------------------------------------------------------------------------------------------

        // create and configure
        PDEButton buttonIconPlate = new PDEButton(this);
        buttonIconPlate.setId(R.id.button_icon_plate);
        buttonIconPlate.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundPlate);
        buttonIconPlate.setIcon( getResources().getDrawable(R.drawable.synchronize_generic_plain_center) ,true);
        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams(buttonHeight, buttonHeight);
        btnRelativeLayoutParams.setMargins(0, 0, gapX, 0);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, buttonIconFontAndText.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.RIGHT_OF, buttonIconFlat.getId());
        //add button to view
        containerView.addView(buttonIconPlate, btnRelativeLayoutParams);



        //--------------------------------------------------------------------------------------------------------------
        //
        //  Insert an Icon only button, rect style.
        //
        //  Rect buttons can be used as background button, the highlight is rectangular.
        //  The default background color of plate buttons is transparent, so it can be used over any background color.
        //
        //--------------------------------------------------------------------------------------------------------------


        // create and configure
        PDEButton buttonIconRect = new PDEButton(this);
        buttonIconRect.setId(id.button_icon_rect);
        buttonIconRect.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundRect);
        buttonIconRect.setIcon( getResources().getDrawable(R.drawable.synchronize_generic_plain_center) ,true);
        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams(buttonHeight, buttonHeight);
        btnRelativeLayoutParams.setMargins(0, 0, gapX, 0);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, buttonIconFontAndText.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.RIGHT_OF, buttonIconPlate.getId());
        //add button to view
        containerView.addView(buttonIconRect, btnRelativeLayoutParams);


        //--------------------------------------------------------------------------------------------------------------
        //
        //  Insert an Icon only button, active and plate style.
        //
        //  Plate buttons in grid often have a magenta plate as background.
        //
        //--------------------------------------------------------------------------------------------------------------
        /*
        // create and configure
        PDEButton buttonIconActive = new PDEButton(this);
        buttonIconActive.setId(R.id.button_icon_active);
        buttonIconActive.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundPlate);
        buttonIconActive.setColorWithString("DTMagenta");
        buttonIconActive.setIcon( getResources().getDrawable(R.drawable.synchronize_generic_plain_center) ,true);
        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams((int)buttonHeight,(int)buttonHeight);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, buttonIconFontAndText.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.RIGHT_OF, buttonIconPlate.getId());
        //add button to view
        containerView.addView(buttonIconActive, btnRelativeLayoutParams);
        */
    }
}
