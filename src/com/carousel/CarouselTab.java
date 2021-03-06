package com.carousel;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sateliteMenu.SatelliteMenu;
import com.sateliteMenu.SatelliteMenuItem;
import com.utility.DebugLogger;
import com.vehicle.uart.R;
import com.sateliteMenu.SatelliteMenu.SateliteClickedListener;

/**
 * This class represents each tab in the {@link CarouselContainer}.
 * 
 * @author Andrew Neal (andrewdneal@gmail.com)
 */
public class CarouselTab extends FrameLayoutWithOverlay 
{	
	private DashedCircularProgress dashedCircularProgress;
	private TextView mText, mNumbers;
	private String mSign;

	/**
     * Used to display the main images in the tabs of the carousel
     */
    private ImageView mCarouselImage;

    /**
     * The label of each tab in the carousel
     */
    private TextView mLabel;

    /**
     * The layer placed over {@code #mCarouselImage}
     */
    private View mAlphaLayer;

    /**
     * @param context The {@link Context} to use
     * @param attrs The attributes of the XML tag that is inflating the view
     */
    public CarouselTab(Context context, AttributeSet attrs) 
    {
        super(context, attrs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        // Initiate the tab
        mCarouselImage = (ImageView) findViewById(R.id.carousel_tab_image);
        mLabel = (TextView) findViewById(R.id.carousel_tab_label);
        mAlphaLayer = findViewById(R.id.carousel_tab_alpha_overlay);
		mNumbers = (TextView) findViewById(R.id.number);
		mText = (TextView) findViewById(R.id.text);
		dashedCircularProgress = (DashedCircularProgress) findViewById(R.id.CircularView);
		dashedCircularProgress.setOnValueChangeListener(
                new DashedCircularProgress.OnValueChangeListener() 
                {
                    @Override
                    public void onValueChange(float value) 
                    {
                        mNumbers.setText((int) value + "" + mSign);
                    }
                });
		
        // Set the alpha layer
        setAlphaLayer(mAlphaLayer);

		SatelliteMenu menu = (SatelliteMenu) findViewById(R.id.menu);
		List<SatelliteMenuItem> items = new ArrayList<SatelliteMenuItem>();
		// TODO: need to update
        items.add(new SatelliteMenuItem(1, R.drawable.ic_1));
        items.add(new SatelliteMenuItem(2, R.drawable.ic_2));
        items.add(new SatelliteMenuItem(3, R.drawable.ic_3));
        items.add(new SatelliteMenuItem(4, R.drawable.ic_4));
        items.add(new SatelliteMenuItem(5, R.drawable.ic_5));
        items.add(new SatelliteMenuItem(6, R.drawable.ic_6));
        menu.addItems(items);        
        menu.setOnItemClickedListener(new SateliteClickedListener() 
		{
			public void eventOccured(int id) 
			{
				DebugLogger.e("Clicked on " + id);
				switch (id)
				{
				case 1:
					{
//						Intent newIntent = new Intent(getContext(), HistoryActivity.class);
//						getContext().startActivity(newIntent);
					}
					break;

				// TODO: need to update
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
					{
//						Intent newIntent = new Intent(getContext(), HistoryActivity.class);
//						getContext().startActivity(newIntent);
					}
					break;
					
				default:
					break;
				}
			}
		});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSelected(boolean selected)
    {
        super.setSelected(selected);
        setOverlayClickable(false);
        setSelectedState(selected);
    }

    /**
     * Sets the label for a tab
     * 
     * @param label The string to set as the label
     */
    public void setLabel(String label)
    {
        mLabel.setText(label);
    }

    /**
     * Sets the selected state of the label view
     * 
     * @param state True to select the label, false otherwise
     */
    public void setSelectedState(boolean state) 
    {
        mLabel.setSelected(state);
    }

    /**
     * Sets a drawable as the content of this ImageView
     * 
     * @param resId The resource identifier of the the drawable
     */
    public void setImageResource(int resId)
    {
        mCarouselImage.setImageResource(resId);
    }

    /**
     * Sets a Bitmap as the content of this ImageView
     * 
     * @param bm The {@link Bitmap} to set
     */
    public void setImageBitmap(Bitmap bm) 
    {
        mCarouselImage.setImageBitmap(bm);
    }

    /**
     * Sets a drawable as the content of this ImageView
     * 
     * @param drawable The {@link Drawable} to set
     */
    public void setImageDrawable(Drawable drawable)
    {
        mCarouselImage.setImageDrawable(drawable);
    }

    /**
     * @return the mCarouselImage
     */
    public ImageView getImage() 
    {
        return mCarouselImage;
    }

    /**
     * @return the mLabel
     */
    public TextView getLabel()
    {
        return mLabel;
    }

    /**
     * @return the mAlphaLayer
     */
    public View getAlphaLayer() 
    {
        return mAlphaLayer;
    }

	public TextView getText()
    {
        return mText;
    }
	
	public void setText(String text)
    {
        mText.setText(text);
    }

	public TextView getNumbers()
    {
        return mNumbers;
    }
	
	public void setNumbers(int value)
    {
    	dashedCircularProgress.setValue(value);
        mNumbers.setText(value + "" + mSign);
    }

	public String getSign()
    {
        return mSign;
    }
	
	public void setSign(String text)
    {
        mSign = text;
    }
}
