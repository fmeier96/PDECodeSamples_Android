/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;


// imports
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;


/**
 * Created with IntelliJ IDEA.
 * User: akitzinger
 * Date: 30.10.12
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 */
public class BlurTestActivity extends Activity {

    /**
     * @brief Global tag for log outputs.
     */
    private final static String LOG_TAG = BlurTestActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.

        setContentView(R.layout.developer_blur_test_screen);

        RelativeLayout rootView = (RelativeLayout)findViewById(R.id.blurtestscreen_relativelayout);
        //rootView.setBackgroundColor(PDEColor.valueOf("DTUIBackground").getIntegerColor());

        RelativeLayout containerView = (RelativeLayout)findViewById(R.id.blurtestscreen_container);
        RelativeLayout.LayoutParams elemRelativeLayoutParams = null;

        Window activityWindow = getWindow();
        View decorView = activityWindow.getDecorView();

        float elemWidth = 200.0f;
        float elemHeight = 200.0f;
        float elemWidth2 = 200.0f;
        float elemHeight2 = 200.0f;
        float cornerRadius = 30;
        float blurRadius = 50;
        float blurRadius2 = 30;
        float gapX = PDEBuildingUnits.pixelFromBU(1);
        float gapY = PDEBuildingUnits.pixelFromBU(1.5f);


        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert default button
        //
        //  The default button is in default color and beveled style. No configuration (except button title) needs
        //  to be set. The font color and size are automatically set based on styleguide rules when using the
        //  default configuration.
        //
        //------------------------------------------------------------------------------------------------------------------

        // create and configure

        BlurTestSimpleRectView rect_inner = new BlurTestSimpleRectView(this);
        rect_inner.setShapeRoundedRect(new RectF(0,0,elemWidth,elemHeight),cornerRadius);
        rect_inner.setColor(Color.BLACK);
        rect_inner.setAlpha(255);
        rect_inner.setBlurFilter(blurRadius,"inner");
        rect_inner.setId(R.id.rect_inner);
        elemRelativeLayoutParams = new RelativeLayout.LayoutParams((int)elemWidth,(int)elemHeight);
        elemRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        containerView.addView(rect_inner, elemRelativeLayoutParams );




        BlurTestSimpleRectView rect_normal = new BlurTestSimpleRectView(this);
        rect_normal.setShapeRoundedRect(new RectF(0,0,elemWidth,elemHeight),cornerRadius);
        rect_normal.setColor(Color.BLACK);
        rect_normal.setAlpha(255);
        rect_normal.setBlurFilter(blurRadius,"normal");
        rect_normal.setId(R.id.rect_normal);
        elemRelativeLayoutParams = new RelativeLayout.LayoutParams((int)elemWidth,(int)elemHeight);
        elemRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        elemRelativeLayoutParams.addRule(RelativeLayout.BELOW, rect_inner.getId());
        elemRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        containerView.addView(rect_normal, elemRelativeLayoutParams );


        BlurTestSimpleRectView rect_outer = new BlurTestSimpleRectView(this);
        rect_outer.setShapeRoundedRect(new RectF(0,0,elemWidth,elemHeight),cornerRadius);
        rect_outer.setColor(Color.BLACK);
        rect_outer.setAlpha(255);
        rect_outer.setBlurFilter(blurRadius,"outer");
        rect_outer.setId(R.id.rect_outer);
        elemRelativeLayoutParams = new RelativeLayout.LayoutParams((int)elemWidth,(int)elemHeight);
        elemRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        elemRelativeLayoutParams.addRule(RelativeLayout.BELOW, rect_normal.getId());
        elemRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        containerView.addView(rect_outer, elemRelativeLayoutParams );


        BlurTestSimpleRectView rect_solid = new BlurTestSimpleRectView(this);
        rect_solid.setShapeRoundedRect(new RectF(0,0,elemWidth,elemHeight),cornerRadius);
        rect_solid.setColor(Color.BLACK);
        rect_solid.setAlpha(255);
        rect_solid.setBlurFilter(blurRadius,"solid");
        rect_solid.setId(R.id.rect_solid);
        elemRelativeLayoutParams = new RelativeLayout.LayoutParams((int)elemWidth,(int)elemHeight);
        elemRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        elemRelativeLayoutParams.addRule(RelativeLayout.BELOW, rect_outer.getId());
        elemRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        containerView.addView(rect_solid, elemRelativeLayoutParams );



        BlurTestSimpleRectView rect_emboss = new BlurTestSimpleRectView(this);
        rect_emboss.setShapeRoundedRect(new RectF(0,0,elemWidth,elemHeight),cornerRadius);
        rect_emboss.setColor(Color.BLACK);
        rect_emboss.setAlpha(255);
        rect_emboss.setEmbossFilter(new float[] {1,1,1},0.5f,0.5f,20);
        rect_emboss.setId(R.id.rect_emboss);
        elemRelativeLayoutParams = new RelativeLayout.LayoutParams((int)elemWidth,(int)elemHeight);
        elemRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        elemRelativeLayoutParams.addRule(RelativeLayout.BELOW, rect_solid.getId());
        elemRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        containerView.addView(rect_emboss, elemRelativeLayoutParams );







        BlurTestDualRectView rect_white_black = new BlurTestDualRectView(this);
        rect_white_black.setShapeRoundedRect(new RectF(0,0,elemWidth2,elemHeight2),cornerRadius);
        rect_white_black.setId(R.id.rect_white_black);
        rect_white_black.setBlurFilter(blurRadius2,"inner");
        rect_white_black.setType(BlurTestDualRectView.TYPE_WHITE_FRAME_BLACK_CENTER);
        elemRelativeLayoutParams = new RelativeLayout.LayoutParams((int)elemWidth2,(int)elemHeight2);
        elemRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        elemRelativeLayoutParams.addRule(RelativeLayout.BELOW, rect_emboss.getId());
        elemRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        containerView.addView(rect_white_black, elemRelativeLayoutParams );



        BlurTestDualRectView rect_transparent_black = new BlurTestDualRectView(this);
        rect_transparent_black.setShapeRoundedRect(new RectF(0,0,elemWidth2,elemHeight2),cornerRadius);
        rect_transparent_black.setId(R.id.rect_transparent_black);
        rect_transparent_black.setBlurFilter(blurRadius2,"inner");
        rect_transparent_black.setType(BlurTestDualRectView.TYPE_TRANSPARENT_FRAME_BLACK_CENTER);
        elemRelativeLayoutParams = new RelativeLayout.LayoutParams((int)elemWidth2,(int)elemHeight2);
        elemRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        elemRelativeLayoutParams.addRule(RelativeLayout.BELOW, rect_white_black.getId());
        elemRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        containerView.addView(rect_transparent_black, elemRelativeLayoutParams );


        BlurTestDualRectView rect_black_white = new BlurTestDualRectView(this);
        rect_black_white.setShapeRoundedRect(new RectF(0,0,elemWidth2,elemHeight2),cornerRadius);
        rect_black_white.setId(R.id.rect_black_white);
        rect_black_white.setBlurFilter(blurRadius2,"inner");
        rect_black_white.setType(BlurTestDualRectView.TYPE_BLACK_FRAME_WHITE_CENTER);
        elemRelativeLayoutParams = new RelativeLayout.LayoutParams((int)elemWidth2,(int)elemHeight2);
        elemRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        elemRelativeLayoutParams.addRule(RelativeLayout.BELOW, rect_transparent_black.getId());
        elemRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        containerView.addView(rect_black_white, elemRelativeLayoutParams );




        BlurTestDualRectView rect_black_transparent_inner = new BlurTestDualRectView(this);
        rect_black_transparent_inner.setShapeRoundedRect(new RectF(0,0,elemWidth2,elemHeight2),cornerRadius);
        rect_black_transparent_inner.setId(R.id.rect_black_transparent_inner);
        rect_black_transparent_inner.setBlurFilter(blurRadius2,"inner");
        rect_black_transparent_inner.setType(BlurTestDualRectView.TYPE_BLACK_FRAME_TRANSPARENT_CENTER);
        elemRelativeLayoutParams = new RelativeLayout.LayoutParams((int)elemWidth2,(int)elemHeight2);
        elemRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        elemRelativeLayoutParams.addRule(RelativeLayout.BELOW, rect_black_white.getId());
        elemRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        containerView.addView(rect_black_transparent_inner, elemRelativeLayoutParams );





        BlurTestDualRectView rect_black_transparent_normal = new BlurTestDualRectView(this);
        rect_black_transparent_normal.setShapeRoundedRect(new RectF(0,0,elemWidth2,elemHeight2),cornerRadius);
        rect_black_transparent_normal.setId(R.id.rect_black_transparent_normal);
        rect_black_transparent_normal.setBlurFilter(blurRadius2,"normal");
        rect_black_transparent_normal.setType(BlurTestDualRectView.TYPE_BLACK_FRAME_TRANSPARENT_CENTER);
        elemRelativeLayoutParams = new RelativeLayout.LayoutParams((int)elemWidth2,(int)elemHeight2);
        elemRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        elemRelativeLayoutParams.addRule(RelativeLayout.BELOW, rect_black_transparent_inner.getId());
        elemRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        containerView.addView(rect_black_transparent_normal, elemRelativeLayoutParams );



        BlurTestDualRectView rect_black_transparent_outer = new BlurTestDualRectView(this);
        rect_black_transparent_outer.setShapeRoundedRect(new RectF(0,0,elemWidth2,elemHeight2),cornerRadius);
        rect_black_transparent_outer.setId(R.id.rect_black_transparent_outer);
        rect_black_transparent_outer.setBlurFilter(blurRadius2,"outer");
        rect_black_transparent_outer.setType(BlurTestDualRectView.TYPE_BLACK_FRAME_TRANSPARENT_CENTER);
        elemRelativeLayoutParams = new RelativeLayout.LayoutParams((int)elemWidth2,(int)elemHeight2);
        elemRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        elemRelativeLayoutParams.addRule(RelativeLayout.BELOW, rect_black_transparent_normal.getId());
        elemRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        containerView.addView(rect_black_transparent_outer, elemRelativeLayoutParams );



        BlurTestDualRectView rect_black_transparent_solid = new BlurTestDualRectView(this);
        rect_black_transparent_solid.setShapeRoundedRect(new RectF(0,0,elemWidth2,elemHeight2),cornerRadius);
        rect_black_transparent_solid.setId(R.id.rect_black_transparent_solid);
        rect_black_transparent_solid.setBlurFilter(blurRadius2,"solid");
        rect_black_transparent_solid.setType(BlurTestDualRectView.TYPE_BLACK_FRAME_TRANSPARENT_CENTER);
        elemRelativeLayoutParams = new RelativeLayout.LayoutParams((int)elemWidth2,(int)elemHeight2);
        elemRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        elemRelativeLayoutParams.addRule(RelativeLayout.BELOW, rect_black_transparent_outer.getId());
        elemRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        containerView.addView(rect_black_transparent_solid, elemRelativeLayoutParams );


        BlurTestDualRectView rect_black_transparent_inner_cut = new BlurTestDualRectView(this);
        rect_black_transparent_inner_cut.setShapeRoundedRect(new RectF(0,0,elemWidth2,elemHeight2),cornerRadius);
        rect_black_transparent_inner_cut.setId(R.id.rect_black_transparent_inner_cut);
        rect_black_transparent_inner_cut.setBlurFilter(blurRadius2,"inner");
        rect_black_transparent_inner_cut.setType(BlurTestDualRectView.TYPE_BLACK_FRAME_TRANSPARENT_CENTER);
        rect_black_transparent_inner_cut.mCutInnerRoundedRect = true;
        elemRelativeLayoutParams = new RelativeLayout.LayoutParams((int)elemWidth2,(int)elemHeight2);
        elemRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        elemRelativeLayoutParams.addRule(RelativeLayout.BELOW, rect_black_transparent_solid.getId());
        elemRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        containerView.addView(rect_black_transparent_inner_cut, elemRelativeLayoutParams );




        BlurTestShapeView shape1 = new BlurTestShapeView(this);
        shape1.setShapeRoundedRect(new RectF(0,0,elemWidth2,elemHeight2),cornerRadius);
        shape1.setId(R.id.shape1);
        shape1.setBlurFilter(blurRadius2,"inner");
        //shape1.setType(BlurTestDualRectView.TYPE_BLACK_FRAME_TRANSPARENT_CENTER);
//        rect_black_transparent_inner_cut.mCutInnerRoundedRect = true;
        elemRelativeLayoutParams = new RelativeLayout.LayoutParams((int)elemWidth2,(int)elemHeight2);
        elemRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        elemRelativeLayoutParams.addRule(RelativeLayout.BELOW, rect_black_transparent_inner_cut.getId());
        elemRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        containerView.addView(shape1, elemRelativeLayoutParams );





        BlurTestDualRectView rect_black_transparent_normal_masked = new BlurTestDualRectView(this);
        rect_black_transparent_normal_masked.setShapeRoundedRect(new RectF(0,0,elemWidth2,elemHeight2),cornerRadius);
        rect_black_transparent_normal_masked.setId(R.id.rect_black_transparent_normal_masked);
        rect_black_transparent_normal_masked.setBlurFilter(blurRadius2,"normal");
        rect_black_transparent_normal_masked.setType(BlurTestDualRectView.TYPE_BLACK_FRAME_TRANSPARENT_CENTER);
        rect_black_transparent_normal_masked.mMasking2 = true;
        elemRelativeLayoutParams = new RelativeLayout.LayoutParams((int)elemWidth2,(int)elemHeight2);
        elemRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        elemRelativeLayoutParams.addRule(RelativeLayout.BELOW, shape1.getId());
        elemRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        containerView.addView(rect_black_transparent_normal_masked, elemRelativeLayoutParams );



        BlurTestDualRectView rect_black_transparent_inner_masked = new BlurTestDualRectView(this);
        rect_black_transparent_inner_masked.setShapeRoundedRect(new RectF(0,0,elemWidth2,elemHeight2),cornerRadius);
        rect_black_transparent_inner_masked.setId(R.id.rect_black_transparent_inner_masked);
        rect_black_transparent_inner_masked.setBlurFilter(blurRadius2,"inner");
        rect_black_transparent_inner_masked.setType(BlurTestDualRectView.TYPE_BLACK_FRAME_TRANSPARENT_CENTER);
        rect_black_transparent_inner_masked.mMasking2 = true;
        elemRelativeLayoutParams = new RelativeLayout.LayoutParams((int)elemWidth2,(int)elemHeight2);
        elemRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        elemRelativeLayoutParams.addRule(RelativeLayout.BELOW, rect_black_transparent_normal_masked.getId());
        elemRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        containerView.addView(rect_black_transparent_inner_masked, elemRelativeLayoutParams );


//        PDELayerSunken sunken = new PDELayerSunken(this);
//        sunken.setShapeRoundedRect(new RectF(0.0f,0.0f,300.0f,150.0f),cornerRadius);
//        elemRelativeLayoutParams = new RelativeLayout.LayoutParams(300,150);
//        elemRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
//        elemRelativeLayoutParams.addRule(RelativeLayout.BELOW, rect_black_transparent_inner_masked.getId());
//        elemRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        containerView.addView(sunken, elemRelativeLayoutParams );

    }


}


