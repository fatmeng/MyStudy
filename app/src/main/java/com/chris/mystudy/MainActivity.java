package com.chris.mystudy;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.chris.mystudy.adapater.ViewPagerAdapter;
import com.chris.mystudy.base.BaseActivity;
import com.chris.mystudy.commonutils.SnackbarUtils;
import com.chris.mystudy.customview.CustomViewFragment;
import com.chris.mystudy.home.HomeFragment;
import com.chris.mystudy.login.LoginActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity{


    private long mExitTime = 0L;

    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)Toolbar mToolBar;
    @BindView(R.id.bottom_navigation_view)BottomNavigationView mBottomNavigationView;
    @BindView(R.id.viewpager)ViewPager mViewPager;


    @Override
    public Toolbar setToolbar() {
        mToolBar.setNavigationIcon(R.drawable.navigation_icon);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        mToolBar.setTitle("主页");
        mToolBar.inflateMenu(R.menu.base_toolbar_menu);
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_search:
                        //调起Android使用记录访问权限的申请界面
                        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                        startActivity(intent);
                        break;
                    case R.id.action_notification:
                        break;
                    case R.id.action_login:
                        LoginActivity.actionStart(MainActivity.this);
                        overridePendingTransition(R.anim.act_right_enter,R.anim.act_left_exit);
                        break;
                    case R.id.action_share:
                        break;
                }
                Snackbar.make(mBottomNavigationView,"点击了菜单",Snackbar.LENGTH_SHORT).show();
                return true;
            }
        });
        return mToolBar;
    }


    @Override
    public int setContentId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initBottomNavigationView();
        initViewPager();
    }

    private void initViewPager() {
        ViewPagerAdapter adpter = new ViewPagerAdapter(getSupportFragmentManager());
        adpter.addFragment(HomeFragment.newInstance("RecycleView"));
        adpter.addFragment(CustomViewFragment.newInstance("CustomView"));
        adpter.addFragment(HomeFragment.newInstance("我的"));
        mViewPager.setAdapter(adpter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initBottomNavigationView() {

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = 0;
                Snackbar mSnackbar = null;
                switch (item.getItemId()){
                    case R.id.home_menu:
                        position = 0;
                        mSnackbar = Snackbar.make(mBottomNavigationView,"首页",Snackbar.LENGTH_SHORT);
                        break;
                    case R.id.look_menu:
                        position = 1;
                        mSnackbar = Snackbar.make(mBottomNavigationView,"浏览",Snackbar.LENGTH_SHORT);
                        break;
                    case R.id.myself_menu:
                        position = 2;
                        mSnackbar = Snackbar.make(mBottomNavigationView,"我的",Snackbar.LENGTH_SHORT);
                        break;
                    default:
                        mSnackbar = Snackbar.make(mBottomNavigationView,"未知",Snackbar.LENGTH_SHORT);
                        break;
                }
                SnackbarUtils.snackbarRefreshLayoutForPaddingBottom(mSnackbar,mBottomNavigationView.getHeight());
                mSnackbar.show();
                mViewPager.setCurrentItem(position);
                return true;
            }
        });
    }

    @Override
    public boolean showNavagationBar() {
        return false;
    }


    @Override
    public void onBackPressed() {
        _exit();
    }

    /**
     * 退出
     */
    private void _exit() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        if (System.currentTimeMillis() - mExitTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}
