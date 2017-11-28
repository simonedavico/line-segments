# Programming Test - Pattern Recognition

## Pattern Recognition

Computer vision involves analyzing patterns in visual images and reconstructing the real world objects that produced them. The process in often broken up into two phases: feature detection and pattern recognition. Feature detection involves selecting important features of the image; pattern recognition involves discovering patterns in the features. We will investigate a particularly clean pattern recognition problem involving points and line segments. This kind of pattern recognition arises in many other applications, for example statistical data analysis.

## Problem to solve

Given a set of N feature points in the plane, determine every line that contains N or more of the points, and return all points involved.
You should also expose a REST API that will allow the caller to: 

* Add a point to the space 

    ```
    POST /point with body { "x": ..., "y": ... }
    ```

* Get all points in the space
    
    ```
    GET /space
    ```

* Get all line segments passing through at least N points. Note that a line segment should be a set of points.

    ```
    GET /lines/{n}
    ```

* Remove all points from the space

    ```
    DELETE /space
    ```

## Additional rules

* All code should be under version control, on a publicly accessible git repository (e.g., a GitHub repository);
* Unless specified in the instructions above, the API should consume and produce JSON;
* The languages you can choose to implement are: Java, Kotlin, JavaScript. Other web/JVM based languages could be taken into consideration. 

## Suggestions

* Properly naming variables and documenting the code can help us understand your solution;
* Validating all inputs to your program will help your solution pass our test cases;
* There is no bound on the computational complexity of the solution, but solutions with good computational complexity will earn you bonus points.

## N.B.

If you are using MacOS Sierra add to your `/etc/hosts` file a mapping to the canonical `127.0.0.1` address for your Mac hostname.

```
127.0.0.1   localhost mbpro.local
::1         localhost mbpro.local
```