package tr.edu.ybu.eventandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zeynep.ybu_app.R;

import java.util.ArrayList;
import java.util.List;

public class KulupListActivity extends AppCompatActivity {
    List<Club> planetsList = new ArrayList<>();
    ClubAdapter planetAdap ;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kulup_list);

        initList();

        ListView lv = (ListView) findViewById(R.id.listViewKulup);

        planetAdap = new ClubAdapter(planetsList, KulupListActivity.this);
        lv.setAdapter(planetAdap);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id)
            {  Intent i=new Intent(KulupListActivity.this,ClubDetail.class);
                startActivity(i);
                Toast.makeText(KulupListActivity.this,"basıldı", Toast.LENGTH_SHORT).show();
            }
        });
        //register for the contextMenu
        registerForContextMenu(lv);
        //text filter
        lv.setTextFilterEnabled(true);
        EditText editTxt = (EditText) findViewById(R.id.editTxtKulupList);

        editTxt.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

                KulupListActivity.this.planetAdap.getFilter().filter(s); // Zeynebin kodunda bulunan kısım
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {   }
            @Override
            public void afterTextChanged(Editable s) {  }
        });
    }
    private void initList()
    {
        planetsList.add(new Club("EU YOUTH PROJECTS CLUB",""));
        planetsList.add(new Club("DEDICATED SOCIETY CLUB",""));
        planetsList.add(new Club("AFAK CLUB",""));
        planetsList.add(new Club("ACADEMIC COOPERATION CLUB",""));
        planetsList.add(new Club("ANALYTHICAL THINKING CLUB",""));
        planetsList.add(new Club("RE-DE ORGANIZATION CLUB",""));
        planetsList.add(new Club("THINKING JUST LIKE ATATÜRK CLUB",""));
        planetsList.add(new Club("BEŞİKTAŞ FAN CLUB",""));
        planetsList.add(new Club("SCIENCE AND TECHNOLOGY CLUB",""));
        planetsList.add(new Club("CYCLING CLUB",""));
        planetsList.add(new Club("RELIGION AND THINKING CLUB",""));
        planetsList.add(new Club("NATURAL SPORTS CLUB",""));
        planetsList.add(new Club("THINKING AND ACTION CLUB",""));
        planetsList.add(new Club("YBU WITHOUT BARRIERS CLUB",""));
        planetsList.add(new Club("ENGINEERING TECHNOLOGIES COMMUNITY",""));
        planetsList.add(new Club("ERASMUS STUDENTS CLUB",""));
        planetsList.add(new Club("FENERBAHÇE FAN CLUB",""));
        planetsList.add(new Club("PHOTOGRAPHY CLUB",""));
        planetsList.add(new Club("GALATASARAY FUN CLUB",""));
        planetsList.add(new Club("YOUNG MEN OF LITERATURE CLUB",""));
        planetsList.add(new Club("YOUNG ENTREPREUNERS CLUB",""));
        planetsList.add(new Club("YOUNG DOCTORS CLUB",""));
        planetsList.add(new Club("VISUAL ARTS CLUB",""));
        planetsList.add(new Club("FOLK DANCES CLUB",""));
        planetsList.add(new Club("LAW AND CULTURE CLUB",""));
        planetsList.add(new Club("FREE THINKING CLUB",""));
        planetsList.add(new Club("INSTITUTE OF ELECTRICAL AND ELECTRONICS E. CLUB",""));
        planetsList.add(new Club("INTERNATIONAL STUDENT CLUB",""));
        planetsList.add(new Club("ECONOMICS CLUB",""));
        planetsList.add(new Club("INNOVATION CLUB",""));
        planetsList.add(new Club("HUMAN RIGHTS CLUB",""));
        planetsList.add(new Club("CAREER CLUB",""));
        planetsList.add(new Club("LOCAL EDUCATION CULTURE, ART AND LITERATURE CLUB",""));
        planetsList.add(new Club("MATERIAL SCIENCE CLUB",""));
        planetsList.add(new Club("MUSIC CLUB",""));
        planetsList.add(new Club("NEOCORTEX CLUB",""));
        planetsList.add(new Club("IDEA AND RESEARCH CLUB",""));
        planetsList.add(new Club("PSYCHOLOGY CLUB",""));
        planetsList.add(new Club("RADIO CINEMA TV CLUB",""));
        planetsList.add(new Club("HEALTHY LIFE CLUB",""));
        planetsList.add(new Club("POLITICAL DEVELOPMENT CLUB",""));
        planetsList.add(new Club("SOCIAL ACTIVITIES AND ORGANIZATION CLUB",""));
        planetsList.add(new Club("SOCIAL ACTIVITIES AND POLITICAL RESEARCHES CLUB",""));
        planetsList.add(new Club("SOCIAL RESPONSIBILITY PROJECTS GROUP CLUB",""));
        planetsList.add(new Club("SOCIOLOGY CLUB",""));
        planetsList.add(new Club("HISTORY AND CULTURE GROUP CLUB",""));
        planetsList.add(new Club("FACULTY OF MEDICINE SCIENTIFIC RESEARCH CLUB",""));
        planetsList.add(new Club("THEATHRE CLUB",""));
        planetsList.add(new Club("TURKISH WORLD AND KIN GROUPS CLUB",""));
        planetsList.add(new Club("TURKISH MUSIC-YBU CLUB",""));
        planetsList.add(new Club("BUSINESS AND INFORMATION CLUB",""));
        planetsList.add(new Club("EMOTION ACADEMY",""));
        planetsList.add(new Club("ENGINEERING TECHNOLOGY COMMUNITY",""));
        planetsList.add(new Club("YOUNG IDEAS WORKSHOP",""));
        planetsList.add(new Club("IEEE STUDENT CLUB",""));
        planetsList.add(new Club("BUTTERFLY EFFECT CLUB",""));
        planetsList.add(new Club("MANAGEMENT AND ECONOMICS CLUB",""));
        planetsList.add(new Club("SIMURG ANATOLIAN CULTURES AND IDEAS CLUB",""));
        planetsList.add(new Club("PRESERVING TURKISH CIVILIZATIONS CLUB",""));
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo anInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle("Options for current planet");
        menu.add(1, 1, 1, "Details");
        menu.add(1, 2, 2, "Delete");
    }
    // This method is called when user selects an Item in the Context menu
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        int itemId = item.getItemId();
        // Implements our logic
       /* Toast.makeText(this, "Item id ["+itemId+"]", Toast.LENGTH_SHORT).show();*/

        AdapterView.AdapterContextMenuInfo aInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        planetsList.remove(aInfo.position);
        planetAdap.notifyDataSetChanged();
        return true;
    }
}
