package dev.paie.console;

import java.util.Scanner;

import dev.paie.ihm.Menu;
import dev.paie.service.CotisationService;
import dev.paie.service.CotisationServiceJpa;

/**
 * Hello world!
 *
 */
public class App {
	
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		CotisationService cotisService = new CotisationServiceJpa();
		
		Menu menu = new Menu(scanner, cotisService);
		menu.demarrer();
	
		scanner.close();
	}
}
