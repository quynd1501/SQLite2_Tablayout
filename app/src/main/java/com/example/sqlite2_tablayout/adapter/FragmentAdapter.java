package com.example.sqlite2_tablayout.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.sqlite2_tablayout.fragment.FragmentDanhSach;
import com.example.sqlite2_tablayout.fragment.FragmentSearch;
import com.example.sqlite2_tablayout.fragment.FragmentThongTin;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentDanhSach();
            case 1: return new FragmentThongTin();
            case 2: return new FragmentSearch();
            default: return new FragmentDanhSach();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        switch (position){
//            case 0: return "Danh sach";
//            case 1: return "Thong tin";
//            case 2: return "Tim kiem";
//            default: return "Danh sach";
//        }
//    }
}
