# GSV Test Suite
This Selenium test suite is designed to validate UI flows.

## Requirements
Please make sure to have Java 1.8 and any version of Maven installed, as these will enable all other dependencies. 

## Usage

### Spring Profiles
The spring profiles are used to separate the multiple test functions within the framework. Currently supported values are;

- **Integration** - Representing the consuming service validation tests created to validate IDM integration with other services, using fully connected development environments.
- **Regression** - Representing the internal IDM regression test suite, using stubs and local development environments.

Each spring profile will run a separate cucumber runner file, load a profile-specific set of properties (along with the standard properties) and will expect different command line arguments.

In order to run the framework you will need to specify the spring profile. You can do this by specifying the `spring.profiles.active` command line / run configuration argument (outlined below).

**NOTE: The spring profile is Case-sensitive, starting with an upper case letter followed by lower case, for example 'Regression'/'Integration'**

### Maven Profiles

#### Local (Default)
The local profile is, as you'd expect, to run the suite on your local dev instance.

#### BrowserStack ('bstack')
The bstack profile can be used to run against all instances of BrowserStack (Local VStack + public cloud). It has a 
set of pre-defined browser/device combinations matching the GDS compatibility requirements (all combinations that are 
supported within BrowserStack).

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
mvn clean verify -Pgrid -Dplatform=sandpit -Dtest.tags=@Preprod -Dimports.sandpit.B2C.url=http://imports-URL.com
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

`project.name` Defaults to "IDM Consumer Validation" (defined in the pom.xml). Used (in combination with the 
'build' property) to identify the tests within BrowserStack. 

`ALL config values` All of the config values defined (config.properties) can be overridden by passing in the 
value as an argument (see above).

#### Maven Profile specific properties

##### Grid
`grid.url` Defaults to `http://127.0.0.1:4444/wd/hub`, which should always target the correct local instance 
regardless of running on localhost or through VStack.   

##### BrowserStack
`browserstack.local` Defaults to false (Required value for VStack). If using the public Browserstack platform, 
change this to false.

`browserstackurl` No default value set (it is passed in via VStack). If using the public Browserstack platform, 
change this to your provided URL. (Naming convention deviates from the standard due to a requirement from VStack)

`localidentifier` No default value set (it is passed in via VStack). Represents a unique identifier for each local 
connection when multiple local connections are connected.

`build` Defaults to a date-time stamp in the format "yyyyMMdd'T'HHmmss" if not defined. Used (in combination with 
the 'projectname' property) to identify the tests within BrowserStack.

### Pre-built Scripts
`build.sh` Required for VStack to run the test suite within a grid, however it does nothing.

`runseleniumgrid_<platform>.sh` Will run the test suite using the grid profile for the target platform, required 
for pipeline runs against VStack. Make sure to match/add to the expectations within the TestBase class.

`runbrowserstack_<browser>.sh` Will run the test suite using the bstack profile for the target browser/device 
combination, required for pipeline runs against VStack. Make sure to match/add to the expectations within the 
TestBase class.

## Project Structure
```
.
├── pom.xml					//Maven build configuration
├── README.md
├── scripts					//DevOps pipeline scripts
│   ├── integration			//'Integration' profile-specific DevOps pipeline scripts
│   └── regression			//'Regression' profile-specific DevOps pipeline scripts
└── src
    └── main
        ├── java
        │   └── com
        │       └── defra
        │           └── identity
        │               ├── config
        │               │   ├── integration
        │               │   │   ├── IdmEnvironments.java				//Enum for Current/Latest backend-environment configuration
        │               │   │   ├── IntegrationEnvironments.java		//Enum for platform to test (Sandpit,Test,etc)
        │               │   │   ├── IntegrationPropertyManager.java		//SpringContext wrapper for 'Integration' profile-specific properties
        │               │   │   └── Integrations.java					//Enum for integrated consuming services
        │               │   ├── MavenProfiles.java						//Enum for configured Maven Profiles
        │               │   ├── regression
        │               │   │   ├── RegressionEnvironments.java			//Enum for platform to test (IDev, ITest)
        │               │   │   └── RegressionPropertyManager.java		//SpringContext wrapper for 'Regression' profile-specific properties
        │               │   ├── ScenarioContext.java					//Config class used to share variables/dynamic states across the cucumber classes.
        │               │   └── SpringContext.java						//Spring Configuration and Properties Class
        │               ├── cukes
        │               │   ├── bdd
        │               │   │   ├── Hooks.java							//Before/After cucumber hooks generic to all profiles
        │               │   │   ├── integration							//All 'Integration' profile-specific BDD step implementation
        │               │   │   └── regression							//All 'Regression' profile-specific BDD step implementations
        │               │   │       ├── account							//Account management BDD step implementations (steps outlined in 'account' feature files)
        │               │   │       ├── CommonStepDefs.java				//BDD step implementations common to multiple 'Regression' scenarios 
        │               │   │       ├── misc							//Miscellaneous BDD step implementations (steps outlined in 'misc' feature files)
        │               │   │       └── registration					//Registration BDD step implementations (steps outlined in 'registration' feature files)
        │               │   ├── runner
        │               │   │   ├── RunCukesIntegrationTest.java		//'Integration' profile-specific cucumber runner class 
        │               │   │   └── RunCukesRegressionTest.java			//'Regression' profile-specific cucumber runner class
        │               │   └── TestBase.java							//Abstract Test Superclass
        │               ├── data
        │               │   ├── DataEntityManager.java					//Creates pre-defined DataEntity objects (JSON Test Data Structures)
        │               │   └── pojo									//Pojo objects used to construct DataEntity objects
        │               ├── model										//Legacy implementation with unknown intention
        │               ├── pages
        │               │   ├── account									//Page objects relating to account management functionality
        │               │   ├── BasePage.java							//Abstract Page Superclass
        │               │   ├── cookie									//Page objects relating to cookie functionality
        │               │   ├── demo									//Page objects relating to demo/stub functionality
        │               │   ├── enums									//Enums used throughout all page objects
        │               │   ├── integration								//Page objects relating to 'Integration' profile-specific functionality
        │               │   ├── misc									//Page objects relating to miscellaneous functionality
        │               │   └── registration							//Page objects relating to registration functionality
        │               └── util
        │                   ├── company									//Company management classes used within the 'Integration' profile
        │                   ├── driver
        │                   │   ├── DriverCapabilityManager.java		//Webdriver Capability Configuration Management
        │                   │   ├── DriverFactory.java					//Webdriver instantiation management
        │                   │   ├── DriverUtils.java					//Webdriver Utility/Helper Class
        │                   │   └── GDSBrowsers.java					//Enum for configuration of GDS-required browser combinations supported through BrowserStack
        │                   ├── email									//GetNada email service interaction classes
        │                   └── misc
        │                       ├── CharityNumber.java					//'Charity' Business type utility class
        │                       ├── Dynamics.java						//Dynamics interaction utility class
        │                       ├── FileUtils.java						//File interaction utiliy class
        └── resources
            ├── binaries									//Binaries required to run Webdriver locally
            ├── config
            │   ├── application-Integration.properties		//'Integration' profile-specific properties
            │   ├── application.properties					//Properties that are common to both Integration and Regression profiles
            │   └── application-Regression.properties		//'Regression' profile-specific properties
			├── cucumber.xml								//Initial spring configuration file
            ├── data
            │   ├── companyHouseNumbers.txt					//Incredibly large list of company ID's used within the 'Regression' profile
            │   ├── DataEntityTemplate.json					//Template file used to populate 
            │   └── RegisteredCompanies.csv					//List of usable CompanyID's used within the 'Integration' profile (DO NOT MODIFY MANUALLY)
            ├── features
            │   ├── integration
            │   │   ├── ChemicalsIntegration.feature		//'Chemicals' consuming service acceptance criteria
            │   │   ├── ExportsIntegration.feature			//'Exports' consuming service acceptance criteria
            │   │   ├── ImportsIntegration.feature			//'Imports' consuming service acceptance criteria
            │   │   ├── MMOIntegration.feature				//'MMO' consuming service acceptance criteria
            │   │   └── VMDIntegration.feature				//'VMD' consuming service acceptance criteria
            │   └── regression
            │       ├── account								//Account management related IDM regression acceptance criteria
            │       ├── cookie								//Cookie related IDM regression acceptance criteria
            │       ├── misc								//Miscellaneous IDM regression acceptance criteria
            │       └── registration						//Registration related IDM regression acceptance criteria
            └── log4j.properties							//Log file configuration
```


## Nuances

### Company Registrations (Integration Profile)
Once a company or email is registered using the IDM flow, it cannot be re-registered. This means that at the beginning 
of the test suite run, a call is made to a Customer API to de-register and delete the list of companies included in 
the 'RegisteredCompanies.csv' file.

### Cucumber Tags (Integration Profile)
The tags included within the feature files have been chosen for a distinct purpose, as follows;

`@All` Every feature file has this tag, so running everything is possible by using it.

`@Project` Each feature file has a tag referencing it's corresponding project, so if you wish to just run the 
tests for a single project, for example Chemicals, you just reference it as such `@Chemicals`. 

`@Sandpit` `@Sandpit_Latest` `@Test` `@Preprod` These tags have been included as there are distinct differences in the 
capabilities and test coverage between environments. For instance, the Imports B2B test does not have a configuration 
for the Sandpit_Latest environment, and so is excluded from the coverage.

Other than this, every test has a unique tag defined using a simple pattern of "@<ProjectName><ScenarioPurpose>", so 
specifically running the B2C login test for MMO, for instance, would be done by running `@MMOB2CLogin`