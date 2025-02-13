**Reflection 1**<br/>
First of all, it was kind of overwhelming. What i mean is that, there are a lot of new information that i am not familiar with (the dependencies and imported stuff). 
So i took my time to understand each stuff's functionality. From there, it was a smooth sailing, as i am already familiar with MVT in Django so learning MVC in BootStrap was not hard. 
One thing i need to note is, i sometimes forgot about the trailing slash for the mapping. After finishing this part, i feel like i need to explore more.

**Reflection 2**
1. After writing unit test, and it didnt fail, i felt some sort of satisfaction, it made me sure that my code was correct. As to how many unit tests we need to make, it depends on the class' complexity, but i think it is quality over quantity. A well made unit test is better than a lot of rubbish one. We can use code coverage metrics to see if our unit tests are enough. A 100% code coverage means, we have tested all of the code that we made. BUT, having 100% code coverage does not mean our code is free of bugs. Bugs like logic errors can't be detected just from the unit test.

2. I think the cleanliness of our code will be reduced because basically it is a code duplication issues, we shouldn't have to repeat what we already wrote. Also it can reduce readability because people may think of why did the programmer wrote the same thing over and over. My suggestion to solve this kinda problem is to make a reusable base class or helper methods that has the basic setup logic and utility functions.
