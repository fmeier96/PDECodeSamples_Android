/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.genericstyle.pdeslider;

// imports

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.sliders.PDEEventSliderControllerState;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESlider;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESliderController;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;


/**
 * This View helps to controll Properties of a given PDESlider
 */
public class SliderRegulatorHelperGenericView extends LinearLayout {

    //----- constants -----

    /**
     * @brief Regulatortypes
     */
    public enum SliderRegulatorHelperType {
        Postion,
        StartPostion,
        PageSize,
    }


    //----- properties -----

    // change type
    private int mSliderControllerId;
    private SliderRegulatorHelperType mRegulatortype;
    private boolean mShowValue;

    // regulator labels
    private TextView mRegulatorNameLabel;
    private TextView mRegulatorValueLabel;

    // regulator
    private PDESlider mRegulator;

    // Store Slider
    private PDESlider mSlider;

    // style flag
    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;


    // ----------------------- constructor ------------------------------------------------------


    public SliderRegulatorHelperGenericView(Context context) {
        super(context);
        init();
    }

    @SuppressWarnings("unused")
    public SliderRegulatorHelperGenericView(Context context, AttributeSet attrs) {

        super(context, attrs);
        init();
    }

    @SuppressWarnings("unused")
    @SuppressLint("NewApi")
    public SliderRegulatorHelperGenericView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public SliderRegulatorHelperGenericView(Context context, PDEConstants.PDEContentStyle contentStyle) {
        super(context);
        mStyle = contentStyle;
        init();
    }

    @SuppressWarnings("unused")
    public SliderRegulatorHelperGenericView(Context context, AttributeSet attrs , PDEConstants.PDEContentStyle contentStyle) {
        super(context, attrs);
        mStyle = contentStyle;
        init();
    }

    @SuppressWarnings("unused")
    @SuppressLint("NewApi")
    public SliderRegulatorHelperGenericView(Context context, AttributeSet attrs, int defStyle, PDEConstants.PDEContentStyle contentStyle) {
        super(context, attrs, defStyle);
        mStyle = contentStyle;
        init();
    }

    private void init() {
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            LinearLayout.inflate(getContext(), R.layout.slider_regulator_helper_haptic, this);
        } else {
            LinearLayout.inflate(getContext(), R.layout.slider_regulator_helper_flat, this);
        }

        mRegulatortype = SliderRegulatorHelperType.Postion;
        mSliderControllerId = 0;
        mShowValue = false;

        // store labels
        mRegulatorNameLabel   = (TextView)findViewById(R.id.slider_regulator_helper_nameLabel);
        mRegulatorValueLabel  = (TextView)findViewById(R.id.slider_regulator_helper_valueLabel);

        // setup regulator
        mRegulator = (PDESlider)findViewById(R.id.slider_regulator_helper_sliderBar);
        mRegulator.addListener(this, "cbSlider", PDESliderController.PDE_SLIDER_CONTROLLER_EVENT_DATA_HAS_CHANGED);

        // setup regulator labels
        mRegulatorNameLabel.setTextColor(PDEColor.DTUITextColor().getIntegerColor());
        mRegulatorValueLabel.setTextColor(PDEColor.DTUITextColor().getIntegerColor());
    }


    // ----------------------- access properties ------------------------------------------------


    /**
     * @brief Set pdeslider to be controlled
     *
     */
    public void setSlider (PDESlider slider) {
        this.mSlider = slider;
        updateRegulatorLabels();
        mRegulator.getSliderControllerForId(0).setSliderPosition(0);
    }


    /**
     * @brief Set which controller has to be regulated
     */
    public void setSliderControllerId (int controllerId) {
        this.mSliderControllerId = controllerId;
        updateRegulatorLabels();
        mRegulator.getSliderControllerForId(0).setSliderPosition(0);
    }


    /**
     *  @brief  Set which property of the slider is regulated
     */
    public void setRegulatortype (SliderRegulatorHelperType regulatortype) {
        this.mRegulatortype = regulatortype;
        updateRegulatorLabels();
        mRegulator.getSliderControllerForId(0).setSliderPosition(0);
    }


    /**
     * @brief Set if regulator shows his current value
     */
    @SuppressWarnings("unused")
    public void setShowValue(boolean showValue) {
        this.mShowValue = showValue;
        updateRegulatorLabels();
    }


    // ---------------------- regulator listener -------------------------------------------------


    /**
     * @brief Listen to changes on regulator Sliders
     *
     * @param event  PDESliderControllerState with all required data
     */
    @SuppressWarnings("unused")
    public void cbSlider(PDEEvent event){

        float value;

         // get value
        value = ((PDEEventSliderControllerState) event).getSliderPosition();

        // security check
        if(mSlider == null) return;

        // set value label if it is active
        if (mShowValue) mRegulatorValueLabel.setText(""+value);

        if (mRegulatortype == SliderRegulatorHelperType.Postion) {
            // only set new values
            if (value == mSlider.getSliderControllerForId(mSliderControllerId).getSliderPosition()) return;
            mSlider.getSliderControllerForId(mSliderControllerId).setSliderPosition(value);
        }
        else if (mRegulatortype == SliderRegulatorHelperType.StartPostion) {
            // only set new values
            if (value == mSlider.getSliderControllerForId(mSliderControllerId).getSliderStartPosition()) return;
            mSlider.getSliderControllerForId(mSliderControllerId).setSliderStartPosition(value);
        }
        else if (mRegulatortype == SliderRegulatorHelperType.PageSize) {
            // only set new values
            if (value == mSlider.getSliderControllerForId(mSliderControllerId).getSliderPageSize()) return;
            mSlider.getSliderControllerForId(mSliderControllerId).setSliderPageSize(value);
        }
    }


    // ----------------------- helper ------------------------------------------------------------


    /**
     * @brief update label information
     */
    private void updateRegulatorLabels() {

        // set name label
        if (mRegulatortype == SliderRegulatorHelperType.Postion) mRegulatorNameLabel.setText("Position "+ mSliderControllerId);
        else if (mRegulatortype == SliderRegulatorHelperType.StartPostion) mRegulatorNameLabel.setText("Start Position "+ mSliderControllerId);
        else if (mRegulatortype == SliderRegulatorHelperType.PageSize) mRegulatorNameLabel.setText("Page Size "+ mSliderControllerId);
        else mRegulatorNameLabel.setText("unspecified Type");

        // set value label
        if (!mShowValue) mRegulatorValueLabel.setText("");
    }
}
