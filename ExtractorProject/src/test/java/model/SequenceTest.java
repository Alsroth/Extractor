package model;

import exception.ProcessBuilderException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SequenceTest {

    @Test
    void test_shift_jeDecaleLesDureeDuneVideo_alorsJeRetrouveLaSequenceDeDebutEtFinDecale() {
        // Given
        Sequence sequence = new Sequence("file", "00:00:00.0", "00:00:30.0");
        Sequence expectedSequence = new Sequence("file", "00:00:30", "00:01:00.0");

        //When
        sequence.shift(30.0);

        //Then
        assertEquals(expectedSequence, sequence);

    }

    @Test
    void test_equals_jeCompareDeuxObjetIdentique_alorsRetourneVrai() {
        // Given
        Sequence sequence1 = new Sequence("file", "00:00:00.0", "00:00:30.0");
        Sequence sequence2 = new Sequence("file", "00:00:00.0", "00:00:30.0");

        // When && then
        assertEquals(sequence1, sequence2);
    }

    @Test
    void test_equals_jeCompareDeuxObjetIdentiqueAvecLaMemeReference_alorsRetourneVrai() {
        // Given
        Sequence sequence = new Sequence("file", "00:00:00.0", "00:00:30.0");

        // When && then
        assertEquals(sequence, sequence);
    }

    @Test
    void test_equals_jeCompareAvecUnObjetNull_alorsRetourneFalse() {
        // Given
        Sequence sequence = new Sequence("file", "00:00:00.0", "00:00:30.0");

        // When && then
        assertNotEquals(null, sequence);
    }

    @Test
    void test_equals_jeCompareAvecUnObjetDunTypeDifferent_alorsRetourneFalse() {
        // Given
        Sequence sequence = new Sequence("file", "00:00:00.0", "00:00:30.0");

        // When && then
        assertNotEquals(new Object(), sequence);
    }

    @Test
    void test_toString_jeCreerUneSequenceEtLaTransformeEnString_AlorsJaiLaBonneString() {
        // Given
        Sequence sequence = new Sequence("file", "00:00:00.0", "00:00:30.0");

        // When && then
        assertEquals("FileName : file Start: 00:00:00.0 End: 00:00:30.0", sequence.toString());
    }

    @Test
    void test_hashCode_jeGenereLeHashCodePourUnMemeObjetDeuxFois_AlorsIlsDoiventEtreIdentique() {
        // Given
        Sequence sequence = new Sequence("file", "00:00:00.0", "00:00:30.0");

        // When && then
        assertEquals(sequence.hashCode(), sequence.hashCode());
    }

    @Test
    void test_hashCode_jeGenereLeHashCodePourDeuxObjetDeuxDifferant_AlorsIlsNeDoiventPasEtreIdentique() {
        // Given
        Sequence sequence1 = new Sequence("file", "00:00:00.0", "00:00:30.0");
        Sequence sequence2 = new Sequence("file", "00:00:00.0", "00:00:30.0");

        // When && then
        assertNotEquals(sequence1.hashCode(), sequence2.hashCode());
    }

    @Test
    void test_cut_jeCoupeUneVideo_AlorsLhistoriqueDesVideosCoupeEtMisAJour() throws ProcessBuilderException {
        // Given
        Sequence sequence = new Sequence("file.mp4", "00:00:00.0", "00:00:30.0");

        // When
        sequence.cut("_edit", ".mp4");

        // When && then
        assertEquals(History.createdOutPutFiles, List.of("file_edit.mp4"));
    }
}
