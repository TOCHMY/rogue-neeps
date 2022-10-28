package map;

import map.Tunnel;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class TunnelMatcher{

    public static TypeSafeMatcher<Tunnel> hasFromRoom(){
        return new TypeSafeMatcher<Tunnel>() {
            @Override
            protected boolean matchesSafely(Tunnel tunnel) {
                if(!tunnel.getFromRoom().isEmpty()){
                    return true;
                } else {
                    return false;
                }
            }
            @Override
            public void describeTo(Description description) {
                description.appendText("Tunnel should have a fromRoom");
            }
        };
    }

    public static TypeSafeMatcher<Tunnel> hasToRoom(){

        return new TypeSafeMatcher<Tunnel>() {

            @Override
            protected boolean matchesSafely(Tunnel tunnel) {
                if(!tunnel.getToRoom().isEmpty()){
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Tunnel should have a toRoom");
            }

        };

    }

}