package com.example.listycitylab3;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {
    /*
     * Instantiate an EditCityFragment with the provided city stored in a bundle
     */
    public static EditCityFragment newInstance(City city) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        EditCityFragment fragment = new EditCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        return builder
                .setView(view)
                .setTitle("Edit a city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add", (dialog, which) -> {
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();

                    Bundle args = requireArguments();
                    City c = (City) args.getSerializable("city");

                    c.setName(cityName);
                    c.setProvince(provinceName);

                    // The provided bundle has to be non-null (even though the parent never uses it)
                    getParentFragmentManager().setFragmentResult("requestKey", args);
                })
                .create();
    }
}
