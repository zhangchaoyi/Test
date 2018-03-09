import interfaces.Contents;
import interfaces.Destination;

/**
 * Created by chris on 8/20/17.
 */
public class ParcelTest {

    public static void main(String[] args){
        Parcel4 p = new Parcel4();
        Contents c = p.contents();
        Destination d = p.destination("Tasmania");
//        Contents c2 = p.new PContents();
    }
}
