package com.arif.otp.FragmentRegister;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arif.otp.R;
import com.arif.otp.Register_Admin.Register_Lembaga_Activity;

/**
 * Created by Angga Kristiono on 26/05/2019.
 */

public class FragmentAdmin extends Fragment implements View.OnClickListener {

    Button regAdmin;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_admin, container, false);
         regAdmin = view.findViewById(R.id.RegAdmin);
         regAdmin.setOnClickListener(this);
         return view;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.RegAdmin:
                Intent intent = new Intent(getActivity(),Register_Lembaga_Activity.class);
                startActivity(intent);
                break;
        }
    }
}
