Spring security - JWT based Authentication and Authorization

Objective : 
     
     
     * create a new authentication API endpoint.
     * Examine every incoming request for valid JWT and authorize
     
Branch "createJwtToken"
Steps : 
      * 1. add maven dependancy 
                
                <dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-security</artifactId>
        		</dependency>
        		
        		<dependency>
                			<groupId>org.springframework.security</groupId>
                			<artifactId>spring-security-test</artifactId>
                			<scope>test</scope>
                </dependency>
      
   * 2. Create an "/authenticate" api endpoint that will
                
                
                - accept user id and password 
                - return a jwt as a reponse


Branch : authenticateApiWithJwt                
   * 3. Intercept all incoming request via a JwtFilter (OncePerRequestFilter)s
               
               - extract JWT from the header
               - validate and set in security context
               
   * 4. configure stateless session creation policy to notify spring security to not manage the sessions , instead we are doing it thru JWT
                       
   