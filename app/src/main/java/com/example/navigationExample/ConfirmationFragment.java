package com.example.navigationExample;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ConfirmationFragment extends Fragment {

    private String recipient;
    private Money money;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            recipient = bundle.getString("recipient","0");
            money = getArguments().getParcelable("amount");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_confirmation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String amount = money.getAmount().toString();
        String confirmationMessage = "You have sent the $" + amount + " to " + recipient;
        TextView textView = view.findViewById(R.id.confirmation_message);
        textView.setText(confirmationMessage);
    }
}
