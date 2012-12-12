/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;


public class StyledButtonListAdapter extends BaseAdapter {
    private Context mContext=null;

    StyledButtonListAdapter(Context context)
    {
        PDEButton button;
        // rememebr context
        mContext = context;
    }


    @Override
    public int getCount() {
        return 100;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getItem(int i) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getItemId(int i) {
        return i;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PDEButton btn;

        // may inflate new item, otherwise reuse
        if (view == null) {
            view = View.inflate(mContext,R.layout.developer_list_styled_button_container,null);
        }
        // get button
        btn = (PDEButton)view.findViewById(R.id.styled_list_telekom_button);
        if (btn!=null) {
            btn.setTitle("Button: "+(i+1));
        }
        return view;
    }
}
