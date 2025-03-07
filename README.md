Advance Programming 2024/2025

Daffa Naufal Rahadian <br/>
NPM: 2306213003 <br />
Class: A

### deployment link : https://uneven-jillian-scarf-y-253c41e6.koyeb.app

## Module 1

**Reflection 1**<br/>
First of all, it was kind of overwhelming. What i mean is that, there are a lot of new information that i am not familiar with (the dependencies and imported stuff). 
So i took my time to understand each stuff's functionality. From there, it was a smooth sailing, as i am already familiar with MVT in Django so learning MVC in BootStrap was not hard. 
One thing i need to note is, i sometimes forgot about the trailing slash for the mapping. After finishing this part, i feel like i need to explore more.

**Reflection 2**
1. After writing unit test, and it didnt fail, i felt some sort of satisfaction, it made me sure that my code was correct. As to how many unit tests we need to make, it depends on the class' complexity, but i think it is quality over quantity. A well made unit test is better than a lot of rubbish one. We can use code coverage metrics to see if our unit tests are enough. A 100% code coverage means, we have tested all of the code that we made. BUT, having 100% code coverage does not mean our code is free of bugs. Bugs like logic errors can't be detected just from the unit test.

2. I think the cleanliness of our code will be reduced because basically it is a code duplication issues, we shouldn't have to repeat what we already wrote. Also it can reduce readability because people may think of why did the programmer wrote the same thing over and over. My suggestion to solve this kinda problem is to make a reusable base class or helper methods that has the basic setup logic and utility functions.

## Module 2

**Reflection** <br/>
1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them. <br/>
    During the exercise, I ran into a few issues with my code. Most of the problems were caused by writing the same code in multiple places, hich goes against the idea of "Don't Repeat Yourself" (DRY). 
    To fix this, I created reusable functions and used constant string values instead of repeating similar code snippets. 
    This change made my code easier to maintain and update. I also removed lines of code that were never used, such as unused variables, which helped simplify the code.


2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!
    <br/>
    I believe my current CI/CD setup meets the goals of Continuous Integration and Continuous Deployment. The workflows automatically run tests and code analysis every time I push code to any branch, which helps catch errors early. 
    In addition, the deployment process is automated: whenever changes are merged into the master branch, my app is automatically redeployed on Koyeb. 
    This setup reduces manual work and minimizes the chance of mistakes, ensuring that my code is always checked and up-to-date.

## Module 3

**Reflection** <br/>
1) Explain what principles you apply to your project!
<br/>
- Single Responsibility Principle (SRP):
I moved the ``CarController`` out of ``ProductController.java`` 
into its own file because they do different things. 
This way, each class is responsible for one task only, which makes the code easier to understand, test, and maintain. 
When a class does just one thing, changes in one part won’t unexpectedly affect another.
<br/>
- Open/Closed Principle (OCP):
I used the ``CarService`` and ``ProductService`` interfaces so that new features can be added 
with new implementations without changing the existing code. 
This keeps the code open for extension but closed for modification, 
meaning you can build upon it without risking bugs in working parts of the system.

- Liskov Substitution Principle (LSP):
Originally, ``CarController`` inherited from ``ProductController``,
but they serve different functions. 
I removed the inheritance so each controller can operate independently. 
This ensures that if one controller is replaced with another, 
the program still works correctly without unexpected behavior.

- Interface Segregation Principle (ISP):
I designed the ``CarService`` and ``ProductService`` interfaces to include only the methods they actually need. 
This prevents the interfaces from becoming bloated with unused methods and forces classes to implement only what they require, which simplifies the design and improves code clarity.

- Dependency Inversion Principle (DIP):
Instead of having ``CarController`` depend directly on the concrete class ``CarServiceImpl``, 
I made it depend on the ``CarService`` interface. 
This reduces coupling between components, making the code more flexible and easier to maintain, because you can swap out implementations without affecting the high-level modules.
<br/>

2) Explain the advantages of applying SOLID principles to your project with examples.

- Single Responsibility Principle (SRP):
Advantage: Each class handles one specific task, which makes the code easier to understand, maintain, and test.

Example: By moving CarController into its own file rather than bundling it with ProductController,
each controller is now focused on its own responsibility. 
This separation reduces the risk that changes in one area (like product management) 
inadvertently affect the other (like car management).

- Open/Closed Principle (OCP):
Advantage: Code is open for extension but closed for modification. 
This means you can add new features without altering existing, well-tested code.

Example: The use of the CarService and ProductService interfaces allows you to add new service implementations if needed. 
If you need a new type of car service, 
you can create a new class implementing CarService without changing the interface 
or the parts of your system that rely on it.

- Liskov Substitution Principle (LSP):
Advantage: Derived classes can be substituted for their base classes without affecting the functionality. 
This improves reliability and code reuse.

Example: Originally, CarController inherited from ProductController, 
even though they serve different functions. 
By removing this inheritance, you ensure that each controller works independently. 
This means if one controller is replaced or updated, 
the overall system behavior remains predictable.

- Interface Segregation Principle (ISP):
Advantage: Clients only depend on methods they actually use, keeping interfaces simple and focused.

Example: Splitting the service interfaces into CarService and ProductService means that classes implementing 
these interfaces only include methods relevant to their model. 
This makes the design cleaner and reduces the risk of errors from unneeded methods.

- Dependency Inversion Principle (DIP):
Advantage: High-level modules rely on abstractions rather than concrete implementations, 
leading to lower coupling and easier testing.

Example: By making CarController depend on the CarService interface instead of a specific implementation 
like CarServiceImpl, you can easily swap out the implementation (for instance, 
when using mocks for testing) without affecting the controller.


3) Explain the disadvantages of not applying SOLID principles to your project with examples

- SRP:
Classes do too many things, making maintenance and testing harder.

Example: Combining CarController and ProductController can lead to unexpected bugs when one part changes.

- OCP:
New features require modifying existing code, which risks breaking things.

Example: Adding a new service by altering a current class can introduce errors in a well-tested system.

- LSP:
Incorrect inheritance can cause unexpected behavior when swapping components.

Example: Forcing CarController to inherit from ProductController (when they have different roles) can break functionality.

- ISP:
Bloated interfaces force classes to implement irrelevant methods.

Example: A single interface covering both car and product methods makes implementations messy and error-prone.

- DIP:
Tightly coupled code makes testing and future changes difficult.

Example: If CarController depends directly on CarServiceImpl, it’s harder to swap implementations or use mocks for testing.


## Module 4

1. Reflection on TDD Flow Based on Percival (2017)
<br/>
Honestly, following the TDD flow has been quite frustrating, especially when working with mocks. 
The idea of writing tests before the actual implementation sounds great in theory, but in practice,
it feels like I spend more time fixing my test cases than actually developing features.
The biggest issue I faced was dealing with Mockito, where I had to mock dependencies correctly. 
Sometimes, the mocks didn't return what I expected, and I had to debug the tests instead of the actual code.
<br/>
Another problem was that writing tests for every small part of the system took way longer than I expected. Instead of making development faster, it felt like the tests slowed me down. Maybe I need to plan my tests better next time instead of just following TDD strictly. I also think I need to improve my understanding of mocking and dependency injection because that part gave me the most trouble.


2. Reflection on F.I.R.S.T. Principle
<br/>
I’m not completely sure if my tests fully followed the F.I.R.S.T. principle.
Some of them definitely felt fast and isolated, but I have doubts about the self-validating part.
There were cases where the test passed, but I wasn’t 100% sure if the logic was actually correct, or if I just mocked the wrong thing.
For example, when testing setStatus(), I relied on mocks for orderService and paymentRepository, and I realized that if my mocks were incorrect, my test could pass even if the actual implementation was wrong.
<br/>Another issue is repeatability. I think my tests should always produce the same result, but since some tests relied on lists and objects that I initialized in setUp(), I’m not sure if they are truly independent. Maybe next time, I need to double-check that each test is completely isolated and does not depend on shared objects from setUp().
<br/>Overall, while the tests helped catch some bugs, they also made development more time-consuming. I think I need to find a better balance between writing tests and implementing features efficiently.