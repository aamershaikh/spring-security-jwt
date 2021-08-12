###### Spring security - JWT based Authentication and Authorization

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



Postman Request : https://www.getpostman.com/collections/6ede24ccd0176ca8a7a7     



###### What is OAuth?
    
            * OAuth is used to authorization of services 
            * Valet parking - key comparison
            * Autorization between services is done using OAuth
            * refer OAuth explained diagram

Terminologies in AOuth
            
            * Resource (Protected Resource)- what is being sought by the services? Eg : Photos on google drive 
            * Resource Owner : Person who has access to the resource. Eg : Person who can grant access to the photo service to access photos from Google drive          
            * Resource server : server that holds the protected resource. Eg : google drive
            * Authorization server : the burden to security lies with the resource owner to make sure that there are no malicious requests for the resource. thats the reason the resource server has with it an authorization server. this is the server that issues tokens to the clients
            * Client : application that needs access to the protected resource from the resource owner and with its authorization
            

Flows in OAuth  
            
            * Authorization Flow
            * Implicit Flow - Not as secure as Authorization Flow, Primarily used with short lived access tokens, Used with JS Based apps
            * OAuth between microservices- Client credential flow : when the client is super trustworthy
            
   
   
   