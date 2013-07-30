package de.telekom.pde.codelibrary.samples.developerscreens;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESlider;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESliderController;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESliderControllerState;


/**
 * Created with IntelliJ IDEA.
 * User: si-fab
 * Date: 03.06.13
 * Time: 15:06
 * To change this template use File | Settings | File Templates.
 */
public class SimonDevScreen extends PDEActivity implements SeekBar.OnSeekBarChangeListener {

    // regulator labels
    private TextView mRegulatorOneLabel;
    private TextView mRegulatorTwoLabel;
    private TextView mRegulatorThreeLabel;
    private TextView mRegulatorFourLabel;

    // regulator value labels
    private TextView mRegulatorOneValue;
    private TextView mRegulatorTwoValue;
    private TextView mRegulatorThreeValue;
    private TextView mRegulatorFourValue;

    // regulators
    private SeekBar mRegulatorOne;
    private SeekBar mRegulatorTwo;
    private SeekBar mRegulatorThree;
    private SeekBar mRegulatorFour;

    private PDESlider mSlider;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developer_simon_devscreen);

        //get the root view and set background color (different when darkstyle is on or of in library)
        RelativeLayout rootView = (RelativeLayout)findViewById(R.id.developer_simon_devscreen_relativelayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        // get Slider
        mSlider = (PDESlider) findViewById(R.id.developer_simon_devscreen_pdeSlider);

        // store labels
        mRegulatorOneLabel   = (TextView)findViewById(R.id.developer_simon_devscreen_regulatorOneLabel);
        mRegulatorTwoLabel   = (TextView)findViewById(R.id.developer_simon_devscreen_regulatorTwoLabel);
        mRegulatorThreeLabel = (TextView)findViewById(R.id.developer_simon_devscreen_regulatorThreeLabel);
        mRegulatorFourLabel  = (TextView)findViewById(R.id.developer_simon_devscreen_regulatorFourLabel);

        // store value labels
        mRegulatorOneValue   = (TextView)findViewById(R.id.developer_simon_devscreen_regulatorOneValue);
        mRegulatorTwoValue   = (TextView)findViewById(R.id.developer_simon_devscreen_regulatorTwoValue);
        mRegulatorThreeValue = (TextView)findViewById(R.id.developer_simon_devscreen_regulatorThreeValue);
        mRegulatorFourValue  = (TextView)findViewById(R.id.developer_simon_devscreen_regulatorFourValue);

        // store regulators
        mRegulatorOne    = (SeekBar) findViewById(R.id.developer_simon_devscreen_regulatorOne);
        mRegulatorTwo    = (SeekBar) findViewById(R.id.developer_simon_devscreen_regulatorTwo);
        mRegulatorThree  = (SeekBar) findViewById(R.id.developer_simon_devscreen_regulatorThree);
        mRegulatorFour   = (SeekBar) findViewById(R.id.developer_simon_devscreen_regulatorFour);

        // add Listeners
        mRegulatorOne.setOnSeekBarChangeListener(this);
        mRegulatorTwo.setOnSeekBarChangeListener(this);
        mRegulatorThree.setOnSeekBarChangeListener(this);
        mRegulatorFour.setOnSeekBarChangeListener(this);

        // set Labels
        mRegulatorOneLabel.setText("Progress");
        mRegulatorTwoLabel.setText("Preload");
        mRegulatorThreeLabel.setText("Width");
        mRegulatorFourLabel.setText("Test4");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        PDESliderControllerState sliderEvent;
        float position;


        // regulator one handling
        if (seekBar == mRegulatorOne)  {
            position = progress/100.0f;
            mRegulatorOneValue.setText(""+position);
            sliderEvent = new PDESliderControllerState();
            sliderEvent.setSliderPosition(position);
            sliderEvent.setType(PDESliderController.PDE_SLIDER_CONTROLLER_EVENT_MASK_ACTION);
            sliderEvent.setSliderControllerId(0);
            mSlider.controllHelp(sliderEvent);
        }

        // regulator two handling
        else if (seekBar == mRegulatorTwo) {
            position = progress/100.0f;
            mRegulatorTwoValue.setText(""+position);
            sliderEvent = new PDESliderControllerState();
            sliderEvent.setSliderPosition(position);
            sliderEvent.setType(PDESliderController.PDE_SLIDER_CONTROLLER_EVENT_MASK_ACTION);
            sliderEvent.setSliderControllerId(1);
            mSlider.controllHelp(sliderEvent);
        }

        // regulator three handling
        else if (seekBar == mRegulatorThree) {
            mRegulatorThreeValue.setText(""+progress);
        }

        // regulator four handling
        else if (seekBar == mRegulatorFour) {
            mRegulatorFourValue.setText(""+progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}