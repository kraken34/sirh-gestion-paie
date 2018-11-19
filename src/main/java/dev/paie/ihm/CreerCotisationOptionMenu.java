package dev.paie.ihm;

import java.util.Scanner;

public class CreerCotisationOptionMenu extends OptionMenu {

	private Scanner scanner;

	public CreerCotisationOptionMenu(Scanner scanner) {
		super("Créer une cotisation");
		this.scanner = scanner;
	}

	@Override
	public void executer() {
		System.out.println("Création en cours");
		
		System.out.println("Veuillez saisir le code : ");

		String codeSaisie = this.scanner.next();
		
		System.out.println("Vous avez saisie :" + codeSaisie);
		
		
		
	}

}
