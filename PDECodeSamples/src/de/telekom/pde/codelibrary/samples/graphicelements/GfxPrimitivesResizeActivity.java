/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.graphicelements;


// imports

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
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
import de.telekom.pde.codelibrary.ui.elements.boxes.*;
import de.telekom.pde.codelibrary.ui.elements.common.*;
import de.telekom.pde.codelibrary.ui.elements.complex.PDEDrawableListHeader;
import de.telekom.pde.codelibrary.ui.elements.complex.PDEDrawableProgressBar;
import de.telekom.pde.codelibrary.ui.elements.complex.PDEDrawableScrollBarIndicative;
import de.telekom.pde.codelibrary.ui.elements.icon.PDEDrawableIcon;
import de.telekom.pde.codelibrary.ui.elements.icon.PDEDrawableIconFont;
import de.telekom.pde.codelibrary.ui.elements.icon.PDEDrawableIconImage;
import de.telekom.pde.codelibrary.ui.elements.metaphor.*;
import de.telekom.pde.codelibrary.ui.elements.text.PDELayerText;

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
    private final static String PRIMITIVE_NAME_PDEICON = "PDEIcon";
    private final static String PRIMITIVE_NAME_PDEICONIMAGE = "PDEIcon Image";
    private final static String PRIMITIVE_NAME_PHOTOFRAMEFLAT = "Photo Frame Flat";
    private final static String PRIMITIVE_NAME_PHOTOFRAMEHAPTIC = "Photo Frame Haptic";
    private final static String PRIMITIVE_NAME_DELIMITER_HORIZONTAL = "Delimiter Horizontal";
    private final static String PRIMITIVE_NAME_DELIMITER_VERTICAL = "Delimiter Vertical";
    private final static String PRIMITIVE_NAME_VIDEOMETAPHORFLAT = "Video Metaphor Flat";
    private final static String PRIMITIVE_NAME_VIDEOMETAPHORHAPTIC = "Video Metaphor Haptic";
    private final static String PRIMITIVE_NAME_NOTIFICATION_FRAME = "Notification Frame";
    private final static String PRIMITIVE_NAME_NOTIFICATION_FRAME_WHITE = "Notification Frame White";
    private final static String PRIMITIVE_NAME_NOTIFICATION_FRAME_WHITE_RIGHT = "Notification Frame White Right";
    private final static String PRIMITIVE_NAME_MUSICMETAPHORFLAT = "Music Metaphor Flat";
    private final static String PRIMITIVE_NAME_MUSICMETAPHORHAPTIC = "Music Metaphor Haptic";
    private final static String PRIMITIVE_NAME_FILMMETAPHORFLAT = "Film Metaphor Flat";
    private final static String PRIMITIVE_NAME_FILMMETAPHORHAPTIC = "Film Metaphor Haptic";
    private final static String PRIMITIVE_NAME_ROUNDED_BOX = "Rounded Box";
    private final static String PRIMITIVE_NAME_SCROLLBAR_INDICATIVE_VERTICAL = "Scrollbar indicative vertical";
    private final static String PRIMITIVE_NAME_SCROLLBAR_INDICATIVE_HORIZONTAL = "Scrollbar indicative horizontal";
    private final static String PRIMITIVE_NAME_ROUNDED_GRADIENT_BOX = "Rounded Gradient Box";
    private final static String PRIMITIVE_NAME_PROGRESSBAR = "Progressbar";
    private final static String PRIMITIVE_NAME_PROGRESSBAR_HANDLE = "Progressbar Handle";
    private final static String PRIMITIVE_NAME_CORNER_BOX = "Corner Box";
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
    private final static String PRIMITIVE_NAME_CORNER_GRADIENT_BOX = "Corner Gradient Box";
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
    //DialogHelper.ChoiceListOnItemClickListener mLeftChoiceItemClickListener;
    //DialogHelper.ChoiceListOnItemClickListener mRightChoiceItemClickListener;

    private Point mPrimitiveDefaultSize;
    private Point mPrimitiveDefaultOffset;
    private String mChosenPrimitive;
    private PDEDrawableMultilayer mMultilayer;


    /**
     * @brief Create the activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        RelativeLayout.LayoutParams primitivesLinearLayoutParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                ViewGroup.LayoutParams.MATCH_PARENT);
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
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_ROUNDED_BOX);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_ROUNDED_GRADIENT_BOX);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_CORNER_BOX);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_CORNER_GRADIENT_BOX);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_NOTIFICATION_FRAME);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_NOTIFICATION_FRAME_WHITE);
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_NOTIFICATION_FRAME_WHITE_RIGHT);
//        mLeftChoiceArrayList.add(PRIMITIVE_NAME_VIDEOMETAPHORFLAT);
//        mLeftChoiceArrayList.add(PRIMITIVE_NAME_VIDEOMETAPHORHAPTIC);
//        mLeftChoiceArrayList.add(PRIMITIVE_NAME_MUSICMETAPHORFLAT);
//        mLeftChoiceArrayList.add(PRIMITIVE_NAME_MUSICMETAPHORHAPTIC);
//        mLeftChoiceArrayList.add(PRIMITIVE_NAME_FILMMETAPHORFLAT);
//        mLeftChoiceArrayList.add(PRIMITIVE_NAME_FILMMETAPHORHAPTIC);
//        mLeftChoiceArrayList.add(PRIMITIVE_NAME_PHOTOFRAMEFLAT);
//        mLeftChoiceArrayList.add(PRIMITIVE_NAME_PHOTOFRAMEHAPTIC);
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
//        mLeftChoiceArrayList.add(PRIMITIVE_NAME_STAGE_WHITE);
//        mLeftChoiceArrayList.add(PRIMITIVE_NAME_STAGE_GRADIENT);
//        mLeftChoiceArrayList.add(PRIMITIVE_NAME_CUTOUT);
//        mLeftChoiceArrayList.add(PRIMITIVE_NAME_PROGRESSBAR);
//        mLeftChoiceArrayList.add(PRIMITIVE_NAME_PROGRESSBAR_HANDLE);
//        mLeftChoiceArrayList.add(PRIMITIVE_NAME_LIST_HEADER);
//        mLeftChoiceArrayList.add(PRIMITIVE_NAME_LIST_HEADER_ENUMERATION);
//        mLeftChoiceArrayList.add(PRIMITIVE_NAME_LIST_HEADER_CLUSTER);






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
                       //icon.setElementIconColor(PDEColor.valueOf("DTBlue"));
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
                   // --- Photo Frame Flat -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_PHOTOFRAMEFLAT)) {
                       PDEDrawablePhotoFrame photoFrame;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_PHOTOFRAMEFLAT;
                       // create element
                       photoFrame = new PDEDrawablePhotoFrame(getResources().getDrawable(R.drawable.kids));
                       mElement = photoFrame;
                       mMultilayer.addLayer(photoFrame);
                       // set element into view
                       mImageView.setImageDrawable(mMultilayer);
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal );
                   }
                   // --- Photo Frame -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_PHOTOFRAMEHAPTIC)) {
                       PDEDrawablePhotoFrame photoFrame;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_PHOTOFRAMEHAPTIC;
                       // create element
                       photoFrame = new PDEDrawablePhotoFrame(getResources().getDrawable(R.drawable.kids));
                       photoFrame.setElementContentStyle(PDEConstants.PDEContentStyle.PDEContentStyleHaptic);
                       photoFrame.setElementShadowEnabled(true);
                       mElement = photoFrame;
                       //mShadow = (PDEDrawableShapedShadow)photoFrame.createElementShadow();
                       //mMultilayer.addLayer(mShadow);
                       mMultilayer.addLayer(photoFrame);
                       // set element into view
                       mImageView.setImageDrawable(mMultilayer);
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                               PDESizingScreenDirectionDiagonal );
                   }
                   // --- Notification Frame -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_NOTIFICATION_FRAME)) {
                       PDEDrawableNotificationFrame notificationFrame;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_NOTIFICATION_FRAME;
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
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_NOTIFICATION_FRAME_WHITE)) {
                       PDEDrawableNotificationFrame notificationFrame;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_NOTIFICATION_FRAME_WHITE;
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
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_NOTIFICATION_FRAME_WHITE_RIGHT)) {
                       PDEDrawableNotificationFrame notificationFrame;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_NOTIFICATION_FRAME_WHITE_RIGHT;
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
                   // --- Video Metaphor Flat -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_VIDEOMETAPHORFLAT)) {
                       //PDEDrawableVideoMetaphor video metaphor;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_VIDEOMETAPHORFLAT;
                       // create element
                       mElement = new PDEDrawableVideoMetaphor(getResources().getDrawable(R.drawable.baum2008), "3:11");
                       // set element into view
                       mImageView.setImageDrawable(mElement);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- Video Metaphor -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_VIDEOMETAPHORHAPTIC)) {
                       PDEDrawableVideoMetaphor videoMetaphor;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_VIDEOMETAPHORHAPTIC;
                       // create element
                       videoMetaphor = new PDEDrawableVideoMetaphor(getResources().getDrawable(R.drawable.baum2008), "3:11");
                       videoMetaphor.setElementContentStyle(PDEConstants.PDEContentStyle.PDEContentStyleHaptic);
                       videoMetaphor.setElementShadowEnabled(true);
                       mElement = videoMetaphor;

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
                   // --- Music Metaphor Flat-------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_MUSICMETAPHORFLAT)) {
                       PDEDrawableMusicMetaphor musicmetaphor;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_MUSICMETAPHORFLAT;
                       // create element
                       musicmetaphor = new PDEDrawableMusicMetaphor(getResources().getDrawable(R.drawable.baum2008));
                       mElement = musicmetaphor;
                       mMultilayer.addLayer(mElement);
                       // set element into view
                       mImageView.setImageDrawable(mMultilayer);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal );
                   }
                   // --- Music Metaphor Haptic-------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_MUSICMETAPHORHAPTIC)) {
                       PDEDrawableMusicMetaphor musicMetaphor;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_MUSICMETAPHORHAPTIC;
                       // create element
                       musicMetaphor = new PDEDrawableMusicMetaphor(getResources().getDrawable(R.drawable.baum2008));
                       musicMetaphor.setElementContentStyle(PDEConstants.PDEContentStyle.PDEContentStyleHaptic);
                       musicMetaphor.setElementShadowEnabled(true);
                       mElement = musicMetaphor;
                       //mShadow = (PDEDrawableShapedShadow)musicMetaphor.createElementShadow();

                       //mMultilayer.addLayer(mShadow);
                       mMultilayer.addLayer(mElement);
                       // set element into view
                       mImageView.setImageDrawable(mMultilayer);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                               PDESizingScreenDirectionDiagonal );
                   }
                   // --- Film Metaphor Flat -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_FILMMETAPHORFLAT)) {
                       PDEDrawableFilmMetaphor filmmetaphor;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_FILMMETAPHORFLAT;
                       // create element
                       filmmetaphor= new PDEDrawableFilmMetaphor(getResources().getDrawable(R.drawable.baum2008));
                       //filmMetaphor.setElementDarkStyle(true);
                       mElement = filmmetaphor;
                       mMultilayer.addLayer(mElement);
                       // set element into view
                       mImageView.setImageDrawable(mMultilayer);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal );
                   }
                   // --- Film Metaphor Haptic-------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_FILMMETAPHORHAPTIC)) {
                       PDEDrawableFilmMetaphor filmMetaphor;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_FILMMETAPHORHAPTIC;
                       // create element
                       filmMetaphor = new PDEDrawableFilmMetaphor(getResources().getDrawable(R.drawable.baum2008));
                       filmMetaphor.setElementContentStyle(PDEConstants.PDEContentStyle.PDEContentStyleHaptic);
                       filmMetaphor.setElementShadowEnabled(true);
                       mElement = filmMetaphor;
                       //mShadow = (PDEDrawableShapedShadow)filmMetaphor.createElementShadow();
                       //mMultilayer.addLayer(mShadow);
                       mMultilayer.addLayer(mElement);
                       // set element into view
                       mImageView.setImageDrawable(mMultilayer);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                               PDESizingScreenDirectionDiagonal );
                   }
                   // --- Rounded Box -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_ROUNDED_BOX)) {
                       PDEDrawableRoundedBox roundedBox;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_ROUNDED_BOX;
                       // create element
                       roundedBox = new PDEDrawableRoundedBox();
                       mElement = roundedBox;
                       // set multilayer into view
                       mImageView.setImageDrawable(mElement);

                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- ScrollBar indicative vertical --------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_SCROLLBAR_INDICATIVE_VERTICAL)) {
                       PDEDrawableScrollBarIndicative scrollbarIndicative;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_SCROLLBAR_INDICATIVE_VERTICAL;
                       // create element
                       scrollbarIndicative = new PDEDrawableScrollBarIndicative();
                       mElement = scrollbarIndicative;
                       // configure element
                       scrollbarIndicative.setElementScrollbarType(PDEDrawableScrollBarIndicative
                                                                           .PDEDrawableScrollbarIndicativeType.PDEDrawableScrollbarIndicativeTypeVertical);
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
                   // --- ScrollBar indicative horizontal ------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_SCROLLBAR_INDICATIVE_HORIZONTAL)) {
                       PDEDrawableScrollBarIndicative scrollbarIndicative;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_SCROLLBAR_INDICATIVE_HORIZONTAL;
                       // create element
                       scrollbarIndicative = new PDEDrawableScrollBarIndicative();
                       mElement = scrollbarIndicative;
                       // configure element
                       scrollbarIndicative.setElementScrollbarType(PDEDrawableScrollBarIndicative
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
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_ROUNDED_GRADIENT_BOX)) {
                       PDEDrawableRoundedGradientBox roundedGradientBox;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_ROUNDED_GRADIENT_BOX;
                       // create element
                       roundedGradientBox = new PDEDrawableRoundedGradientBox();
                       mElement = roundedGradientBox;
                       // set multilayer into view
                       mImageView.setImageDrawable(mElement);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }

                   // --- Progressbar -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_PROGRESSBAR)) {
                       PDEDrawableProgressBar progressBar;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_PROGRESSBAR;
                       // create element
                       progressBar = new PDEDrawableProgressBar();
                       mElement = progressBar;
                       // configure element
                       progressBar.setElementProgressValue(0.5f);
                       progressBar.setElementPreloadValue(0.7f);
                       //progressBar.setElementPreloadStartValue(0.1f);
                       //progressBar.setElementProgressStartValue(0.2f);
                       //progressBar.setElementNumMarkers(4);

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
                   // --- CornerBox -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_CORNER_BOX)) {
                       PDEDrawableCornerBox cornerBox;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_CORNER_BOX;
                       // create element
                       cornerBox = new PDEDrawableCornerBox();
                       mElement = cornerBox;
                       // configure element
                       cornerBox.setElementRoundedCornerConfiguration(
                               PDEDrawableCornerBox.PDEDrawableCornerBoxCornerTopLeft |
                                       PDEDrawableCornerBox.PDEDrawableCornerBoxCornerBottomRight);

                       mShadow = (PDEDrawableShapedShadow)cornerBox.createElementShadow();
                       mMultilayer.addLayer(mShadow);
                       mMultilayer.addLayer(cornerBox);
                       // set multilayer into view
                       mImageView.setImageDrawable(mMultilayer);

                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- IconFont-Wrapper -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_PDEICONFONT)) {
                       PDEDrawableIconFont iconfontwrapper = new PDEDrawableIconFont("z");
                       //iconfontwrapper.setElementStretchToSize(true);
                       //iconfontwrapper.setElementShadowEnabled(true);
                       //iconfontwrapper.setElementShadowColor(PDEColor.valueOf("DTFunctionalRed"));
                       //iconfontwrapper.setElementShadowXOffset(3.0f);
                       //iconfontwrapper.setElementShadowYOffset(3.0f);
                       //iconfontwrapper.setElementPadding(3.0f);
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
//                       innerShadow.setElementShapeOpacity(0.28f);
                       innerShadow.setElementShapeColor(PDEColor.valueOf("Black34Alpha"));
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
                       sunkenArea.setElementBorderColor(PDEColor.valueOf("DTGrey237_Idle_Border"));
//                       sunkenArea.setElementInnerShadowOpacity(0.28f);
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
                       sunkenArea.setElementBorderColor(PDEColor.valueOf("DTGrey237_Idle_Border"));
//                       sunkenArea.setElementInnerShadowOpacity(0.28f);
                       mElement = sunkenArea;
                       // set element into view
                       mImageView.setImageDrawable(mElement);

                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- layer text ---------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_PDELAYERTEXT)) {
                       PDELayerText layerText;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_PDELAYERTEXT;
                       // create element
                       layerText = new PDELayerText("this is an even longer test string with more words");
                       //layerText.setElementLineDistanceFactor(0);
                       //layerText = new PDELayerText("Cluster Headline");
                       layerText.setElementTextSize(60);
                       layerText.setElementEllipsize(true);
                       //layerText.setElementPaddingLeft(50);
                       layerText.setElementPaddingAll(10);
                       //layerText.setElementTextColor(PDEColor.valueOf("DTDarkBlue"));
                       //layerText.setElementBackgroundColor(PDEColor.valueOf("DTYellow"));
                       layerText.setElementAlignmentMode(PDELayerText.PDELayerTextAlignmentMode.PDELayerTextAlignmentModeCapHeight);
                       //layerText.setElementBaseLine(-100);
                       //layerText.setElementShadowEnabled(true);
                       //layerText.setElementShadowColor(PDEColor.valueOf("DTFunctionalRed"));
                       //layerText.setElementShadowXOffset(3.0f);
                       //layerText.setElementShadowYOffset(3.0f);
                       layerText.setElementVerticalAlignment(PDEConstants.PDEVerticalAlignment.PDEAlignmentTop);
                       layerText.setElementHorizontalAlignment(PDEConstants.PDEAlignment.PDEAlignmentLeft);
                       //layerText.setElementPadding(50,60,20,10);
                       mElement = layerText;
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
                       sunkenArea.setElementBackgroundColor(PDEColor.valueOf("DTGrey237"));
                       sunkenArea.setElementBorderColor(PDEColor.valueOf("DTGrey208"));
//                       sunkenArea.setElementInnerShadowOpacity(0.28f);
                       // set multilayer into view
                       mImageView.setImageDrawable(mElement);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- text area ------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_TEXTAREA)) {
                       PDEDrawableSunkenArea sunkenArea;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_TEXTAREA;
                       // create element
                       sunkenArea = new PDEDrawableSunkenArea();
                       mElement = sunkenArea;
                       sunkenArea.setElementBackgroundColor(PDEColor.valueOf("DTWhite"));
                       sunkenArea.setElementBorderColor(PDEColor.valueOf("DTGrey208"));
                       sunkenArea.setElementInnerShadowBlurRadius(1.0f);
                       sunkenArea.setElementInnerShadowOpacity(0.17f);
                       sunkenArea.setElementInnerShadowLightIncidenceOffset(new PointF(0.0f, 1.0f));
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
                       mShadow = polaroid.createElementShadow();
                       mMultilayer.addLayer(mShadow);
                       mMultilayer.addLayer(mElement);
                       // set multilayer into view
                       mImageView.setImageDrawable(mMultilayer);
                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }
                   // --- CornerGradientBox -------------------------------------------------------------------------
                   else if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_CORNER_GRADIENT_BOX)) {
                       PDEDrawableCornerGradientBox cornerBox;
                       // remember
                       mChosenPrimitive = PRIMITIVE_NAME_CORNER_GRADIENT_BOX;
                       // create element
                       cornerBox = new PDEDrawableCornerGradientBox();
                       mElement = cornerBox;
                       // configure element
                       cornerBox.setElementRoundedCornerConfiguration(
                               PDEDrawableCornerBox.PDEDrawableCornerBoxCornerTopLeft |
                                       PDEDrawableCornerBox.PDEDrawableCornerBoxCornerBottomRight);

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

                       // set list header into view
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

                       // set list header into view
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

                       // set list header into view
                       mImageView.setImageDrawable(mElement);

                       // configure possible sizing directions
                       setContentSizingDirection(PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                                                 PDESizingScreenDirectionDiagonal);
                   }


                   //check if we have padding and maye correct container offset
                   if(mElement instanceof PDEDrawableInterface ){
                       float neededPadding = ((PDEDrawableInterface)mElement).getNeededPadding();

                       //correct view size with drawable padding
                       mPrimitiveDefaultSize.x += (neededPadding*2);
                       mPrimitiveDefaultSize.y += (neededPadding*2);
                        //correct view offset with drawable padding
                       mPrimitiveDefaultOffset.x = PDEBuildingUnits.BU() - (int)neededPadding;
                       mPrimitiveDefaultOffset.y = PDEBuildingUnits.BU() - (int)neededPadding;
                       setOptionalBoundsVisibilityPadding(neededPadding);
                   } else {
                       mPrimitiveDefaultOffset.x = PDEBuildingUnits.BU();
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


    @SuppressWarnings("unused")
    public void notifyPrimitiveAboutBoundsChange(Drawable source, Rect bounds) {
        if (DEBUG) Log.d(LOG_TAG, "CALLBACK!!! " + bounds.left + " " + bounds.top + " " + bounds.right + " " + bounds
                .bottom);
        if (mElement == null) return;
        if (TextUtils.equals(mChosenPrimitive, PRIMITIVE_NAME_STAGE_WHITE)){
            int padding = ((PDEDrawableRoundedBox) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                                        bounds.bottom -padding));
        } else if (TextUtils.equals(mChosenPrimitive, PRIMITIVE_NAME_STAGE_GRADIENT)){
            int padding = ((PDEDrawableRoundedGradientBox) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                                        bounds.bottom -padding));
        } else if (TextUtils.equals(mChosenPrimitive, PRIMITIVE_NAME_FILMMETAPHORHAPTIC)){
            int padding = ((PDEDrawableFilmMetaphor) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                    bounds.bottom - padding));
        } else if (TextUtils.equals(mChosenPrimitive, PRIMITIVE_NAME_FILMMETAPHORFLAT)){
            int padding = ((PDEDrawableFilmMetaphor) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                    bounds.bottom - padding));
        }
        else if (TextUtils.equals(mChosenPrimitive, PRIMITIVE_NAME_MUSICMETAPHORFLAT)){
            int padding = ((PDEDrawableMusicMetaphor) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                    bounds.bottom - padding));
        }
        else if (TextUtils.equals(mChosenPrimitive, PRIMITIVE_NAME_MUSICMETAPHORHAPTIC)){
            int padding = ((PDEDrawableMusicMetaphor) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                    bounds.bottom - padding));
        } else if (TextUtils.equals(mChosenPrimitive, PRIMITIVE_NAME_PHOTOFRAMEFLAT)){
            int padding = ((PDEDrawablePhotoFrame) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                    bounds.bottom - padding));
        } else if (TextUtils.equals(mChosenPrimitive, PRIMITIVE_NAME_PHOTOFRAMEHAPTIC)){
            int padding = ((PDEDrawablePhotoFrame) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                    bounds.bottom - padding));
        } else if (TextUtils.equals(mChosenPrimitive, PRIMITIVE_NAME_CORNER_BOX)){
            int padding = ((PDEDrawableCornerBox) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                                        bounds.bottom - padding));
        }  else if (TextUtils.equals(mChosenPrimitive, PRIMITIVE_NAME_PROGRESSBAR_HANDLE)){
            int padding = ((PDEDrawableRoundedGradientBox) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                                        bounds.bottom - padding));
        } else if (TextUtils.equals(mChosenPrimitive, PRIMITIVE_NAME_NOTIFICATION_FRAME) || TextUtils.equals
                (mChosenPrimitive, PRIMITIVE_NAME_NOTIFICATION_FRAME_WHITE) ||
                  TextUtils.equals(mChosenPrimitive, PRIMITIVE_NAME_NOTIFICATION_FRAME_WHITE_RIGHT)){
            int padding = ((PDEDrawableNotificationFrame) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                                        bounds.bottom - padding));
        } else if (TextUtils.equals(mChosenPrimitive, PRIMITIVE_NAME_POLAROID)){
            int padding = ((PDEDrawablePolaroidFrame) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                                        bounds.bottom - padding));
        } else if (TextUtils.equals(mChosenPrimitive, PRIMITIVE_NAME_CORNER_GRADIENT_BOX)){
            int padding = ((PDEDrawableCornerGradientBox) mElement).getNeededPadding();
            mElement.setBounds(new Rect(bounds.left + padding, bounds.top + padding, bounds.right - padding,
                                        bounds.bottom - padding));
        }

        if (mShadow == null) return;
        mShadow.setLayoutOffset(new Point(Math.round(mElement.getBounds().left - mShadow.getElementBlurRadius()),
                                          Math.round(mElement.getBounds().top - mShadow.getElementBlurRadius())
                                                        + PDEBuildingUnits.oneTwelfthsBU()));
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
                Point newSize = new Point();
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
                if (mElement instanceof PDEDrawableInterface ){
                    //for correct view size we need to add the drawable padding on each side
                    newSize.x += ((PDEDrawableInterface)mElement).getNeededPadding() * 2;
                    newSize.y += ((PDEDrawableInterface)mElement).getNeededPadding() * 2;
                }
                setContainerSize(newSize);
            }
        });
    }
}
