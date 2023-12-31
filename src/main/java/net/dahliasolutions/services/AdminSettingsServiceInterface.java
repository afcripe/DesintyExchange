package net.dahliasolutions.services;

import net.dahliasolutions.models.AdminSettings;

public interface AdminSettingsServiceInterface {
    AdminSettings getAdminSettings();
    void setCompanyName(String name);
    void setWikiHome(String name);
    void setPortalHome(String name);
    void setStoreHome(String name);
    void setDocumentationHome(String name);
    void setRestrictStorePosition(boolean bool);
    void setRestrictStoreDepartment(boolean bool);

}
