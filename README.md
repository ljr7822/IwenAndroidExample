# IwenAndroidExample库

## 自定义TabLayout

## 使用方法
**1、先引入jitpack：**在项目的gradle下
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

**2、添加依赖：**在app的grale下
 - Tag:1.0.0
```
dependencies {
	        implementation 'com.github.ljr7822:IwenAndroidExample:Tag'
	}
```

**3、在布局中使用：**
```
<androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#eeeeee"
        android:scrollbars="none">

        <cn.iwenddg.tablayoutlib.SegmentTabLayout
            android:id="@+id/segmentTabLayout"
            android:layout_width="280dp"
            android:layout_height="36dp"
            android:layout_marginTop="15dp"
            android:paddingLeft="25dp"
            android:paddingRight="25dp"
            tl:layout_constraintEnd_toEndOf="parent"
            tl:layout_constraintStart_toStartOf="parent"
            tl:layout_constraintTop_toTopOf="parent"
            tl:tl_bar_color="#ffffff"
            tl:tl_indicator_anim_enable="true"
            tl:tl_indicator_color="#9664EB"
            tl:tl_indicator_margin_bottom="2dp"
            tl:tl_indicator_margin_left="2dp"
            tl:tl_indicator_margin_right="2dp"
            tl:tl_indicator_margin_top="2dp"
            tl:tl_textBold="SELECT" />

        <androidx.viewpager.widget.ViewPager
            android:id="@+id/viewPager"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            tl:layout_constraintBottom_toBottomOf="parent"
            tl:layout_constraintEnd_toEndOf="parent"
            tl:layout_constraintStart_toStartOf="parent"
            tl:layout_constraintTop_toBottomOf="@+id/segmentTabLayout" />
    </androidx.constraintlayout.widget.ConstraintLayout>
```

**4、对应的Activity中：**
```
public class TabLayoutActivity extends BaseBDActivity<ActivityTabLayoutBinding, TabLayoutVM> {

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles_3 = {"关注", "推荐", "周围"};

    private SegmentTabLayout mTabLayout;

    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tab_layout;
    }

    private void initView() {
        for (String title : mTitles_3) {
            mFragments.add(SimpleCardFragment.getInstance("Switch ViewPager " + title));
        }
        mTabLayout = binding.segmentTabLayout;


        mViewPager = binding.viewPager;
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        mTabLayout.setTabData(mTitles_3);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // 初始页面
        mViewPager.setCurrentItem(1);

        // 显示未读红点
        mTabLayout.showDot(1);

        // 设置未读消息红点颜色
        mTabLayout.showDot(2);
        MsgView rtv_3_2 = mTabLayout.getMsgView(2);
        if (rtv_3_2 != null) {
            rtv_3_2.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
        // 显示未读消息数量
        mTabLayout.showMsg(0, 3);
        // 设置显示位置
        //mTabLayout.setMsgMargin(0, 1f, 8f);

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles_3[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
```
