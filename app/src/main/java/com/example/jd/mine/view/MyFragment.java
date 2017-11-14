package com.example.jd.mine.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jd.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class MyFragment extends Fragment{

    private View view;

    @InjectView(R.id.login_register_tv)
    TextView login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, null);
        ButterKnife.inject(getActivity());
        initView();

        return view;
    }

    private void initView() {


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
