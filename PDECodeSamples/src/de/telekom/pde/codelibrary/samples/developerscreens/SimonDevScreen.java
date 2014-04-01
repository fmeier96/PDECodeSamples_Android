package de.telekom.pde.codelibrary.samples.developerscreens;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.sliders.PDEEventSliderControllerState;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESlider;
import de.telekom.pde.codelibrary.ui.components.sliders.PDESliderController;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;



public class SimonDevScreen extends PDEActivity implements SeekBar.OnSeekBarChangeListener {


    private static final String LOG_TAG = SimonDevScreen.class.getName();

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

    private PDESlider mSliderOne;
    private PDESlider mSliderTwo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developer_simon_devscreen);

        //get the root view and set background color (different when darkstyle is on or of in library)
        RelativeLayout rootView = (RelativeLayout)findViewById(R.id.developer_simon_devscreen_relativelayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        // get Slider
        mSliderOne = (PDESlider) findViewById(R.id.developer_simon_devscreen_pdeSliderOne);
        mSliderTwo = (PDESlider) findViewById(R.id.developer_simon_devscreen_pdeSliderTwo);

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
        mRegulatorTwoLabel.setText("Page Size");
        mRegulatorThreeLabel.setText("Range Start");
        mRegulatorFourLabel.setText("Range End");

        // couple Sliders
        mSliderTwo.setSliderControllerForId(mSliderOne.getSliderControllerForId(0),0);

        // add listener
        //mSliderOne.addListener(this, "cbSlider",PDESliderController.PDE_SLIDER_CONTROLLER_EVENT_MASK_DATA_WILL_CHANGE);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //float position;


        // regulator one handling
        if (seekBar == mRegulatorOne)  {
            //position = progress/100.0f;
            progress -= 50;
            mRegulatorOneValue.setText(""+progress);
            mSliderOne.getSliderControllerForId(0).setSliderPositionUserRange(progress);
        }

        // regulator two handling
        else if (seekBar == mRegulatorTwo) {
            //position = progress/100.0f;
            progress -= 50;
            mRegulatorTwoValue.setText(""+progress);
            mSliderOne.getSliderControllerForId(0).setSliderPageSizeUserRange(progress);
        }

        // regulator three handling
        else if (seekBar == mRegulatorThree) {
            //position = progress/100.0f;
            progress -= 50;
            mRegulatorThreeValue.setText(""+progress);
            mSliderOne.getSliderControllerForId(0).setSliderValueRangeMinimum(progress);
        }

        // regulator four handling
        else if (seekBar == mRegulatorFour) {
            //position = progress/100.0f;
            progress -= 50;
            mRegulatorFourValue.setText(""+progress);
            mSliderTwo.getSliderControllerForId(0).setSliderValueRangeMaximum(progress);
        }
    }


    // Listen to slider
    @SuppressWarnings("unused")
    public void cbSlider(PDEEvent event){
        PDEEventSliderControllerState sliderEvent;

        sliderEvent = (PDEEventSliderControllerState) event;

        sliderEvent.setSliderPositionUserRange(20.0f);
        sliderEvent.setSliderPageSizeUserRange(30.0f);
        sliderEvent.setProcessed();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @SuppressWarnings("unused")
    public void startMovingThread(){
        Log.d(LOG_TAG,"start Thread");

        new Thread(new Runnable() {
            public void run() {

                PDEEventSliderControllerState sliderEvent;
                int value = 0;
                float position;
                int stepValue = 10;
                int i = 0;

                while (i<7) {


                    value = value+stepValue;

                    if (value >= 100) {
                        stepValue = -10;
                        i++;
                    }

                    else if (value <= 0) stepValue = 10;

                    //Log.d(LOG_TAG, "" + value);

                    position = value/100.0f;

                    Log.d(LOG_TAG,"Position: "+ position);

                    sliderEvent = new PDEEventSliderControllerState();
                    sliderEvent.setSliderPosition(position);
                    sliderEvent.setType(PDESliderController.PDE_SLIDER_CONTROLLER_EVENT_MASK_ACTION);
                    sliderEvent.setSliderControllerId(0);
                    //final PDEEventSliderControllerState finalSliderEvent = sliderEvent;
                    //mSliderOne.post(new Runnable() {
                    //    public void run() {
                    //        mSliderOne.controllHelp(finalSliderEvent);
                    //    }
                    //});

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();


    }
}