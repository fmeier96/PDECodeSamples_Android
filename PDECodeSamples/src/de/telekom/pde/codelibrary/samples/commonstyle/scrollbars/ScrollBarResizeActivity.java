/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.commonstyle.scrollbars;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.basescreens.DialogHelper;
import de.telekom.pde.codelibrary.samples.basescreens.ResizeBaseActivity;
import de.telekom.pde.codelibrary.samples.genericstyle.pdeslider.SliderRegulatorHelperGenericView;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESlider;
import de.telekom.pde.codelibrary.ui.elements.common.PDEDrawableDelimiter;
import de.telekom.pde.codelibrary.ui.helpers.PDEUtils;

import java.util.ArrayList;


public class ScrollBarResizeActivity extends ResizeBaseActivity {

    // global names of the different sliders
    private final static String SLIDER_NAME_SCROLLBAR_HORIZONTAL = "Scrollbar Horizontal";
    private final static String SLIDER_NAME_SCROLLBAR_VERTICAL = "Scrollbar Vertical";
    private final static String SLIDER_NAME_SCROLLBAR_HANDLE_ONLY_HORIZONTAL = "Scrollbar Handle Only Horizontal ";
    private final static String SLIDER_NAME_SCROLLBAR_HANDLE_ONLY_VERTICAL = "Scrollbar Handle Only Vertical";


    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = ScrollBarResizeActivity.class.getName();

    // slider
    private PDESlider mSlider;

    // different slider contents
    private ArrayList<String> mSliderChoiceArrayList;

    // store viewgroups
    private ArrayList<ViewGroup> mRegulatorArray;

    // structure layout
    private View separatorTopLine;


    // ---------------------------------- initialize --------------------------------------------------------------------

    /**
     * @brief Create the activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create the slider
        mSlider = new PDESlider(this);

        // set layout
        RelativeLayout.LayoutParams sliderLinearLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                                               ViewGroup.LayoutParams.MATCH_PARENT);

        // add slider to resize container
        addViewToResizeContainer(mSlider, sliderLinearLayoutParams);

        // store slider regulator helpers
        mRegulatorArray = new ArrayList<ViewGroup>();

        // setup thin seperator line
        separatorTopLine = new View(ScrollBarResizeActivity.this);
        separatorTopLine.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        PDEUtils.setViewBackgroundDrawable(separatorTopLine, new PDEDrawableDelimiter());
        ((ViewGroup) findViewById(R.id.resize_base_rootlayout)).addView(separatorTopLine, 1);

        // setup slider choice
        fillSliderChoiceArray();

        getSupportActionBar().setTitle("Common Style/Scrollbar resizing");
        setContainerSize(PDEBuildingUnits.exactPixelFromBU(10.0f),PDEBuildingUnits.exactPixelFromBU(6.0f));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSelectionPos(LEFT_RIGHT_BUTTON.LEFT, 0);
    }


    // ---------------------------------- helper -----------------------------------------------------------------------


    /**
     * @brief Add choice list with possible slider contents
     */
    private void fillSliderChoiceArray() {

        // create arraylist
        mSliderChoiceArrayList = new ArrayList<String>();

        // add names
        mSliderChoiceArrayList.add(SLIDER_NAME_SCROLLBAR_HANDLE_ONLY_HORIZONTAL);
        mSliderChoiceArrayList.add(SLIDER_NAME_SCROLLBAR_HANDLE_ONLY_VERTICAL);
        mSliderChoiceArrayList.add(SLIDER_NAME_SCROLLBAR_HORIZONTAL);
        mSliderChoiceArrayList.add(SLIDER_NAME_SCROLLBAR_VERTICAL);


        // set choice information and listener
        addChoiceArrayList(LEFT_RIGHT_BUTTON.LEFT, "Choose", mSliderChoiceArrayList,
                           new DialogHelper.ChoiceListOnItemClickListener() {
                               @Override
                               public void onListItemClicked(String itemContentString) {

                                   // remove old regulators
                                   for (ViewGroup vg : mRegulatorArray) {
                                       ((ViewGroup) vg.getParent()).removeView(vg);
                                   }

                                   // remove old regulators
                                   mRegulatorArray.clear();


                                   // react on list selection


                                   // ----- Scrollbar Horizontal -----

                                   if (TextUtils.equals(itemContentString, SLIDER_NAME_SCROLLBAR_HORIZONTAL)) {

                                       // set type to scrollbar horizontal
                                       mSlider.setSliderContentType(PDESlider.PDESliderContentType.ScrollBarHorizontal);

                                       // control pages size for controller 0
                                       SliderRegulatorHelperGenericView scrollbarHorizontalPageSize = new SliderRegulatorHelperGenericView(ScrollBarResizeActivity.this);
                                       scrollbarHorizontalPageSize.setSlider(mSlider);
                                       scrollbarHorizontalPageSize.setSliderControllerId(0);
                                       scrollbarHorizontalPageSize.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.PageSize);
                                       mRegulatorArray.add(scrollbarHorizontalPageSize);

                                       // control position for controller 0
                                       SliderRegulatorHelperGenericView scrollbarHorizontalPosition = new SliderRegulatorHelperGenericView(ScrollBarResizeActivity.this);
                                       scrollbarHorizontalPosition.setSlider(mSlider);
                                       scrollbarHorizontalPosition.setSliderControllerId(0);
                                       scrollbarHorizontalPosition.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.Postion);
                                       mRegulatorArray.add(scrollbarHorizontalPosition);
                                   }


                                   // ----- Scrollbar Vertical -----

                                   else if (TextUtils.equals(itemContentString, SLIDER_NAME_SCROLLBAR_VERTICAL)) {

                                       // set type to scrollbar horizontal
                                       mSlider.setSliderContentType(PDESlider.PDESliderContentType.ScrollBarVertical);

                                       // control pages size for controller 0
                                       SliderRegulatorHelperGenericView scrollbarVerticalPageSize = new SliderRegulatorHelperGenericView(ScrollBarResizeActivity.this);
                                       scrollbarVerticalPageSize.setSlider(mSlider);
                                       scrollbarVerticalPageSize.setSliderControllerId(0);
                                       scrollbarVerticalPageSize.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.PageSize);
                                       mRegulatorArray.add(scrollbarVerticalPageSize);

                                       // control position for controller 0
                                       SliderRegulatorHelperGenericView scrollbarVerticalPosition = new SliderRegulatorHelperGenericView(ScrollBarResizeActivity.this);
                                       scrollbarVerticalPosition.setSlider(mSlider);
                                       scrollbarVerticalPosition.setSliderControllerId(0);
                                       scrollbarVerticalPosition.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.Postion);
                                       mRegulatorArray.add(scrollbarVerticalPosition);
                                   }


                                   // ----- Scrollbar Handle Only Horizontal -----

                                   else if (TextUtils.equals(itemContentString, SLIDER_NAME_SCROLLBAR_HANDLE_ONLY_HORIZONTAL)) {

                                       // set type to scrollbar horizontal
                                       mSlider.setSliderContentType(PDESlider.PDESliderContentType.ScrollBarHandleOnlyHorizontal);

                                       // control pages size for controller 0
                                       SliderRegulatorHelperGenericView scrollbarHorizontalPageSize = new SliderRegulatorHelperGenericView(ScrollBarResizeActivity.this);
                                       scrollbarHorizontalPageSize.setSlider(mSlider);
                                       scrollbarHorizontalPageSize.setSliderControllerId(0);
                                       scrollbarHorizontalPageSize.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.PageSize);
                                       mRegulatorArray.add(scrollbarHorizontalPageSize);

                                       // control position for controller 0
                                       SliderRegulatorHelperGenericView scrollbarHorizontalPosition = new SliderRegulatorHelperGenericView(ScrollBarResizeActivity.this);
                                       scrollbarHorizontalPosition.setSlider(mSlider);
                                       scrollbarHorizontalPosition.setSliderControllerId(0);
                                       scrollbarHorizontalPosition.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.Postion);
                                       mRegulatorArray.add(scrollbarHorizontalPosition);
                                   }


                                   // ----- Scrollbar Handle Only Vertical -----

                                   else if (TextUtils.equals(itemContentString, SLIDER_NAME_SCROLLBAR_HANDLE_ONLY_VERTICAL)) {

                                       // set type to scrollbar horizontal
                                       mSlider.setSliderContentType(PDESlider.PDESliderContentType.ScrollBarHandleOnlyVertical);

                                       // control pages size for controller 0
                                       SliderRegulatorHelperGenericView scrollbarVerticalPageSize = new SliderRegulatorHelperGenericView(ScrollBarResizeActivity.this);
                                       scrollbarVerticalPageSize.setSlider(mSlider);
                                       scrollbarVerticalPageSize.setSliderControllerId(0);
                                       scrollbarVerticalPageSize.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.PageSize);
                                       mRegulatorArray.add(scrollbarVerticalPageSize);

                                       // control poistion for controller 0
                                       SliderRegulatorHelperGenericView scrollbarVerticalPosition = new SliderRegulatorHelperGenericView(ScrollBarResizeActivity.this);
                                       scrollbarVerticalPosition.setSlider(mSlider);
                                       scrollbarVerticalPosition.setSliderControllerId(0);
                                       scrollbarVerticalPosition.setRegulatortype(SliderRegulatorHelperGenericView.SliderRegulatorHelperType.Postion);
                                       mRegulatorArray.add(scrollbarVerticalPosition);
                                   }



                                   // add new regulator views
                                   for (ViewGroup vg : mRegulatorArray) {
                                       ((ViewGroup) findViewById(R.id.resize_base_rootlayout)).addView(vg, 2);
                                   }
                               }
                           });
    }
}
