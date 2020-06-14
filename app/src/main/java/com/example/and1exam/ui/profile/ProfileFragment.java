package com.example.and1exam.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.and1exam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment implements View.OnClickListener{

    private ProfileViewModel profileViewModel;

    private FirebaseUser fireBaseUser;

    private Button logOut,deleteAccount;
    private TextView email;
    private EditText passwordConfirm;

    private FirebaseAuth firebaseAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        logOut = root.findViewById(R.id.profile_log_out);
        deleteAccount = root.findViewById(R.id.profile_delete_account_button);

        logOut.setOnClickListener(this);
        deleteAccount.setOnClickListener(this);


        email = root.findViewById(R.id.profile_email);

        passwordConfirm = root.findViewById(R.id.profile_delete_confirm);

        firebaseAuth = FirebaseAuth.getInstance();

        email.setText(firebaseAuth.getCurrentUser().getEmail());

        fireBaseUser = firebaseAuth.getCurrentUser();

        return root;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile_log_out:

                //FirebaseAuth.getInstance().signOut();
                firebaseAuth.signOut();
                NavDirections navDirections = ProfileFragmentDirections.actionNavSlideshowToNavLogin();
                Navigation.findNavController(v).navigate(navDirections);
                break;

            case R.id.profile_delete_account_button:
                if (deleteAccount.getText().toString().equals("DELETE ACCOUNT")){
                    passwordConfirm.setVisibility(View.VISIBLE);
                    deleteAccount.setText("CONFIRM DELETION");
                }
                else{
                    firebaseAuth.signOut();
                    fireBaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            NavDirections navDirections = ProfileFragmentDirections.actionNavSlideshowToNavLogin();
                            Navigation.findNavController(v).navigate(navDirections);
                        }
                    });
                }

                break;
        }
    }
}
