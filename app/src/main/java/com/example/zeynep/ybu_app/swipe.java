package com.example.zeynep.ybu_app;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by vaio on 02.07.2016.
 */
public class swipe extends PagerAdapter
{

   private int []array={R.drawable.bir,R.drawable.iki,R.drawable.uc};

    private Context context;
    private LayoutInflater layoutInflater;

    swipe(Context context)
    {
        this.context=context;

    }


    @Override
    public int getCount() {
        return array.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    public Object instantiateItem(ViewGroup container, int position)
    {
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.swipe,container,false);
        ImageView imageview=(ImageView)view.findViewById(R.id.imageView);
        TextView textView=(TextView)view.findViewById(R.id.text);
        imageview.setImageResource(array[position]);
        container.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((LinearLayout) object);
    }
}
