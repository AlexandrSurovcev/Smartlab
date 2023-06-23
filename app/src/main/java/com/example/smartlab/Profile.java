package com.example.smartlab;

import static android.app.Activity.RESULT_OK;
import static com.example.smartlab.R.layout.fragment_analys;
import static com.example.smartlab.R.layout.fragment_profile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Profile extends Fragment {
    SharedPreferences preferences;
    ImageView avatar;
    static final int GALLERY_REQUEST = 1;
    TextView btnSave,editname,editpatronymic,editsurname,editdateofbirth,gendersList;
    boolean name,patronymic,surname,dateofbirth,genderbool,btnEnabled=true;
    EnableTextView ETV = new EnableTextView();
    Calendar calendar = Calendar.getInstance();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(fragment_profile, container, false);
        //поля ввода
        editname = v.findViewById(R.id.editname);
        avatar = v.findViewById(R.id.avatarka);
        btnSave = v.findViewById(R.id.btnSave);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });
        editpatronymic = v.findViewById(R.id.editpatronymic);
        editsurname = v.findViewById(R.id.editsurname);
        editdateofbirth = v.findViewById(R.id.editdateofbirth);
        gendersList = v.findViewById(R.id.gendersList);
        //ВЫПАДАЮЩИЙ СПИСОК
        AutoCompleteTextView genderChoice = v.findViewById(R.id.genderChoice);
        String[] gender = {"Мужской","Женский"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getContext(), R.layout.dropdown_items, gender);
        genderChoice.setAdapter(arrayAdapter);
        genderChoice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                gendersList.setText(genderChoice.getText().toString());
            }
        });
        //ПОЛЯ ВВОДА
        preferences = getActivity().getSharedPreferences("UserInfo", 0);
        editname.setText(preferences.getString("name",null));
        editpatronymic.setText(preferences.getString("patronymic",null));
        editsurname.setText(preferences.getString("surname",null));
        editdateofbirth.setText(preferences.getString("date",null));
        gendersList.setText(preferences.getString("gender",null));
        editname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editname.getText().toString().length()!=0){
                    name=true;
                    if(name && patronymic && surname&& dateofbirth && genderbool){
                        ETV.onEnableBtn(btnSave,getContext());
                        btnEnabled = true;
                    }
                }
                else {
                    ETV.onDisableBtn(btnSave,getContext());
                    btnEnabled = false;
                }
            }
        });
        editpatronymic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editpatronymic.getText().toString().length()!=0){
                    patronymic=true;
                    if(name && patronymic && surname&& dateofbirth && genderbool){
                        ETV.onEnableBtn(btnSave,getContext());
                        btnEnabled = true;
                    }
                }
                else {
                    ETV.onDisableBtn(btnSave,getContext());
                    btnEnabled = false;
                }
            }
        });
        editsurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editsurname.getText().toString().length()!=0){
                    surname=true;
                    if(name && patronymic && surname&& dateofbirth && genderbool ){
                        ETV.onEnableBtn(btnSave,getContext());
                        btnEnabled = true;
                    }
                }
                else {
                    ETV.onDisableBtn(btnSave,getContext());
                    btnEnabled = false;
                }
            }
        });
        editdateofbirth.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editdateofbirth.getText().toString().length()!=0){
                    dateofbirth=true;
                    editdateofbirth.setTextColor(ContextCompat.getColor(getContext(),R.color.black));
                    if(name && patronymic && surname&& dateofbirth && genderbool){
                        ETV.onEnableBtn(btnSave,getContext());
                        btnEnabled = true;
                    }
                }
                else {
                    ETV.onDisableBtn(btnSave,getContext());
                    btnEnabled = false;
                }
            }
        });
        gendersList.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(gendersList.getText().toString().length()!=0){
                    genderbool=true;
                    gendersList.setTextColor(ContextCompat.getColor(getContext(),R.color.black));
                    if(name && patronymic && surname&& dateofbirth && genderbool){
                        ETV.onEnableBtn(btnSave,getContext());
                        btnEnabled = true;
                    }
                }
                else {
                    ETV.onDisableBtn(btnSave,getContext());
                    btnEnabled = false;
                }
            }
        });
        editdateofbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(),d,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnEnabled) {
                    String nameValue = editname.getText().toString();
                    String patronymicValue = editpatronymic.getText().toString();
                    String surnameValue = editsurname.getText().toString();
                    String dateValue = editdateofbirth.getText().toString();
                    String genderValue = gendersList.getText().toString();
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("name", nameValue);
                    editor.putString("patronymic", patronymicValue);
                    editor.putString("surname", surnameValue);
                    editor.putString("date", dateValue);
                    editor.putString("gender", genderValue);
                    editor.putBoolean("createdcard",true);
                    editor.apply();
                    Toast.makeText(getContext(), "Готово", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getContext(), "Не все поля заполнены", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDate();
        }
    };
    private void setInitialDate() {

        editdateofbirth.setText(DateUtils.formatDateTime(getContext(),
                calendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        avatar = (ImageView) getActivity().findViewById(R.id.avatarka);

        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    avatar.setImageURI(selectedImage);
                }
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}