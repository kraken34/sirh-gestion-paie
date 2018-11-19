package dev.paie.ihm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import dev.paie.service.CotisationService;

public class Menu {
	
	private Map<Integer, OptionMenu> options = new HashMap<>();
	private Scanner scanner;

	public Menu(Scanner scanner, CotisationService cotisService) {
		init(scanner, cotisService);
		this.scanner = scanner;
	}

	private void init(Scanner scanner, CotisationService cotisService) {
		this.options.put(1, new ListerCotisationsOptionMenu(cotisService));
		this.options.put(2, new CreerCotisationOptionMenu(scanner));
	}
	
	public void demarrer() {
		
		// Afficher les libellés des options
		this.options.forEach((key, option) -> {
			System.out.println(key + ". " + option.getLibelle());
		});
		
		int optionMenuChoisie = this.scanner.nextInt();
		
		this.options.get(optionMenuChoisie).executer();
		
	}
	
	
	
	


}
