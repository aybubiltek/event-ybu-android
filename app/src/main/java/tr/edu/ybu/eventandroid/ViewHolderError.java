package tr.edu.ybu.eventandroid;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.zeynep.ybu_app.R;

/**
 * Created by User on 6.09.2016.
 */
public class ViewHolderError extends RecyclerView.ViewHolder {

    private TextView ivExample;

    public ViewHolderError(View v) {
        super(v);
        ivExample = (TextView) v.findViewById(R.id.error);
    }

    public TextView getlabel() {
        return ivExample;
    }

    public void setlabel(TextView ivExample) {
        this.ivExample = ivExample;
    }
}