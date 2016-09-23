package tr.edu.ybu.eventandroid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.zeynep.ybu_app.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import fragments.OneFragment;
import fragments.TwoFragment;

public class EventDetailActivity extends AppCompatActivity {
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton5;

    ViewPager viewPager;
    swipe adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager2;

    public int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        viewPager=(ViewPager)findViewById(R.id.viewpagerKulupDetay);
        adapter=new swipe(this);



        final TextView b1 = (TextView) findViewById(R.id.buttonTakip);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setText(" ✔");
                flag++;
                if(flag==2)
                {
                    b1.setText("Katıl");
                    flag=flag-2;
                }
            }




        });
        viewPager2 = (ViewPager) findViewById(R.id.viewpager2);
        setupViewPager(viewPager2);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager2);
        viewPager.setAdapter(adapter);



        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.social_floating_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.floating_facebook);
        floatingActionButton5 = (FloatingActionButton) findViewById(R.id.floating_instagram);


        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                Intent facebookIntent = getOpenFacebookIntent(EventDetailActivity.this);
                startActivity(facebookIntent);

            }
        });




        floatingActionButton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                Intent instagramIntent = getOpenInstagramIntent(EventDetailActivity.this);
                startActivity(instagramIntent);
            }
        });




    }



    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://page/376227335860239")); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/YbuBiltek.Official/")); //catches and opens a url to the desired page
        }
    }






    public static Intent getOpenInstagramIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.instagram.android", 0); //Checks if Instagram is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/accounts/login/")); //Trys to make intent with Instagram's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/accounts/login/")); //catches and opens a url to the desired page
        }
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OneFragment(), "BİLGİ");
        adapter.addFrag(new TwoFragment(), "PROGRAM");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
