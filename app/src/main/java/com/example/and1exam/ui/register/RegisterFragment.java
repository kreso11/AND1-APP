package com.example.and1exam.ui.register;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.and1exam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends Fragment implements View.OnClickListener{

    private RegisterViewModel mViewModel;

    private Button registerAndLogin;
    private EditText email,password,confirmPassword;

    private FirebaseAuth firebaseAuth;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.register_fragment, container, false);

        registerAndLogin = view.findViewById(R.id.register_registerButton);
        registerAndLogin.setOnClickListener(this);

        email = view.findViewById(R.id.register_email);
        password = view.findViewById(R.id.register_password);
        confirmPassword = view.findViewById(R.id.register_passwordConfirm);

        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_registerButton:
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || confirmPassword.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "One or more fields are empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.getText().toString().length() > 7) {
                        if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                            firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.i("SUCC", "SESS");
                                        NavDirections navDirections = RegisterFragmentDirections.actionNavRegisterToNavHome();
                                        Navigation.findNavController(v).navigate(navDirections);
                                    } else
                                        Log.i("SUCC", task.getException().toString());
                                }
                            });
                        } else {
                            Toast.makeText(getContext(), "Passwords don't match", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
