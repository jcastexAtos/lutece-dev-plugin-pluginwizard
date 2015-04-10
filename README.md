
#Plugin pluginwizard

##Introduction
The primary goal of Lutece pluginwizard is to reduce developer configuration files overhead and the risk of infringing the development norms. Also new developers entering the project will inheritthe best pratices directly in their project. A basic plugin can be conceived in some minutes and executed immediately.
##What do you get?

All the files needed to implement CRUD functionalities on defined business classes. The files include :

* The Project Object Model( i.e. [pom.xml](http://maven.apache.org/guides/introduction/introduction-to-the-pom.html) )
* The Java classes
* The Spring framework context file
* The sql file needed by the plugin
* The Jsp files needed by Front and Back Office
* Html templates needed by the view layer
* The properties files
* The plugin xml definition needed by the Lutece core
* Basic XSL for defined portlets


##What are the Pros ans Cons?


Pros

 
* The process is iterative, the back button allows to improve and stock the model.
* The model is simple and easy to understand.

Cons
The generation based upon a model which is datacentered doesn't imply that all plugins are based upon business layers. There are a many mechanisms in Lutece which helps to comply to requirements. The mechanismcould be the daemons which are responsible to execute asynchronous tasks or Role Based Access Control which offers the possibility to set up fine-grained control on declared resources.

##Plugin conception

 **Simplified Requirements:** 
 
* Manage a list of persons.
* An administrator must be able to modify,delete and update.
 **Possible Solution:** 
 
* Use the pluginwizard to attach an admin feature to the plugin.
* Attach a business class to the admin feature.
* The business class is comprised of some fields(id_person,person_name and person_address).
* The id_person is the primary key and person_name is the description of the business object.



[Maven documentation and reports](http://dev.lutece.paris.fr/plugins/plugin-pluginwizard/)



 *generated by [xdoc2md](https://github.com/lutece-platform/tools-maven-xdoc2md-plugin) - do not edit directly.*