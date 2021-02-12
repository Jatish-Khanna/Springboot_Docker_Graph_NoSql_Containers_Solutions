

public class PlagScanRequestInterceptor {

  @Bean(name = "plagScanRequestInterceptor")
    public RequestInterceptor plagScanRequestInterceptor() {
        log.info("plag scan request interceptor");
        String plagScanServiceUrl = "https://api.plagscan.com/v3/token";
        PlagScanCredentials plagScanCredentials = plagScanMapper.toDto(plagScanConfiguration);
        ResponseEntity<PlagScanAuthenticationResponse> plagScanAuthentication =
            restTemplate.postForEntity(plagScanServiceUrl, plagiarismScanCredentials,
                PlagScanAuthenticationResponse.class);
        if (plagScanAuthentication.getStatusCode() == HttpStatus.OK) {
            return requestTemplate -> {
                log.debug("access_token as query parameter");
                requestTemplate.query("access_token",
                    plagScanAuthentication.getBody().getAccess_token());
            };
        }
        return requestTemplate -> {
        };
    }

}
