# GVS Test Suite
This Selenium test suite is designed to validate UI flows.

## Requirements
Please make sure to have Java 1.8 and any version of Maven installed, as these will enable all other dependencies. 

## Usage

### Spring Profiles
The spring profiles are used to separate the multiple test functions within the framework. Currently supported values are;

- **Regression** - Representing the internal regression test suite
- 
Each spring profile will run a separate cucumber runner file, load a profile-specific set of properties (along with the standard properties) and will expect different command line arguments.

In order to run the framework you will need to specify the spring profile. You can do this by specifying the `spring.profiles.active` command line / run configuration argument (outlined below).

**NOTE: The spring profile is Case-sensitive, starting with an upper case letter followed by lower case, for example 'Regression' **

### Maven Profiles

#### Local (Default)
The local profile is to run the suite on your local dev instance.

#### BrowserStack ('bstack')
The bstack profile can be used to run against all instances of BrowserStack 

#### Grid
The grid profile can be used to run against a local running Selenium Grid. This is required to scale the tests 
in parallel.

### How to run
All additional arguments are optional and will overwrite the config and pom based properties. The only requirement 
is to run the suite using `mvn verify` as opposed to `mvn test` as the reporting mechanism is linked to the 
former maven stage.

Maven command format:
```
mvn clean verify -P<MavenProfile> -Dspring.profiles.active=<SpringProfile> -Dplatform=<platform> -Dcucumber.options="--tags <DesiredTag>" 
```

Example:
```
mvn clean verify -Dspring.profiles.active=Regression
```

### How to debug
Debug the runner file corresponding to the spring profile you wish to run with the following addition in the debug configuration (Intellij - 'VM Options');

```
-Dspring.profiles.active=<SpringProfile>
```

### Full list of configurable properties
`parallel.tests` Defaults to 1 local, 5 bstack, 10 grid. The number of simultaneous JVM processes spawned to run 
tests. I.e. the number of tests to run in parallel. 

`browser` Defaults to 'default' (pom.xml), which, within the framework will generate a chrome instance - Always 
required when using the bstack profile.

`platform` Defaults to 'sandpit' (config.properties). Sets the target environment to run the suite against. 
Possible values are (all lower case, no spaces) `sandpit`,`sandpit_latest`,`test`,`preprod`

`project.name` Defaults to "GVS Functional Test Suite" (defined in the pom.xml).  

`ALL config values` All of the config values defined (config.properties) can be overridden by passing in the 
value as an argument (see above).


## Project Structure
```
.
├── pom.xml					//Maven build configuration
├── README.md  
└── src
    └── main
        ├── java
                └── com
                    └── gvs
                        └── framework
                            ├── config
                                  ├── MavenProfiles.java						//Enum for configured Maven Profiles
                                  ├── regression
                                      ├── RegressionEnvironments.java			//Enum for platform to test (IDev, ITest)
                                      └── RegressionPropertyManager.java		//SpringContext wrapper for 'Regression' profile-specific properties
                                  ├── ScenarioContext.java					//Config class used to share variables/dynamic states across the cucumber classes.
                                  └── SpringContext.java						//Spring Configuration and Properties Class
                             ├── cukes
                                  ├── bdd
                                      ├─ Hooks.java							//Before/After cucumber hooks generic to all profiles
                                      └── regression.login							//All 'Regression' profile-specific BDD step implementations
                                          ├── hudlLoginStepsDef.java				//BDD step implementations common to multiple 'Regression' scenarios 
                                  ├── runner
                                      └── RunCukesRegressionTest.java			//'Regression' profile-specific cucumber runner class
                                  └── TestBase.java							//Abstract Test Superclass
                             ├── pages
                                  ├── BasePage.java							//Abstract Page Superclass	                                       
                                  └── search							    //Page objects relating to search functionality
                                        └── HomeSearchPage.java
                            └── util
                                  ├── driver
                                      ├── DriverCapabilityManager.java		//Webdriver Capability Configuration Management
                                      ├── DriverFactory.java					//Webdriver instantiation management
                                      ├── DriverUtils.java					//Webdriver Utility/Helper Class
                                  └── misc
                                        └── FileUtils.java					//File interaction utiliy class
        └── resources
            ├── binaries									//Binaries required to run Webdriver locally
            ├── config
                    ├── application.properties					//Properties that are common
                    └── application-Regression.properties		//'Regression' profile-specific properties
			├── cucumber.xml								    //Initial spring configuration file
            ├── features.regression.search
                    └── hudlLogin.feature				
            └── log4j.properties							//Log file configuration
```
