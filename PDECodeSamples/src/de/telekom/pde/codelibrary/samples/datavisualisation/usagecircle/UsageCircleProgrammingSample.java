package de.telekom.pde.codelibrary.samples.datavisualisation.usagecircle;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.basescreens.DialogHelper;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.components.datavisualisation.PDEUsageEvent;
import de.telekom.pde.codelibrary.ui.components.datavisualisation.usagecircle.PDEUsageCircle;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;

import java.util.ArrayList;


//----------------------------------------------------------------------------------------------------------------------
//  UsageCircleProgrammingSample
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief UsageCircleProgrammingSample activity for a usage circle sample.
 */
@SuppressWarnings("unused")
public class UsageCircleProgrammingSample extends PDEActionBarActivity {


    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = UsageCircleProgrammingSample.class.getName();


    // Private variables to handle touches with the thumb
    private Button mStyleButton;

    // Two predefined dialogs
    private DialogHelper mStyleDialogHelper;

    //global names of the primitive elements
    private final static String STYLE_DEFAULT = "Default";
    private final static String STYLE_LEAD_CIRCLE = "Lead Circle";
    private final static String STYLE_SLIM_CIRCLE = "Slim Circle";
    private final static String STYLE_SEGMENTED = "Segmented";
    private final static String STYLE_DIVIDED = "Divided";


    // image view as drawable container
    PDEUsageCircle mUsageCircle;
    ArrayList<String> mStyleArrayList;


    /**
     * @brief Create the activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.usagecircle_programming_sample);


        Button mUsageMinusButton = ((Button) findViewById(R.id.usageminus_button));
        Button mUsagePlusButton = ((Button) findViewById(R.id.usageplus_button));
        mStyleButton = ((Button) findViewById(R.id.style_button));
        Button mTotalMinusButton = ((Button) findViewById(R.id.totalminus_button));
        Button mTotalPlusButton = ((Button) findViewById(R.id.totalplus_button));
        Button mSegMinusButton = ((Button) findViewById(R.id.segminus_button));
        Button mSegPlusButton = ((Button) findViewById(R.id.segplus_button));
        Button mTwentyButton = ((Button) findViewById(R.id.twenty_button));
        Button mEightyButton = ((Button) findViewById(R.id.eighty_button));
        ToggleButton mCircleButton = (ToggleButton) findViewById(R.id.circle_button);
        ToggleButton mTextButton = (ToggleButton) findViewById(R.id.text_button);
        ToggleButton mUnitButton = (ToggleButton) findViewById(R.id.unit_button);

        // create some predefined dialog helpers for left and right button
        mStyleDialogHelper = new DialogHelper(this, mStyleButton);

        mUsageCircle = ((PDEUsageCircle) findViewById(R.id.usageCircle));
        mUsageCircle.setTotalFillValue(60.0f);
        mUsageCircle.setTargetFillValue(0.0f);
        mUsageCircle.setFillDurationToTotalFillValue(2000);
        mUsageCircle.setNumberOfDecimalPlaces(1);
        mUsageCircle.setUnitText("min");
        mUsageCircle.setUnitTextEnabled(false);

        mUsageCircle.addListener(this, "onProgressChanged");

        fillStyleArray();
        setSelectionPos(0);
        mCircleButton.setChecked(true);
        mTextButton.setChecked(true);
        mUnitButton.setChecked(false);


        mCircleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    mUsageCircle.setCircleEnabled(true);
                } else {
                    mUsageCircle.setCircleEnabled(false);
                }
            }
        });

        mTextButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    mUsageCircle.setTextViewsEnabled(true);
                } else {
                    mUsageCircle.setTextViewsEnabled(false);
                }
            }
        });

        mUnitButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    mUsageCircle.setUnitTextEnabled(true);
                } else {
                    mUsageCircle.setUnitTextEnabled(false);
                }
            }
        });
     }


    /**
     * @brief Fill style array.
     */
    private void fillStyleArray() {
        mStyleArrayList = new ArrayList<String>();

        //fill array
        mStyleArrayList.add(STYLE_DEFAULT);
        mStyleArrayList.add(STYLE_LEAD_CIRCLE);
        mStyleArrayList.add(STYLE_SLIM_CIRCLE);
        mStyleArrayList.add(STYLE_SEGMENTED);
        mStyleArrayList.add(STYLE_DIVIDED);

        //set choice information and listener
        addChoiceArrayList("Style", mStyleArrayList, new DialogHelper.ChoiceListOnItemClickListener() {
            @Override
            public void onListItemClicked(String itemContentString) {
                // react on list selection
                if (TextUtils.equals(itemContentString, STYLE_DEFAULT)) {
                    mUsageCircle.setCircleStyle(PDEUsageCircle.PDEUsageCircleStyle.PDEUsageCircleStyleDefault);
                }
                if (TextUtils.equals(itemContentString, STYLE_LEAD_CIRCLE)) {
                    mUsageCircle.setCircleStyle(PDEUsageCircle.PDEUsageCircleStyle.PDEUsageCircleStyleLeadCircle);
                }
                if (TextUtils.equals(itemContentString, STYLE_SLIM_CIRCLE)) {
                    mUsageCircle.setCircleStyle(PDEUsageCircle.PDEUsageCircleStyle.PDEUsageCircleStyleSlimCircle);
                }
                if (TextUtils.equals(itemContentString, STYLE_SEGMENTED)) {
                    mUsageCircle.setCircleStyle(PDEUsageCircle.PDEUsageCircleStyle.PDEUsageCircleStyleSegmented);
                }
                if (TextUtils.equals(itemContentString, STYLE_DIVIDED)) {
                    mUsageCircle.setCircleStyle(PDEUsageCircle.PDEUsageCircleStyle.PDEUsageCircleStyleDivided);
                }
            }
        });
    }


    /**
     * @brief Callback for the  usage - button clicked event
     * @param v Clicked view
     */
    @SuppressWarnings("unused")
    public void usageMinus_click(View v) {
        mUsageCircle.setTargetFillValue(mUsageCircle.getTargetFillValue() - 4.0f);
        mUsageCircle.startAnimation();
    }


    /**
     * @brief Callback for the  usage + button clicked event
     * @param v Clicked view
     */
    @SuppressWarnings("unused")
    public void usagePlus_click(View v) {
        mUsageCircle.setTargetFillValue(mUsageCircle.getTargetFillValue() + 4.0f);
        mUsageCircle.startAnimation();
    }


    /**
     * @brief Callback for the  total fill value - button clicked event
     * @param v Clicked view
     */
    @SuppressWarnings("unused")
    public void totalMinus_click(View v) {
        mUsageCircle.setTotalFillValue(Math.max(0.0f, mUsageCircle.getTotalFillValue() - 10.0f));

    }


    /**
     * @brief Callback for the total fill value + button clicked event
     * @param v Clicked view
     */
    @SuppressWarnings("unused")
    public void totalPlus_click(View v) {
        mUsageCircle.setTotalFillValue(mUsageCircle.getTotalFillValue() + 10.0f);

    }


    /**
     * @brief Callback for the segment- button clicked event
     * @param v Clicked view
     */
    @SuppressWarnings("unused")
    public void segMinus_click(View v) {
        Toast toast;


        if (mUsageCircle.getNumberOfSegments() != 0) {
            mUsageCircle.setNumberOfSegments(mUsageCircle.getNumberOfSegments() - 5);
        }

        if (mUsageCircle.getNumberOfSegments() == 0) {
            toast = Toast.makeText(UsageCircleProgrammingSample.this,
                           "Number of segments: 0, total value is taken as number of segments",
                           Toast.LENGTH_SHORT);
        } else {
            toast = Toast.makeText(UsageCircleProgrammingSample.this,
                           "Number of segments: " + mUsageCircle.getNumberOfSegments(),
                           Toast.LENGTH_SHORT);
        }

        toast.setGravity(Gravity.BOTTOM, PDEBuildingUnits.BU(), PDEBuildingUnits.BU());
        toast.show();

    }


    /**
     * @brief Callback for the segment+ button clicked event
     * @param v Clicked view
     */
    @SuppressWarnings("unused")
    public void segPlus_click(View v) {
        Toast toast;

        if (mUsageCircle.getNumberOfSegments() == 0) {
            mUsageCircle.setNumberOfSegments(5);
        } else {
            mUsageCircle.setNumberOfSegments(mUsageCircle.getNumberOfSegments() + 5);
        }

        toast = Toast.makeText(UsageCircleProgrammingSample.this,
                               "Number of segments: " + mUsageCircle.getNumberOfSegments(),
                               Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.BOTTOM, PDEBuildingUnits.BU(), PDEBuildingUnits.BU());
        toast.show();
    }


    /**
     * @brief Callback for the 20% button clicked event
     * @param v Clicked view
     */
    @SuppressWarnings("unused")
    public void twenty_click(View v) {
        mUsageCircle.setTargetFillValue(0.2f * mUsageCircle.getTotalFillValue());
        mUsageCircle.startAnimation();
    }


    /**
     * @brief Callback for the  80% button clicked event
     * @param v Clicked view
     */
    @SuppressWarnings("unused")
    public void eighty_click(View v) {
        mUsageCircle.setTargetFillValue(0.8f * mUsageCircle.getTotalFillValue());
        mUsageCircle.startAnimation();
    }


    /**
     * @brief Create a dialog with content for the left or right button and listen to listview item clicks.
     */
    public void addChoiceArrayList(String buttonText,
                                   ArrayList<String> arrayList,
                                   DialogHelper.ChoiceListOnItemClickListener clickListener) {
        final DialogHelper dialogHelper;

        dialogHelper = mStyleDialogHelper;
        mStyleButton.setText(buttonText);

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
    public void setSelectionPos(int selectionPos) {
        mStyleDialogHelper.setSelectionPos(selectionPos);
    }


    /**
     * Callback for the progress value changed event
     * @param event The received event.
     */
    @SuppressWarnings("unused")
    public void onProgressChanged(PDEEvent event) {
        PDEUsageEvent usageEvent = (PDEUsageEvent) event;

        if (event.isType(PDEUsageEvent.EVENT_ACTION_ANIMATION_FINISHED)) {
            String messageString = event.getType() + ", currFillValue: " + String.format("%f",
                                   usageEvent.getCurrentFillValue())
                                   +", total value: " + String.format("%f", usageEvent.getTotalValue());
            Log.d(LOG_TAG, messageString);
        }
    }


}
