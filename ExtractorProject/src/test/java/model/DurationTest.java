package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DurationTest {

    @Test
    void test_minus_soustrait_une_duree_en_seconde_sur_une_duree_en_minute() {
        //Given
        Duration duration = new Duration(0, 1, 0.00);

        duration.minus(new Duration(0, 0, 30.0));

        //When && then
        Assertions.assertEquals(new Duration(0, 0, 30.0), duration);
    }

    @Test
    void test_minus_soustrait_une_duree_en_seconde_sur_une_duree_en_heure() {
        //Given
        Duration duration = new Duration(1, 0, 0.00);

        duration.minus(new Duration(0, 0, 30.0));

        //When && then
        Assertions.assertEquals(new Duration(0, 59, 30.0), duration);
    }

    @Test
    void test_minus_soustrait_une_duree_en_seconde_sur_une_duree_en_seconde() {
        //Given
        Duration duration = new Duration(0, 0, 45.0);

        duration.minus(new Duration(0, 0, 30.0));

        //When && then
        Assertions.assertEquals(new Duration(0, 0, 15.0), duration);
    }

    @Test
    void test_minus_soustrait_une_duree_supérieur_a_une_duree_inferieur_alors_je_avoir_une_duree_a_0() {
        //Given
        Duration duration = new Duration(0, 0, 45.0);

        duration.minus(new Duration(0, 0, 50.0));

        //When && then
        Assertions.assertEquals(new Duration(0, 0, 0.0), duration);
    }

    @Test
    void test_minus_soustrait_une_duree_complexe_a_une_duree_autre_duree_complexe_et_retourne_le_bonne_durée() {
        //Given
        Duration duration = new Duration(5, 15, 20.0);

        duration.minus(new Duration(3, 20, 30.0));

        //When && then
        Assertions.assertEquals(new Duration(1, 54, 50.0), duration);
    }

    @Test
    void test_plus_additionne_une_duree_en_seconde_sur_une_duree_avec_minute() {
        //Given
        Duration duration = new Duration(0, 1, 0.00);

        duration.plus(new Duration(0, 0, 30.0));

        //When && then
        Assertions.assertEquals(new Duration(0, 1, 30.0), duration);
    }

    @Test
    void test_plus_additionne_une_duree_en_seconde_sur_une_duree_en_heure() {
        //Given
        Duration duration = new Duration(1, 0, 0.00);

        duration.plus(new Duration(0, 0, 30.0));

        //When && then
        Assertions.assertEquals(new Duration(1, 0, 30.0), duration);
    }

    @Test
    void test_plus_additionne_une_duree_en_seconde_sur_une_duree_en_seconde_dépassant_60seconde() {
        //Given
        Duration duration = new Duration(0, 0, 45.0);

        duration.plus(new Duration(0, 0, 30.0));

        //When && then
        Assertions.assertEquals(new Duration(0, 1, 15.0), duration);
    }

    @Test
    void test_plus_additionne_une_duree_en_minute_sur_une_duree__dépassant_60minute() {
        //Given
        Duration duration = new Duration(0, 59, 0.0);

        duration.plus(new Duration(0, 1, 0.0));

        //When && then
        Assertions.assertEquals(new Duration(1, 0, 0.0), duration);
    }

    @Test
    void test_plus_additionne_une_duree_complexe_a_une_duree_autre_duree_complexe_et_retourne_le_bonne_durée() {
        //Given
        Duration duration = new Duration(5, 15, 20.0);

        duration.plus(new Duration(3, 20, 30.0));

        //When && then
        Assertions.assertEquals(new Duration(8, 35, 50.0), duration);
    }


}
