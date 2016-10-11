package tr.edu.ybu.eventandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MyComplexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int DUYURU = 0, ETKINLIK = 1;
    Context context;
    // The items to display in your RecyclerView
    private List<Object> items;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyComplexAdapter(List<Object> items, Context c) {
        this.context = c;
        this.items = items;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Duyuru) {
            return DUYURU;
        } else if (items.get(position) instanceof Etkinlik) {
            return ETKINLIK;
        }
        return -1;
    }

    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case DUYURU:
                View v1 = inflater.inflate(R.layout.duyuru_layout, viewGroup, false);
                viewHolder = new ViewHolderDuyuru(v1);
                break;
            case ETKINLIK:
                View v2 = inflater.inflate(R.layout.etkinlik_layout, viewGroup, false);
                viewHolder = new ViewHolderEtkinlik(v2);
                break;
            default:
                View v = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
                viewHolder = new ViewHolderError(v);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {

         /*   viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id=v.getId();
                    int id1=R.layout.activity_duyuru;

                        Toast.makeText(context,"duyuru"+id+"  "+id1,Toast.LENGTH_LONG).show();



                }
            });*/

        switch (viewHolder.getItemViewType()) {
            case DUYURU:
                ViewHolderDuyuru vh1 = (ViewHolderDuyuru) viewHolder;
                configureViewHolderDuyuru(vh1, position);
                break;
            case ETKINLIK:
                ViewHolderEtkinlik vh2 = (ViewHolderEtkinlik) viewHolder;
                configureViewHolderEtkinlik(vh2, position);
                break;
            default:
                ViewHolderError vh = (ViewHolderError) viewHolder;
                configureDefaultViewHolder(vh, position);
                break;
        }
    }

    private void configureDefaultViewHolder(ViewHolderError vh, int position) {
        vh.getlabel().setText((CharSequence) items.get(position));
    }

    private void configureViewHolderDuyuru(ViewHolderDuyuru vh1, int position) {
        Duyuru d = (Duyuru) items.get(position);
        //data[position][0]
        if (d != null) {
            vh1.getLabel1().setText(d.baslik);
            vh1.getLabel2().setText(d.ayrinti);
        }
    }

    private void configureViewHolderEtkinlik(ViewHolderEtkinlik vh2, int position) {
        Etkinlik e = (Etkinlik) items.get(position);
        if (e != null) {
            vh2.getText1().setText(e.etkinlikbaslik);
            vh2.getText2().setText(e.etkinlikayrinti);
            vh2.getImageView().setImageResource(R.drawable.bg);
        }
    }
}