package dev.paie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void initialiser() {
        String iciUnMotDePasse = "topSecret";
        String iciMotDePasseHashe = this.passwordEncoder.encode(iciUnMotDePasse);
    }


}
