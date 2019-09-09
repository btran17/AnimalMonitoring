

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class Example.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Example
{
    private java.util.ArrayList<java.lang.String> arrayLis1;
    private AnimalMonitor animalMo1;

    /**
     * Default constructor for test class Example
     */
    public Example()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        arrayLis1 = new java.util.ArrayList<>();
        animalMo1 = new AnimalMonitor();
        animalMo1.addSightings("sightings.csv");
        arrayLis1.add("Elephant");
        arrayLis1.add("Mountain Gorilla");
        arrayLis1.add("Buffalo");
        arrayLis1.add("Topi");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
