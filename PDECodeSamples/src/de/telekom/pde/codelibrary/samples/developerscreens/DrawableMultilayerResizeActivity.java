/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;


// imports

import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.basescreens.DialogHelper;
import de.telekom.pde.codelibrary.ui.helpers.GridBackgroundDrawable;
import de.telekom.pde.codelibrary.samples.basescreens.ResizeBaseActivity;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.drawables.PDEDrawableMultilayer;

import java.util.ArrayList;


/**
 * @brief Activity class for the sizing test screen.
 */
public class DrawableMultilayerResizeActivity extends ResizeBaseActivity {

    /**
     * @brief Global tag for log outputs.
     */
	@SuppressWarnings("unused")
    private final static String LOG_TAG = DrawableMultilayerResizeActivity.class.getName();

    //global names of the primitive elements
    private final static String PRIMITIVE_NAME_MULTILAYER = "MultiLayer";


    //global names of available sizes
    private final static String PRIMITIVE_SIZE_2BU = "2 BU";
    private final static String PRIMITIVE_SIZE_3BU = "3 BU";
    private final static String PRIMITIVE_SIZE_4BU = "4 BU";
    private final static String PRIMITIVE_SIZE_5BU = "5 BU";
    private final static String PRIMITIVE_SIZE_8BU = "8 BU";
    private final static String PRIMITIVE_SIZE_10BU = "10 BU";
    private final static String PRIMITIVE_SIZE_15BU = "15 BU";
    private final static String PRIMITIVE_SIZE_20BU = "20 BU";


    // private variables

    // image view as drawable container
    ImageView mImageView;

    ArrayList<String> mLeftChoiceArrayList;
    ArrayList<String> mRightChoiceArrayList;
    DialogHelper.ChoiceListOnItemClickListener mLeftChoiceItemClickListener;
    DialogHelper.ChoiceListOnItemClickListener mRightChoiceItemClickListener;


    /**
     * @brief Create the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // *************************
        // Create image view as drawable container
        // *************************
        mImageView = new ImageView(this);

        //set some linear layout parameter to have correct position and size of button
        RelativeLayout.LayoutParams primitivesLinearLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        //add button to view
        addViewToResizeContainer(mImageView, primitivesLinearLayoutParams);

        fillLeftChoiceArray();
        fillRightChoiceArray();
        setSelectionPos(LEFT_RIGHT_BUTTON.LEFT,0);
    }


    /**
     * @brief Fill the left choice array.
     */
    private void fillLeftChoiceArray() {
        mLeftChoiceArrayList = new ArrayList<String>();

        //fill array
        mLeftChoiceArrayList.add(PRIMITIVE_NAME_MULTILAYER);


        //set choice information and listener
        addChoiceArrayList(LEFT_RIGHT_BUTTON.LEFT, "Choose", mLeftChoiceArrayList,
            new DialogHelper.ChoiceListOnItemClickListener() {
               @Override
               public void onListItemClicked(String itemContentString) {
                   // react on list selection
                   if (TextUtils.equals(itemContentString, PRIMITIVE_NAME_MULTILAYER)) {
                       //multilayer containing 2 other multilayer
                       PDEDrawableMultilayer backgroundML = new PDEDrawableMultilayer();
                       backgroundML.setMultilayerBackgroundColor(PDEColor.valueOf("#44777777"));

                       //multilayer containing 4 Grid drawable layers
                       PDEDrawableMultilayer ml1 = new PDEDrawableMultilayer();
                       ml1.setBounds(PDEBuildingUnits.pixelFromBU(1.0f),PDEBuildingUnits.pixelFromBU(1.0f),PDEBuildingUnits.pixelFromBU(10.0f),PDEBuildingUnits.pixelFromBU(10.0f));
                       ml1.setMultilayerBackgroundColor(PDEColor.valueOf("#44FF00FF"));
                       GridBackgroundDrawable grid1_1 = new GridBackgroundDrawable(PDEColor.valueOf("#FF0000"));
                       grid1_1.setBounds(0,0,PDEBuildingUnits.pixelFromBU(4.0f),PDEBuildingUnits.pixelFromBU(4.0f));
                       GridBackgroundDrawable grid1_2 = new GridBackgroundDrawable(PDEColor.valueOf("#00FF00"));
                       grid1_2.setBounds(PDEBuildingUnits.pixelFromBU(4.0f),0,PDEBuildingUnits.pixelFromBU(8.0f),PDEBuildingUnits.pixelFromBU(4.0f));
                       GridBackgroundDrawable grid1_3 = new GridBackgroundDrawable(PDEColor.valueOf("#0000FF"));
                       grid1_3.setBounds(0,PDEBuildingUnits.pixelFromBU(4.0f),PDEBuildingUnits.pixelFromBU(4.0f),PDEBuildingUnits.pixelFromBU(8.0f));
                       GridBackgroundDrawable grid1_4 = new GridBackgroundDrawable(PDEColor.valueOf("#FF00FF"));
                       grid1_4.setBounds(PDEBuildingUnits.pixelFromBU(5.0f),PDEBuildingUnits.pixelFromBU(4.0f),PDEBuildingUnits.pixelFromBU(9.0f),PDEBuildingUnits.pixelFromBU(9.0f));
                       ml1.addLayer(grid1_1);
                       ml1.addLayer(grid1_2);
                       ml1.addLayer(grid1_3);
                       ml1.addLayer(grid1_4);

                       //multilayer containing 2 other multilayer
                       PDEDrawableMultilayer ml2 = new PDEDrawableMultilayer();
                       ml2.setBounds(PDEBuildingUnits.pixelFromBU(10.0f),PDEBuildingUnits.pixelFromBU(10.0f),PDEBuildingUnits.pixelFromBU(28.0f),PDEBuildingUnits.pixelFromBU(28.0f));
                       ml2.setMultilayerBackgroundColor(PDEColor.valueOf("#4400FFFF"));

                       //multilayer containing 4 grid drawable multilayer
                       PDEDrawableMultilayer ml2_1 = new PDEDrawableMultilayer();
                       ml2_1.setBounds(PDEBuildingUnits.pixelFromBU(0.0f),PDEBuildingUnits.pixelFromBU(0.0f),PDEBuildingUnits.pixelFromBU(9.0f),PDEBuildingUnits.pixelFromBU(9.0f));
                       ml2_1.setMultilayerBackgroundColor(PDEColor.valueOf("#AAF000F5"));
                       GridBackgroundDrawable grid2_1 = new GridBackgroundDrawable(PDEColor.valueOf("#FF0000"));
                       grid2_1.setBounds(0,0,PDEBuildingUnits.pixelFromBU(4.0f),PDEBuildingUnits.pixelFromBU(4.0f));
                       GridBackgroundDrawable grid2_2 = new GridBackgroundDrawable(PDEColor.valueOf("#00FF00"));
                       grid2_2.setBounds(PDEBuildingUnits.pixelFromBU(4.0f),0,PDEBuildingUnits.pixelFromBU(8.0f),PDEBuildingUnits.pixelFromBU(4.0f));
                       GridBackgroundDrawable grid2_3 = new GridBackgroundDrawable(PDEColor.valueOf("#0000FF"));
                       grid2_3.setBounds(0,PDEBuildingUnits.pixelFromBU(4.0f),PDEBuildingUnits.pixelFromBU(4.0f),PDEBuildingUnits.pixelFromBU(8.0f));
                       GridBackgroundDrawable grid2_4 = new GridBackgroundDrawable(PDEColor.valueOf("#FF00FF"));
                       grid2_4.setBounds(PDEBuildingUnits.pixelFromBU(5.0f),PDEBuildingUnits.pixelFromBU(4.0f),PDEBuildingUnits.pixelFromBU(9.0f),PDEBuildingUnits.pixelFromBU(9.0f));
                       ml2_1.addLayer(grid2_1);
                       ml2_1.addLayer(grid2_2);
                       ml2_1.addLayer(grid2_3);
                       ml2_1.addLayer(grid2_4);

                       //multilayer containing 4 grid drawable multilayer
                       PDEDrawableMultilayer ml2_2 = new PDEDrawableMultilayer();
                       ml2_2.setBounds(PDEBuildingUnits.pixelFromBU(9.0f),PDEBuildingUnits.pixelFromBU(9.0f),PDEBuildingUnits.pixelFromBU(18.0f),PDEBuildingUnits.pixelFromBU(18.0f));
                       ml2_2.setMultilayerBackgroundColor(PDEColor.valueOf("#440F0F0F"));
                       GridBackgroundDrawable grid3_1 = new GridBackgroundDrawable(PDEColor.valueOf("#FF0000"));
                       grid3_1.setBounds(0,0,PDEBuildingUnits.pixelFromBU(4.0f),PDEBuildingUnits.pixelFromBU(4.0f));
                       GridBackgroundDrawable grid3_2 = new GridBackgroundDrawable(PDEColor.valueOf("#00FF00"));
                       grid3_2.setBounds(PDEBuildingUnits.pixelFromBU(4.0f),0,PDEBuildingUnits.pixelFromBU(8.0f),PDEBuildingUnits.pixelFromBU(4.0f));
                       GridBackgroundDrawable grid3_3 = new GridBackgroundDrawable(PDEColor.valueOf("#0000FF"));
                       grid3_3.setBounds(0,PDEBuildingUnits.pixelFromBU(4.0f),PDEBuildingUnits.pixelFromBU(4.0f),PDEBuildingUnits.pixelFromBU(8.0f));
                       GridBackgroundDrawable grid3_4 = new GridBackgroundDrawable(PDEColor.valueOf("#FF00FF"));
                       grid3_4.setBounds(PDEBuildingUnits.pixelFromBU(5.0f),PDEBuildingUnits.pixelFromBU(4.0f),PDEBuildingUnits.pixelFromBU(9.0f),PDEBuildingUnits.pixelFromBU(9.0f));
                       ml2_2.addLayer(grid2_1);
                       ml2_2.addLayer(grid2_2);
                       ml2_2.addLayer(grid2_3);
                       ml2_2.addLayer(grid2_4);


                       ml2.addLayer(ml2_1);
                       ml2.addLayer(ml2_2);
                       backgroundML.addLayer(ml1);
                       backgroundML.addLayer(ml2);

                       mImageView.setImageDrawable(backgroundML);


                       setContentSizingDirection(
                               PDESizingScreenDirectionDown | PDESizingScreenDirectionRight |
                               PDESizingScreenDirectionDiagonal);
                   }
               }
            });
    }


    /**
     * @brief Fill the right choice array.
     */
    private void fillRightChoiceArray() {
        mRightChoiceArrayList = new ArrayList<String>();

        //fill array
        mRightChoiceArrayList.add(PRIMITIVE_SIZE_2BU);
        mRightChoiceArrayList.add(PRIMITIVE_SIZE_3BU);
        mRightChoiceArrayList.add(PRIMITIVE_SIZE_4BU);
        mRightChoiceArrayList.add(PRIMITIVE_SIZE_5BU);
        mRightChoiceArrayList.add(PRIMITIVE_SIZE_8BU);
        mRightChoiceArrayList.add(PRIMITIVE_SIZE_10BU);
        mRightChoiceArrayList.add(PRIMITIVE_SIZE_15BU);
        mRightChoiceArrayList.add(PRIMITIVE_SIZE_20BU);

        //set choice information and listener
        addChoiceArrayList(LEFT_RIGHT_BUTTON.RIGHT, "Size", mRightChoiceArrayList, new DialogHelper.ChoiceListOnItemClickListener() {
            @Override
            public void onListItemClicked(String itemContentString) {
                // react on list selection
                if(TextUtils.equals(itemContentString,PRIMITIVE_SIZE_2BU)) {
                    setContainerSize(PDEBuildingUnits.pixelFromBU(2.0f), PDEBuildingUnits.pixelFromBU(2.0f));
                } else if(TextUtils.equals(itemContentString,PRIMITIVE_SIZE_3BU)) {
                    setContainerSize(PDEBuildingUnits.pixelFromBU(3.0f), PDEBuildingUnits.pixelFromBU(3.0f));
                } else if(TextUtils.equals(itemContentString,PRIMITIVE_SIZE_4BU)) {
                    setContainerSize(PDEBuildingUnits.pixelFromBU(4.0f), PDEBuildingUnits.pixelFromBU(4.0f));
                } else if(TextUtils.equals(itemContentString,PRIMITIVE_SIZE_5BU)) {
                    setContainerSize(PDEBuildingUnits.pixelFromBU(5.0f), PDEBuildingUnits.pixelFromBU(5.0f));
                } else if(TextUtils.equals(itemContentString,PRIMITIVE_SIZE_8BU)) {
                    setContainerSize(PDEBuildingUnits.pixelFromBU(8.0f), PDEBuildingUnits.pixelFromBU(8.0f));
                } else if(TextUtils.equals(itemContentString,PRIMITIVE_SIZE_10BU)) {
                    setContainerSize(PDEBuildingUnits.pixelFromBU(10.0f), PDEBuildingUnits.pixelFromBU(10.0f));
                } else if(TextUtils.equals(itemContentString,PRIMITIVE_SIZE_15BU)) {
                    setContainerSize(PDEBuildingUnits.pixelFromBU(15.0f), PDEBuildingUnits.pixelFromBU(15.0f));
                } else if(TextUtils.equals(itemContentString,PRIMITIVE_SIZE_20BU)) {
                    setContainerSize(PDEBuildingUnits.pixelFromBU(20.0f), PDEBuildingUnits.pixelFromBU(20.0f));
                }
            }
        });
    }
}
