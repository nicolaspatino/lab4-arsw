# Blueprint Management 2

# PART I
* Integrate to the base project supplied the Beans developed in the previous exercise. Just copy the classes, NOT the configuration files. Rectify that the dependency injection scheme is correctly configured with the @Service and @Autowired annotations.
* Modify the persistence bean InMemoryBlueprintPersistence so that by default it is initialized with at least three other planes, and with two associated with the same author.
* Configure your application to offer the resource /blueprints, so that when a GET request is made, return in JSON format - all the drawings. For this:
Modify the BlueprintAPIController class taking into account the following example of a REST controller made with SpringMVC/SpringBoot
Have the BlueprintServices type bean injected into this class (which, in turn, will be injected with its persistence and point filtering dependencies).
![](img/controller.png)

* Verify the operation of the application by launching the application with maven. And then sending a GET request to: http://localhost:8080/blueprints. Rectify that, in response, a JSON object is obtained with a list containing the detail of the drawings provided by default, and that the corresponding point filtering has been applied.

![](img/localhost.png)
## Autores ✒️

* **Nicolas Patiño** 
* **Andres Rodriguez del toro**
