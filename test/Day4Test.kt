import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day4Test {

    @Test
    fun test_passPhraseIsValid() {
        assertTrue(passPhraseIsValid("aa bb cc dd ee"))
        assertTrue(passPhraseIsValid("aa bb cc dd aaa"))
        assertFalse(passPhraseIsValid("aa bb cc dd aa"))
    }

    @Test
    fun test_passPhraseIsValidWithAnagrams() {
        assertTrue(passPhraseIsValidWithAnagrams("abcde fghij"))
        assertTrue(passPhraseIsValidWithAnagrams("a ab abc abd abf abj"))
        assertTrue(passPhraseIsValidWithAnagrams("iiii oiii ooii oooi oooo"))
        assertFalse(passPhraseIsValidWithAnagrams("abcde xyz ecdab"))
        assertFalse(passPhraseIsValidWithAnagrams("oiii ioii iioi iiio"))
    }

}