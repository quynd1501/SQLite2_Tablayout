package com.example.sqlite2_tablayout.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sqlite2_tablayout.R;
import com.example.sqlite2_tablayout.UpdateDeleteActivity;
import com.example.sqlite2_tablayout.adapter.RecycleViewAdapter;
import com.example.sqlite2_tablayout.model.CongViec;
import com.example.sqlite2_tablayout.sqlite.SQLiteHelper;

import java.util.List;

public class FragmentDanhSach extends Fragment implements RecycleViewAdapter.ItemListener {
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_danhsach,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recycleView);
        adapter=new RecycleViewAdapter();
        //database
        db=new SQLiteHelper(getContext());
        List<CongViec> list=db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        CongViec congViec=adapter.getCongViec(position);
        Intent intent=new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("congviec",congViec);
        startActivity(intent);
    }

    @Override
    public void onResume() {//lam moi danh sach khi co thay doi du lieu
        super.onResume();
        List<CongViec> list=db.getAll();
        adapter.setList(list);
    }
}
