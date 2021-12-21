package cn.iwenddg.tablayoutlib.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

/**
 * Fragment 变更管理器
 *
 * @author iwen大大怪
 * @create 2021/12/19 15:20
 */
public class FragmentChangeManager {
    private FragmentManager mFragmentManager;
    private int mContainerViewId;
    /**
     * Fragment切换数组
     */
    private ArrayList<Fragment> mFragments;

    /**
     * 当前选中的Tab
     */
    private int mCurrentTab;

    public FragmentChangeManager(FragmentManager fm, int containerViewId, ArrayList<Fragment> fragments) {
        this.mFragmentManager = fm;
        this.mContainerViewId = containerViewId;
        this.mFragments = fragments;
        initFragments();
    }

    /**
     * 初始化fragments
     */
    private void initFragments() {
        for (Fragment fragment : mFragments) {
            mFragmentManager.beginTransaction().add(mContainerViewId, fragment).hide(fragment).commit();
        }
        setFragments(0);
    }

    /**
     * 界面切换控制
     */
    public void setFragments(int index) {
        for (int i = 0; i < mFragments.size(); i++) {
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            Fragment fragment = mFragments.get(i);
            if (i == index) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
            ft.commit();
        }
        mCurrentTab = index;
    }

    /**
     * 获取当前Tab
     *
     * @return int
     */
    public int getCurrentTab() {
        return mCurrentTab;
    }

    /**
     * 获取当前Fragment
     *
     * @return Fragment
     */
    public Fragment getCurrentFragment() {
        return mFragments.get(mCurrentTab);
    }
}