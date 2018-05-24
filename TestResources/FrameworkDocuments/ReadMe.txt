
Download "apache-maven-3.3.9" and place it in "C" drive. (Where ever we want)

Compilation Error while Running Maven Project(Git Repository) through Jenkins : 
	You cannot use dependencies with scope test in src/main. 
	If you want to use TestNG in your main code then you can remove <scope>test</scope> from the TestNG dependency in your POM.
	This goes for any Maven dependency and not just TestNG.


-	Eclipse Configuration: 
	-	Install "TestNG for Eclipse", "Maven Integration for Eclipse" from Eclipse Marketplace to run TestNG & Maven Projects.
	-	If you want to change ".m2" repository location, go to Window-> Preference-> Maven-> User Settings->Global Settings: 
		give the "settings.xml" file location inside apche-maven folder (Eg: "C:\apache-maven-3.3.9\conf\settings.xml"), which points the "localRepository" inside the xml file.

	

Note: Always try to use the latest Jars & WebDrivers for the project.
	
