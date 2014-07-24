package de.telekom.pde.codelibrary.samples.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import de.telekom.pde.codelibrary.samples.R;


public class NavigationModeSortAdapter extends ArrayAdapter<String> {

    private final String title;


    public NavigationModeSortAdapter(final Context context, final int textViewResourceId, final String[] objects,
                                     final String folderTitle) {
        super(context, textViewResourceId, objects);
        title = folderTitle;
    }


    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            final LayoutInflater inflater = LayoutInflater.from(getContext());
            //  inflate layout from xml without root view
            // ( i think this is intended, so we add @SuppressLint("InflateParams")
            convertView = inflater.inflate(R.layout.actionbar_navigation_adapterview, null);
        }

        if (convertView != null
            && convertView.findViewById(R.id.navigationSortMode) != null
            && convertView.findViewById(R.id.navigationTitle) != null) {
            ((TextView) convertView.findViewById(R.id.navigationSortMode)).setText(getItem(position));
            ((TextView) convertView.findViewById(R.id.navigationTitle)).setText(title);
        }
        return convertView;

    }

}
