package com.example.jd.sort.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jd.R;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class SortFragment extends Fragment{

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fenlei, null);
        return view;
    }
}
