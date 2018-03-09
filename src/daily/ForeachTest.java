package daily;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by chris on 3/4/18.
 */
public class ForeachTest {

    public static void main(String[] args){
        Collection<Suit> suits = Arrays.asList(Suit.values());
        Collection<RANK> ranks = Arrays.asList(RANK.values());

        System.out.println(suits);
        System.out.println(ranks);

        /* bug
        for(Iterator<Suit> i = suits.iterator(); i.hasNext(); )
            for(Iterator<RANK> j = ranks.iterator(); j.hasNext();)
                System.out.println("i:" + i.next() + ",j:" + j.next());
         */

        for(Iterator<Suit> i = suits.iterator(); i.hasNext(); ) {
            Suit suit = i.next();
            for (Iterator<RANK> j = ranks.iterator(); j.hasNext(); )
                System.out.println("i:" + suit + ",j:" + j.next());
        }

    }

}

enum Suit {CLUB, DIAMOND, HEART, SPADE}
enum RANK {ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}
