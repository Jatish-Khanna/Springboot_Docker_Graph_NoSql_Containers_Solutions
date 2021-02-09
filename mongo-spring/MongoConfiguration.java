

@Configuration
public class MongoConfiguration {

  @Bean
  public MongoCustomConversions customConversions() {
    List<Converter<?, ?>> converters = new ArrayList<>();
    converters.add(DateToZonedDateTimeConverter.INSTANCE);
    converters.add(ZonedDateTimeToDateConverter.INSTANCE);
    converters.add(StringToZonedDateTimeConverter.INSTANCE);
    converters.add(ZonedDateTimeToStringConverter.INSTANCE);
    return new MongoCustomConversions(converters);
  }

  enum DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {

    INSTANCE;

    @Override
    public ZonedDateTime convert(Date source) {
      return ZonedDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
    }
  }

  enum ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {

    INSTANCE;

    @Override
    public Date convert(ZonedDateTime source) {
      return Date.from(source.toInstant());
    }
  }
  
  enum StringToZonedDateTimeConverter implements Converter<String, ZonedDateTime> {

    INSTANCE;

    @Override
    public ZonedDateTime convert(String source) {
      return ZonedDateTime.parse(source);
    }
  }

  enum ZonedDateTimeToStringConverter implements Converter<ZonedDateTime, String> {

    INSTANCE;

    @Override
    public String convert(ZonedDateTime source) {
      return source.toString();
    }
  }
}
