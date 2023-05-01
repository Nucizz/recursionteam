package com.example.recursionteamapp.activities.fragment;

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
import com.example.recursionteamapp.database.UserDB;
import com.example.recursionteamapp.objects.User;

import java.util.regex.Pattern;

public class RegisterFragment extends Fragment {

    UserDB userDB;
    Button formSubmit;
    EditText formUsername, formEmail, formPassword, formConfirmPassword;
    TextView buttonToLogin;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        userDB = new UserDB(getContext());

        formSubmit = view.findViewById(R.id.registerButton);
        formSubmit.setOnClickListener(n -> validateRegister());
        formUsername = view.findViewById(R.id.editTextUsername);
        formEmail = view.findViewById(R.id.editTextEmail);
        formPassword = view.findViewById(R.id.editTextPassword);
        formConfirmPassword = view.findViewById(R.id.editTextConfirmPassword);

        buttonToLogin = view.findViewById(R.id.toLoginButton);
        buttonToLogin.setOnClickListener(n -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.authenticationContainer, new LoginFragment()).commit());
        return view;
    }

    void validateRegister() {
        String username = formUsername.getText().toString();
        String email = formEmail.getText().toString();
        String password = formPassword.getText().toString();
        String confirmPassword = formConfirmPassword.getText().toString();

        final Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()_–[{}]:;',?/*~$^+=<>]).{8,64}$");

        /*
        Rules:
        Username = 5 - 32 char
        Email = 11 - 64 char | regexp
        Password = 8 - 64 char | regexp (Alphanumeric + Lower/Upper + 1 symbol)
        ConfirmPassword = compare
         */

        if(username.length() > 32 || username.length() < 5) {
            Toast.makeText(getActivity(), "Username must be 5 - 32 characters!", Toast.LENGTH_LONG).show();
            return;
        }

        if(email.length() > 64 || email.length() < 11 || !EMAIL_REGEX.matcher(email).matches()) {
            Toast.makeText(getActivity(), "Please use a valid email address!", Toast.LENGTH_LONG).show();
            return;
        }

        if(password.length() > 64 || password.length() < 8 || !PASSWORD_REGEX.matcher(password).matches()) {
            Toast.makeText(getActivity(), "Password must be 8 - 64 characters with alphanumeric and symbols!", Toast.LENGTH_LONG).show();
            return;
        }

        if(!password.equals(confirmPassword)) {
            Toast.makeText(getActivity(), "Passwords don't match!", Toast.LENGTH_LONG).show();
            return;
        }

        if(!userDB.checkUser(username, email)) {
            Toast.makeText(getActivity(), "User already exist, please login!", Toast.LENGTH_LONG).show();
            return;
        }

        userDB.addUser(new User(0, username.toLowerCase(), email.toLowerCase(), password));
        Toast.makeText(getActivity(), "Register success!", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.authenticationContainer, new LoginFragment()).commit();
    }
}