package dev.paie.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {

		final ResultatCalculRemuneration resCalcRem = new ResultatCalculRemuneration();
		final RemunerationEmploye remEmploye = bulletin.getRemunerationEmploye();
		final Grade grade = remEmploye.getGrade();
		final List<Cotisation> cotisations = remEmploye.getProfilRemuneration().getCotisations();

		final BigDecimal salaireBase = grade.getNbHeuresBase().multiply(grade.getTauxBase());
		final BigDecimal salaireBrut = new BigDecimal(PaieUtils.formaterBigDecimal(salaireBase.add(bulletin.getPrimeExceptionnelle())));

		final BigDecimal sumNonImpo = cotisations.stream()
				.filter(cot -> !cot.getImposable())
				.filter(cot->cot.getTauxSalarial()!=null)
				.map(cot -> cot.getTauxSalarial())
				.reduce(new BigDecimal(0), (bd1, bd2) -> bd2.add(bd1));

		final BigDecimal retenueSalariale = new BigDecimal(PaieUtils.formaterBigDecimal(salaireBrut.multiply(sumNonImpo)));

		final BigDecimal sumImpo = cotisations.stream()
				.filter(cot -> !cot.getImposable())
				.filter(cot->cot.getTauxPatronal()!=null)
				.map(cot -> cot.getTauxPatronal())
				.reduce(new BigDecimal(0), (bd1, bd2) -> bd1.add(bd2));

		final BigDecimal cotisationPatronales = salaireBrut.multiply(sumImpo);
		final BigDecimal netImposable = salaireBrut.subtract(retenueSalariale);

		final BigDecimal sumCotImpo = cotisations.stream()
				.filter(cot -> cot.getImposable())
				.filter(cot->cot.getTauxSalarial()!=null)
				.map(cot -> cot.getTauxSalarial())
				.reduce(new BigDecimal(0), (bd1, bd2) -> bd1.add(bd2));

		final BigDecimal netAPayer = netImposable.subtract(salaireBrut.multiply(sumCotImpo));

		resCalcRem.setSalaireDeBase(salaireBase.toString());
		resCalcRem.setSalaireBrut(salaireBrut.toString());
		resCalcRem.setTotalRetenueSalarial(new BigDecimal(PaieUtils.formaterBigDecimal(retenueSalariale)).toString());
		resCalcRem.setTotalCotisationsPatronales(new BigDecimal(PaieUtils.formaterBigDecimal(cotisationPatronales)).toString());
		resCalcRem.setNetImposable(new BigDecimal(PaieUtils.formaterBigDecimal(netImposable)).toString());
		resCalcRem.setNetAPayer(new BigDecimal(PaieUtils.formaterBigDecimal(netAPayer)).toString());

		return resCalcRem;
	}
}
