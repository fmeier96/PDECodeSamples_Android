/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2014. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.elements.common.PDEDrawableBorderLine;
import de.telekom.pde.codelibrary.ui.layout.PDEAbsoluteLayout;

import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;


/**
 * Created by akitzinger on 28.03.14.
 */
public class AKTestScreenActivity  extends PDEActionBarActivity {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.ak_testscreen_activity);
        PDEAbsoluteLayout layout = (PDEAbsoluteLayout) findViewById(R.id.rootviewAK);

        PDEDrawableBorderLine mBorderLineDrawable;
        mBorderLineDrawable = new PDEDrawableBorderLine();


        mBorderLineDrawable.setElementBorderColor(PDEColor.valueOf("DTFunctionalRed").getIntegerColor());
        mBorderLineDrawable.setElementBorderWidth(1.0f);
        Path path, path2;


        // init
        path = new Path();

        path.moveTo(150,0);
        path.lineTo(300, 150);
        path.lineTo(150,300);
        path.lineTo(0,150);
        path.close();

        path2 = new Path();

        path2.moveTo(300,0);
        path2.lineTo(300, 300);
        path2.lineTo(0,300);
        path2.close();

        mBorderLineDrawable.setElementShapePath(null);
        layout.addView(mBorderLineDrawable.getWrapperView());

        PDEAbsoluteLayout.PDEAbsoluteLayoutHelper.setViewRect(mBorderLineDrawable.getWrapperView(),
                                                              new Rect(10, 10, 310, 310));
        mBorderLineDrawable.getWrapperView().measure(View.MeasureSpec.makeMeasureSpec(300, View.MeasureSpec.EXACTLY),
                                                     View.MeasureSpec.makeMeasureSpec(300, View.MeasureSpec.EXACTLY));

//        path.lineTo(150,150);
//        path.close();
//
//        mBorderLineDrawable.setElementShapePath(path);

    }
}
