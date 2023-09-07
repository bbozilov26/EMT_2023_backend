package mk.ukim.finki.usersmanagement.localization;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

import static mk.ukim.finki.usersmanagement.localization.Locales.LOCALE_EN;

@Component
@RequiredArgsConstructor
public class LocalizationService {
    private final MessageSource messageSource;

    public Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    public String getLocalizedMessage(String messageKey) {
        return messageSource.getMessage(messageKey, null, getLocale());
    }

    public String getDisplayName(String name, String nameEn) {
        String displayName = name;

        if (getLocale().equals(LOCALE_EN) && nameEn != null) {
            displayName = nameEn;
        }

        return displayName;
    }


}
