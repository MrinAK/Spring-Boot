package eu.itdc.internetprovider.persistence.entity;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void checkLogOutExpirationEndNotExpiredIn5Min() {
//        Given
        User user = new User();
        user.setAccountNonLocked(false);
        user.setLastFellLoginAttempt(Instant.now().minusSeconds(30));

//        When

        user.checkLogOutExpiration(Duration.parse("PT5M"));

//        Then
        assertFalse(user.isAccountNonLocked(), "Account is lock incorrectly");
        assertNotNull(user.getLastFellLoginAttempt(),"Last fail login time is populate correctly");

    }

    @Test
    void checkLogOutExpirationAfterIn5Min() {
//        Given
        User user = new User();
        user.setAccountNonLocked(false);
        user.setLastFellLoginAttempt(Instant.now().minusSeconds(800));

//        When

        user.checkLogOutExpiration(Duration.parse("PT5M"));

//        Then
        assertTrue(user.isAccountNonLocked(), "Account is lock incorrectly");
        assertNull(user.getLastFellLoginAttempt(),"Last fail login time is populate correctly");

    }

    @Test
    public void successfullyLogin(){
//        Given
        User user = new User();

//        When
        user.checkLogOutExpiration(Duration.parse("PT5M"));

//        Then
        assertEquals(0, user.getBadLoginAttempt(), "Incorrect number of login");
        assertNull(user.getLastFellLoginAttempt(), "Last fail login time is populate correctly");
        assertTrue(user.isAccountNonLocked(), "Account is lock incorrectly" );
    }

    @Test
    void failFirstLoginAttempt() {
//        Given
        User user = new User();

//        When
        user.failLoginAttempt(5);

//        Then
        assertTrue(user.isAccountNonLocked());
        assertEquals(1, user.getBadLoginAttempt());
        assertNotNull(user.getLastFellLoginAttempt());
    }

    @Test
    public void lockedAccountAfter5TimesIncorrectLogin(){
//        Given
        User user = new User();

//        When
        user.failLoginAttempt(5);
        user.failLoginAttempt(5);
        user.failLoginAttempt(5);
        user.failLoginAttempt(5);
        user.failLoginAttempt(5);

//        Then
        assertFalse(user.isAccountNonLocked());
        assertEquals(5,user.getBadLoginAttempt());
        assertNotNull(user.getLastFellLoginAttempt());
    }
}