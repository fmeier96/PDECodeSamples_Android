package de.telekom.pde.codelibrary.samples.developerscreens;

import android.os.Bundle;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;


public class KDMetaphorTestActivity extends PDEActivity {

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = KDMetaphorTestActivity.class.getName();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.developer_kd_methaphor_test);
    }
}
