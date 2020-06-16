package edu.escuelaing.arsw.webserver;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws Exception
    {   
        /*String [] args = new String[] {"http://localhost:30000/example.html"};
        System.out.println("Listo para recibir ...");
        int i = 0;
       // while(i < 1000){
            System.out.println("entro");
            BrowserPrototype.main(args);
            i++;
            System.out.println("numero:" +i);
        //}*/
        assert(true);
    }
}
