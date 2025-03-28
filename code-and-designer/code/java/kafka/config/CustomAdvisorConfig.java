package java.kafka.config;

// private packages
import arch.messaging.provider.subscription.Subscription;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class CustomAdvisorConfig implements Subscription.Advisor {

    private final @NonNull Boolean canKeepPolling;

    @Override
    public boolean canKeepPolling() {
        return canKeepPolling;
    }
}

