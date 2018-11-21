package dev.paie.web.controller.viewmodel;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;

public class CalculSurBulletin {
	
	private BulletinSalaire bulletin;
	private ResultatCalculRemuneration remuneration;
	
	public CalculSurBulletin(BulletinSalaire bulletin, ResultatCalculRemuneration remuneration) {
		super();
		this.bulletin = bulletin;
		this.remuneration = remuneration;
	}
	public BulletinSalaire getBulletin() {
		return bulletin;
	}
	public void setBulletin(BulletinSalaire bulletin) {
		this.bulletin = bulletin;
	}
	public ResultatCalculRemuneration getRemuneration() {
		return remuneration;
	}
	public void setRemuneration(ResultatCalculRemuneration remuneration) {
		this.remuneration = remuneration;
	}
	
	

}
