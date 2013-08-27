/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;

// imports
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.components.sliders.PDEEventSliderControllerState;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESlider;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESliderController;


/**
 * This View helps to controll Properties of a given PDESlider
 */
public class SliderSizetestRegulatorView extends LinearLayout implements SeekBar.OnSeekBarChangeListener{

    //----- constants -----

    /**
     * @brief Regulatortypes
     */
    public enum SliderSizetestRegulatortype {
        Postion,
        StartPostion,
        PageSize,
    }


    //----- properties -----

    // change type
    private int mSliderControllerId;
    private SliderSizetestRegulatortype mRegulatortype;
    private boolean mShowValue;

    // regulator labels
    private TextView mRegulatorNameLabel;
    private TextView mRegulatorValueLabel;

    // regulator
    private SeekBar mRegulator;

    // Store Slider
    private PDESlider mSlider;


    // ----------------------- constructor ------------------------------------------------------


    public SliderSizetestRegulatorView(Context context) {
        super(context);
        init();
    }

    public SliderSizetestRegulatorView (Context context, AttributeSet attrs) {

        super(context, attrs);
        init();
    }

    @SuppressLint("NewApi")
    public SliderSizetestRegulatorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        LinearLayout.inflate(getContext(), R.layout.slider_sizetest_regulator, this);
        mRegulatortype = SliderSizetestRegulatortype.Postion;
        mSliderControllerId = 0;
        mShowValue = true;

        // store labels
        mRegulatorNameLabel   = (TextView)findViewById(R.id.slider_sizetest_regulator_nameLabel);
        mRegulatorValueLabel  = (TextView)findViewById(R.id.slider_sizetest_regulator_valueLabel);

        mRegulator = (SeekBar)findViewById(R.id.slider_sizetest_regulator_sliderBar);
        mRegulator.setOnSeekBarChangeListener(this);
    }


    // ----------------------- access properties ------------------------------------------------


    /**
     * @brief Set pdeSlider to be controlled
     *
     */
    public void setSlider (PDESlider slider) {
        this.mSlider = slider;
        updateRegulatorLabels();
        onProgressChanged(mRegulator,0,false);
    }


    /**
     * @brief Set which controller has to be regulated
     *
     * @param controllerId
     */
    public void setSliderControllerId (int controllerId) {
        this.mSliderControllerId = controllerId;
        updateRegulatorLabels();
        onProgressChanged(mRegulator,0,false);
    }


    /**
     *  @brief  Set which property of the slider is regulated
     */
    public void setRegulatortype (SliderSizetestRegulatortype regulatortype) {
        this.mRegulatortype = regulatortype;
        updateRegulatorLabels();
        onProgressChanged(mRegulator,0,false);
    }


    /**
     * @brief Set if regulator shows his current value
     *
     * @param showValue
     */
    public void setShowValue(boolean showValue) {
        this.mShowValue = showValue;
        updateRegulatorLabels();
    }


    // ----------------------- seekbar listener ------------------------------------------------


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        float value;

        // set value to slider
        value = progress/100.0f;
        if (mRegulatortype == SliderSizetestRegulatortype.Postion) mSlider.getSliderControllerForId(mSliderControllerId).setSliderPosition(value);
        else if (mRegulatortype == SliderSizetestRegulatortype.StartPostion)  mSlider.getSliderControllerForId(mSliderControllerId).setSliderStartPosition(value);
        else if (mRegulatortype == SliderSizetestRegulatortype.PageSize)  mSlider.getSliderControllerForId(mSliderControllerId).setSliderPageSize(value);
        else return;

        // set value label if it is active
        if (mShowValue) mRegulatorValueLabel.setText(""+value);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    // ----------------------- helper ------------------------------------------------------------


    /**
     * @brief update label information
     */
    private void updateRegulatorLabels() {

        // set name label
        if (mRegulatortype == SliderSizetestRegulatortype.Postion) mRegulatorNameLabel.setText("Postion "+ mSliderControllerId);
        else if (mRegulatortype == SliderSizetestRegulatortype.StartPostion) mRegulatorNameLabel.setText("Start Position "+ mSliderControllerId);
        else if (mRegulatortype == SliderSizetestRegulatortype.PageSize) mRegulatorNameLabel.setText("Page Size "+ mSliderControllerId);
        else mRegulatorNameLabel.setText("unspecified Type");

        // set value label
        if (!mShowValue) mRegulatorValueLabel.setText("");
    }

}
