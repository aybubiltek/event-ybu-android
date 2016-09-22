package com.example.zeynep.ybu_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zeynep on 22.09.2016.
 */
public class ClubAdapter extends ArrayAdapter<Club> implements Filterable {
    List<Club> planetList;
    private Context context;
    private Filter planetFilter;
    private List<Club> origPlanetList;
    public ClubAdapter(List<Club> planetList, Context context)
    {
        super(context, R.layout.img_row_layout, planetList);
        this.planetList = planetList;
        this.context = context;
        this.origPlanetList = planetList;
    }

    public int getCount() {
        return planetList.size();
    }

    public Club getItem(int position) {
        return planetList.get(position);
    }

    public long getItemId(int position) {
        return planetList.get(position).hashCode();
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;

        ClubHolder holder = new ClubHolder();
        Club club = planetList.get(position);

        // First let's verify the convertView is not null
        if(convertView == null)        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.img_row_layout, null);

            TextView tv = (TextView) v.findViewById(R.id.nameKulup);
            TextView expView = (TextView) v.findViewById(R.id.expKulup);
            ImageView img = (ImageView) v.findViewById(R.id.imgKulup);

            holder.planetNameView = tv;
            holder.expView = expView;
            holder.img = img;

            v.setTag(holder);
        }
        else         {
            holder = (ClubHolder) v.getTag();
        }

        holder.planetNameView.setText(club.getName());
        holder.expView.setText("" + club.getExp());
        holder.img.setImageResource(club.getIdImg());
        return v;

    }
    public void resetData()
    {
        planetList = origPlanetList;
    }
    /* *********************************
	 * We use the holder pattern
	 * It makes the view faster and avoid finding the component
	 * **********************************/
    private static class ClubHolder
    {
        public TextView planetNameView;
        public TextView expView;
        public ImageView img;
    }
    /*
     * We create our filter
     */
    @Override
    public Filter getFilter()
    {
        if (planetFilter == null)
            planetFilter = new PlanetFilter();

        return planetFilter;
    }
    private class PlanetFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0)
            {
                // No filter implemented we return all the list
                results.values = origPlanetList;
                results.count = origPlanetList.size();
            }
            else
            {
                // We perform filtering operation
                List<Club> nPlanetList = new ArrayList<Club>();

                for (Club p : planetList)
                {
                    if (p.getName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        nPlanetList.add(p);
                }
                results.values = nPlanetList;
                results.count = nPlanetList.size();
            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            // Now we have to inform the adapter about the new list filtered
            if (results.count == 0)
                notifyDataSetInvalidated();
            else
            {
                planetList = (List<Club>) results.values;
                notifyDataSetChanged();
            }

        }

    }
}