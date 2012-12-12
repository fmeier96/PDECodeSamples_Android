/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;

import android.app.Activity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;
import de.telekom.pde.codelibrary.samples.R;

/**
 * Created with IntelliJ IDEA.
 * User: klaus
 * Date: 27.11.12
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
public class MixedButtonListSample extends Activity {
    ListView mListView;
    BaseAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set xml layout
        setContentView(R.layout.developer_mixedbuttonlist_sample_screen);
        // get gfx references
        mListView = (ListView)findViewById(R.id.mixedbuttonlist_sample_list);
        // create adapter
        mAdapter = new MixedButtonListAdapter(this);
        mListView.setAdapter(mAdapter);
        //((LinearLayout)mListView.getParent()).addView(((MixedButtonListAdapter)mAdapter).mButtons.get(0));
    }
}