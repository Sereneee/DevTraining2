### i. What is SpringBean?  
* objects that from backbone of application
* managed by Spring IoC container
* an object that is instantiated, assembled and managed by Spring IoC container

### ii. Inversion of Control (IoC)
* a process which an object defines its dependencies without creating them
* object delegates the job of constructing dependencies to an IoC container

## 1. Singleton Scope
* container creates a single instance of that bean
* all requests for that bean name will return the same object, which is cached
* any modifications to the object will be reflected in all references to the bean. 
* this is the default value if no other scope is 

#### Codes to exemplify the Singleton Scope concept:

##### Class file
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/ce85863a.png)

##### Scopes Configuration file - to define the bean
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/d0562b56.png)

##### Scopes xml file - xml definitions of the beans used
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/eec61e73.png)

##### Test file - test that shows that two objects referring to the same bean will have the same values, even if only one of them changes their state
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/138972d7.png)

#### Results of test:
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/2d1dbd6.png)

## 2. Prototype Scope
* bean with prototype scope will return a different instance every
time it is requested from the container

#### Codes to exemplify the Prototype Scope concept:

##### Scopes Configuration file - to define the bean
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/aec359a8.png)

##### Scopes xml file - xml definitions of the beans used
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/5a54858c.png)

##### Test file - test that shows  shows two objects requesting the same bean name with scope prototype will have different states, as they are no longer referring to the same bean instance
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/1f211cb3.png)

#### Results of test:
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/5dd3da77.png)

## 3. Web Aware Scopes
### 3(a) Request scope
#### Codes:
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/57f7aa18.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/9ac4ebd6.png)
#### Proof of Concept:
Type http://localhost:8080/scopes/request in browser, you’ll see that the previous bean is null because this is a request bean, it is new every time a request is sent, even if it is in the same session (open new tab in the same browsing window), as shown below:
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/d5b27ff7.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/f9b943df.png)

### 3(b) Session scope
#### Codes:
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/1895b800.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/42be83f4.png)
#### Proof of Concept:
Type http://localhost:8080/scopes/session in browser, you’ll see that the previous bean is null because this is a new session, meaning no session bean was there previously. As shown below:

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/7126a32f.png)

Open another tab or windows of the same or different browser and type http://localhost:8080/scopes/session, It retrieves the same session bean, because they are in the same session. As shown below:

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/8015a03e.png)

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/69a5cf5d.png)

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/dbd112f6.png)

Closing ALL the opened browser window will close the current session and when we open a new window, it is a new session bean present, it cannot retrieve the previous bean, because there isn’t one. So, the previous session bean will be null. As shown below:

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/fa8aac67.png)

### 3(c) Application scope
#### Codes:
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/7eacddd1.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/2ab32019.png)

#### Proof of Concept:
Type http://localhost:8080/scopes/session in browser, you’ll see that the previous bean is null because this is a new request, meaning no application bean was there previously. As shown below:

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/bcfc6e1.png)

Opening another tab/window of a browser shows us that the previous application bean is still there, as shown below: 
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/72bbc5c0.png)

Closing and reopening the browser windows won’t clear the bean. As shown below:

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/92ee414f.png)

Restarting the application in the IDE will reset the application bean, as shown below:

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/24/de0bb9a3.png)


### 3(a) Websocket scope
#### Codes:
#### Proof of Concept: