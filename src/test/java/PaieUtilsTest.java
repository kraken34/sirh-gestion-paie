import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dev.paie.util.PaieUtils;

public class PaieUtilsTest {

    private PaieUtils paieUtils;
    private ClassPathXmlApplicationContext ctx;

    @Before
    public void onSetup() {
        // code exécuté avant chaque test
    	ctx = new ClassPathXmlApplicationContext("app-config.xml");
    	paieUtils = ctx.getBean(PaieUtils.class);
    }

    @Test
    public void test_formaterBigDecimal_entier_positif() {

        String resultat = paieUtils.formaterBigDecimal(new BigDecimal("2"));

        assertThat(resultat, equalTo("2.00"));

    }
    
    @Test
    public void test_formaterBigDecimal_entier_negatif() {

        String resultat = paieUtils.formaterBigDecimal(new BigDecimal("-2"));

        assertThat(resultat, equalTo("-2.00"));

    }

    @Test
    public void test_formaterBigDecimal_trois_chiffres_apres_la_virgule() {

        String resultat = paieUtils.formaterBigDecimal(new BigDecimal("2.199"));

        assertThat(resultat, equalTo("2.20"));

    }
    
    @Test
    public void test_formaterBigDecimal_trois_chiffres_apres_la_virgule_negatif() {

        String resultat = paieUtils.formaterBigDecimal(new BigDecimal("-2.199"));

        assertThat(resultat, equalTo("-2.20"));

    }

    @After
    public void onExit() {
        // code exécuté après chaque test
    	ctx.close();
    }
}
