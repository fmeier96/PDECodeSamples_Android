/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2013. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.basescreens;


import android.annotation.SuppressLint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;


/**
 * @brief Activity class for the sizing test screen.
 *
 * Suppress lint warning "not registered in the manifest" because it is only a base class and we dont want to use this
 * directly.
 */
@SuppressLint("Registered")
public class ResizeBaseActivity extends PDEActionBarActivity {

    /**
     * @brief Global tag for log outputs.
     */
    private final static String LOG_TAG = ResizeBaseActivity.class.getName();


    // Enum for the choice buttons to know which to use.
    public enum LEFT_RIGHT_BUTTON {
        LEFT,
        RIGHT
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
    protected RelativeLayout mResizeViewContainer;
    protected RelativeLayout mResizeLayoutContainer;
    private View mBoundsViewContainer;
    private float mRawXAtStart;
    private float mRawYAtStart;
    private float mContainerWidthAtStart;
    private float mContainerHeightAtStart;
    private int mContentSizingDirection;
    private int mDragDirection;
    private boolean mFirstLayoutingFinished;
    private Point mMinSize;


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


    /**
     * @param savedInstanceState The saved instance state to recreate.
     * @brief Create the Activity.
     *
     * The on touch listener sends a onClick event on every touch up within the thumbs (also when thumb is moved)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.resize_base_screen);

        //get the root view and set background color (different when darkstyle is on or of in library)
        LinearLayout rootView = (LinearLayout) findViewById(R.id.resize_base_rootlayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        // remember some values
        mResizeLayoutContainer = ((RelativeLayout) findViewById(R.id.resize_base_layoutContainer));
        mResizeViewContainer = ((RelativeLayout) findViewById(R.id.resize_base_view_container));
        mBoundsViewContainer = findViewById(R.id.boundsViewContainer);
        mThumbViewRight = (ImageView) findViewById(R.id.sizing_basescreen_thumb_right);
        mThumbViewDown = (ImageView) findViewById(R.id.sizing_basescreen_thumb_down);
        mThumbViewDiagonal = (ImageView) findViewById(R.id.sizing_basescreen_thumb_diagonal);
        mThumbViewRight.setImageDrawable(new SimpleTriangleDrawable(PDEColor.valueOf("DTGrey1"), -90));
        mThumbViewDown.setImageDrawable(new SimpleTriangleDrawable(PDEColor.valueOf("DTGrey1")));
        mThumbViewDiagonal.setImageDrawable(new SimpleTriangleDrawable(PDEColor.valueOf("DTGrey1"), -45));

        // initial directions
        setContentSizingDirection(
                PDESizingScreenDirectionRight | PDESizingScreenDirectionDown | PDESizingScreenDirectionDiagonal);
        mDragDirection = 0;

        mFirstLayoutingFinished = false;
        mMinSize = new Point(PDEBuildingUnits.BU(), PDEBuildingUnits.BU());

        // default container view offset
        setContainerOffset(PDEBuildingUnits.BU(), PDEBuildingUnits.BU());

        // set the onTouch listener on the thumbs -> send click event on every touch up
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
                    if (view == mThumbViewRight) {
                        mDragDirection = PDESizingScreenDirectionRight;
                    } else if (view == mThumbViewDown) {
                        mDragDirection = PDESizingScreenDirectionDown;
                    } else if (view == mThumbViewDiagonal) {
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
                    if ((mDragDirection & PDESizingScreenDirectionRight) != 0
                        || (mDragDirection & PDESizingScreenDirectionDiagonal) != 0) {
                        newButtonWidth = mContainerWidthAtStart + deltaX;
                    }
                    if ((mDragDirection & PDESizingScreenDirectionDown) != 0
                        || (mDragDirection & PDESizingScreenDirectionDiagonal) != 0) {
                        newButtonHeight = mContainerHeightAtStart + deltaY;
                    }

                    //set new size
                    setContainerSize(newButtonWidth, newButtonHeight);

                    return true;
                } else if (action == MotionEvent.ACTION_UP) {
                    //Log.d(LOG_TAG, "ACTION_UP "+event.getRawX()+", "+event.getRawY());

                    // send click event via "performClick" to avoid analyse warnings
                    boolean focusTaken = false;
                    if (view.isFocusable() && view.isFocusableInTouchMode() && !view.isFocused()) {
                        focusTaken = view.requestFocus();
                    }
                    if (!focusTaken) {
                        view.performClick();

                    }
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
        if (vto != null) {
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    ViewTreeObserver vto = ResizeBaseActivity.this.findViewById(android.R.id.content)
                                                                  .getViewTreeObserver();
                    // remove the listener... or we'll be doing this a lot.
                    removeOnGlobalLayoutListener(vto, this);
                    mFirstLayoutingFinished = true;

                    //check valid bounds of current container view size, after layout and measure runs
                    RelativeLayout.LayoutParams lpContainer
                            = (RelativeLayout.LayoutParams) mResizeViewContainer.getLayoutParams();
                    setContainerSize(checkValidWidth(lpContainer.width), checkValidHeight(lpContainer.height));
                }
            });
        }
        setShowModeOfViewBounds(VIEW_BOUNDS_SHOW_MODE.NEVER);
    }


    /**
     * @brief Add a view to the resize container.
     */
    public void addViewToResizeContainer(View view, ViewGroup.LayoutParams layoutParams) {
        if (mResizeViewContainer != null) {
            mResizeViewContainer.addView(view, 0, layoutParams);
        }
    }


    /**
     * @brief Set the padding for the optional bounds we show when user clicks on the bounds toggle button in the bottom
     */
    public void setOptionalBoundsVisibilityPadding(Rect padding) {
        //set the padding of the container view, because this limits the optional padding bounds view
        mBoundsViewContainer.setPadding(padding.left, padding.top, padding.right, padding.bottom);
    }


    /**
     * @brief Set the padding for the optional bounds we show when user clicks on the bounds toggle button in the bottom
     */
    public void setOptionalBoundsVisibilityPadding(float padding) {
        //set the padding of the container view, because this limits the optional padding bounds view
        mBoundsViewContainer.setPadding((int) padding, (int) padding, (int) padding, (int) padding);
    }


    /**
     * @brief Set the padding for the optional bounds we show when user clicks on the bounds toggle button in the bottom
     */
    @SuppressWarnings("unused")
    public void setOptionalBoundsVisibilityPadding(float paddingLeft,
                                                   float paddintTop,
                                                   float paddingRight,
                                                   float paddingBottom) {
        //set the padding of the container view, because this limits the optional padding bounds view
        mBoundsViewContainer.setPadding((int) paddingLeft, (int) paddintTop, (int) paddingRight, (int) paddingBottom);
    }


    /**
     * @brief Sets the new size for the view container, which holds the element (with match_parent) to fit in.
     */
    public void setContainerSize(Point size) {
        setContainerSize(size.x, size.y);
    }


    /**
     * @brief Get the current size for the view container
     */
    public Point getContainerSize() {
        RelativeLayout.LayoutParams lpContainer;

        lpContainer = (RelativeLayout.LayoutParams) mResizeViewContainer.getLayoutParams();

        if (lpContainer == null) return new Point(0, 0);

        return new Point(lpContainer.width, lpContainer.height);
    }


    private float checkValidWidth(float width) {
        //we only can check the max bounds when measure was finished after layout so check flag
        if (mFirstLayoutingFinished && mThumbViewRight.getLayoutParams() != null) {
            if (width > mResizeLayoutContainer.getMeasuredWidth() - PDEBuildingUnits.pixelFromBU(2.0f) - mThumbViewRight
                    .getLayoutParams().width) {
                width = mResizeLayoutContainer.getMeasuredWidth() - PDEBuildingUnits.pixelFromBU(2.0f) - mThumbViewRight
                        .getLayoutParams().width;
            }
        }
        //check minimum
        if (width < mMinSize.x) {
            width = mMinSize.x;
        }
        return width;
    }


    private float checkValidHeight(float height) {
        //we only can check the max bounds when measure was finished after layout so check flag
        if (mFirstLayoutingFinished && mThumbViewDown.getLayoutParams() != null) {
            if (height > mResizeLayoutContainer.getMeasuredHeight() - PDEBuildingUnits.pixelFromBU(2.0f)
                         - mThumbViewDown.getLayoutParams().height) {
                height = mResizeLayoutContainer.getMeasuredHeight() - PDEBuildingUnits.pixelFromBU(2.0f)
                         - mThumbViewDown.getLayoutParams().height;
            }
        }
        //check minimum
        if (height < mMinSize.y) {
            height = mMinSize.y;
        }
        return height;
    }


    /**
     * Helper function to set the minimum size
     *
     * @param minSize New minimum size.
     */
    public void setMinimumSize(Point minSize) {
        mMinSize = minSize;
    }


    /**
     * @brief set the offset of the container view via margins
     */
    public void setContainerOffset(float marginLeft, float marginTop) {
        //get layout params
        RelativeLayout.LayoutParams lpContainer = (RelativeLayout.LayoutParams) mResizeViewContainer.getLayoutParams();

        if (lpContainer != null) {
            lpContainer.leftMargin = (int) marginLeft;
            lpContainer.topMargin = (int) marginTop;
            mResizeViewContainer.setLayoutParams(lpContainer);
        }
    }


    /**
     * @brief Set the offset of the container view via margins
     */
    public void setContainerOffset(Point marginOffset) {
        if (marginOffset == null) return;
        setContainerOffset(marginOffset.x, marginOffset.y);
    }


    /**
     * @brief Sets the new size for the view container, which holds the element (with match_parent) to fit in.
     */
    public void setContainerSize(float width, float height) {
        //get layout params
        RelativeLayout.LayoutParams lpContainer = (RelativeLayout.LayoutParams) mResizeViewContainer.getLayoutParams();

        //really changed?
        if (lpContainer.width == width && lpContainer.height == height) return;

        // limit the size
        width = checkValidWidth(width);
        height = checkValidHeight(height);

        // set size
        lpContainer.width = (int) width;
        lpContainer.height = (int) height;
        mResizeViewContainer.setLayoutParams(lpContainer);

        // size changed, so show bounds if flag if set
        // hide after delay
        if (mShowViewBounds == VIEW_BOUNDS_SHOW_MODE.ON_SIZE_CHANGE) {
            setViewBoundsVisibility(View.VISIBLE);
            setViewBoundsVisibility(View.GONE, VIEW_BOUNDS_SHOW_TIME);
        }
        updateDirectionButtons();
    }


    /**
     * @brief Update all of the direction resizing buttons (update position and visibility).
     */
    private void updateDirectionButtons() {
        int left, top, width, height;
        RelativeLayout.LayoutParams lpResizeContainer;

        //get resizeview contrainer params to get position/size values
        lpResizeContainer = (RelativeLayout.LayoutParams) mResizeViewContainer.getLayoutParams();
        left = lpResizeContainer.leftMargin;
        top = lpResizeContainer.topMargin;
        width = lpResizeContainer.width;
        height = lpResizeContainer.height;

        // check if right direction is set
        if ((mContentSizingDirection & PDESizingScreenDirectionRight) != 0) {
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
        if ((mContentSizingDirection & PDESizingScreenDirectionDown) != 0) {
            RelativeLayout.LayoutParams lp;
            lp = (RelativeLayout.LayoutParams) mThumbViewDown.getLayoutParams();
            lp.setMargins(((width - mThumbViewDown.getLayoutParams().width) / 2) + left,
                          top + PDEBuildingUnits.BU() + height, 0, 0);
            mThumbViewDown.setLayoutParams(lp);
            mThumbViewDown.setVisibility(View.VISIBLE);
        } else {
            mThumbViewDown.setVisibility(View.INVISIBLE);
        }

        // check if diagonal direction is set
        if ((mContentSizingDirection & PDESizingScreenDirectionDiagonal) != 0) {
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
        if (mShowViewBounds == show) return;

        mShowViewBounds = show;

        if (mShowViewBounds == VIEW_BOUNDS_SHOW_MODE.ALWAYS) {
            if (mViewBoundsRunnable != null) mViewBoundsShowHandler.removeCallbacks(mViewBoundsRunnable);
            mViewBoundsRunnable = null;
            mViewBoundsShowHandler = null;
            setViewBoundsVisibility(View.VISIBLE);
        } else if (mShowViewBounds == VIEW_BOUNDS_SHOW_MODE.NEVER) {
            if (mViewBoundsRunnable != null) mViewBoundsShowHandler.removeCallbacks(mViewBoundsRunnable);
            mViewBoundsRunnable = null;
            mViewBoundsShowHandler = null;
            setViewBoundsVisibility(View.GONE);
        } else if (mShowViewBounds == VIEW_BOUNDS_SHOW_MODE.ON_SIZE_CHANGE) {
            if (mViewBoundsShowHandler == null) {
                mViewBoundsShowHandler = new Handler();
            }
        }
    }


    /**
     * @brief Shows or hides the bounds after delayed time.
     */
    @SuppressWarnings("unused")
    private void setViewBoundsVisibility(final int visibility, int delay) {
        // security
        if (mViewBoundsRunnable != null) mViewBoundsShowHandler.removeCallbacks(mViewBoundsRunnable);

        mViewBoundsRunnable = new Runnable() {
            public void run() {
                setViewBoundsVisibility(visibility);
            }
        };
        mViewBoundsShowHandler.postDelayed(mViewBoundsRunnable, 3000);
    }


    /**
     * @brief Shows/hides the bounds.
     */
    private void setViewBoundsVisibility(int visibility) {
        if (mBoundsViewContainer != null) {
            mBoundsViewContainer.setVisibility(visibility);
        }
    }


    /**
     * @brief Helper function to set OnGlobalLayoutListener if a specific ViewTreeObserver (avoid warnings -> use reflection).
     *
     * removeGlobalOnLayoutListener is marked as deprecated in api level 16, in order to avoid the warning use reflection.
     */
    private void removeOnGlobalLayoutListener(ViewTreeObserver vto, ViewTreeObserver.OnGlobalLayoutListener listener) {
        Method method;

        // valid?
        if (vto == null) return;

        try {
            //try to use the removeOnGlobalLayoutListener function which was introduced in android 4.1 (api level 16)
            method = vto.getClass().getMethod("removeOnGlobalLayoutListener",
                                              new Class[]{ViewTreeObserver.OnGlobalLayoutListener.class});
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
                                              new Class[]{ViewTreeObserver.OnGlobalLayoutListener.class});
            method.invoke(vto, listener);
            return;
        } catch (NoSuchMethodException e) {
            // function not available
        } catch (IllegalAccessException e) {
            // function not available
        } catch (InvocationTargetException e) {
            // function not available
        }

        // everything goes wront -> SHOULD NOT HAPPEN
        Log.e(LOG_TAG,
              "there is no removeOnGlobalLayoutListener or removeGlobalOnLayoutListener function for this object:" + vto
                      .getClass()
                      .toString());
    }
}
