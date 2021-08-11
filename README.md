Spring security - JWT based Authentication and Authorization

Objective : 
     
     
     * create a new authentication API endpoint.
     * Examine every incoming request for valid JWT and authorize
     

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
                
                 
     