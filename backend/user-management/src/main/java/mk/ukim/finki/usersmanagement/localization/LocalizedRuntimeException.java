package mk.ukim.finki.usersmanagement.localization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties({"stackTrace", "cause", "suppressed"})
public abstract class LocalizedRuntimeException extends RuntimeException {
    private String localizedMessage;
    private final String[] messageArgs;

    public LocalizedRuntimeException() {
        super();

        messageArgs = null;
    }

    public LocalizedRuntimeException(String... messageArgs) {
        super();

        this.messageArgs = messageArgs;
    }

    private String getMessageKey() {
        return this.getClass().getSimpleName();
    }

    public LocalizedRuntimeException resolve(LocalizationService localizationService) {
        String key = getMessageKey();
        localizedMessage = localizationService.getLocalizedMessage(key);

        if (messageArgs != null && messageArgs.length > 0) {
            localizedMessage = String.format(localizedMessage, (Object[]) messageArgs);
        }

        return this;
    }

    @Override
    public String getMessage() {
        return localizedMessage != null ? localizedMessage : getMessageKey();
    }

    @Override
    public String getLocalizedMessage() {
        return getMessage();
    }

    /** Returns the exception type. This method is defined to be used for JSON serialization.
     * @return The simple class name of the object.
     */
    @JsonSerialize
    public String exceptionType() {
        return this.getClass().getSimpleName();
    }
}
