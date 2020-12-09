  @PostMapping(value = "/call", produces = MediaType.APPLICATION_JSON_VALUE)
  public Object connectCall() {
    log.info("request to call");


    String username = "<Exotel API key>";
    String password = "<Exotel API token";
    HttpHeaders headers = new HttpHeaders();
    headers.setBasicAuth(username, password);
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
    HttpEntity<String> request = new HttpEntity<String>(headers);

    Object response = restTemplate.exchange(
        "https://twilix.exotel.in/v1/Accounts/infoys1/Calls/connect?From=xxxx&To=xxxx&Url=http://my.exotel.in/exoml/start/<APP ID>",
        HttpMethod.POST, request, Object.class).getBody();
    
    return response;
  }
