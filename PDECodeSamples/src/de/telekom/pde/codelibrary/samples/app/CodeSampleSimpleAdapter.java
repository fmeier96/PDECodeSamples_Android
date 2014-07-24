/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 * 
 * kdanner - 08.07.13
 */

package de.telekom.pde.codelibrary.samples.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.color.PDEColor;

import java.util.List;
import java.util.Map;

public class CodeSampleSimpleAdapter extends SimpleAdapter{
    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     *                 in the from parameter.
     */
    public CodeSampleSimpleAdapter(Context context,
                                   List<? extends Map<String, ?>> data,
                                   int resource,
                                   String[] from,
                                   int[] to) {
        super(context, data, resource, from, to);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;

        v = super.getView(position, convertView, parent);
        if (v!=null) {
            ((TextView) v.findViewById(R.id.choose_simple_list_text)).setTextColor(PDEColor.DTUITextColor().getIntegerColor());
            //((TextView)v.findViewById(R.id.choose_simple_list_text)).setTypeface(PDETypeface.sDefaultFont.getTypeface());
        }
        return v;
    }
}
