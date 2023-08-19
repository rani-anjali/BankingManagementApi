package com.bankingmanagement.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ReportServiceHealth implements HealthIndicator {
    @Override
    public Health health(){
        if(isReportServiceAvailable()){
            return Health.up().withDetail("Report Serive", "Report Service is up").build();
        }else{
            return Health.down().withDetail("Report Serive", "Report Service is down").build();
        }
    }
    public boolean isReportServiceAvailable(){
        return true;
    }

}
