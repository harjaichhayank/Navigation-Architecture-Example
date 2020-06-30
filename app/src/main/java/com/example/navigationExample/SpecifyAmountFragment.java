package com.example.navigationExample;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SpecifyAmountFragment extends Fragment implements View.OnClickListener{

    private NavController navController;
    private String recipient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            recipient = bundle.getString("recipient","0");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_specify_amount, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        view.findViewById(R.id.send_btn).setOnClickListener(this);
        view.findViewById(R.id.cancel_btn).setOnClickListener(this);

        String message = "Sending money to " + recipient;
        TextView textView = requireView().findViewById(R.id.recipient);
        textView.setText(message);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_btn:
                TextInputEditText editText = requireView().findViewById(R.id.input_amount);
                if (!TextUtils.isEmpty(editText.getText())){

                    Integer value = Integer.parseInt(String.valueOf(editText.getText()));

                    ConfirmationFragment confirmationFragment = new ConfirmationFragment();

                    Money amount = new Money(value);
                    Bundle bundle = new Bundle();
                    bundle.putString("recipient",recipient);
                    bundle.putParcelable("amount",amount);
                    confirmationFragment.setArguments(bundle);

                    navController.navigate(R.id.action_specifyAmountFragment_to_confirmationFragment,bundle);
                }else {
                    Toast.makeText(getActivity(), "Enter Amount", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cancel_btn:
                requireActivity().onBackPressed();
                break;
        }
    }
}
