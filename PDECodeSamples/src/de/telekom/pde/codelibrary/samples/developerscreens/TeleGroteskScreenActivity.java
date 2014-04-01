package de.telekom.pde.codelibrary.samples.developerscreens;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;
import de.telekom.pde.codelibrary.ui.helpers.PDETypeface;


public class TeleGroteskScreenActivity extends PDEActivity implements SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener{

    private final static String LOG_TAG = TeleGroteskScreenActivity.class.getName();
    private final static float MIN_SEEKBAR = 5.0f;
    private final static float MAX_SEEKBAR = 55.0f;

    private TextView mLabelSystem;
    private TextView mLabelTeleGrotesk;
    private TextView mLabelArial;

    private TextView mTextViewSystem;
    private TextView mTextViewTeleGrotesk;
    private TextView mTextViewArial;

    private EditText mEditTextSystem;
    private EditText mEditTextTeleGrotesk;
    private EditText mEditTextArial;

    private Button mButtonSystem;
    private Button mButtonTeleGrotesk;
    private Button mButtonArial;

    private ToggleButton mToggleBold;
    private SeekBar mSeekbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.developer_telegrotesk_screen);

        mSeekbar = (SeekBar) findViewById(R.id.telegroteskscreen_seekBar);
        mSeekbar.setOnSeekBarChangeListener(this);

        mToggleBold = (ToggleButton) findViewById(R.id.telegroteskscreen_boldToggleButton);
        mToggleBold.setOnCheckedChangeListener(this);

        mLabelSystem = (TextView) findViewById(R.id.telegroteskscreen_labelSystem);
        mLabelTeleGrotesk = (TextView) findViewById(R.id.telegroteskscreen_labelTeleGrotesk);
        mLabelArial = (TextView) findViewById(R.id.telegroteskscreen_labelArial);

        mTextViewSystem = (TextView) findViewById(R.id.telegroteskscreen_textViewSystem);
        mTextViewTeleGrotesk = (TextView) findViewById(R.id.telegroteskscreen_textViewTeleGrotesk);
        mTextViewArial = (TextView) findViewById(R.id.telegroteskscreen_textViewArial);

        mButtonSystem = (Button) findViewById(R.id.telegroteskscreen_buttonSystem);
        mButtonTeleGrotesk = (Button) findViewById(R.id.telegroteskscreen_buttonTeleGrotesk);
        mButtonArial = (Button) findViewById(R.id.telegroteskscreen_buttonArial);

        mEditTextSystem = (EditText) findViewById(R.id.telegroteskscreen_editTextSystem);
        mEditTextTeleGrotesk = (EditText) findViewById(R.id.telegroteskscreen_editTextTeleGrotesk);
        mEditTextArial = (EditText) findViewById(R.id.telegroteskscreen_editTextArial);

        mEditTextArial.setFocusable(false);
        mEditTextTeleGrotesk.setFocusable(false);
        mEditTextSystem.setFocusable(false);

        updateFont(mSeekbar.getProgress(), mToggleBold.isChecked());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        Log.d(LOG_TAG, "onProgressChanged " + progress);

        updateFont(progress, mToggleBold.isChecked());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        updateFont(mSeekbar.getProgress(), mToggleBold.isChecked());
    }

    private void updateFont(float size, boolean bold) {

        String teleGrotesk;
        PDETypeface systemFont;
        PDETypeface teleGroteskFont;
        PDETypeface serifFont;

        float fontSize = MIN_SEEKBAR + ((MAX_SEEKBAR - MIN_SEEKBAR) * size/100.0f);

        ((TextView)findViewById(R.id.telegroteskscreen_fontsizeTextView)).setText(""+fontSize);


        if (bold) {
            teleGrotesk = getResources().getString(R.string.Tele_GroteskFet);
            systemFont = PDETypeface.createByNameAndTypeface("SystemBold", Typeface.DEFAULT_BOLD);
            serifFont = PDETypeface.createByNameAndTypeface("SerifBold", Typeface.create(Typeface.SERIF,Typeface.BOLD));
        } else {
            teleGrotesk  = getResources().getString(R.string.Tele_GroteskNor);
            systemFont = PDETypeface.createByNameAndTypeface("System", Typeface.DEFAULT);
            serifFont = PDETypeface.createByNameAndTypeface("Serif", Typeface.create(Typeface.SERIF,Typeface.NORMAL));
        }

        teleGroteskFont = PDETypeface.createByName(teleGrotesk);

        mLabelSystem.setText(systemFont.getName());
        mLabelTeleGrotesk.setText(teleGroteskFont.getName());
        mLabelArial.setText(serifFont.getName());

        mTextViewSystem.setTypeface(systemFont.getTypeface());
        mTextViewSystem.setTextSize(fontSize);
        mTextViewTeleGrotesk.setTypeface(teleGroteskFont.getTypeface());
        mTextViewTeleGrotesk.setTextSize(fontSize);
        mTextViewArial.setTypeface(serifFont.getTypeface());
        mTextViewArial.setTextSize(fontSize);

        mButtonSystem.setTypeface(systemFont.getTypeface());
        mButtonSystem.setTextSize(fontSize);
        mButtonTeleGrotesk.setTypeface(teleGroteskFont.getTypeface());
        mButtonTeleGrotesk.setTextSize(fontSize);
        mButtonArial.setTypeface(serifFont.getTypeface());
        mButtonArial.setTextSize(fontSize);

        mEditTextSystem.setTypeface(systemFont.getTypeface());
        mEditTextSystem.setTextSize(fontSize);
        mEditTextTeleGrotesk.setTypeface(teleGroteskFont.getTypeface());
        mEditTextTeleGrotesk.setTextSize(fontSize);
        mEditTextArial.setTypeface(serifFont.getTypeface());
        mEditTextArial.setTextSize(fontSize);
    }
}