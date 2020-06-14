package com.example.and1exam.ui.login;

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
import com.example.and1exam.ui.register.RegisterFragmentDirections;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment implements View.OnClickListener{

    private LoginViewModel mViewModel;
    private View view;
    private Button loginButton,registerButton;

    private EditText email,password;

    private FirebaseAuth firebaseAuth;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.login_fragment,container,false);

        registerButton = view.findViewById(R.id.login_registerButton);
        registerButton.setOnClickListener(this);

        loginButton = view.findViewById(R.id.login_loginButton);
        loginButton.setOnClickListener(this);

        email = view.findViewById(R.id.login_email);
        password = view.findViewById(R.id.login_password);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null)
        {
            NavDirections action;
            action = LoginFragmentDirections.actionNavLoginToNavHome();
            Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(action);
        }
        return view;
}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_registerButton:
                NavDirections navDirections = LoginFragmentDirections.actionNavLoginToNavRegister();
                Navigation.findNavController(v).navigate(navDirections);
                break;
            case R.id.login_loginButton:
                if (email.getText().toString().isEmpty() ||	password.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "One or more fields empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                NavDirections navDirections1 = LoginFragmentDirections.actionNavLoginToNavHome();
                                Navigation.findNavController(v).navigate(navDirections1);
                            } else
                                Toast.makeText(getContext(), "Wrong password or email", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
        }
    }
}
