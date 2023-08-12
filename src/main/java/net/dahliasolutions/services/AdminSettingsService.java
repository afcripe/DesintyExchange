package net.dahliasolutions.services;

import lombok.RequiredArgsConstructor;
import net.dahliasolutions.data.AdminSettingsRepository;
import net.dahliasolutions.models.AdminSettings;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class AdminSettingsService implements AdminSettingsServiceInterface{

    private final AdminSettingsRepository adminSettingsRepository;

    @Override
    public AdminSettings getAdminSettings() {
        AdminSettings adminSettings = adminSettingsRepository.findById(BigInteger.valueOf(1)).orElse(null);
        if (adminSettings == null) {
            return adminSettingsRepository.save(
                    new AdminSettings(
                            BigInteger.valueOf(1),
                            "Destiny Worship Exchange",
                            false,
                            "",
                            "",
                            "",
                            false,
                            false)
            );
        }
        return adminSettings;
    }

    @Override
    public void setCompanyName(String name) {
        AdminSettings adminSettings = getAdminSettings();
        adminSettings.setCompanyName(name);
        adminSettingsRepository.save(adminSettings);
    }

    @Override
    public void setMonthlyStatements(boolean bool) {
        AdminSettings adminSettings = getAdminSettings();
        adminSettings.setMonthlyStatements(bool);
        adminSettingsRepository.save(adminSettings);
    }

    @Override
    public void setWikiHome(String name) {
        AdminSettings adminSettings = getAdminSettings();
        adminSettings.setWikiHome(name);
        adminSettingsRepository.save(adminSettings);
    }

    @Override
    public void setPortalHome(String name) {
        AdminSettings adminSettings = getAdminSettings();
        adminSettings.setPortalHome(name);
        adminSettingsRepository.save(adminSettings);
    }

    @Override
    public void setStoreHome(String name) {
        AdminSettings adminSettings = getAdminSettings();
        adminSettings.setStoreHome(name);
        adminSettingsRepository.save(adminSettings);
    }

    @Override
    public void setRestrictStorePosition(boolean bool) {
        AdminSettings adminSettings = getAdminSettings();
        adminSettings.setRestrictStorePosition(bool);
        adminSettingsRepository.save(adminSettings);
    }

    @Override
    public void setRestrictStoreDepartment(boolean bool) {
        AdminSettings adminSettings = getAdminSettings();
        adminSettings.setRestrictStoreDepartment(bool);
        adminSettingsRepository.save(adminSettings);
    }


}
