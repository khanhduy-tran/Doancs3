package com.khanhduy.doancuoiki.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.khanhduy.doancuoiki.Activity.CapNhatThongTinUserActivity;
import com.khanhduy.doancuoiki.Activity.DoiMatKhauActivity;
import com.khanhduy.doancuoiki.Activity.HomeActivity;
import com.khanhduy.doancuoiki.R;
import com.khanhduy.doancuoiki.Object.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FragmentProfile extends Fragment {
    View view;
    Button btnDoimatkhau, btnCapnhat;
    TextView txtTenUser,txtTentaikhoan,txtMatkhau,txtNgaysinh,txtDiachi,txtEmail,txtDienthoai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile,container,false);
        AnhXa();
        InformationUser();
        ButtonClick();
        return view;
    }

    private void ButtonClick() {
        btnCapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CapNhatThongTinUserActivity.class);
                startActivity(intent);
            }
        });
        btnDoimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DoiMatKhauActivity.class);
                startActivity(intent);
            }
        });
    }


    private void InformationUser() {
        HomeActivity homeActivity = (HomeActivity) getActivity();
        User user = homeActivity.getUser2();
        int idUser = user.getId();
        String tenUser = user.getName();
       Date ngaySinhUser = user.getBirthday();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(ngaySinhUser);

       String diachiUser = user.getAddress();
       String emailUser = user.getEmail();
       String sdtUser = user.getPhone();
       String taikhoanUser = user.getTaikhoan();
       String matkhauUser = user.getPassword();
       String mahoa = "";
        for(int i = 0;i< matkhauUser.length();i++){
            mahoa = mahoa + "*";
        }
       txtTenUser.setText(tenUser);
        txtTentaikhoan.setText(taikhoanUser);
       txtMatkhau.setText(mahoa);
       txtNgaysinh.setText(date);
       txtDiachi.setText(diachiUser);
       txtEmail.setText(emailUser);
       txtDienthoai.setText(sdtUser);


    }

    private void AnhXa() {
        btnDoimatkhau = (Button) view.findViewById(R.id.buttonDoimatkhau);
        btnCapnhat = (Button) view.findViewById(R.id.buttonUpdateUser);
        txtTenUser = (TextView) view.findViewById(R.id.tenUser);
        txtTentaikhoan = (TextView) view.findViewById(R.id.tenTaiKhoan);
        txtMatkhau = (TextView) view.findViewById(R.id.matkhauUser);
        txtNgaysinh = (TextView) view.findViewById(R.id.ngaySinhUser);
        txtDiachi = (TextView) view.findViewById(R.id.diaChiUser);
        txtEmail = (TextView) view.findViewById(R.id.emailUser);
        txtDienthoai = (TextView) view.findViewById(R.id.sdtUser);

     }


}