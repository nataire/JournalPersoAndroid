package com.example.JournalPerso.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.JournalPerso.R;
import com.example.JournalPerso.data.DataApi;
import com.example.JournalPerso.data.DataLocal;
import com.example.JournalPerso.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class ModifierProfilFragment extends Fragment {

    private User monUser;
    private EditText nom;
    private EditText prenom;
    private EditText mail;
    private EditText password;
    private EditText confirmPasswword;
    private DataApi dataApi;
    private DataLocal dataLocal;
    private FloatingActionButton monBouton;
    private NavigationView navigationView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_modifier_profil, container, false);

        dataApi = new DataApi(getContext());
        dataLocal = new DataLocal();

        monUser = dataLocal.recuperationUser(getContext());

        nom = root.findViewById(R.id.nomUser);
        prenom = root.findViewById(R.id.prenomUser);
        mail = root.findViewById(R.id.mailUser);
        password = root.findViewById(R.id.passwordUser);
        confirmPasswword = root.findViewById(R.id.passwordUserConfirm);
        monBouton = root.findViewById(R.id.accepterModificationUser);

        nom.setText(monUser.getNomUser());
        prenom.setText(monUser.getPrenomUser());
        mail.setText(monUser.getEmail());
        password.setText(monUser.getPassword());
        confirmPasswword.setText(monUser.getPassword());

        navigationView = root.findViewById(R.id.nav_view);

        monBouton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!monUser.isEmailValid(mail.getText().toString())) {
                    Toast toast = Toast.makeText(getContext(), "Erreur mail", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (!password.getText().toString().equals(confirmPasswword.getText().toString())) {
                    Toast toast = Toast.makeText(getContext(), "erreur mot de passe ", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    dataApi.updateUser(monUser.getId(), nom.getText().toString(), prenom.getText().toString(), mail.getText().toString(), password.getText().toString());
                    Toast toast = Toast.makeText(getContext(), "Modification de l'utilisateur effectué", Toast.LENGTH_SHORT);
                    toast.show();

                    monUser.setEmail(mail.getText().toString());
                    monUser.setNomUser(nom.getText().toString());
                    monUser.setPrenomUser(prenom.getText().toString());
                    monUser.setPassword(password.getText().toString());

                    dataLocal.sauvegarderUser(getContext(), monUser);

                }


            }
        });
        return root;
    }
}