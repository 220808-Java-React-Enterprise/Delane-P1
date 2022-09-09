package com.revature.reimbapi.services;

import com.revature.reimbapi.dtos.responeses.Principal;
import com.revature.reimbapi.utils.JwtConfig;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.UUID;

import static org.mockito.Mockito.spy;

public class TokenServiceTest extends TestCase {
    private JwtConfig spyConfig = spy(JwtConfig.class);

    private TokenService sut = new TokenService(spyConfig);
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testGenerateToken() {

        Principal testPrincipal = new Principal(String.valueOf(UUID.randomUUID()), "Helena09", "Pa$sw0rd");

        Assert.assertNotNull(sut.generateToken(testPrincipal));

    }

    public void testExtractRequesterDetails_NotNull() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4Nzc5YzkyMy1hOWE5LTRlOTUtYTYzYS0yY2QxNmVjMzdlNWIiLCJpc3MiOiJyZWltYmFwaSIsImlhdCI6MTY2MjY1OTE1NiwiZXhwIjoxNjYyNjYyNzU2LCJzdWIiOiJBZG1pbiIsInJvbGUiOiJBZG1pbiJ9.8IM8j9hc8VCufkL-hJ8lgvwwV4EinuzVYMQQzh-Oo9I";

        Assert.assertNotNull(sut.extractRequesterDetails(token));
    }

    public void testExtractRequesterDetails_CorrectRole() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4Nzc5YzkyMy1hOWE5LTRlOTUtYTYzYS0yY2QxNmVjMzdlNWIiLCJpc3MiOiJyZWltYmFwaSIsImlhdCI6MTY2MjY1OTE1NiwiZXhwIjoxNjYyNjYyNzU2LCJzdWIiOiJBZG1pbiIsInJvbGUiOiJBZG1pbiJ9.8IM8j9hc8VCufkL-hJ8lgvwwV4EinuzVYMQQzh-Oo9I";
        Principal principal = sut.extractRequesterDetails(token);
        Assert.assertEquals("Admin", principal.getRole());


    }

}