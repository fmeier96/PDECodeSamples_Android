/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.graphicelements;


// imports

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.basescreens.DialogHelper;
import de.telekom.pde.codelibrary.samples.basescreens.ResizeBaseActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.drawables.PDEDrawableInterface;
import de.telekom.pde.codelibrary.ui.components.drawables.PDEDrawableMultilayer;
//import de.telekom.pde.codelibrary.ui.components.helpers.OnPDEBoundsChangeListener;
import de.telekom.pde.codelibrary.ui.elements.boxes.PDEDrawableCornerBox;
import de.telekom.pde.codelibrary.ui.elements.boxes.PDEDrawableCornerGradientBox;
import de.telekom.pde.codelibrary.ui.elements.boxes.PDEDrawableNotificationFrame;
import de.telekom.pde.codelibrary.ui.elements.boxes.PDEDrawableRoundedBox;
import de.telekom.pde.codelibrary.ui.elements.boxes.PDEDrawableRoundedGradientBox;
import de.telekom.pde.codelibrary.ui.elements.boxes.PDEDrawableSunkenArea;
import de.telekom.pde.codelibrary.ui.elements.common.PDEDrawableDelimiter;
import de.telekom.pde.codelibrary.ui.elements.common.PDEDrawableShapedInnerShadow;
import de.telekom.pde.codelibrary.ui.elements.common.PDEDrawableShapedShadow;
import de.telekom.pde.codelibrary.ui.elements.complex.PDEDrawableListHeader;
import de.telekom.pde.codelibrary.ui.elements.complex.PDEDrawableProgressBar;
import de.telekom.pde.codelibrary.ui.elements.complex.PDEDrawableScrollbarIndicative;
import de.telekom.pde.codelibrary.ui.elements.complex.PDEDrawableScrollbarInteractive.PDEDrawableScrollbarInteractive;
import de.telekom.pde.codelibrary.ui.elements.complex.PDEDrawableScrollbarInteractive.PDEDrawableScrollbarInteractiveArrowButton;
import de.telekom.pde.codelibrary.ui.elements.icon.PDEDrawableIcon;
import de.telekom.pde.codelibrary.ui.elements.icon.PDEDrawableIconImage;
import de.telekom.pde.codelibrary.ui.elements.icon.PDEDrawableIconfont;
import de.telekom.pde.codelibrary.ui.elements.metapher.PDEDrawableFilmMetaphor;
import de.telekom.pde.codelibrary.ui.elements.metapher.PDEDrawableMusicMetaphor;
import de.telekom.pde.codelibrary.ui.elements.metapher.PDEDrawablePhotoFrame;
import de.telekom.pde.codelibrary.ui.elements.metapher.PDEDrawablePolaroidFrame;
import de.telekom.pde.codelibrary.ui.elements.metapher.PDEDrawableVideoMetaphor;
import de.telekom.pde.codelibrary.ui.elements.text.PDELayerText;

import android.graphics.*;
import java.util.ArrayList;


/**
 * @brief Activity class for the sizing test screen.
 */
public class GfxPrimitivesResizeActivity extends ResizeBaseActivity {

    /**
     * @brief Global tag for log outputs.
     */
	@SuppressWarnings("unused")
    private final static String LOG_TAG = GfxPrimitivesResizeActivity.class.getName();
    private final static boolean DEBUG = false;

    //global names of the primitive elements
    private final static String PRIMITIVE_NAME_PDEICON = "PdeIcon";
    private final static String PRIMITIVE_NAME_PDEICONIMAGE = "Pde Icon Image";
    private final static String PRIMITIVE_NAME_PHOTOFRAME = "Photo Frame";
    private final static String PRIMITIVE_NAME_DELIMITER_HORIZONTAL = "Delimiter Horizontal";
    private final static String PRIMITIVE_NAME_DELIMITER_VERTICAL = "Delimiter Vertical";
    private final static String PRIMITIVE_NAME_VIDEOMETAPHOR = "Video Metaphor";
    private final static String PRIMITIVE_NAME_NOTIFICATIONFRAME = "Notification Frame";
    private final static String PRIMITIVE_NAME_NOTIFICATIONFRAME_WHITE = "Notification Frame White";
    private final static String PRIMITIVE_NAME_NOTIFICATIONFRAME_WHITE_RIGHT = "Notification Frame White Right";
    private final static String PRIMITIVE_NAME_MUSICMETAPHOR = "Music Metaphor";
    private final static String PRIMITIVE_NAME_FILMMETAPHOR = "Film Metaphor";
    private final static String PRIMITIVE_NAME_ROUNDEDBOX = "Rounded Box";
    private final static String PRIMITIVE_NAME_SCROLLBAR_INDICATIVE_VERTICAL = "Scrollbar indicative vertical";
    private final static String PRIMITIVE_NAME_SCROLLBAR_INDICATIVE_HORIZONTAL = "Scrollbar indicative horizontal";
    private final static String PRIMITIVE_NAME_ROUNDEDGRADIENTBOX = "Rounded Gradient Box";
    private final static String PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_VERTICAL = "Scrollbar interactive vertical";
    private final static String PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_HORIZONTAL = "Scrollbar interactive horizontal";
    private final static String PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_ARROWBUTTON_LEFT = "Scrollbar interactive " +
                                                                                        "arrow left";
    private final static String PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_ARROWBUTTON_RIGHT = "Scrollbar interactive " +
                                                                                         "arrow right";
    private final static String PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_ARROWBUTTON_TOP = "Scrollbar interactive " +
                                                                                       "arrow top";
    private final static String PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_ARROWBUTTON_BOTTOM = "Scrollbar interactive " +
                                                                                          "arrow bottom";
    private final static String PRIMITIVE_NAME_PROGRESSBAR = "Progressbar";
    private final static String PRIMITIVE_NAME_PROGRESSBAR_HANDLE = "Progressbar Handle";
    private final static String PRIMITIVE_NAME_CORNERBOX = "Corner Box";
    private final static String PRIMITIVE_NAME_PDEICONFONT = "Iconfont";
    private final static String PRIMITIVE_NAME_OUTERSHADOW_ROUNDED = "Outer Shadow rounded";
    private final static String PRIMITIVE_NAME_OUTERSHADOW_RECT = "Outer Shadow rectangular";
    private final static String PRIMITIVE_NAME_INNERSHADOW_ROUNDED = "Inner Shadow rounded";
    private final static String PRIMITIVE_NAME_SUNKEN_AREA_RECT = "Sunken area rectangular";
    private final static String PRIMITIVE_NAME_SUNKEN_AREA_CIRCLE = "Sunken area circle";
    private final static String PRIMITIVE_NAME_STAGE_WHITE = "Stage white";
    private final static String PRIMITIVE_NAME_STAGE_GRADIENT = "Stage gradient";
    private final static String PRIMITIVE_NAME_CUTOUT = "Cutout";
    private final static String PRIMITIVE_NAME_TEXTAREA = "Textarea";
    private final static String PRIMITIVE_NAME_POLAROID = "Polaroid Frame";
    private final static String PRIMITIVE_NAME_PDELAYERTEXT = "PDE Layer Text";
    private final static String PRIMITIVE_NAME_CORNERGRADIENTBOX = "Corner Gradient Box";
    private final static String PRIMITIVE_NAME_LIST_HEADER = "List Header";
    private final static String PRIMITIVE_NAME_LIST_HEADER_ENUMERATION = "List Header Enumeration";
    private final static String PRIMITIVE_NAME_LIST_HEADER_CLUSTER = "List Header Cluster";

    //global names of available sizes
    private final static String PRIMITIVE_SIZE_2BU = "2 BU";
    private final static String PRIMITIVE_SIZE_3BU = "3 BU";
    private final static String PRIMITIVE_SIZE_4BU = "4 BU";
    private final static String PRIMITIVE_SIZE_5BU = "5 BU";
    private final static String PRIMITIVE_SIZE_8BU = "8 BU";
    private final static String PRIMITIVE_SIZE_10BU = "10 BU";
    private final static String PRIMITIVE_SIZE_15BU = "15 BU";
    private final static String PRIMITIVE_SIZE_20BU = "20 BU";


    // private variables

    // image view as drawable container
    ImageView mImageView;
    // element
    Drawable mElement;
    // element outer shadow
    PDEDrawableShapedShadow mShadow;

    ArrayList<String> mLeftChoiceArrayList;
    ArrayList<String> mRightChoiceArrayList;
    DialogHelper.ChoiceListOnItemClickListener mLeftChoiceItemClickListener;
    DialogHelper.ChoiceListOnItemClickListener mRightChoiceItemClickListener;

    private Point mPrimitiveDefaultSize;
    private Point mPrimitiveDefaultOffset;
    private String mChosenPrimitive;
    private PDEDrawableMultilayer mMultilayer;


    /**
     * @brief Create the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPrimitiveDefaultSize = null;
        mPrimitiveDefaultOffset = new Point(PDEBuildingUnits.BU(),PDEBuildingUnits.BU());

        // *************************
        // Create image view as drawable container
        // *************************
        mImageView = new ImageView(this);
        mElement = null;
        mShadow = null;
        mChosenPrimitive = "";
        mMultilayer = new PDEDrawableMultilayer();
        mMultilayer.setOnBoundsChangeListener( new PDEDrawableMultilayer.OnPDEBoundsChangeListener() {
            @Override
            public void onPDEBoundsChange(Drawable source, Rect bounds) {
                notifyPrimitiveAboutBoundsChange(source, bounds);
            }
        });


        //set some linear layout parameter to have correct position and size of button
        RelativeLayout.LayoutParams primitivesLinearLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        //add button to view
        addViewToResizeContainer(mImageView, primitivesLinearLayoutParams);

        fillLeftChoiceArray();
        fillRightChoiceArray();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setSelectionPos(LEFT_RIGHT_BUTTON.LEFT, 0);
       // this.
    }

    /**
     * @brief Fill the left choice array.
     */
    private void fillLeftChoiceArray() {
        mLeftChoiceArrayList = new ArrayList<String>();

        //fill array
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_ROUNDEDBOX);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_ROUNDEDGRADIENTBOX);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_CORNERBOX);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_CORNERGRADIENTBOX);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_NOTIFICATIONFRAME);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_NOTIFICATIONFRAME_WHITE);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_NOTIFICATIONFRAME_WHITE_RIGHT);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_VIDEOMETAPHOR);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_MUSICMETAPHOR);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_FILMMETAPHOR);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_PHOTOFRAME);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_POLAROID);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_PDEICON);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_PDEICONIMAGE);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_PDEICONFONT);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_PDELAYERTEXT);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_TEXTAREA);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_DELIMITER_HORIZONTAL);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_DELIMITER_VERTICAL);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_OUTERSHADOW_ROUNDED);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_OUTERSHADOW_RECT);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_INNERSHADOW_ROUNDED);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_SUNKEN_AREA_RECT);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_SUNKEN_AREA_CIRCLE);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_STAGE_WHITE);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_STAGE_GRADIENT);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_CUTOUT);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_PROGRESSBAR);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_PROGRESSBAR_HANDLE);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_LIST_HEADER);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_LIST_HEADER_ENUMERATION);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_LIST_HEADER_CLUSTER);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_SCROLLBAR_INDICATIVE_HORIZONTAL);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_SCROLLBAR_INDICATIVE_VERTICAL);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_HORIZONTAL);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_VERTICAL);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_ARROWBUTTON_LEFT);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_ARROWBUTTON_RIGHT);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_ARROWBUTTON_TOP);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_ARROWBUTTON_BOTTOM);





        //set choice information and listener
        addChoiceArrayList(LEFT_RIGHT_BUTTON.LEFT, "Choose", mLeftChoiceArrayList,
           new DialogHelper.ChoiceListOnItemClickListener() {
               @Override
               public void onListItemClicked(String itemContentString) {

                   mChosenPrimitive = "";
                   mElement = null;
                   mShadow = null;
                   mPrimitiveDefaultSize = new Point(PDEBuildingUnits.pixelFromBU(12.0f),
                                                     PDEBuildingUnits.pixelFromBU(6.0f));
                   setContainerSize(new Point(0,0));
                   mImageView.setImageDrawable(null);
                   mMultilayer.clearLayers();
                   mMultilayer.setLayoutSize(0,0);

                   // react on list selection
                   // --- Icon -------------------------------------------------------------------------
                   if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_PDEICON)) {
                       //PDEDrawableIcon icon;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_PDEICON;
                       // create element
                       //PDEDrawableIcon icon = new PDEDrawableIcon("#z");
                       PDEDrawableIcon icon = new PDEDrawableIcon(getResources().getDrawable(R.drawable.baum2008));
                       //icon.setElementIconColor(PDEColor.valueOf("DTBlack"));
                       //icon.setElementShadowEnabled(true);
                       //icon.setElementShadowColor(PDEColor.valueOf("DTFunctionalRed"));
                       //icon.setElementShadowXOffset(3.0f);
                       //icon.setElementShadowYOffset(3.0f);
                       //icon.setElementPadding(3.0f);

                       mElement = icon;
                       // set element into view
                       mImageView.setImageDrawable(mElement);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown);
                   }
                   // --- IconImage -------------------------------------------------------------------------
                   if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_PDEICONIMAGE)) {
                       //PDEDrawableIcon icon;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_PDEICONIMAGE;
                       // create element
                       PDEDrawableIconImage icon = new PDEDrawableIconImage(getResources().getDrawable(R.drawable
                               .synchronize_generic_plain_center));
                       icon.setElementIconColor(PDEColor.valueOf("DTBlue"));
                       icon.setElementShadowEnabled(true);
                       icon.setElementShadowColor(PDEColor.valueOf("DTFunctionalRed"));
                       icon.setElementShadowXOffset(3.0f);
                       icon.setElementShadowYOffset(3.0f);
                       icon.setElementPadding(3.0f);
                       mElement = icon;
                       // set element into view
                       mImageView.setImageDrawable(mElement);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown);
                   }
                   // --- Photo Frame -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_PHOTOFRAME)) {
                       PDEDrawablePhotoFrame photoframe;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_PHOTOFRAME;
                       // create element
                       photoframe = new PDEDrawablePhotoFrame(getResources().getDrawable(R.drawable.kids));
                       mElement = photoframe;
                       mShadow = (PDEDrawableShapedShadow)photoframe.createElementShadow();
                       mMultilayer.addLayer(mShadow);
                       mMultilayer.addLayer(photoframe);
                       // set element into view
                       mImageView.setImageDrawable(mMultilayer);
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal );
                   }
                   // --- Notification Frame -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_NOTIFICATIONFRAME)) {
                       PDEDrawableNotificationFrame notificationFrame;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_NOTIFICATIONFRAME;
                       // create element
                       notificationFrame = new PDEDrawableNotificationFrame();
                       mElement = notificationFrame;
                       mShadow = (PDEDrawableShapedShadow)notificationFrame.createElementShadow();
                       // configure element
                       notificationFrame.setElementTriangleTipPositionAbsolute(
                               PDEBuildingUnits.pixelFromBU(4.0f),
                               PDEDrawableNotificationFrame.TriangleSide.SideBottom);
                       mMultilayer.addLayer(mShadow);
                       mMultilayer.addLayer(mElement);
                       // set element into view
                       mImageView.setImageDrawable(mMultilayer);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- White Notification Frame --------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString,PRIMITIVE_NAME_NOTIFICATIONFRAME_WHITE)) {
                       PDEDrawableNotificationFrame notificationFrame;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_NOTIFICATIONFRAME_WHITE;
                       // create element
                       notificationFrame = new PDEDrawableNotificationFrame();
                       mElement = notificationFrame;
                       mShadow = (PDEDrawableShapedShadow)notificationFrame.createElementShadow();
                       // configure element
                       notificationFrame.setElementTriangleTipPositionAbsolute(PDEBuildingUnits.pixelFromBU(4.0f),
                                                                               PDEDrawableNotificationFrame.TriangleSide.SideBottom);
                       notificationFrame.setElementBackgroundColor(PDEColor.valueOf("DTWhite"));
                       notificationFrame.setElementBorderColor(PDEColor.valueOf("DTGrey208"));
                       mMultilayer.addLayer(mShadow);
                       mMultilayer.addLayer(mElement);
                       // set element into view
                       mImageView.setImageDrawable(mMultilayer);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- White Notification Frame with triangle on right side ---------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_NOTIFICATIONFRAME_WHITE_RIGHT)) {
                       PDEDrawableNotificationFrame notificationFrame;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_NOTIFICATIONFRAME_WHITE_RIGHT;
                       // create element
                       notificationFrame = new PDEDrawableNotificationFrame();
                       mElement = notificationFrame;
                       mShadow = (PDEDrawableShapedShadow)notificationFrame.createElementShadow();
                       // configure element
                       notificationFrame.setElementTriangleTipPositionRelative(0.5f,
                                                                               PDEDrawableNotificationFrame.TriangleSide.SideRight);
                       notificationFrame.setElementBackgroundColor(PDEColor.valueOf("DTWhite"));
                       notificationFrame.setElementBorderColor(PDEColor.valueOf("DTGrey208"));
                       mMultilayer.addLayer(mShadow);
                       mMultilayer.addLayer(mElement);
                       // set element into view
                       mImageView.setImageDrawable(mMultilayer);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- Video Metaphor -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_VIDEOMETAPHOR)) {
                       //PDEDrawableVideoMetaphor videometaphor;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_VIDEOMETAPHOR;
                       // create element
                       mElement = new PDEDrawableVideoMetaphor(getResources().getDrawable(R.drawable.baum2008), "3:11");
                       // set element into view
                       mImageView.setImageDrawable(mElement);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- Delimiter horizontal -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_DELIMITER_HORIZONTAL)) {
                       PDEDrawableDelimiter delimiter;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_DELIMITER_HORIZONTAL;
                       // create element
                       delimiter = new PDEDrawableDelimiter();
                       mElement = delimiter;
                       // configure element
                       delimiter.setElementType(
                               PDEDrawableDelimiter.PDEDrawableDelimiterType.PDEDrawableDelimiterTypeHorizontal);
                       // set element into view
                       mImageView.setImageDrawable(mElement);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionRight);
                       // change default size
                       mPrimitiveDefaultSize = new Point(PDEBuildingUnits.pixelFromBU(12.0f),
                                                         PDEBuildingUnits.pixelFromBU(1.0f));
                   }
                   // --- Delimiter vertical -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_DELIMITER_VERTICAL)) {
                       PDEDrawableDelimiter delimiter;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_DELIMITER_VERTICAL;
                       // create element
                       delimiter = new PDEDrawableDelimiter();
                       mElement = delimiter;
                       // configure element
                       delimiter.setElementType(PDEDrawableDelimiter.PDEDrawableDelimiterType
                                                        .PDEDrawableDelimiterTypeVertical);
                       // set element into view
                       mImageView.setImageDrawable(mElement);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown);
                       // change default size
                       mPrimitiveDefaultSize = new Point(PDEBuildingUnits.pixelFromBU(1.0f),
                                                         PDEBuildingUnits.pixelFromBU(6.0f));
                   }
                   // --- Music Metaphor -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_MUSICMETAPHOR)) {
                       PDEDrawableMusicMetaphor musicmetaphor;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_MUSICMETAPHOR;
                       // create element
                       musicmetaphor = new PDEDrawableMusicMetaphor(getResources().getDrawable(R.drawable.baum2008));
                       mElement = musicmetaphor;
                       mShadow = (PDEDrawableShapedShadow)musicmetaphor.createElementShadow();

                       mMultilayer.addLayer(mShadow);
                       mMultilayer.addLayer(musicmetaphor);
                       // set element into view
                       mImageView.setImageDrawable(mMultilayer);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal );
                   }
                   // --- Film Metaphor -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_FILMMETAPHOR)) {
                       PDEDrawableFilmMetaphor filmmetaphor;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_FILMMETAPHOR;
                       // set show mode of view bounds
                       //setShowModeOfViewBounds(VIEW_BOUNDS_SHOW_MODE.NEVER);
                       // create element
                       filmmetaphor= new PDEDrawableFilmMetaphor(getResources().getDrawable(R.drawable.baum2008));
                       mElement = filmmetaphor;
                       mShadow = (PDEDrawableShapedShadow)filmmetaphor.createElementShadow();
                       mMultilayer.addLayer(mShadow);
                       mMultilayer.addLayer(filmmetaphor);
                       // set element into view
                       mImageView.setImageDrawable(mMultilayer);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal );
                   }
                   // --- Rounded Box -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_ROUNDEDBOX)) {
                       PDEDrawableRoundedBox roundedbox;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_ROUNDEDBOX;
                       // create element
                       roundedbox = new PDEDrawableRoundedBox();
                       mElement = roundedbox;
                       // set multilayer into view
                       mImageView.setImageDrawable(mElement);

                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- Scrollbar indicative vertical --------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_SCROLLBAR_INDICATIVE_VERTICAL)) {
                       PDEDrawableScrollbarIndicative scrollbarIndicative;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_SCROLLBAR_INDICATIVE_VERTICAL;
                       // create element
                       scrollbarIndicative = new PDEDrawableScrollbarIndicative();
                       mElement = scrollbarIndicative;
                       // configure element
                       scrollbarIndicative.setElementScrollbarType(PDEDrawableScrollbarIndicative
                                                                           .PDEDrawableScrollbarIndicativeType.PDEDrawablScrollbarIndicativeTypeVertical);
                       scrollbarIndicative.setElementScrollContentSize(100.0f);
                       scrollbarIndicative.setElementScrollPageSize(75.0f);
                       scrollbarIndicative.setElementScrollPos(12.5f);
                       // set element into view
                       mImageView.setImageDrawable(scrollbarIndicative);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown);
                       // change default size
                       mPrimitiveDefaultSize = new Point(PDEBuildingUnits.pixelFromBU(1.0f),
                                                         PDEBuildingUnits.pixelFromBU(6.0f));
                   }
                   // --- Scrollbar indicative horizontal ------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_SCROLLBAR_INDICATIVE_HORIZONTAL)) {
                       PDEDrawableScrollbarIndicative scrollbarIndicative;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_SCROLLBAR_INDICATIVE_HORIZONTAL;
                       // create element
                       scrollbarIndicative = new PDEDrawableScrollbarIndicative();
                       mElement = scrollbarIndicative;
                       // configure element
                       scrollbarIndicative.setElementScrollbarType(PDEDrawableScrollbarIndicative
                                                                           .PDEDrawableScrollbarIndicativeType.PDEDrawableScrollbarIndicativeTypeHorizontal);
                       scrollbarIndicative.setElementScrollContentSize(100.0f);
                       scrollbarIndicative.setElementScrollPageSize(75.0f);
                       scrollbarIndicative.setElementScrollPos(12.5f);
                       // set element into view
                       mImageView.setImageDrawable(scrollbarIndicative);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionRight);
                       // change default size
                       mPrimitiveDefaultSize = new Point(PDEBuildingUnits.pixelFromBU(12.0f),
                                                         PDEBuildingUnits.pixelFromBU(1.0f));
                   }
                   // --- rounded gradient box ------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_ROUNDEDGRADIENTBOX)) {
                       PDEDrawableRoundedGradientBox roundedgradientbox;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_ROUNDEDGRADIENTBOX;
                       // create element
                       roundedgradientbox = new PDEDrawableRoundedGradientBox();
                       mElement = roundedgradientbox;
                       // set multilayer into view
                       mImageView.setImageDrawable(mElement);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- scrollbar interactive vertical ---------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_VERTICAL)) {
                       PDEDrawableScrollbarInteractive scrollbarInteractive;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_VERTICAL;
                       // create element
                       scrollbarInteractive = new PDEDrawableScrollbarInteractive();
                       mElement = scrollbarInteractive;
                       // configure element
                       scrollbarInteractive.setElementScrollbarType(PDEDrawableScrollbarInteractive
                                                                            .PDEDrawableScrollbarInteractiveType
                                                                            .Vertical);
                       scrollbarInteractive.setElementScrollContentSize(100.0f);
                       scrollbarInteractive.setElementScrollPageSize(75.0f);
                       scrollbarInteractive.setElementScrollPos(12.5f);
                       // set element into view
                       mImageView.setImageDrawable(mElement);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown);
                       // change default size
                       mPrimitiveDefaultSize = new Point(PDEBuildingUnits.pixelFromBU(1.0f),
                                                         PDEBuildingUnits.pixelFromBU(6.0f));
                   }
                   // --- scrollbar interactive horizontal -----------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_HORIZONTAL)) {
                       PDEDrawableScrollbarInteractive scrollbarInteractive;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_HORIZONTAL;
                       // create element
                       scrollbarInteractive = new PDEDrawableScrollbarInteractive();
                       mElement = scrollbarInteractive;
                       // configure element
                       scrollbarInteractive.setElementScrollbarType(PDEDrawableScrollbarInteractive
                                                                            .PDEDrawableScrollbarInteractiveType
                                                                            .Horizontal);
                       scrollbarInteractive.setElementScrollContentSize(100.0f);
                       scrollbarInteractive.setElementScrollPageSize(75.0f);
                       scrollbarInteractive.setElementScrollPos(12.5f);
                       // set element into view
                       mImageView.setImageDrawable(mElement);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionRight);
                       // change default size
                       mPrimitiveDefaultSize = new Point(PDEBuildingUnits.pixelFromBU(12.0f),
                                                         PDEBuildingUnits.pixelFromBU(1.0f));
                   }
                   // --- Scrollbar-Button left ---------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_ARROWBUTTON_LEFT)){
                       PDEDrawableScrollbarInteractiveArrowButton scrollbarInteractiveBtn;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_ARROWBUTTON_LEFT;
                       // create element
                       scrollbarInteractiveBtn = new PDEDrawableScrollbarInteractiveArrowButton();
                       mElement = scrollbarInteractiveBtn;
                       // configure element
                       scrollbarInteractiveBtn.setElementButtonType(PDEDrawableScrollbarInteractiveArrowButton
                                                                            .PDEDrawableScrollbarInteractiveArrowButtonType
                                                                            .Left);
                       // set element into view
                       mImageView.setImageDrawable(mElement);
                       // change default size
                       mPrimitiveDefaultSize = new Point(PDEBuildingUnits.pixelFromBU(2.0f),
                                                         PDEBuildingUnits.pixelFromBU(1.0f));
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionNone);
                   }
                   // --- Scrollbar-Button right ---------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_ARROWBUTTON_RIGHT)) {
                       PDEDrawableScrollbarInteractiveArrowButton scrollbarInteractiveBtn;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_ARROWBUTTON_RIGHT;
                       // create element
                       scrollbarInteractiveBtn = new PDEDrawableScrollbarInteractiveArrowButton();
                       mElement = scrollbarInteractiveBtn;
                       // configure element
                       scrollbarInteractiveBtn.setElementButtonType(PDEDrawableScrollbarInteractiveArrowButton
                                                                            .PDEDrawableScrollbarInteractiveArrowButtonType
                                                                            .Right);
                       // set element into view
                       mImageView.setImageDrawable(mElement);
                       // change default size
                       mPrimitiveDefaultSize = new Point(PDEBuildingUnits.pixelFromBU(2.0f),
                                                         PDEBuildingUnits.pixelFromBU(1.0f));
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionNone);
                   }
                   // --- Scrollbar-Button top -----------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_ARROWBUTTON_TOP)) {
                       PDEDrawableScrollbarInteractiveArrowButton scrollbarInteractiveBtn;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_ARROWBUTTON_TOP;
                       // create element
                       scrollbarInteractiveBtn = new PDEDrawableScrollbarInteractiveArrowButton();
                       mElement = scrollbarInteractiveBtn;
                       // configure element
                       scrollbarInteractiveBtn.setElementButtonType(PDEDrawableScrollbarInteractiveArrowButton
                                                                            .PDEDrawableScrollbarInteractiveArrowButtonType
                                                                            .Top);
                       // set element into view
                       mImageView.setImageDrawable(mElement);
                       // change default size
                       mPrimitiveDefaultSize = new Point(PDEBuildingUnits.pixelFromBU(1.0f),
                                                         PDEBuildingUnits.pixelFromBU(2.0f));
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionNone);
                   }
                   // --- Scrollbar button bottom -------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_ARROWBUTTON_BOTTOM)) {
                       PDEDrawableScrollbarInteractiveArrowButton scrollbarInteractiveBtn;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_SCROLLBAR_INTERACTIVE_ARROWBUTTON_BOTTOM;
                       // create element
                       scrollbarInteractiveBtn = new PDEDrawableScrollbarInteractiveArrowButton();
                       mElement = scrollbarInteractiveBtn;
                       // configure element
                       scrollbarInteractiveBtn.setElementButtonType(PDEDrawableScrollbarInteractiveArrowButton
                                                                            .PDEDrawableScrollbarInteractiveArrowButtonType
                                                                            .Bottom);
                       // set element into view
                       mImageView.setImageDrawable(mElement);
                       // change default size
                       mPrimitiveDefaultSize = new Point(PDEBuildingUnits.pixelFromBU(1.0f),
                                                         PDEBuildingUnits.pixelFromBU(2.0f));
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionNone);
                   }
                   // --- Progressbar -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_PROGRESSBAR)) {
                       PDEDrawableProgressBar progressbar;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_PROGRESSBAR;
                       // create element
                       progressbar = new PDEDrawableProgressBar();
                       mElement = progressbar;
                       // configure element
                       progressbar.setElementProgressValue(0.5f);
                       progressbar.setElementPreloadValue(0.7f);
                       //progressbar.setElementPreloadStartValue(0.1f);
                       //progressbar.setElementProgressStartValue(0.2f);
                       //progressbar.setElementNumMarkers(4);

                       // set element into view
                       mImageView.setImageDrawable(mElement);
                       // change default size
                       mPrimitiveDefaultSize = new Point(PDEBuildingUnits.pixelFromBU(12.0f),
                                                         PDEBuildingUnits.pixelFromBU(1.0f));
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionRight);
                   }
                   // --- Progressbar-Handle -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_PROGRESSBAR_HANDLE)) {
                       PDEDrawableRoundedGradientBox handle;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_PROGRESSBAR_HANDLE;
                       // create element
                       handle = new PDEDrawableRoundedGradientBox();
                       mElement = handle;
                       // configure element
                       handle.setElementBorderColor(PDEColor.valueOf("DTGrey208"));
                       handle.setElementCornerRadius(PDEBuildingUnits.oneThirdBU());
                       mShadow = (PDEDrawableShapedShadow)handle.createElementShadow();
                       mMultilayer.addLayer(mShadow);
                       mMultilayer.addLayer(handle);
                       // set element into view
                       mImageView.setImageDrawable(mMultilayer);
                       // change default size
                       mPrimitiveDefaultSize = new Point(PDEBuildingUnits.pixelFromBU(6.0f),
                                                         PDEBuildingUnits.pixelFromBU(6.0f));
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionNone);
                   }
                   // --- Cornerbox -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_CORNERBOX)) {
                       PDEDrawableCornerBox cornerBox;
                       PDEDrawableMultilayer multilayer;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_CORNERBOX;
                       // create element
                       cornerBox = new PDEDrawableCornerBox();
                       mElement = cornerBox;
                       // configure element
                       cornerBox.setElementRoundedCornerConfiguration(
                               PDEDrawableCornerBox.PDEDrawableCornerBoxCornerTopLeft |
                                       PDEDrawableCornerBox.PDEDrawableCornerBoxCornerBottomRight );

                       mShadow = (PDEDrawableShapedShadow)cornerBox.createElementShadow();
                       mMultilayer.addLayer(mShadow);
                       mMultilayer.addLayer(cornerBox);
                       // set multilayer into view
                       mImageView.setImageDrawable(mMultilayer);

                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- Iconfont-Wrapper -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_PDEICONFONT)) {
                       PDEDrawableIconfont iconfontwrapper = new PDEDrawableIconfont("z");
                       //iconfontwrapper.setElementStretchToSize(true);
                       iconfontwrapper.setElementShadowEnabled(true);
                       iconfontwrapper.setElementShadowColor(PDEColor.valueOf("DTFunctionalRed"));
                       iconfontwrapper.setElementShadowXOffset(3.0f);
                       iconfontwrapper.setElementShadowYOffset(3.0f);
                       iconfontwrapper.setElementPadding(3.0f);
                       //iconfontwrapper.setElementIconColor(PDEColor.valueOf("DTDarkBlue"));
                       mImageView.setImageDrawable(iconfontwrapper);
                       setContentSizingDirection(PDESizingScreenDirectionDown );
                   }
                   // --- Outer Shadow rounded ----------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_OUTERSHADOW_ROUNDED)) {
                       PDEDrawableShapedShadow roundedShadow;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_OUTERSHADOW_ROUNDED;
                       // create element
                       roundedShadow = new PDEDrawableShapedShadow();
                       roundedShadow.setElementShapeOpacity(0.25f);
                       roundedShadow.setElementShapeRoundedRect(PDEBuildingUnits.twoThirdsBU());
                       mElement = roundedShadow;
                       // set element into view
                       mImageView.setImageDrawable(mElement);

                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- Outer Shadow rectangular ---------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_OUTERSHADOW_RECT)) {
                       PDEDrawableShapedShadow rectShadow;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_OUTERSHADOW_RECT;
                       // create element
                       rectShadow = new PDEDrawableShapedShadow();
                       rectShadow.setElementShapeOpacity(0.25f);
                       rectShadow.setElementShapeRect();
                       mElement = rectShadow;
                       // set element into view
                       mImageView.setImageDrawable(mElement);

                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- Inner Shadow rounded ---------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_INNERSHADOW_ROUNDED)) {
                       PDEDrawableShapedInnerShadow innerShadow;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_INNERSHADOW_ROUNDED;
                       // create element
                       innerShadow = new PDEDrawableShapedInnerShadow();
                       innerShadow.setElementShapeRoundedRect(PDEBuildingUnits.twoThirdsBU());
                       mElement = innerShadow;
                       // set element into view
                       mImageView.setImageDrawable(mElement);

                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- Sunken Area rectangular ---------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_SUNKEN_AREA_RECT)) {
                       PDEDrawableSunkenArea sunkenArea;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_SUNKEN_AREA_RECT;
                       // create element
                       sunkenArea = new PDEDrawableSunkenArea();
                       sunkenArea.setElementShapeRect();
                       sunkenArea.setElementSunkenBorderColor(PDEColor.valueOf("DTGrey237_Idle_Border"));
                       mElement = sunkenArea;
                       // set element into view
                       mImageView.setImageDrawable(mElement);

                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- Sunken Area circle ---------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_SUNKEN_AREA_CIRCLE)) {
                       PDEDrawableSunkenArea sunkenArea;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_SUNKEN_AREA_CIRCLE;
                       // create element
                       sunkenArea = new PDEDrawableSunkenArea();
                       sunkenArea.setElementShapeOval();
                       sunkenArea.setElementSunkenBorderColor(PDEColor.valueOf("DTGrey237_Idle_Border"));
                       mElement = sunkenArea;
                       // set element into view
                       mImageView.setImageDrawable(mElement);

                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- layer text ---------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_PDELAYERTEXT)) {
                       PDELayerText layertext;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_PDELAYERTEXT;
                       // create element
                       layertext = new PDELayerText("this is an even longer test string with more words");
                       //layertext.setElementLineDistanceFactor(0);
                       //layertext = new PDELayerText("Cluster Headline");
                       layertext.setElementTextSize(60);
                       layertext.setElementEllipsize(true);
                       //layertext.setElementPaddingLeft(50);
                       layertext.setElementPaddingAll(10);
                       //layertext.setElementTextColor(PDEColor.valueOf("DTDarkBlue"));
                       layertext.setElementBackgroundColor(PDEColor.valueOf("DTYellow"));
                       layertext.setElementAlignmentMode(PDELayerText.PDELayerTextAlignmentMode.PDELayerTextAlignmentModeCapHeight);
                       //layertext.setElementBaseLine(-100);
                       layertext.setElementShadowEnabled(true);
                       layertext.setElementShadowColor(PDEColor.valueOf("DTFunctionalRed"));
                       layertext.setElementShadowXOffset(3.0f);
                       layertext.setElementShadowYOffset(3.0f);
                       layertext.setElementVerticalAlignment(PDEConstants.PDEVerticalAlignment.PDEAlignmentTop);
                       layertext.setElementHorizontalAlignment(PDEConstants.PDEAlignment.PDEAlignmentLeft);
                       //layertext.setElementPadding(50,60,20,10);
                       mElement = layertext;
                       // set element into view
                       mImageView.setImageDrawable(mElement);


                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                               PDESizingScreenDirectionDiagonal);
                   }

                   // --- Stage White -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_STAGE_WHITE)) {
                       PDEDrawableRoundedBox stage;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_STAGE_WHITE;
                       // create element
                       stage = new PDEDrawableRoundedBox();
                       mElement = stage;
                       stage.setElementBackgroundColor(PDEColor.valueOf("DTWhite"));
                       stage.setElementBorderColor(PDEColor.valueOf("DTGrey208"));
                       mShadow = (PDEDrawableShapedShadow)stage.createElementShadow();
                       mMultilayer.addLayer(mShadow);
                       mMultilayer.addLayer(stage);
                       // set multilayer into view
                       mImageView.setImageDrawable(mMultilayer);

                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- stage gradient ------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_STAGE_GRADIENT)) {
                       PDEDrawableRoundedGradientBox stage;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_STAGE_GRADIENT;
                       // create element
                       stage = new PDEDrawableRoundedGradientBox();
                       mElement = stage;
                       stage.setElementBorderColor(PDEColor.valueOf("DTGrey208"));
                       mShadow = (PDEDrawableShapedShadow)stage.createElementShadow();
                       mMultilayer.addLayer(mShadow);
                       mMultilayer.addLayer(mElement);
                       // set multilayer into view
                       mImageView.setImageDrawable(mMultilayer);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- cutout ------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_CUTOUT)) {
                       PDEDrawableSunkenArea sunkenArea;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_CUTOUT;
                       // create element
                       sunkenArea = new PDEDrawableSunkenArea();
                       mElement = sunkenArea;
                       sunkenArea.setElementSunkenBackgroundColor(PDEColor.valueOf("DTGrey237"));
                       sunkenArea.setElementSunkenBorderColor(PDEColor.valueOf("DTGrey208"));
                       // set multilayer into view
                       mImageView.setImageDrawable(mElement);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- textarea ------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_TEXTAREA)) {
                       PDEDrawableSunkenArea sunkenArea;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_TEXTAREA;
                       // create element
                       sunkenArea = new PDEDrawableSunkenArea();
                       mElement = sunkenArea;
                       sunkenArea.setElementSunkenBackgroundColor(PDEColor.valueOf("DTWhite"));
                       sunkenArea.setElementSunkenBorderColor(PDEColor.valueOf("DTGrey208"));
                       sunkenArea.setElementInnerShadowBlurRadius(1.0f);
                       sunkenArea.setElementInnerShadowOpacity(0.17f);
                       sunkenArea.setElementInnerShadowLightIncidenceOffset(new PointF(0.0f,1.0f));
                       // set multilayer into view
                       mImageView.setImageDrawable(mElement);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- polaroid ------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_POLAROID)) {
                       PDEDrawablePolaroidFrame polaroid;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_POLAROID;
                       // create element
                       polaroid = new PDEDrawablePolaroidFrame(getResources().getDrawable(R.drawable.baum2008));
                       mElement = polaroid;
                       mShadow = (PDEDrawableShapedShadow)polaroid.createElementShadow();
                       mMultilayer.addLayer(mShadow);
                       mMultilayer.addLayer(mElement);
                       // set multilayer into view
                       mImageView.setImageDrawable(mMultilayer);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- CornerGradientBox -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_CORNERGRADIENTBOX)) {
                       PDEDrawableCornerGradientBox cornerBox;
                       PDEDrawableMultilayer multilayer;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_CORNERGRADIENTBOX;
                       // create element
                       cornerBox = new PDEDrawableCornerGradientBox();
                       mElement = cornerBox;
                       // configure element
                       cornerBox.setElementRoundedCornerConfiguration(
                               PDEDrawableCornerBox.PDEDrawableCornerBoxCornerTopLeft |
                                       PDEDrawableCornerBox.PDEDrawableCornerBoxCornerBottomRight );

                       mShadow = (PDEDrawableShapedShadow)cornerBox.createElementShadow();
                       mMultilayer.addLayer(mShadow);
                       mMultilayer.addLayer(cornerBox);
                       // set multilayer into view
                       mImageView.setImageDrawable(mMultilayer);

                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }

                   // --- ListHeader -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_LIST_HEADER)) {
                       PDEDrawableListHeader listHeader;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_LIST_HEADER;
                       // create element
                       listHeader = new PDEDrawableListHeader();
                       mElement = listHeader;
                       // configure element
                       listHeader.setElementText("Headline");
                       listHeader.setElementBackgroundColor(PDEColor.valueOf("DTWhite"));
                       listHeader.setElementAlignment(PDEConstants.PDEAlignment.PDEAlignmentCenter);

                       // set listheader into view
                       mImageView.setImageDrawable(mElement);

                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- ListHeader Enumeration ----------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_LIST_HEADER_ENUMERATION)) {
                       PDEDrawableListHeader listHeader;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_LIST_HEADER_ENUMERATION;
                       // create element
                       listHeader = new PDEDrawableListHeader();
                       mElement = listHeader;
                       // configure element
                       listHeader.setElementText("Headline");
                       listHeader.setElementBackgroundColor(PDEColor.valueOf("DTWhite"));
                       listHeader.setElementSubText("(23)");
                       listHeader.setElementAlignment(PDEConstants.PDEAlignment.PDEAlignmentCenter);

                       // set listheader into view
                       mImageView.setImageDrawable(mElement);

                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }

                   // --- ListHeader Cluster ----------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_LIST_HEADER_CLUSTER)) {
                       PDEDrawableListHeader listHeader;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_LIST_HEADER_CLUSTER;
                       // create element
                       listHeader = new PDEDrawableListHeader();
                       mElement = listHeader;
                       // configure element
                       listHeader.setElementBackgroundColor(PDEColor.valueOf("DTWhite"));
                       listHeader.setElementSubText("Cluster Headline");
                       listHeader.setElementAlignment(PDEConstants.PDEAlignment.PDEAlignmentCenter);

                       // set listheader into view
                       mImageView.setImageDrawable(mElement);

                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }


                   //check if we have padding and maye correct container offset
                   if(mElement instanceof PDEDrawableInterface ){
                       float neededPadding = ((PDEDrawableInterface)mElement).getNeededPadding();

                       //correct view size with drawable padding
                       mPrimitiveDefaultSize.x+=(neededPadding*2);
                       mPrimitiveDefaultSize.y+=(neededPadding*2);
                        //correct view offset with drawable padding
                       mPrimitiveDefaultOffset.x = PDEBuildingUnits.BU()-(int)neededPadding;
                       mPrimitiveDefaultOffset.y = PDEBuildingUnits.BU()-(int)neededPadding;
                       setOptionalBoundsVisibilityPadding(neededPadding);
                   } else {
                       mPrimitiveDefaultOffset.x =PDEBuildingUnits.BU();
                       mPrimitiveDefaultOffset.y = PDEBuildingUnits.BU();
                       setOptionalBoundsVisibilityPadding(0);
                   }

                   setContainerOffset(mPrimitiveDefaultOffset);
                   setContainerSize(mPrimitiveDefaultSize);

                   // Set shadow offset if we have a shadow
    //                   if(mShadow != null && mElement != null) {
    //                       mShadow.setLayoutOffset(new Point(mElement.getBounds().left,
    //                                                         mElement.getBounds().top + PDEBuildingUnits.oneTwelfthsBU()));
    //                   }

               }
           });
    }


    public void notifyPrimitiveAboutBoundsChange(Drawable source, Rect bounds) {
        if (DEBUG) Log.d(LOG_TAG, "CALLBACK!!! " + bounds.left + " " + bounds.top + " " + bounds.right + " " + bounds
                .bottom);
        if (mElement == null) return;
        if (TextUtils.equals(mChosenPrimitive,PRIMITIVE_NAME_STAGE_WHITE)){
            int padding = ((PDEDrawableRoundedBox) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                                        bounds.bottom -padding));
        } else if(TextUtils.equals(mChosenPrimitive,PRIMITIVE_NAME_STAGE_GRADIENT)){
            int padding = ((PDEDrawableRoundedGradientBox) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                                        bounds.bottom -padding));
        } else if(TextUtils.equals(mChosenPrimitive,PRIMITIVE_NAME_FILMMETAPHOR)){
            int padding = ((PDEDrawableFilmMetaphor) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                                        bounds.bottom - padding));
        } else if(TextUtils.equals(mChosenPrimitive,PRIMITIVE_NAME_MUSICMETAPHOR)){
            int padding = ((PDEDrawableMusicMetaphor) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                                        bounds.bottom - padding));
        } else if(TextUtils.equals(mChosenPrimitive,PRIMITIVE_NAME_PHOTOFRAME)){
            int padding = ((PDEDrawablePhotoFrame) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                                        bounds.bottom - padding));
        } else if(TextUtils.equals(mChosenPrimitive,PRIMITIVE_NAME_CORNERBOX)){
            int padding = ((PDEDrawableCornerBox) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                                        bounds.bottom - padding));
        }  else if(TextUtils.equals(mChosenPrimitive,PRIMITIVE_NAME_PROGRESSBAR_HANDLE)){
            int padding = ((PDEDrawableRoundedGradientBox) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                                        bounds.bottom - padding));
        } else if(TextUtils.equals(mChosenPrimitive,PRIMITIVE_NAME_NOTIFICATIONFRAME) || TextUtils.equals
                (mChosenPrimitive,PRIMITIVE_NAME_NOTIFICATIONFRAME_WHITE) ||
                  TextUtils.equals(mChosenPrimitive,PRIMITIVE_NAME_NOTIFICATIONFRAME_WHITE_RIGHT)){
            int padding = ((PDEDrawableNotificationFrame) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                                        bounds.bottom - padding));
        } else if(TextUtils.equals(mChosenPrimitive,PRIMITIVE_NAME_POLAROID)){
            int padding = ((PDEDrawablePolaroidFrame) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                                        bounds.bottom - padding));
        } else if(TextUtils.equals(mChosenPrimitive,PRIMITIVE_NAME_CORNERGRADIENTBOX)){
            int padding = ((PDEDrawableCornerGradientBox) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                                        bounds.bottom - padding));
        }




        if(mShadow == null) return;
        mShadow.setLayoutOffset(new Point(mElement.getBounds().left,
                                          mElement.getBounds().top + PDEBuildingUnits.oneTwelfthsBU()));
    }


    /**
     * @brief Fill the right choice array.
     */
    private void fillRightChoiceArray() {
        mRightChoiceArrayList = new ArrayList<String>();

        //fill array
        mRightChoiceArrayList.add(PRIMITIVE_SIZE_2BU);
        mRightChoiceArrayList.add(PRIMITIVE_SIZE_3BU);
        mRightChoiceArrayList.add(PRIMITIVE_SIZE_4BU);
        mRightChoiceArrayList.add(PRIMITIVE_SIZE_5BU);
        mRightChoiceArrayList.add(PRIMITIVE_SIZE_8BU);
        mRightChoiceArrayList.add(PRIMITIVE_SIZE_10BU);
        mRightChoiceArrayList.add(PRIMITIVE_SIZE_15BU);
        mRightChoiceArrayList.add(PRIMITIVE_SIZE_20BU);

        //set choice information and listener
        addChoiceArrayList(LEFT_RIGHT_BUTTON.RIGHT, "Size", mRightChoiceArrayList, new DialogHelper.ChoiceListOnItemClickListener() {
            @Override
            public void onListItemClicked(String itemContentString) {
                Point newSize = null;
                // react on list selection
                if(TextUtils.equals(itemContentString,PRIMITIVE_SIZE_2BU)) {
                    newSize = new Point(PDEBuildingUnits.pixelFromBU(2.0f), PDEBuildingUnits.pixelFromBU(2.0f));
                } else if(TextUtils.equals(itemContentString,PRIMITIVE_SIZE_3BU)) {
                    newSize = new Point(PDEBuildingUnits.pixelFromBU(3.0f), PDEBuildingUnits.pixelFromBU(3.0f));
                } else if(TextUtils.equals(itemContentString,PRIMITIVE_SIZE_4BU)) {
                    newSize = new Point(PDEBuildingUnits.pixelFromBU(4.0f), PDEBuildingUnits.pixelFromBU(4.0f));
                } else if(TextUtils.equals(itemContentString,PRIMITIVE_SIZE_5BU)) {
                    newSize = new Point(PDEBuildingUnits.pixelFromBU(5.0f), PDEBuildingUnits.pixelFromBU(5.0f));
                } else if(TextUtils.equals(itemContentString,PRIMITIVE_SIZE_8BU)) {
                    newSize = new Point(PDEBuildingUnits.pixelFromBU(8.0f), PDEBuildingUnits.pixelFromBU(8.0f));
                } else if(TextUtils.equals(itemContentString,PRIMITIVE_SIZE_10BU)) {
                    newSize = new Point(PDEBuildingUnits.pixelFromBU(10.0f), PDEBuildingUnits.pixelFromBU(10.0f));
                } else if(TextUtils.equals(itemContentString,PRIMITIVE_SIZE_15BU)) {
                    newSize = new Point(PDEBuildingUnits.pixelFromBU(15.0f), PDEBuildingUnits.pixelFromBU(15.0f));
                } else if(TextUtils.equals(itemContentString,PRIMITIVE_SIZE_20BU)) {
                    newSize = new Point(PDEBuildingUnits.pixelFromBU(20.0f), PDEBuildingUnits.pixelFromBU(20.0f));
                }
                //check if we have padding and maye correct container offset
                if(mElement instanceof PDEDrawableInterface ){
                    //for correct view size we need to add the drawable padding on each side
                    newSize.x += ((PDEDrawableInterface)mElement).getNeededPadding()*2;
                    newSize.y += ((PDEDrawableInterface)mElement).getNeededPadding()*2;
                }
                setContainerSize(newSize);
            }
        });
    }
}
