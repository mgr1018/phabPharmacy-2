import ServletCommunications.ReturnDetails;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;


public class TestReturnDetails {
    @Test
    public void testServerReturn(){
        ReturnDetails query = new ReturnDetails(10);
        Assert.assertEquals("5","5");
        Assert.assertThat(query.getName(), is(equalTo("Martin Holloway")));
    }

}
