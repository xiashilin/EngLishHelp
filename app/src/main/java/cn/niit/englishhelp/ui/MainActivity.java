package cn.niit.englishhelp.ui;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import cn.niit.englishhelp.R;
import cn.niit.englishhelp.fragment.HomeFragment;
import cn.niit.englishhelp.fragment.PersonFragment;
import cn.niit.englishhelp.fragment.ReviewFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    private BottomNavigationBar mBottomNavigationBar;
    private HomeFragment mHomeFragment;
    private ReviewFragment mReviewFragment;
    private PersonFragment mPersonFragment;
    private ActionBar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = getSupportActionBar();
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);

        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home_fill, getString(R.string.home)).setInactiveIconResource(R.mipmap.home).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.black_1))
                .addItem(new BottomNavigationItem(R.mipmap.review_fill, getString(R.string.review)).setInactiveIconResource(R.mipmap.review).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.black_1))
                .addItem(new BottomNavigationItem(R.mipmap.person_fill, getString(R.string.person)).setInactiveIconResource(R.mipmap.person).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.black_1))
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(this);

        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        HomeFragment homeFragment = mHomeFragment.newInstance();
        mToolbar.setTitle("首页");
        transaction.replace(R.id.sub_content, homeFragment).commit();

    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();

        switch (position) {
            case 0:
                mHomeFragment = HomeFragment.newInstance();
                mToolbar.setTitle("首页");
                beginTransaction.replace(R.id.sub_content, mHomeFragment);
                break;
            case 1:
                mReviewFragment = ReviewFragment.newInstance();
                mToolbar.setTitle("复习");
                beginTransaction.replace(R.id.sub_content, mReviewFragment);
                break;
            case 2:
                mPersonFragment = PersonFragment.newInstance();
                mToolbar.setTitle("我的");
                beginTransaction.replace(R.id.sub_content, mPersonFragment);
                break;
        }
        beginTransaction.commit();

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
