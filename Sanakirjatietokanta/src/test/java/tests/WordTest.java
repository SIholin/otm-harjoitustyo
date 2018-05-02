/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import sanakirja.domain.Word;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ihqsanna
 */
public class WordTest {

    Word word;

    @Before
    public void setUp() {
        word = new Word(1, "kissa", "cat");
    }

    @Test
    public void wordExsists() {
        assertTrue(word != null);
    }

    @Test
    public void idWorks() {
        assertTrue(word.getId() == 1);
    }

    @Test
    public void formIsRight() {
        assertTrue(word.getForm().equals("kissa"));
    }

    @Test
    public void translationIsRight() {
        assertTrue(word.getTransaltion().equals("cat"));
    }

}
