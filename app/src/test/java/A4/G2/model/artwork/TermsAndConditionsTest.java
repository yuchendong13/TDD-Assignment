package A4.G2.model.artwork;

import A4.G2.model.Gallery;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TermsAndConditionsTest {
    Gallery gallery;
    String termsAndConditions;

    @BeforeAll
    public void setUp(){
        gallery= Mockito.spy(new Gallery());
    }

    @BeforeEach
    public void setUpTermsAndConditions(){
        termsAndConditions="Initial terms and conditions";
        gallery.setTermsAndConditions(termsAndConditions);
    }

    @Test
    public void testGetTermsAndConditions(){
        assertEquals(termsAndConditions, gallery.getTermsAndConditions());
    }

    @Test
    public void testEditTermsAndConditions(){
        String newTermsAndConditions="New terms and conditions";
        gallery.setTermsAndConditions(newTermsAndConditions);
        assertEquals(newTermsAndConditions, gallery.getTermsAndConditions());
    }
}
