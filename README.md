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
