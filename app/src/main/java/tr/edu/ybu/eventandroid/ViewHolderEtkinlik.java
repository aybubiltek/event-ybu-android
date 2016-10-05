package tr.edu.ybu.eventandroid;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by User on 4.09.2016.
 */
public class ViewHolderEtkinlik extends RecyclerView.ViewHolder {

    private ImageView ivExample;
    private TextView text1;
    private TextView text2;

    public ViewHolderEtkinlik(View v) {
        super(v);
        ivExample = (ImageView) v.findViewById(R.id.photo);
        text1 = (TextView) v.findViewById(R.id.etkinlikbaslık);
        text2 = (TextView) v.findViewById(R.id.altbaslık);
    }

    public ImageView getImageView() {
        return ivExample;
    }

    public void setImageView(ImageView ivExample) {
        this.ivExample = ivExample;
    }

    public TextView getText1() {
        return text1;
    }

    public void setText1(TextView label1) {
        this.text1 = text1;
    }

    public TextView getText2() {
        return text2;
    }

    public void setText2(TextView label2) {
        this.text2 = text2;
    }
}