package util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class MappingUtil {
    private final Map<String, String> countryCurrency;

    @Autowired
    public MappingUtil(CountryCurrencyProperties properties) {
        this.countryCurrency = properties.getMap();
    }

    public String getCountryCurrency(String country) {
        if (countryCurrency == null || country == null) {
            return "NOT_FOUND";
        }
        return countryCurrency.getOrDefault(country, "NOT_FOUND");
    }
}
