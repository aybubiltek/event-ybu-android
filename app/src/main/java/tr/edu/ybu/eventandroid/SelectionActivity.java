package tr.edu.ybu.eventandroid;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import tr.edu.ybu.eventandroid.R;

public class SelectionActivity extends AppCompatActivity {
    ListView myList;
    TextView getChoice;
    TextView ann;

    String[] annList = {

            "Kulüp Duyuruları",

            "Etkinlik Duyuruları",

            "Rektörlük Duyuruları",

            "Bölüm Duyuruları",

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_selection);
        Intent intent = new Intent(this, SigninActivity.class);
        startActivity(intent);
        myList = (ListView)findViewById(R.id.list);
        ann=(TextView)findViewById(R.id.announcements);
        getChoice = (TextView) findViewById(R.id.getchoice);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),"font/Lato-Light.ttf");
        ann.setTypeface(custom_font);
        getChoice.setTypeface(custom_font);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                                                android.R.layout.simple_list_item_multiple_choice,
                                                                annList);

        myList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        myList.setAdapter(adapter);

        getChoice.setOnClickListener(new View.OnClickListener(){



            @Override
            public void onClick(View v) {
                String selected = "";
                int cntChoice = myList.getCount();

                SparseBooleanArray sparseBooleanArray = myList.getCheckedItemPositions();

                for(int i = 0; i < cntChoice; i++){

                    if(sparseBooleanArray.get(i)) {

                        selected += myList.getItemAtPosition(i).toString() + "\n";
                    }
                }
                Toast.makeText(SelectionActivity.this, selected, Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),DuyuruActivity.class);
                startActivity(intent);
            }});



    }
}
