package de.telekom.pde.codelibrary.samples.developerscreens;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.*;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.basescreens.DialogHelper;
import de.telekom.pde.codelibrary.samples.basescreens.SimpleTriangleDrawable;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.elements.text.PDELayerText;
import de.telekom.pde.codelibrary.ui.components.elementwrappers.PDETextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

//ignore "unused" warnings (mVisibleSampleView,mOptionalPaddingBoundsView)
@SuppressWarnings("unused")
public class LayerTextTestScreen extends PDEActivity {

    // Enum for the choice buttons to know which to use.
    public enum WHICH_BUTTON {
        VERTICALALIGNMENTBUTTON,
        HORIZONTALALIGNMENTBUTTON,
        ALIGNMENTMODEBUTTON,
        BASELINEBUTTON,
        TEXTSIZEBUTTON,
        PADDINGBUTTON,
        MAXLINESBUTTON,
        PADDINGLEFTBUTTON,
        PADDINGTOPBUTTON,
        PADDINGRIGHTBUTTON,
        PADDINGBOTTOMBUTTON
    }

    // arrow size directions
    public final static int PDESizingScreenDirectionNone = 0x00000;
    public final static int PDESizingScreenDirectionRight = 0x00001;
    public final static int PDESizingScreenDirectionDown = 0x00002;
    public final static int PDESizingScreenDirectionDiagonal = 0x00004;
    //public final static int PDESizingScreenDirectionOutside = 0x00008;


    // Private variables to handle touches with the thumb
    private ImageView mThumbViewRight;
    private ImageView mThumbViewDiagonal;
    private ImageView mThumbViewDown;
    private View.OnTouchListener thumbsOnTouchListener;
    private View mVisibleSampleView;
    private RelativeLayout mResizeViewContainer;
    private RelativeLayout mResizeLayoutContainer;
    private View mBoundsViewContainer;
    private View mOptionalPaddingBoundsView;
    private Button mVerticalAlignmentButton;
    private Button mHorizontalAlignmentButton;
    private Button mAlignmentModeButton;
    private Button mTextSizeButton;
    private Button mBaselineButton;
    private Button mPaddingButton;
    private Button mPaddingLeftButton;
    private Button mPaddingTopButton;
    private Button mPaddingBottomButton;
    private Button mPaddingRightButton;
    private Button mMaxLinesButton;
    private ToggleButton mEllipsizeButton;
    private ToggleButton mShadowButton;
    private ToggleButton mBorderButton;
    private float mRawXAtStart;
    private float mRawYAtStart;
    private float mContainerWidthAtStart;
    private float mContainerHeightAtStart;
    private int mContentSizingDirection;
    private int mDragDirection;

    private boolean mFirstLayoutingFinished;

    // Two predefined dialogs
    private DialogHelper mVerticalAlignmentDialogHelper;
    private DialogHelper mHorizontalAlignmentDialogHelper;
    private DialogHelper mAlignmentModeDialogHelper;
    private DialogHelper mTextSizeDialogHelper;
    private DialogHelper mPaddingLeftDialogHelper;
    private DialogHelper mPaddingTopDialogHelper;
    private DialogHelper mPaddingBottomDialogHelper;
    private DialogHelper mPaddingRightDialogHelper;
    private DialogHelper mPaddingDialogHelper;
    private DialogHelper mBaseLineDialogHelper;
    private DialogHelper mMaxLinesDialogHelper;

    // helper for view bounds
    public enum VIEW_BOUNDS_SHOW_MODE {
        NEVER,
        ALWAYS,
        ON_SIZE_CHANGE
    }
    private VIEW_BOUNDS_SHOW_MODE mShowViewBounds;
    private static final int VIEW_BOUNDS_SHOW_TIME = 3000;
    private Handler mViewBoundsShowHandler;
    private Runnable mViewBoundsRunnable;

    //global names of the primitive elements
    private final static String PRIMITIVE_VERTICALALIGNMENT_TOP = "Top";
    private final static String PRIMITIVE_VERTICALALIGNMENT_CENTER = "Center";
    private final static String PRIMITIVE_VERTICALALIGNMENT_BOTTOM = "Bottom";

    private final static String PRIMITIVE_HORIZONTALALIGNMENT_LEFT = "Left";
    private final static String PRIMITIVE_HORIZONTALALIGNMENT_CENTER = "Center";
    private final static String PRIMITIVE_HORIZONTALALIGNMENT_RIGHT = "Right";


    private final static String PRIMITIVE_ALIGNMENTMODE_STANDARD = "Standard";
    private final static String PRIMITIVE_ALIGNMENTMODE_CAPHEIGHT = "Cap Height";
    private final static String PRIMITIVE_ALIGNMENTMODE_BASELINE = "Baseline";





    private final static String PRIMITIVE_M10 = "-10";
    private final static String PRIMITIVE_M20 = "-20";
    private final static String PRIMITIVE_M30 = "-30";
    private final static String PRIMITIVE_M40 = "-40";
    private final static String PRIMITIVE_M50 = "-50";
    private final static String PRIMITIVE_M60 = "-60";
    private final static String PRIMITIVE_M70 = "-70";
    private final static String PRIMITIVE_M80 = "-80";
    private final static String PRIMITIVE_M90 = "-90";
    private final static String PRIMITIVE_M100 = "-100";
    private final static String PRIMITIVE_0 = "0";
    private final static String PRIMITIVE_10 = "10";
    private final static String PRIMITIVE_20 = "20";
    private final static String PRIMITIVE_30 = "30";
    private final static String PRIMITIVE_40 = "40";
    private final static String PRIMITIVE_50 = "50";
    private final static String PRIMITIVE_60 = "60";
    private final static String PRIMITIVE_70 = "70";
    private final static String PRIMITIVE_80 = "80";
    private final static String PRIMITIVE_90 = "90";
    private final static String PRIMITIVE_100 = "100";

    private final static String PRIMITIVE_MANY = "many";
    private final static String PRIMITIVE_1 = "1";
    private final static String PRIMITIVE_2 = "2";
    private final static String PRIMITIVE_3 = "3";
    private final static String PRIMITIVE_4 = "4";
    private final static String PRIMITIVE_5 = "5";

    // private variables

    // image view as drawable container
    //ImageView mImageView;
    PDETextView mLayerTextView;
    //PDELayerText mLayerText;



    ArrayList<String> mVerticalAlignmentArrayList;
    ArrayList<String> mHorizontalAlignmentArrayList;
    ArrayList<String> mAlignmentModeArrayList;
    ArrayList<String> mTextSizeArrayList;
    ArrayList<String> mPaddingArrayList;
    ArrayList<String> mPaddingLeftArrayList;
    ArrayList<String> mPaddingTopArrayList;
    ArrayList<String> mPaddingRightArrayList;
    ArrayList<String> mPaddingBottomArrayList;
    ArrayList<String> mBaseLineArrayList;
    ArrayList<String> mMaxLinesArrayList;




    /**
     * @brief Create the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layertext_test_screen);

        //get the root view and set background color (different when darkstyle is on or of in library)
        LinearLayout rootView = (LinearLayout)findViewById(R.id.resize_base_rootlayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        // remember some values
        mResizeLayoutContainer = ((RelativeLayout) findViewById(R.id.resize_base_layoutContainer));
        mResizeViewContainer = ((RelativeLayout) findViewById(R.id.resize_base_view_container));
        mBoundsViewContainer = findViewById(R.id.boundsViewContainer);
        mOptionalPaddingBoundsView = findViewById(R.id.optionalPaddingBoundsView);
        mVerticalAlignmentButton = ((Button) findViewById(R.id.verticalalignment_button));
        mHorizontalAlignmentButton = ((Button) findViewById(R.id.horizontalalignment_button));
        mTextSizeButton = ((Button) findViewById(R.id.textsize_button));
        mPaddingButton = ((Button) findViewById(R.id.padding_button));
        mPaddingLeftButton = ((Button) findViewById(R.id.paddingleft_button));
        mPaddingTopButton = ((Button) findViewById(R.id.paddingtop_button));
        mPaddingRightButton = ((Button) findViewById(R.id.paddingright_button));
        mPaddingBottomButton = ((Button) findViewById(R.id.paddingbottom_button));
        mBaselineButton = ((Button) findViewById(R.id.baseline_button));
        mAlignmentModeButton = ((Button) findViewById(R.id.alignmentmode_button));
        mMaxLinesButton = ((Button) findViewById(R.id.maxlines_button));
        mShadowButton = (ToggleButton) findViewById(R.id.shadow_button);
        mBorderButton = (ToggleButton) findViewById(R.id.border_button);
        mEllipsizeButton = (ToggleButton) findViewById(R.id.ellipsize_button);
        mThumbViewRight = (ImageView)findViewById(R.id.sizing_basescreen_thumb_right);
        mThumbViewDown = (ImageView)findViewById(R.id.sizing_basescreen_thumb_down);
        mThumbViewDiagonal = (ImageView)findViewById(R.id.sizing_basescreen_thumb_diagonal);

        mThumbViewRight.setImageDrawable(new SimpleTriangleDrawable(PDEColor.valueOf("DTGrey1"),-90));
        mThumbViewDown.setImageDrawable(new SimpleTriangleDrawable(PDEColor.valueOf("DTGrey1")));
        mThumbViewDiagonal.setImageDrawable(new SimpleTriangleDrawable(PDEColor.valueOf("DTGrey1"),-45));

        // create some predefined dialog helpers for left and right button
        mVerticalAlignmentDialogHelper = new DialogHelper(this, mVerticalAlignmentButton);
        mHorizontalAlignmentDialogHelper = new DialogHelper(this, mHorizontalAlignmentButton);
        mAlignmentModeDialogHelper = new DialogHelper(this, mAlignmentModeButton);
        mTextSizeDialogHelper = new DialogHelper(this, mTextSizeButton);
        mPaddingDialogHelper = new DialogHelper(this, mPaddingButton);
        mPaddingLeftDialogHelper = new DialogHelper(this, mPaddingLeftButton);
        mPaddingTopDialogHelper = new DialogHelper(this, mPaddingTopButton);
        mPaddingRightDialogHelper = new DialogHelper(this, mPaddingRightButton);
        mPaddingBottomDialogHelper = new DialogHelper(this, mPaddingBottomButton);
        mBaseLineDialogHelper = new DialogHelper(this, mBaselineButton);
        mMaxLinesDialogHelper = new DialogHelper(this, mMaxLinesButton);

        // initial directions
        setContentSizingDirection(PDESizingScreenDirectionRight|PDESizingScreenDirectionDown|PDESizingScreenDirectionDiagonal);
        mDragDirection = 0;

        mFirstLayoutingFinished = false;


        // default container view offset
        setContainerOffset(PDEBuildingUnits.BU(),PDEBuildingUnits.BU());

        thumbsOnTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int action = event.getAction();

                if (action == MotionEvent.ACTION_DOWN) {
                    //Log.d(LOG_TAG, "ACTION_DOWN "+event.getRawX()+", "+event.getRawY());
                    mRawXAtStart = event.getRawX();
                    mRawYAtStart = event.getRawY();
                    mContainerWidthAtStart = mResizeViewContainer.getWidth();
                    mContainerHeightAtStart = mResizeViewContainer.getHeight();
                    // check touched button to know drag direction
                    if(view == mThumbViewRight) {
                        mDragDirection = PDESizingScreenDirectionRight;
                    } else if(view == mThumbViewDown) {
                        mDragDirection = PDESizingScreenDirectionDown;
                    } else if(view == mThumbViewDiagonal) {
                        mDragDirection = PDESizingScreenDirectionDiagonal;
                    }

                    return true;
                } else if (action == MotionEvent.ACTION_MOVE) {
                    //Log.d(LOG_TAG, "ACTION_MOVE "+event.getRawX()+", "+event.getRawY());
                    float deltaX = event.getRawX() - mRawXAtStart;
                    float deltaY = event.getRawY() - mRawYAtStart;
                    float newButtonWidth = mContainerWidthAtStart;
                    float newButtonHeight = mContainerHeightAtStart;

                    // check the drag direction to know where we can resize (x/y)
                    if((mDragDirection&PDESizingScreenDirectionRight)!=0 || (mDragDirection&PDESizingScreenDirectionDiagonal)!=0) {
                        newButtonWidth = mContainerWidthAtStart + deltaX;
                    }
                    if((mDragDirection&PDESizingScreenDirectionDown)!=0 || (mDragDirection&PDESizingScreenDirectionDiagonal)!=0) {
                        newButtonHeight = mContainerHeightAtStart + deltaY;
                    }

                    //set new size
                    setContainerSize(newButtonWidth, newButtonHeight);

                    return true;
                } else if (action == MotionEvent.ACTION_UP) {
                    //Log.d(LOG_TAG, "ACTION_UP "+event.getRawX()+", "+event.getRawY());
                    return true;
                } else if (action == MotionEvent.ACTION_CANCEL) {
                    //Log.d(LOG_TAG, "ACTION_CANCEL "+event.getRawX()+", "+event.getRawY());
                    return true;
                }

                return false;
            }
        };

        mThumbViewRight.setOnTouchListener(thumbsOnTouchListener);
        mThumbViewDown.setOnTouchListener(thumbsOnTouchListener);
        mThumbViewDiagonal.setOnTouchListener(thumbsOnTouchListener);

        // get the view tree observer ans set layout listener for boundary checks in setContainerSize
        ViewTreeObserver vto = (findViewById(android.R.id.content)).getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                ViewTreeObserver vto = LayerTextTestScreen.this.findViewById(android.R.id.content).getViewTreeObserver();
                vto.isAlive();
                // remove the listener... or we'll be doing this a lot.
                removeOnGlobalLayoutListener(vto,this);
                mFirstLayoutingFinished = true;

                //check valid bounds of current container view size, after all is layouted and measured
                RelativeLayout.LayoutParams lpContainer = (RelativeLayout.LayoutParams) mResizeViewContainer.getLayoutParams();
                setContainerSize(checkValidWidth(lpContainer.width),checkValidHeight(lpContainer.height));
            }
        });
        setShowModeOfViewBounds(VIEW_BOUNDS_SHOW_MODE.NEVER);



        //mImageView = new ImageView(this);
        mLayerTextView = new PDETextView(this);

        /*
        // create element
        mLayerText = new PDELayerText("this is an even longer test string with more words");
        mLayerText.setElementTextSize(60);
        mLayerText.setElementMaximumLines(-1);
        mLayerText.setElementEllipsize(true);
        mLayerText.setElementBackgroundColor(PDEColor.valueOf("DTYellow"));
        //mLayerText.setElementTextColor(PDEColor.valueOf("DTDarkBlue"));
        mLayerText.setElementShadowColor(PDEColor.valueOf("DTFunctionalRed"));
        mLayerText.setElementShadowXOffset(3.0f);
        mLayerText.setElementShadowYOffset(3.0f);
        mLayerText.setElementAlignmentMode(PDELayerText.PDELayerTextAlignmentMode.PDELayerTextAlignmentModeCapHeight);
        mLayerText.setElementVerticalAlignment(PDEConstants.PDEVerticalAlignment.PDEAlignmentTop);
        mLayerText.setElementHorizontalAlignment(PDEConstants.PDEAlignment.PDEAlignmentLeft);
        //mLayerText.setElementMaximumLines(1);

        mImageView.setImageDrawable(mLayerText);
        */

        //mLayerTextView.setText("longlongword");
        //mLayerTextView.setText("this is an even longer test string with more words");
        mLayerTextView.setText("Tempor invidunt-ut-labore-dolore magna aliquam " );
             //+   "erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.");
        //mLayerTextView.setText("short text");
        mLayerTextView.setTextSize(100);
        mLayerTextView.setMaxLines(-1);
        mLayerTextView.setEllipsize(true);
        mLayerTextView.setBackgroundColor(PDEColor.valueOf("DTYellow"));
        mLayerTextView.setShadowColor(PDEColor.valueOf("DTFunctionalRed"));
        mLayerTextView.setShadowOffsetX(3.0f);
        mLayerTextView.setShadowOffsetY(3.0f);
        mLayerTextView.setAlignmentMode(PDELayerText.PDELayerTextAlignmentMode.PDELayerTextAlignmentModeCapHeight);
        mLayerTextView.setHorizontalAlignment(PDEConstants.PDEAlignment.PDEAlignmentLeft);
        mLayerTextView.setVerticalAlignment(PDEConstants.PDEVerticalAlignment.PDEAlignmentTop);
        mLayerTextView.setPaddingAll(0);
        //mLayerTextView.setLineDistanceFactor(2);





        //set some linear layout parameter to have correct position and size of button
        RelativeLayout.LayoutParams primitivesLinearLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        //add to view
        //addViewToResizeContainer(mImageView, primitivesLinearLayoutParams);
        addViewToResizeContainer(mLayerTextView, primitivesLinearLayoutParams);

        fillVerticalAlignmentArray();
        fillHorizontalAlignmentArray();
        fillAlignmentModeArray();
        fillBaseLineArray();
        fillPaddingArray();
        fillPaddingLeftArray();
        fillPaddingTopArray();
        fillPaddingRightArray();
        fillPaddingBottomArray();
        fillTextSizeArray();
        fillMaxLinesArray();
        setSelectionPos(WHICH_BUTTON.VERTICALALIGNMENTBUTTON,0);
        mEllipsizeButton.setChecked(true);



        mShadowButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked){
                    //mLayerText.setElementShadowEnabled(true);
                    mLayerTextView.setShadowEnabled(true);
                } else {
                    //mLayerText.setElementShadowEnabled(false);
                    mLayerTextView.setShadowEnabled(false);
                }
            }
        });

        mBorderButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked){
                    setShowModeOfViewBounds(VIEW_BOUNDS_SHOW_MODE.ALWAYS);
                } else {
                    setShowModeOfViewBounds(VIEW_BOUNDS_SHOW_MODE.NEVER);
                }
            }
        });

        mEllipsizeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    //mLayerText.setElementEllipsize(true);
                    mLayerTextView.setEllipsize(true);
                } else {
                    //mLayerText.setElementEllipsize(false);
                    mLayerTextView.setEllipsize(false);
                }
            }
        });
    }


    /**
     * @brief Fill the text size array.
     */
    private void fillTextSizeArray() {
        mTextSizeArrayList = new ArrayList<String>();

        //fill array
        mTextSizeArrayList.add(PRIMITIVE_10);
        mTextSizeArrayList.add(PRIMITIVE_20);
        mTextSizeArrayList.add(PRIMITIVE_30);
        mTextSizeArrayList.add(PRIMITIVE_40);
        mTextSizeArrayList.add(PRIMITIVE_50);
        mTextSizeArrayList.add(PRIMITIVE_60);
        mTextSizeArrayList.add(PRIMITIVE_70);
        mTextSizeArrayList.add(PRIMITIVE_80);
        mTextSizeArrayList.add(PRIMITIVE_90);
        mTextSizeArrayList.add(PRIMITIVE_100);


        //set choice information and listener
        addChoiceArrayList(WHICH_BUTTON.TEXTSIZEBUTTON, "Text Size", mTextSizeArrayList,
                new DialogHelper.ChoiceListOnItemClickListener() {
                    @Override
                    public void onListItemClicked(String itemContentString) {
                        // react on list selection
                        if (TextUtils.equals(itemContentString, PRIMITIVE_10)) {
                            //mLayerText.setElementTextSize(10);
                            mLayerTextView.setTextSize(10);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_20)) {
                            //mLayerText.setElementTextSize(20);
                            mLayerTextView.setTextSize(20);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_30)) {
                            //mLayerText.setElementTextSize(30);
                            mLayerTextView.setTextSize(30);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_40)) {
                            //mLayerText.setElementTextSize(40);
                            mLayerTextView.setTextSize(40);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_50)) {
                            //mLayerText.setElementTextSize(50);
                            mLayerTextView.setTextSize(50);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_60)) {
                            //mLayerText.setElementTextSize(60);
                            mLayerTextView.setTextSize(60);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_70)) {
                            //mLayerText.setElementTextSize(70);
                            mLayerTextView.setTextSize(70);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_80)) {
                            //mLayerText.setElementTextSize(80);
                            mLayerTextView.setTextSize(80);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_90)) {
                            //mLayerText.setElementTextSize(90);
                            mLayerTextView.setTextSize(90);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_100)) {
                            //mLayerText.setElementTextSize(100);
                            mLayerTextView.setTextSize(100);
                        }

                    }
                });
    }

    /**
     * @brief Fill the padding array.
     */
    private void fillPaddingArray() {
        mPaddingArrayList = new ArrayList<String>();

        //fill array
        mPaddingArrayList.add(PRIMITIVE_0);
        mPaddingArrayList.add(PRIMITIVE_10);
        mPaddingArrayList.add(PRIMITIVE_20);
        mPaddingArrayList.add(PRIMITIVE_30);
        mPaddingArrayList.add(PRIMITIVE_40);
        mPaddingArrayList.add(PRIMITIVE_50);
        mPaddingArrayList.add(PRIMITIVE_60);
        mPaddingArrayList.add(PRIMITIVE_70);
        mPaddingArrayList.add(PRIMITIVE_80);
        mPaddingArrayList.add(PRIMITIVE_90);
        mPaddingArrayList.add(PRIMITIVE_100);


        //set choice information and listener
        addChoiceArrayList(WHICH_BUTTON.PADDINGBUTTON, "Padding", mPaddingArrayList,
                new DialogHelper.ChoiceListOnItemClickListener() {
                    @Override
                    public void onListItemClicked(String itemContentString) {
                        // react on list selection
                        if (TextUtils.equals(itemContentString, PRIMITIVE_0)) {
                            //mLayerText.setElementPadding(0);
                            mLayerTextView.setPaddingAll(0);

                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_10)) {
                            //mLayerText.setElementPadding(10);
                            mLayerTextView.setPaddingAll(10);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_20)) {
                            //mLayerText.setElementPadding(20);
                            mLayerTextView.setPaddingAll(20);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_30)) {
                            //mLayerText.setElementPadding(30);
                            mLayerTextView.setPaddingAll(30);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_40)) {
                            //mLayerText.setElementPadding(40);
                            mLayerTextView.setPaddingAll(40);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_50)) {
                            //mLayerText.setElementPadding(50);
                            mLayerTextView.setPaddingAll(50);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_60)) {
                            //mLayerText.setElementPadding(60);
                            mLayerTextView.setPaddingAll(60);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_70)) {
                            //mLayerText.setElementPadding(70);
                            mLayerTextView.setPaddingAll(70);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_80)) {
                            //mLayerText.setElementPadding(80);
                            mLayerTextView.setPaddingAll(80);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_90)) {
                            //mLayerText.setElementPadding(90);
                            mLayerTextView.setPaddingAll(90);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_100)) {
                            //mLayerText.setElementPadding(100);
                            mLayerTextView.setPaddingAll(100);
                        }

                    }
                });
    }

    /**
     * @brief Fill the left padding array.
     */
    private void fillPaddingLeftArray() {
        mPaddingLeftArrayList = new ArrayList<String>();

        //fill array
        mPaddingLeftArrayList.add(PRIMITIVE_0);
        mPaddingLeftArrayList.add(PRIMITIVE_10);
        mPaddingLeftArrayList.add(PRIMITIVE_20);
        mPaddingLeftArrayList.add(PRIMITIVE_30);
        mPaddingLeftArrayList.add(PRIMITIVE_40);
        mPaddingLeftArrayList.add(PRIMITIVE_50);
        mPaddingLeftArrayList.add(PRIMITIVE_60);
        mPaddingLeftArrayList.add(PRIMITIVE_70);
        mPaddingLeftArrayList.add(PRIMITIVE_80);
        mPaddingLeftArrayList.add(PRIMITIVE_90);
        mPaddingLeftArrayList.add(PRIMITIVE_100);


        //set choice information and listener
        addChoiceArrayList(WHICH_BUTTON.PADDINGLEFTBUTTON, "Left", mPaddingLeftArrayList,
                new DialogHelper.ChoiceListOnItemClickListener() {
                    @Override
                    public void onListItemClicked(String itemContentString) {
                        // react on list selection
                        if (TextUtils.equals(itemContentString, PRIMITIVE_0)) {
                            //mLayerText.setElementPaddingLeft(0);
                            mLayerTextView.setPaddingLeft(0);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_10)) {
                            //mLayerText.setElementPaddingLeft(10);
                            mLayerTextView.setPaddingLeft(10);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_20)) {
                            //mLayerText.setElementPaddingLeft(20);
                            mLayerTextView.setPaddingLeft(20);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_30)) {
                            //mLayerText.setElementPaddingLeft(30);
                            mLayerTextView.setPaddingLeft(30);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_40)) {
                            //mLayerText.setElementPaddingLeft(40);
                            mLayerTextView.setPaddingLeft(40);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_50)) {
                            //mLayerText.setElementPaddingLeft(50);
                            mLayerTextView.setPaddingLeft(50);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_60)) {
                            //mLayerText.setElementPaddingLeft(60);
                            mLayerTextView.setPaddingLeft(60);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_70)) {
                            //mLayerText.setElementPaddingLeft(70);
                            mLayerTextView.setPaddingLeft(70);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_80)) {
                            //mLayerText.setElementPaddingLeft(80);
                            mLayerTextView.setPaddingLeft(80);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_90)) {
                            //mLayerText.setElementPaddingLeft(90);
                            mLayerTextView.setPaddingLeft(90);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_100)) {
                            //mLayerText.setElementPaddingLeft(100);
                            mLayerTextView.setPaddingLeft(100);
                        }

                    }
                });
    }

    /**
     * @brief Fill the top padding array.
     */
    private void fillPaddingTopArray() {
        mPaddingTopArrayList = new ArrayList<String>();

        //fill array
        mPaddingTopArrayList.add(PRIMITIVE_0);
        mPaddingTopArrayList.add(PRIMITIVE_10);
        mPaddingTopArrayList.add(PRIMITIVE_20);
        mPaddingTopArrayList.add(PRIMITIVE_30);
        mPaddingTopArrayList.add(PRIMITIVE_40);
        mPaddingTopArrayList.add(PRIMITIVE_50);
        mPaddingTopArrayList.add(PRIMITIVE_60);
        mPaddingTopArrayList.add(PRIMITIVE_70);
        mPaddingTopArrayList.add(PRIMITIVE_80);
        mPaddingTopArrayList.add(PRIMITIVE_90);
        mPaddingTopArrayList.add(PRIMITIVE_100);


        //set choice information and listener
        addChoiceArrayList(WHICH_BUTTON.PADDINGTOPBUTTON, "Top", mPaddingTopArrayList,
                new DialogHelper.ChoiceListOnItemClickListener() {
                    @Override
                    public void onListItemClicked(String itemContentString) {
                        // react on list selection
                        if (TextUtils.equals(itemContentString, PRIMITIVE_0)) {
                            //mLayerText.setElementPaddingTop(0);
                            mLayerTextView.setPaddingTop(0);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_10)) {
                            //mLayerText.setElementPaddingTop(10);
                            mLayerTextView.setPaddingTop(10);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_20)) {
                            //mLayerText.setElementPaddingTop(20);
                            mLayerTextView.setPaddingTop(20);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_30)) {
                            //mLayerText.setElementPaddingTop(30);
                            mLayerTextView.setPaddingTop(30);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_40)) {
                            //mLayerText.setElementPaddingTop(40);
                            mLayerTextView.setPaddingTop(40);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_50)) {
                            //mLayerText.setElementPaddingTop(50);
                            mLayerTextView.setPaddingTop(50);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_60)) {
                            //mLayerText.setElementPaddingTop(60);
                            mLayerTextView.setPaddingTop(60);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_70)) {
                            //mLayerText.setElementPaddingTop(70);
                            mLayerTextView.setPaddingTop(70);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_80)) {
                            //mLayerText.setElementPaddingTop(80);
                            mLayerTextView.setPaddingTop(80);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_90)) {
                            //mLayerText.setElementPaddingTop(90);
                            mLayerTextView.setPaddingTop(90);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_100)) {
                            //mLayerText.setElementPaddingTop(100);
                            mLayerTextView.setPaddingTop(100);
                        }

                    }
                });
    }

    /**
     * @brief Fill the right padding array.
     */
    private void fillPaddingRightArray() {
        mPaddingRightArrayList = new ArrayList<String>();

        //fill array
        mPaddingRightArrayList.add(PRIMITIVE_0);
        mPaddingRightArrayList.add(PRIMITIVE_10);
        mPaddingRightArrayList.add(PRIMITIVE_20);
        mPaddingRightArrayList.add(PRIMITIVE_30);
        mPaddingRightArrayList.add(PRIMITIVE_40);
        mPaddingRightArrayList.add(PRIMITIVE_50);
        mPaddingRightArrayList.add(PRIMITIVE_60);
        mPaddingRightArrayList.add(PRIMITIVE_70);
        mPaddingRightArrayList.add(PRIMITIVE_80);
        mPaddingRightArrayList.add(PRIMITIVE_90);
        mPaddingRightArrayList.add(PRIMITIVE_100);


        //set choice information and listener
        addChoiceArrayList(WHICH_BUTTON.PADDINGRIGHTBUTTON, "Right", mPaddingRightArrayList,
                new DialogHelper.ChoiceListOnItemClickListener() {
                    @Override
                    public void onListItemClicked(String itemContentString) {
                        // react on list selection
                        if (TextUtils.equals(itemContentString, PRIMITIVE_0)) {
                            //mLayerText.setElementPaddingRight(0);
                            mLayerTextView.setPaddingRight(0);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_10)) {
                            //mLayerText.setElementPaddingRight(10);
                            mLayerTextView.setPaddingRight(10);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_20)) {
                            //mLayerText.setElementPaddingRight(20);
                            mLayerTextView.setPaddingRight(20);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_30)) {
                            //mLayerText.setElementPaddingRight(30);
                            mLayerTextView.setPaddingRight(30);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_40)) {
                            //mLayerText.setElementPaddingRight(40);
                            mLayerTextView.setPaddingRight(40);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_50)) {
                            //mLayerText.setElementPaddingRight(50);
                            mLayerTextView.setPaddingRight(50);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_60)) {
                            //mLayerText.setElementPaddingRight(60);
                            mLayerTextView.setPaddingRight(60);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_70)) {
                            //mLayerText.setElementPaddingRight(70);
                            mLayerTextView.setPaddingRight(70);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_80)) {
                            //mLayerText.setElementPaddingRight(80);
                            mLayerTextView.setPaddingRight(80);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_90)) {
                            //mLayerText.setElementPaddingRight(90);
                            mLayerTextView.setPaddingRight(90);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_100)) {
                            //mLayerText.setElementPaddingRight(100);
                            mLayerTextView.setPaddingRight(100);
                        }

                    }
                });
    }

    /**
     * @brief Fill the bottom padding array.
     */
    private void fillPaddingBottomArray() {
        mPaddingBottomArrayList = new ArrayList<String>();

        //fill array
        mPaddingBottomArrayList.add(PRIMITIVE_0);
        mPaddingBottomArrayList.add(PRIMITIVE_10);
        mPaddingBottomArrayList.add(PRIMITIVE_20);
        mPaddingBottomArrayList.add(PRIMITIVE_30);
        mPaddingBottomArrayList.add(PRIMITIVE_40);
        mPaddingBottomArrayList.add(PRIMITIVE_50);
        mPaddingBottomArrayList.add(PRIMITIVE_60);
        mPaddingBottomArrayList.add(PRIMITIVE_70);
        mPaddingBottomArrayList.add(PRIMITIVE_80);
        mPaddingBottomArrayList.add(PRIMITIVE_90);
        mPaddingBottomArrayList.add(PRIMITIVE_100);


        //set choice information and listener
        addChoiceArrayList(WHICH_BUTTON.PADDINGBOTTOMBUTTON, "Bottom", mPaddingBottomArrayList,
                new DialogHelper.ChoiceListOnItemClickListener() {
                    @Override
                    public void onListItemClicked(String itemContentString) {
                        // react on list selection
                        if (TextUtils.equals(itemContentString, PRIMITIVE_0)) {
                            //mLayerText.setElementPaddingBottom(0);
                            mLayerTextView.setPaddingBottom(0);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_10)) {
                            //mLayerText.setElementPaddingBottom(10);
                            mLayerTextView.setPaddingBottom(10);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_20)) {
                            //mLayerText.setElementPaddingBottom(20);
                            mLayerTextView.setPaddingBottom(20);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_30)) {
                            //mLayerText.setElementPaddingBottom(30);
                            mLayerTextView.setPaddingBottom(30);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_40)) {
                            //mLayerText.setElementPaddingBottom(40);
                            mLayerTextView.setPaddingBottom(40);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_50)) {
                            //mLayerText.setElementPaddingBottom(50);
                            mLayerTextView.setPaddingBottom(50);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_60)) {
                            //mLayerText.setElementPaddingBottom(60);
                            mLayerTextView.setPaddingBottom(60);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_70)) {
                            //mLayerText.setElementPaddingBottom(70);
                            mLayerTextView.setPaddingBottom(70);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_80)) {
                            //mLayerText.setElementPaddingBottom(80);
                            mLayerTextView.setPaddingBottom(80);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_90)) {
                            //mLayerText.setElementPaddingBottom(90);
                            mLayerTextView.setPaddingBottom(90);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_100)) {
                            //mLayerText.setElementPaddingBottom(100);
                            mLayerTextView.setPaddingBottom(100);
                        }

                    }
                });
    }


    /**
     * @brief Fill the baseline array.
     */
    private void fillBaseLineArray() {
        mBaseLineArrayList = new ArrayList<String>();

        //fill array
        mBaseLineArrayList.add(PRIMITIVE_M100);
        mBaseLineArrayList.add(PRIMITIVE_M90);
        mBaseLineArrayList.add(PRIMITIVE_M80);
        mBaseLineArrayList.add(PRIMITIVE_M70);
        mBaseLineArrayList.add(PRIMITIVE_M60);
        mBaseLineArrayList.add(PRIMITIVE_M50);
        mBaseLineArrayList.add(PRIMITIVE_M40);
        mBaseLineArrayList.add(PRIMITIVE_M30);
        mBaseLineArrayList.add(PRIMITIVE_M20);
        mBaseLineArrayList.add(PRIMITIVE_M10);
        mBaseLineArrayList.add(PRIMITIVE_0);
        mBaseLineArrayList.add(PRIMITIVE_10);
        mBaseLineArrayList.add(PRIMITIVE_20);
        mBaseLineArrayList.add(PRIMITIVE_30);
        mBaseLineArrayList.add(PRIMITIVE_40);
        mBaseLineArrayList.add(PRIMITIVE_50);
        mBaseLineArrayList.add(PRIMITIVE_60);
        mBaseLineArrayList.add(PRIMITIVE_70);
        mBaseLineArrayList.add(PRIMITIVE_80);
        mBaseLineArrayList.add(PRIMITIVE_90);
        mBaseLineArrayList.add(PRIMITIVE_100);


        //set choice information and listener
        addChoiceArrayList(WHICH_BUTTON.BASELINEBUTTON, "Base Line", mBaseLineArrayList,
                new DialogHelper.ChoiceListOnItemClickListener() {
                    @Override
                    public void onListItemClicked(String itemContentString) {
                        // react on list selection
                        if (TextUtils.equals(itemContentString, PRIMITIVE_0)) {
                            //mLayerText.setElementBaseLine(0);
                            mLayerTextView.setBaseLine(0);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_10)) {
                            //mLayerText.setElementBaseLine(10);
                            mLayerTextView.setBaseLine(10);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_20)) {
                            //mLayerText.setElementBaseLine(20);
                            mLayerTextView.setBaseLine(20);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_30)) {
                            //mLayerText.setElementBaseLine(30);
                            mLayerTextView.setBaseLine(30);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_40)) {
                            //mLayerText.setElementBaseLine(40);
                            mLayerTextView.setBaseLine(40);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_50)) {
                            //mLayerText.setElementBaseLine(50);
                            mLayerTextView.setBaseLine(50);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_60)) {
                            //mLayerText.setElementBaseLine(60);
                            mLayerTextView.setBaseLine(60);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_70)) {
                            //mLayerText.setElementBaseLine(70);
                            mLayerTextView.setBaseLine(70);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_80)) {
                            //mLayerText.setElementBaseLine(80);
                            mLayerTextView.setBaseLine(80);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_90)) {
                            //mLayerText.setElementBaseLine(90);
                            mLayerTextView.setBaseLine(90);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_100)) {
                            //mLayerText.setElementBaseLine(100);
                            mLayerTextView.setBaseLine(100);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_M10)) {
                            //mLayerText.setElementBaseLine(-10);
                            mLayerTextView.setBaseLine(-10);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_M20)) {
                            //mLayerText.setElementBaseLine(-20);
                            mLayerTextView.setBaseLine(-20);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_M30)) {
                            //mLayerText.setElementBaseLine(-30);
                            mLayerTextView.setBaseLine(-30);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_M40)) {
                            //mLayerText.setElementBaseLine(-40);
                            mLayerTextView.setBaseLine(-40);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_M50)) {
                            //mLayerText.setElementBaseLine(-50);
                            mLayerTextView.setBaseLine(-50);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_M60)) {
                            //mLayerText.setElementBaseLine(-60);
                            mLayerTextView.setBaseLine(-60);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_M70)) {
                            //mLayerText.setElementBaseLine(-70);
                            mLayerTextView.setBaseLine(-70);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_M80)) {
                            //mLayerText.setElementBaseLine(-80);
                            mLayerTextView.setBaseLine(-80);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_M90)) {
                            //mLayerText.setElementBaseLine(-90);
                            mLayerTextView.setBaseLine(-90);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_M100)) {
                            //mLayerText.setElementBaseLine(-100);
                            mLayerTextView.setBaseLine(-100);
                        }

                    }
                });
    }


    /**
     * @brief Fill the alignment mode array.
     */
    private void fillAlignmentModeArray() {
        mAlignmentModeArrayList = new ArrayList<String>();

        //fill array
        mAlignmentModeArrayList.add(PRIMITIVE_ALIGNMENTMODE_STANDARD);
        mAlignmentModeArrayList.add(PRIMITIVE_ALIGNMENTMODE_CAPHEIGHT);
        mAlignmentModeArrayList.add(PRIMITIVE_ALIGNMENTMODE_BASELINE);


        //set choice information and listener
        addChoiceArrayList(WHICH_BUTTON.ALIGNMENTMODEBUTTON, "Alignment Mode", mAlignmentModeArrayList, new DialogHelper.ChoiceListOnItemClickListener() {
            @Override
            public void onListItemClicked(String itemContentString) {
                // react on list selection
                if(TextUtils.equals(itemContentString,PRIMITIVE_ALIGNMENTMODE_STANDARD)) {
                    //mLayerText.setElementAlignmentMode(PDELayerText.PDELayerTextAlignmentMode.PDELayerTextAlignmentModeStandard);
                    mLayerTextView.setAlignmentMode(PDELayerText.PDELayerTextAlignmentMode.PDELayerTextAlignmentModeStandard);
                }
                if(TextUtils.equals(itemContentString,PRIMITIVE_ALIGNMENTMODE_CAPHEIGHT)) {
                    //mLayerText.setElementAlignmentMode(PDELayerText.PDELayerTextAlignmentMode.PDELayerTextAlignmentModeCapHeight);
                    mLayerTextView.setAlignmentMode(PDELayerText.PDELayerTextAlignmentMode.PDELayerTextAlignmentModeCapHeight);
                }
                if(TextUtils.equals(itemContentString,PRIMITIVE_ALIGNMENTMODE_BASELINE)) {
                    //mLayerText.setElementAlignmentMode(PDELayerText.PDELayerTextAlignmentMode.PDELayerTextAlignmentModeBaseLine);
                    mLayerTextView.setAlignmentMode(PDELayerText.PDELayerTextAlignmentMode.PDELayerTextAlignmentModeBaseLine);
                }
            }
        });
    }

    /**
     * @brief Fill the vertical alginment array.
     */
    private void fillVerticalAlignmentArray() {
        mVerticalAlignmentArrayList = new ArrayList<String>();

        //fill array
        mVerticalAlignmentArrayList.add(PRIMITIVE_VERTICALALIGNMENT_TOP);
        mVerticalAlignmentArrayList.add(PRIMITIVE_VERTICALALIGNMENT_CENTER);
        mVerticalAlignmentArrayList.add(PRIMITIVE_VERTICALALIGNMENT_BOTTOM);


        //set choice information and listener
        addChoiceArrayList(WHICH_BUTTON.VERTICALALIGNMENTBUTTON, "Vertical Alignment", mVerticalAlignmentArrayList,
                new DialogHelper.ChoiceListOnItemClickListener() {
                    @Override
                    public void onListItemClicked(String itemContentString) {
                        // react on list selection
                        if (TextUtils.equals(itemContentString, PRIMITIVE_VERTICALALIGNMENT_TOP)) {
                            //mLayerText.setElementVerticalAlignment(PDEConstants.PDEVerticalAlignment.PDEAlignmentTop);
                            mLayerTextView.setVerticalAlignment(PDEConstants.PDEVerticalAlignment.PDEAlignmentTop);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_VERTICALALIGNMENT_CENTER)) {
                            //mLayerText.setElementVerticalAlignment(PDEConstants.PDEVerticalAlignment.PDEAlignmentVerticalCenter);
                            mLayerTextView.setVerticalAlignment(PDEConstants.PDEVerticalAlignment.PDEAlignmentVerticalCenter);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_VERTICALALIGNMENT_BOTTOM)) {
                            //mLayerText.setElementVerticalAlignment(PDEConstants.PDEVerticalAlignment.PDEAlignmentBottom);
                            mLayerTextView.setVerticalAlignment(PDEConstants.PDEVerticalAlignment.PDEAlignmentBottom);
                        }
                    }
                });
    }

    /**
     * @brief Fill the horizontal alignment array.
     */
    private void fillHorizontalAlignmentArray() {
        mHorizontalAlignmentArrayList = new ArrayList<String>();

        //fill array
        mHorizontalAlignmentArrayList.add(PRIMITIVE_HORIZONTALALIGNMENT_LEFT);
        mHorizontalAlignmentArrayList.add(PRIMITIVE_HORIZONTALALIGNMENT_CENTER);
        mHorizontalAlignmentArrayList.add(PRIMITIVE_HORIZONTALALIGNMENT_RIGHT);


        //set choice information and listener
        addChoiceArrayList(WHICH_BUTTON.HORIZONTALALIGNMENTBUTTON, "Horizontal Alignment", mHorizontalAlignmentArrayList,
                new DialogHelper.ChoiceListOnItemClickListener() {
                    @Override
                    public void onListItemClicked(String itemContentString) {
                        // react on list selection
                        if (TextUtils.equals(itemContentString, PRIMITIVE_HORIZONTALALIGNMENT_LEFT)) {
                            //mLayerText.setElementHorizontalAlignment(PDEConstants.PDEAlignment.PDEAlignmentLeft);
                            mLayerTextView.setHorizontalAlignment(PDEConstants.PDEAlignment.PDEAlignmentLeft);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_HORIZONTALALIGNMENT_CENTER)) {
                            //mLayerText.setElementHorizontalAlignment(PDEConstants.PDEAlignment.PDEAlignmentCenter);
                            mLayerTextView.setHorizontalAlignment(PDEConstants.PDEAlignment.PDEAlignmentCenter);
                        }
                        if (TextUtils.equals(itemContentString, PRIMITIVE_HORIZONTALALIGNMENT_RIGHT)) {
                            //mLayerText.setElementHorizontalAlignment(PDEConstants.PDEAlignment.PDEAlignmentRight);
                            mLayerTextView.setHorizontalAlignment(PDEConstants.PDEAlignment.PDEAlignmentRight);
                        }
                    }
                });
    }


    /**
     * @brief Fill the maximum lines array.
     */
    private void fillMaxLinesArray() {
        mMaxLinesArrayList = new ArrayList<String>();

        //fill array
        mMaxLinesArrayList.add(PRIMITIVE_MANY);
        mMaxLinesArrayList.add(PRIMITIVE_1);
        mMaxLinesArrayList.add(PRIMITIVE_2);
        mMaxLinesArrayList.add(PRIMITIVE_3);
        mMaxLinesArrayList.add(PRIMITIVE_4);
        mMaxLinesArrayList.add(PRIMITIVE_5);



        //set choice information and listener
        addChoiceArrayList(WHICH_BUTTON.MAXLINESBUTTON, "Max Lines", mMaxLinesArrayList, new DialogHelper.ChoiceListOnItemClickListener() {
            @Override
            public void onListItemClicked(String itemContentString) {
                // react on list selection
                if(TextUtils.equals(itemContentString,PRIMITIVE_MANY)) {
                    //mLayerText.setElementMaximumLines(-1);
                    mLayerTextView.setMaxLines(-1);
                }
                if(TextUtils.equals(itemContentString,PRIMITIVE_1)) {
                    //mLayerText.setElementMaximumLines(1);
                    mLayerTextView.setMaxLines(1);
                }
                if(TextUtils.equals(itemContentString,PRIMITIVE_2)) {
                    //mLayerText.setElementMaximumLines(2);
                    mLayerTextView.setMaxLines(2);
                }
                if(TextUtils.equals(itemContentString,PRIMITIVE_3)) {
                    //mLayerText.setElementMaximumLines(3);
                    mLayerTextView.setMaxLines(3);
                }
                if(TextUtils.equals(itemContentString,PRIMITIVE_4)) {
                    //mLayerText.setElementMaximumLines(4);
                    mLayerTextView.setMaxLines(4);
                }
                if(TextUtils.equals(itemContentString,PRIMITIVE_5)) {
                    //mLayerText.setElementMaximumLines(5);
                    mLayerTextView.setMaxLines(5);
                }

            }
        });
    }


    /**
     * @brief Add a view to the resize container.
     */
    public void addViewToResizeContainer(View view,ViewGroup.LayoutParams layoutParams) {
        if(mResizeViewContainer!=null) {
            mVisibleSampleView = view;
            mResizeViewContainer.addView(view,0,layoutParams);
        }
    }






    /**
     * @brief Create a dialog with content for the left or right button and listen to listview item clicks.
     */
    public void addChoiceArrayList(WHICH_BUTTON mode, String buttonText, ArrayList<String> arrayList, DialogHelper.ChoiceListOnItemClickListener clickListener) {
        final DialogHelper dialogHelper;

        // check left or right button
        if(mode== WHICH_BUTTON.VERTICALALIGNMENTBUTTON) {
            dialogHelper = mVerticalAlignmentDialogHelper;
            mVerticalAlignmentButton.setText(buttonText);
        }else if(mode== WHICH_BUTTON.HORIZONTALALIGNMENTBUTTON) {
            dialogHelper = mHorizontalAlignmentDialogHelper;
            mHorizontalAlignmentButton.setText(buttonText);
        } else if(mode== WHICH_BUTTON.PADDINGBUTTON) {
            dialogHelper = mPaddingDialogHelper;
            mPaddingButton.setText(buttonText);
        } else if(mode== WHICH_BUTTON.PADDINGLEFTBUTTON) {
            dialogHelper = mPaddingLeftDialogHelper;
            mPaddingLeftButton.setText(buttonText);
        } else if(mode== WHICH_BUTTON.PADDINGTOPBUTTON) {
            dialogHelper = mPaddingTopDialogHelper;
            mPaddingTopButton.setText(buttonText);
        } else if(mode== WHICH_BUTTON.PADDINGRIGHTBUTTON) {
            dialogHelper = mPaddingRightDialogHelper;
            mPaddingRightButton.setText(buttonText);
        } else if(mode== WHICH_BUTTON.PADDINGBOTTOMBUTTON) {
            dialogHelper = mPaddingBottomDialogHelper;
            mPaddingBottomButton.setText(buttonText);
        } else if(mode== WHICH_BUTTON.TEXTSIZEBUTTON) {
            dialogHelper = mTextSizeDialogHelper;
            mTextSizeButton.setText(buttonText);
        } else if(mode== WHICH_BUTTON.BASELINEBUTTON) {
            dialogHelper = mBaseLineDialogHelper;
            mBaselineButton.setText(buttonText);
        } else if(mode== WHICH_BUTTON.MAXLINESBUTTON) {
            dialogHelper = mMaxLinesDialogHelper;
            mMaxLinesButton.setText(buttonText);
        } else {
            dialogHelper = mAlignmentModeDialogHelper;
            mAlignmentModeButton.setText(buttonText);
        }

        // set the title
        dialogHelper.getDialog().setTitle(buttonText);

        //no content -> disable listeners and hide button again
        if(arrayList==null) {
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
    public void setSelectionPos(WHICH_BUTTON mode, int selectionPos) {
        if (mode== WHICH_BUTTON.VERTICALALIGNMENTBUTTON) {
            mVerticalAlignmentDialogHelper.setSelectionPos(selectionPos);
        } else if (mode== WHICH_BUTTON.HORIZONTALALIGNMENTBUTTON) {
            mHorizontalAlignmentDialogHelper.setSelectionPos(selectionPos);
        } else if (mode== WHICH_BUTTON.BASELINEBUTTON) {
            mBaseLineDialogHelper.setSelectionPos(selectionPos);
        } else if (mode== WHICH_BUTTON.TEXTSIZEBUTTON) {
            mTextSizeDialogHelper.setSelectionPos(selectionPos);
        } else if (mode== WHICH_BUTTON.PADDINGBUTTON) {
            mPaddingDialogHelper.setSelectionPos(selectionPos);
        } else if (mode== WHICH_BUTTON.PADDINGLEFTBUTTON) {
            mPaddingLeftDialogHelper.setSelectionPos(selectionPos);
        } else if (mode== WHICH_BUTTON.PADDINGTOPBUTTON) {
            mPaddingTopDialogHelper.setSelectionPos(selectionPos);
        } else if (mode== WHICH_BUTTON.PADDINGRIGHTBUTTON) {
            mPaddingRightDialogHelper.setSelectionPos(selectionPos);
        } else if (mode== WHICH_BUTTON.PADDINGBOTTOMBUTTON) {
            mPaddingBottomDialogHelper.setSelectionPos(selectionPos);
        } else if (mode== WHICH_BUTTON.MAXLINESBUTTON) {
            mMaxLinesDialogHelper.setSelectionPos(selectionPos);
        } else {
            mAlignmentModeDialogHelper.setSelectionPos(selectionPos);
        }
    }


    /**
     * @brief Set the padding for the optional bounds we show when user clicks on the bounds toggle button in the bottom
     */
    public void setOptionalBoundsVisibilityPadding(Rect padding) {
        //set the padding of the container view, because this limits the optional padding bounds view
        mBoundsViewContainer.setPadding(padding.left,padding.top,padding.right,padding.bottom);
    }


    /**
     * @brief Set the padding for the optional bounds we show when user clicks on the bounds toggle button in the bottom
     */
    public void setOptionalBoundsVisibilityPadding(float padding) {
        //set the padding of the container view, because this limits the optional padding bounds view
        mBoundsViewContainer.setPadding((int)padding,(int)padding,(int)padding,(int)padding);
    }


    /**
     * @brief Set the padding for the optional bounds we show when user clicks on the bounds toggle button in the bottom
     */
    public void setOptionalBoundsVisibilityPadding(float paddingLeft, float paddintTop, float paddingRight, float paddingBottom) {
        //set the padding of the container view, because this limits the optional padding bounds view
        mBoundsViewContainer.setPadding((int)paddingLeft,(int)paddintTop,(int)paddingRight,(int)paddingBottom);
    }


    /**
     * @brief Sets the new size for the view container, which holds the element (with match_parent) to fit in.
     */
    public void setContainerSize(Point size) {
        setContainerSize(size.x,size.y);
    }


    private float checkValidWidth(float width) {
        //we only can check the max bouns when measure was finished after layouting so check flag
        if(mFirstLayoutingFinished) {
            if (width > mResizeLayoutContainer.getMeasuredWidth() - PDEBuildingUnits.pixelFromBU(2.0f) - mThumbViewRight.getLayoutParams().width) {
                width = mResizeLayoutContainer.getMeasuredWidth() - PDEBuildingUnits.pixelFromBU(2.0f) - mThumbViewRight.getLayoutParams().width;
            }
        }
        //check minimum
        if (width < PDEBuildingUnits.exactBU()) {
            width = PDEBuildingUnits.exactBU();
        }
        return width;
    }

    private float checkValidHeight(float height) {
        //we only can check the max bouns when measure was finished after layouting so check flag
        if(mFirstLayoutingFinished) {
            if (height > mResizeLayoutContainer.getMeasuredHeight() - PDEBuildingUnits.pixelFromBU(2.0f) - mThumbViewDown.getLayoutParams().height) {
                height = mResizeLayoutContainer.getMeasuredHeight() - PDEBuildingUnits.pixelFromBU(2.0f) - mThumbViewDown.getLayoutParams().height;
            }
        }
        //check minimum
        if (height < PDEBuildingUnits.exactBU()) {
            height = PDEBuildingUnits.exactBU();
        }
        return height;
    }


    /**
     * set the offset of the container view via margins
     */
    public void setContainerOffset(float marginLeft, float marginTop) {
        //get layout params
        RelativeLayout.LayoutParams lpContainer = (RelativeLayout.LayoutParams) mResizeViewContainer.getLayoutParams();

        lpContainer.leftMargin = (int)marginLeft;
        lpContainer.topMargin = (int)marginTop;
        mResizeViewContainer.setLayoutParams(lpContainer);
    }


    /**
     * Set the offset of the container view via margins
     */
    public  void setContainerOffset(Point marginOffset) {
        if(marginOffset==null) return;
        setContainerOffset(marginOffset.x,marginOffset.y);
    }


    /**
     * @brief Sets the new size for the view container, which holds the element (with match_parent) to fit in.
     */
    public void setContainerSize(float width, float height) {
        //get layout params
        RelativeLayout.LayoutParams lpContainer = (RelativeLayout.LayoutParams) mResizeViewContainer.getLayoutParams();

        //really changed?
        if(lpContainer.width==width && lpContainer.height==height) return;

        // limit the size
        width = checkValidWidth(width);
        height = checkValidHeight(height);

        // set size
        lpContainer.width = (int) width;
        lpContainer.height = (int) height ;
        mResizeViewContainer.setLayoutParams(lpContainer);

        // size changed, so show bounds if flag if set
        // hide after delay
        if(mShowViewBounds== VIEW_BOUNDS_SHOW_MODE.ON_SIZE_CHANGE) {
            setViewBoundsVisibility(View.VISIBLE);
            setViewBoundsVisibility(View.GONE, VIEW_BOUNDS_SHOW_TIME);
        }
        updateDirectionButtons();
    }


    /**
     * @brief Update all of the direction resizing buttons (update position and visibility).
     */
    private void updateDirectionButtons() {
        int left,top,width,height;
        RelativeLayout.LayoutParams lpResizeContainer;

        //get resizeview contrainer params to get position/size values
        lpResizeContainer = (RelativeLayout.LayoutParams) mResizeViewContainer.getLayoutParams();
        left = lpResizeContainer.leftMargin;
        top = lpResizeContainer.topMargin;
        width = lpResizeContainer.width;
        height = lpResizeContainer.height;

        // check if right direction is set
        if((mContentSizingDirection&PDESizingScreenDirectionRight)!=0) {
            RelativeLayout.LayoutParams lp;
            lp = (RelativeLayout.LayoutParams) mThumbViewRight.getLayoutParams();
            lp.setMargins(left + PDEBuildingUnits.BU() + width,
                    ((height - mThumbViewRight.getLayoutParams().height) / 2) + top,
                    0,
                    0);
            mThumbViewRight.setLayoutParams(lp);
            mThumbViewRight.setVisibility(View.VISIBLE);
        } else {
            mThumbViewRight.setVisibility(View.INVISIBLE);
        }

        // check if down direction is set
        if((mContentSizingDirection&PDESizingScreenDirectionDown)!=0) {
            RelativeLayout.LayoutParams lp;
            lp = (RelativeLayout.LayoutParams) mThumbViewDown.getLayoutParams();
            lp.setMargins(((width - mThumbViewDown.getLayoutParams().width) / 2) + left,
                    top + PDEBuildingUnits.BU() + height,0,0);
            mThumbViewDown.setLayoutParams(lp);
            mThumbViewDown.setVisibility(View.VISIBLE);
        } else {
            mThumbViewDown.setVisibility(View.INVISIBLE);
        }

        // check if diagonal direction is set
        if((mContentSizingDirection&PDESizingScreenDirectionDiagonal)!=0) {
            RelativeLayout.LayoutParams lp;
            lp = (RelativeLayout.LayoutParams) mThumbViewDiagonal.getLayoutParams();
            lp.setMargins(left + width, top + height, 0, 0);
            mThumbViewDiagonal.setLayoutParams(lp);
            mThumbViewDiagonal.setVisibility(View.VISIBLE);
        } else {
            mThumbViewDiagonal.setVisibility(View.INVISIBLE);
        }
    }


    /**
     * @brief Sets the possible directions of this screen.
     */
    public void setContentSizingDirection(int sizingDirection) {
        mContentSizingDirection = sizingDirection;
        updateDirectionButtons();
    }


    /**
     * @brief Sets the mode of the view bounds visibility.
     */
    public void setShowModeOfViewBounds(VIEW_BOUNDS_SHOW_MODE show) {
        if(mShowViewBounds==show) return;

        mShowViewBounds = show;

        if(mShowViewBounds == VIEW_BOUNDS_SHOW_MODE.ALWAYS) {
            if(mViewBoundsRunnable != null) mViewBoundsShowHandler.removeCallbacks(mViewBoundsRunnable);
            mViewBoundsRunnable = null;
            mViewBoundsShowHandler = null;
            setViewBoundsVisibility(View.VISIBLE);
        } else if(mShowViewBounds == VIEW_BOUNDS_SHOW_MODE.NEVER) {
            if(mViewBoundsRunnable != null) mViewBoundsShowHandler.removeCallbacks(mViewBoundsRunnable);
            mViewBoundsRunnable = null;
            mViewBoundsShowHandler = null;
            setViewBoundsVisibility(View.GONE);
        } else if(mShowViewBounds == VIEW_BOUNDS_SHOW_MODE.ON_SIZE_CHANGE) {
            if(mViewBoundsShowHandler == null) {
                mViewBoundsShowHandler = new Handler();
            }
        }
    }


    /**
     * @brief Shows or hides the bounds after delayed time.
     */
    private void setViewBoundsVisibility(final int visibility, int delay) {
        // security
        if(mViewBoundsRunnable!=null) mViewBoundsShowHandler.removeCallbacks(mViewBoundsRunnable);

        mViewBoundsRunnable = new Runnable()
        {
            public void run()
            {
                setViewBoundsVisibility(visibility);
            }
        };
        mViewBoundsShowHandler.postDelayed(mViewBoundsRunnable, 3000);
    }


    /**
     * @brief Shows/hides the bounds.
     */
    private void setViewBoundsVisibility(int visibility) {
        if(mBoundsViewContainer!=null) {
            mBoundsViewContainer.setVisibility(visibility);
        }
    }


    /**
     * @brief Helper function to set OnGlobalLayoutListener if a specific ViewTreeObserver (avoid warnings -> use reflection).
     *
     * removeGlobalOnLayoutListener is marked as deprecated in api level 16, in order to avoid the warning use reflection.
     */
    private void removeOnGlobalLayoutListener(ViewTreeObserver vto, ViewTreeObserver.OnGlobalLayoutListener  listener) {
        Method method;

        // valid?
        if(vto==null) return;

        try {
            //try to use the removeOnGlobalLayoutListener function which was introduced in android 4.1 (api level 16)
            method = vto.getClass().getMethod("removeOnGlobalLayoutListener",
                    new Class[] {ViewTreeObserver.OnGlobalLayoutListener.class});
            method.invoke(vto, listener);
            return;
        } catch (NoSuchMethodException e) {
            // function not available
        } catch (IllegalAccessException e) {
            // function not available
        } catch (InvocationTargetException e) {
            // function not available
        }

        // removeOnGlobalLayoutListener function not found -> use old one (removeGlobalOnLayoutListener)
        try {
            //try to use the removeGlobalOnLayoutListener which is deprecated in android 4.1
            method = vto.getClass().getMethod("removeGlobalOnLayoutListener",
                    new Class[] {ViewTreeObserver.OnGlobalLayoutListener.class});
            method.invoke(vto, listener);
        } catch (NoSuchMethodException e) {
            // function not available
        } catch (IllegalAccessException e) {
            // function not available
        } catch (InvocationTargetException e) {
            // function not available
        }

    }

}
