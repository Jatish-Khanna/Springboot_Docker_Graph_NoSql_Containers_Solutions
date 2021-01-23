

public interface QueryBuilder {

default void buildQuery(BoolQueryBuilder mainQuery, UserQuery userQuery) {
    // Full Text search across all fields
    if (!StringUtils.isBlank(userQuery.getAdvancedQuery())) {
      mainQuery.must(QueryBuilders.multiMatchQuery(userQuery.getAdvancedQuery())
          .fuzziness(Fuzziness.TWO).prefixLength(2));
    }

    // Prefix query
    if (!StringUtils.isBlank(userQuery.getName())) {
      mainQuery.must(QueryBuilders.prefixQuery("name", userQuery.getName()));
    }

    // Search context - with the @ symbol handled and prefix search
    if (!StringUtils.isBlank(userQuery.getEmail())) {
      mainQuery.must(QueryBuilders.matchPhrasePrefixQuery("email", userQuery.getEmail()));
    }
    
    // Term query match
    if(!StringUtils.isBlank(userQuery.getAccountId())) {
      mainQuery.must(QueryBuilders.termQuery("accountId", userQuery.getAccountId()));
    }

  }

}
