package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link SystemSettingsService}</h1>
 * Backend hilla endpoint service for system settings operations.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class SystemSettingsService {

    /**
     * <h2>{@link #SystemSettingsService()}</h2>
     * 0-arg constructor.
     */
    public SystemSettingsService() {}

    /**
     * <h2>{@link #manageSystemSettings(String, String)}</h2>
     * Perform system settings management operations.
     *
     * @param setting The setting to manage.
     * @param value   The value to set the setting to.
     */
    public void manageSystemSettings(String setting, String value) {

    }
}
