package com.example.recursionteamapp.activities.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recursionteamapp.R;
import com.example.recursionteamapp.activities.MainActivity;
import com.example.recursionteamapp.database.TemporaryData;
import com.example.recursionteamapp.database.UserDB;
import com.example.recursionteamapp.objects.User;


public class LoginFragment extends Fragment {

    UserDB userDB;
    Button formSubmit;
    EditText formUsername, formPassword;
    TextView buttonToRegister;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        userDB = new UserDB(getContext());

        formSubmit = view.findViewById(R.id.loginButton);
        formSubmit.setOnClickListener(n -> validateLogin());
        formUsername = view.findViewById(R.id.editTextUsername);
        formPassword = view.findViewById(R.id.editTextPassword);

        buttonToRegister = view.findViewById(R.id.toRegisterButton);
        buttonToRegister.setOnClickListener(n -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.authenticationContainer, new RegisterFragment()).commit());

        return view;
    }

    void validateLogin() {
        String username = formUsername.getText().toString();
        String password = formPassword.getText().toString();

        if(username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getActivity(), "Please fill in the form!", Toast.LENGTH_LONG).show();
            return;
        }

        int userId = userDB.validateUser(username.toLowerCase(), User.encrypt(password));
        if(userId != 0) {
            TemporaryData.setCurrentUser(userDB.getUser(userId));
            startActivity(new Intent(getActivity(), MainActivity.class));
            Toast.makeText(getActivity(), "Login success!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Wrong username or password!", Toast.LENGTH_LONG).show();
        }
    }

}