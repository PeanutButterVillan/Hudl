package com.gvs.framework.util.misc;

//import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//TODO: Change this class to actually fit OO principles - Instantiated manager or static utility?
@Component
@Scope("cucumber-glue")
public class CharityNumber {

//  private static final Logger LOGGER = Logger.getLogger(CharityNumber.class);
  private static final String NO_EXISTING_MSG = "A charity needs to be registered with the number returned by " +
      "getNewCharityNumber before using this method";

  private final Map<Country, String> charityNumbers = new HashMap<>();

  public synchronized String getNewCharityNumber(Country country) {
    String number = generateCharityNumber(country);
    charityNumbers.put(country, number);
//    LOGGER.info("Using NEW charity number " + number);
    return number;
  }

  public synchronized String getExistingCharityNumber(Country country) {
    String number = charityNumbers.get(country);
    if (number == null) {
      throw new IllegalStateException(NO_EXISTING_MSG);
    }
//    LOGGER.info("Using EXISTING charity number " + number);
    return number;
  }

  private String generateCharityNumber(Country country) {
    Random rnd = new Random();
    return String.format("%s%06d", country.getPrefix(), rnd.nextInt(999999));
  }

  public enum Country {
    ENGLAND("England", ""),
    SCOTLAND("Scotland", "SC"),
    NORTHERN_IRELAND("Northern Ireland", "NIC");

    private final String name;
    private final String prefix;

    Country(String name, String prefix) {
      this.name = name;
      this.prefix = prefix;
    }

    public String getName() {
      return name;
    }

    public String getPrefix() {
      return prefix;
    }

    public static Country byName(String name) {
      return Country.valueOf(name.trim().replace(" ", "_").toUpperCase());
    }
  }

}
