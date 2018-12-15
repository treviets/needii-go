package net.needii;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jndi.JndiLocatorDelegate;

import javax.naming.NamingException;

/**
 * Created by Vincent
 */
public class SpringProfilesActiveApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final String[] DEFAULT_PROFILES_ACTIVE = {"default", "dev", "stage", "prod", "timers", "swagger"};

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        String[] profilesActive;
        JndiLocatorDelegate jndiLocatorDelegate = JndiLocatorDelegate.createDefaultResourceRefLocator();
        try {
            profilesActive = jndiLocatorDelegate.lookup("java:/comp/env/neediigo/springProfilesActive", String.class).split(",");
        } catch (NamingException e) {
            profilesActive = DEFAULT_PROFILES_ACTIVE;
        }

        configurableApplicationContext.getEnvironment().setActiveProfiles(profilesActive);
    }

}
