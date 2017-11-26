package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ConnectActivity extends AppCompatActivity {

   public static final String TAG = "ConnectActivity";

   private SectionsPageAdapter cSectionsPageAdapter;

   private ViewPager cViewPager;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.activity_connect);

   cSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

   cViewPager = (ViewPager) findViewById(R.id.container);
   Intent myIntent = getIntent();
   String title = myIntent.getStringExtra("title");
   setupViewPager(cViewPager, title);



   TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
   tabLayout.setupWithViewPager(cViewPager);
   }

   private void setupViewPager(ViewPager viewPager, String selectedTab) {
       SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
       adapter.addFragment(new VideocallFragment(), "Videocall");
       adapter.addFragment(new PhonecallFragment(), "Phonecall");
       adapter.addFragment(new ChatFragment(), "Chat");
       viewPager.setAdapter(adapter);
       viewPager.setCurrentItem(adapter.getPosition(selectedTab));

   }
}

class SectionsPageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> cFragmentList = new ArrayList<> ();
    private final List<String> cFragmentTitleList = new ArrayList<>();

    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        cFragmentList.add(fragment);
        cFragmentTitleList.add(title);
    }

    public int getPosition(String title) {
        return cFragmentTitleList.indexOf(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return cFragmentTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return cFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return cFragmentList.size();
    }
}