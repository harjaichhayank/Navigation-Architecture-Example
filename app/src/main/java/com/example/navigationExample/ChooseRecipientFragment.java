package com.example.navigationExample;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class ChooseRecipientFragment extends Fragment implements View.OnClickListener {

    private NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_choose_recipient, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.next_btn).setOnClickListener(this);
        view.findViewById(R.id.cancel_btn).setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next_btn:
                TextInputEditText editText = requireView().findViewById(R.id.input_recipient);
                if (!TextUtils.isEmpty(editText.getText())){
                    Bundle bundle = new Bundle();
                    bundle.putString("recipient", editText.getText().toString());
                    navController.navigate(R.id.action_chooseRecipientFragment_to_specifyAmountFragment,bundle);
                }else {
                    Toast.makeText(getActivity(), "Enter Recipient", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cancel_btn:
                requireActivity().onBackPressed();
                break;
        }
    }
}
